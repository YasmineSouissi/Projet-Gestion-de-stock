import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Serveur {


    public static class Server {
        private ServerSocket serverSocket;
        private List<Socket> clients = new ArrayList<>();

        public Server(int port) throws IOException {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                clients.add(clientSocket);
                new Thread(new ClientHandler(clientSocket)).start();
            }
        }

        private class ClientHandler implements Runnable {
            private Socket clientSocket;
            private InputStream input;
            private OutputStream output;

            public ClientHandler(Socket socket) throws IOException {
                clientSocket = socket;
                input = clientSocket.getInputStream();
                output = clientSocket.getOutputStream();
            }

            public void run() {
                try {
                    byte[] buffer = new byte[1024];
                    while (true) {
                        int bytesRead = input.read(buffer);
                        if (bytesRead == -1) break;
                        String message = new String(buffer, 0, bytesRead);
                        System.out.println("Received message from client: " + message);
                        for (Socket s : clients) {
                            if (s != clientSocket) {
                                s.getOutputStream().write(buffer, 0, bytesRead);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
