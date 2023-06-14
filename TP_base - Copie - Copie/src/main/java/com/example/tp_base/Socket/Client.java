package com.example.tp_base.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    private Scanner scanner;

    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
        input = socket.getInputStream();
        output = socket.getOutputStream();
        scanner = new Scanner(System.in);
        new Thread(new ServerHandler()).start();
    }

    private class ServerHandler implements Runnable {
        public void run() {
            try {
                byte[] buffer = new byte[1024];
                while (true) {
                    int bytesRead = input.read(buffer);
                    if (bytesRead == -1) {
                        System.out.println("Connection closed by server.");
                        break;
                    }
                    System.out.println(new String(buffer, 0, bytesRead));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }
    }

    public void start() {
        try {
            while (true) {
                String message = scanner.nextLine();
                output.write(message.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) throws IOException {
        System.out.println("Connecting to server...");
        Client client = new Client("localhost", 8080);
        client.start();
    }*/
}