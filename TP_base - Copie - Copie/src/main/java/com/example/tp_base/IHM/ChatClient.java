package com.example.tp_base.IHM;

import com.example.tp_base.OO.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatClient extends VBox {

    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private VBox chatBox;
    private ScrollPane scrollPane;
    private TextField messageField;


    public ChatClient(User user) throws Exception {

        // Création des éléments de l'interface
        chatBox = new VBox();
        chatBox.setPrefSize(300,300); // définir la largeur préférée du chatBox
        chatBox.setSpacing(10);

        scrollPane = new ScrollPane(chatBox);
        scrollPane.setVisible(true);
        scrollPane.setPrefSize(200, 200); // définir la taille préférée du ScrollPane
        //scrollPane.setFitToWidth(true); // adapter le ScrollPane à la largeur du conteneur parent
        messageField = new TextField();
        messageField.setMinHeight(30);

        Button sendButton = new Button("Envoyer");
        sendButton.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;");
        sendButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sendButton.setStyle("-fx-background-color: white; -fx-text-fill: #0175A2; -fx-font-size: 13px;  "); // Change la couleur du fond en blanc
            }
        });

        sendButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sendButton.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   "); // Change la couleur du fond en bleu
            }
        });

        sendButton.setOnAction(e -> sendMessage(user));

        // Mise en page des éléments
        VBox root = new VBox(scrollPane, messageField, sendButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setSpacing(30);
        this.getChildren().add(root);

        // Connexion au serveur
        String serverAddress = "localhost"; // Adresse IP du serveur
        clientSocket = new Socket(serverAddress, 1234);
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer = new PrintWriter(clientSocket.getOutputStream(), true);

        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm"); // Modèle pour afficher l'heure
        String formattedTime = formatter.format(date);

        // Thread pour recevoir les messages du serveur
        Thread receiveThread = new Thread(() -> {
            try {
                while (true) {
                    String message = reader.readLine();
                    if (message == null) {
                        break;
                    }
                    Platform.runLater(() -> {
                        MessageLabel messageLabel = new MessageLabel(message+"\n"+formattedTime, true);
                        chatBox.getChildren().add(messageLabel);

                        chatBox.getChildren().add(messageLabel);
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        receiveThread.start();
    }

    private void sendMessage(User user) {
        Date date= new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm"); // Modèle pour afficher l'heure
        String formattedTime = formatter.format(date);
        String message = messageField.getText();
        writer.println(" " + user.getNom() + ": " + message);
        System.out.println("Sent message to server: " + message);
        MessageLabel messageLabel = new MessageLabel(message+"\n"+formattedTime, false);
        chatBox.getChildren().add(messageLabel);
        messageField.clear();
    }

}