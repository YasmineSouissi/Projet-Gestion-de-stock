import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class DemandesHandler extends Thread {
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;
    private ArrayList<DemandesHandler> clients;
    public DemandesHandler(Socket socket, ArrayList<DemandesHandler> clients){
            try {
                this.socket = socket;
                this.clients = clients;
                this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.pw = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    @Override
    public void run(){
        try {
            String msg;
            while ((msg = br.readLine()) != null) {
                if (msg.equalsIgnoreCase( "exit")) {
                    break;
                }
                for (DemandesHandler dh : clients) {
                    dh.pw.println(msg);
                    System.out.println(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close();
                pw.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
