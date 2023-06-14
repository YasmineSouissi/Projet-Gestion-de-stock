package com.example.tp_base.IHM;

import com.example.tp_base.DAO.*;
import com.example.tp_base.OO.Demande;
import com.example.tp_base.OO.Produit;
import com.example.tp_base.OO.User;
import com.example.tp_base.Socket.EnvoyerDemande;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Date;

public class DemandesChat extends BorderPane {
    public DemandesChat(User user){
        /*BackgroundFill backgroundFill = new BackgroundFill(Color.web("#0175A2"), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);*/


        Label lb_top = new Label("Envoyer une nouvelle demande");
        lb_top.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        lb_top.setStyle("-fx-text-fill: #0175A2;");
        lb_top.setPadding(new Insets(20));
        lb_top.setAlignment(Pos.CENTER);

        Button btn_annuler = new Button("annuler");
        Button btn_Confirmer = new Button("confirmer");

        HBox hb = new HBox(btn_annuler,btn_Confirmer);

        this.setCenter(hb);

        btn_annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage st=(Stage) btn_annuler.getScene().getWindow();
                st.close();
            }
        });

        btn_annuler.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_annuler.setStyle("-fx-background-color: white; -fx-text-fill: #0175A2; -fx-font-size: 13px;  "); // Change la couleur du fond en blanc
            }
        });

        btn_annuler.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_annuler.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   "); // Change la couleur du fond en bleu
            }
        });

        /*btn_Confirmer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String reponse="acceptee";
                EnvoyerDemande envoyerDemande = new EnvoyerDemande(reponse);

            }
        });*/
        btn_Confirmer.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_Confirmer.setStyle("-fx-background-color: white; -fx-text-fill: #0175A2; -fx-font-size: 13px;  "); // Change la couleur du fond en blanc
            }
        });

        btn_Confirmer.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_Confirmer.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   "); // Change la couleur du fond en bleu
            }
        });



    }
}
