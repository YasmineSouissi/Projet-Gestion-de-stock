package com.example.projet.IHM;

import com.example.tp_base.OO.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;

public class DemandesTable extends BorderPane {

    public  DemandesTable(User user){
        Label lb_demandes = new Label("Demandes");
        lb_demandes.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
        lb_demandes.setStyle("-fx-text-fill: #0175A2;");
        lb_demandes.setPadding(new Insets(20));

        TableView<Demande> tv_demande = new TableView<Demande>();
        //tv_demande.setPadding(new Insets(20));

        String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\add.png";
        File file = new File(imagePath);
        Image image = new Image(file.toURI().toString());
        ImageView imageV = new ImageView(image);
        imageV.setFitHeight(16);
        imageV.setFitHeight(16);
        Button btn_ajouter  = new Button("Nouovelle Demande",imageV);
        btn_ajouter.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   ");



        Button btn_modifier= new Button("Modifier");
        btn_modifier.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   ");

        Button btn_supprimer= new Button("Supprimer");
        btn_supprimer.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   ");

        HBox hb_btn= new HBox(btn_modifier,btn_supprimer);
        hb_btn.setSpacing(20);
        HBox hb_bottom= new HBox(hb_btn,btn_ajouter);
        hb_bottom.setPadding(new Insets(15));
        hb_bottom.setSpacing(240);

        HBox hb =new HBox();
        hb.setMaxSize(0,0);

        this.setCenter(tv_demande);
        this.setBottom(hb_bottom);
        this.setTop(lb_demandes);

        //event handelers
        btn_ajouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Scene sc = new Scene(new FormulaireDemande(user),350,500);
                Stage stage = new Stage();
                stage.setScene(sc);
                stage.setTitle("Nouvelle Demande");
                stage.show();
            }
        });
        btn_ajouter.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\add (1).png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                btn_ajouter.setGraphic(imageV);
                btn_ajouter.setStyle("-fx-background-color: white; -fx-text-fill: #0175A2; -fx-font-size: 13px;  "); // Change la couleur du fond en blanc
            }
        });

        btn_ajouter.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_ajouter.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   "); // Change la couleur du fond en bleu
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\add.png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                btn_ajouter.setGraphic(imageV);
            }
        });

        btn_supprimer.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_supprimer.setStyle("-fx-background-color: white; -fx-text-fill: #0175A2; -fx-font-size: 13px;  "); // Change la couleur du fond en blanc
            }
        });

        btn_supprimer.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_supprimer.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   "); // Change la couleur du fond en bleu
            }
        });

        btn_modifier.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_modifier.setStyle("-fx-background-color: white; -fx-text-fill: #0175A2; -fx-font-size: 13px;  "); // Change la couleur du fond en blanc
            }
        });

        btn_modifier.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_modifier.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   "); // Change la couleur du fond en bleu
            }
        });


    }


}
