package com.example.projet.IHM;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Arrays;

public class FournisseursTable extends BorderPane {
    public FournisseursTable(){
            TableView<Fournisseur> tableFournisseurs;
            TextField tf_nom;
            TextField tf_email;
            TextField tf_tel;
            TextField tf_adresse;


            Label lb_fournisseurs= new Label("Fournisseurs");
            lb_fournisseurs.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
            lb_fournisseurs.setStyle("-fx-text-fill: #0175A2;");
            lb_fournisseurs.setPadding(new Insets(20));

            // Création de la table des fournisseurs
        tableFournisseurs = new TableView<>();
        TableColumn<Fournisseur, String> nameColumn = new TableColumn<>("Nom");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Fournisseur, String> emailColumn = new TableColumn<>("E-mail");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Fournisseur, String> phoneColumn = new TableColumn<>("Téléphone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

                tableFournisseurs.getColumns().add(nameColumn);
                tableFournisseurs.getColumns().add(emailColumn);
                tableFournisseurs.getColumns().add(phoneColumn);

                // Création des champs de saisie
                tf_nom = new TextField();
                tf_nom.setPromptText("Nom");

                tf_email = new TextField();
                tf_email.setPromptText("E-mail");

                tf_tel = new TextField();
                tf_tel.setPromptText("Téléphone");

                tf_adresse = new TextField();
                tf_adresse.setPromptText("Adresse");
                //tf_adresse.setStyle("-fx-background-color: #f2f2f2;-fx-border-color: #d6d6d6;-fx-border-radius: 5;-fx-font-family: 'Segoe UI', Arial, sans-serif;-fx-font-size: 14px;-fx-padding: 8 10 8 10;-fx-text-fill: #333333;");

                // Création des boutons d'action
                Button btn_ajout = new Button("Ajouter");
                btn_ajout.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;");

        TableView<Fournisseur> finalTableFournisseurs = tableFournisseurs;
        btn_ajout.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Fournisseur f=new Fournisseur(tf_nom.getText(),tf_adresse.getText(),tf_tel.getText(),tf_email.getText() );
                        finalTableFournisseurs.getItems().add(f);
                    }
                });

                btn_ajout.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                            btn_ajout.setStyle("-fx-background-color: white; -fx-text-fill: #0175A2; -fx-font-size: 13px;  "); // Change la couleur du fond en blanc
                    }
            });

            btn_ajout.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                            btn_ajout.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   "); // Change la couleur du fond en bleu
                    }
            });

                //addButton.setOnAction(event -> addSupplier());

                Button btn_modifier = new Button("Modifier");
                //updateButton.setOnAction(event -> updateSupplier());

                Button btn_supp = new Button("Supprimer");
                //deleteButton.setOnAction(event -> deleteSupplier());

                //création du tableau fournisseurs
                tableFournisseurs=new TableView<>();

                // Création du panneau d'édition
               HBox hb_ajout = new HBox();
               hb_ajout.getChildren().addAll(tf_nom,tf_tel,tf_email,tf_adresse,btn_ajout);
               hb_ajout.setSpacing(10);
               hb_ajout.setPadding(new Insets(15));

            VBox vb_top= new VBox(lb_fournisseurs,hb_ajout);

            for (TextField tf : Arrays.asList(tf_nom,tf_tel,tf_email,tf_adresse)) {
                   tf.setStyle("-fx-pref-width: 100");
            }

               this.setTop(vb_top);
               this.setCenter(tableFournisseurs);

            }
}
