import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class DemandeServer {
    private static ArrayList<DemandesHandler> clients = new ArrayList<DemandesHandler>();
    public DemandeServer(){
        try (ServerSocket serverSocket = new ServerSocket(8080)) { // Écoute sur le port 5555
            System.out.println("Serveur en attente de connexions...");
            while (true) {
                Socket socket = serverSocket.accept(); // Accepter une connexion entrante
                System.out.println("Client connecté: " + socket.getInetAddress().getHostName());
                DemandesHandler demandesHandler = new DemandesHandler(socket,clients);
                //lecture de message
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                clients.add(demandesHandler);
                demandesHandler.start();


                //socket.close(); // Fermer la connexion
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
