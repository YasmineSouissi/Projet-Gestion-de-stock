package com.example.projet.IHM;

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

public class FormulaireDemande extends BorderPane {
    public FormulaireDemande(){
        /*BackgroundFill backgroundFill = new BackgroundFill(Color.web("#0175A2"), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);*/
        Label lb_top = new Label("Envoyer une nouvelle demande");
        lb_top.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        lb_top.setStyle("-fx-text-fill: #0175A2;");
        lb_top.setPadding(new Insets(20));
        lb_top.setAlignment(Pos.CENTER);

        Label lb_fournisseur = new Label("Fournisseur");

        Label lb_categorie=new Label("Catégorie");

        Label lb_produit= new Label("Produit");

        Label lb_quantite= new Label("Quantité");

        ComboBox cb_categorie=new ComboBox<>();
        cb_categorie.getItems().addAll("Cat1","Cat2");
        cb_categorie.getSelectionModel().select(0);

        ComboBox cb_fournissuer=new ComboBox();
        cb_fournissuer.getItems().setAll("F1","F2");
        cb_fournissuer.getSelectionModel().select(0);

        ComboBox cb_Produits=new ComboBox();
        cb_Produits.getItems().setAll("P1","P2");
        cb_Produits.getSelectionModel().select(0);

        Spinner<Integer> sp_quantité = new Spinner<>(0, 100, 0); // Création d'un Spinner avec une plage de valeurs allant de 0 à 100 et une valeur initiale de 0.
        sp_quantité.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100)); // Définition de la plage de valeurs du Spinner.

        VBox vb_fournisseur =new VBox(lb_fournisseur,cb_fournissuer);


        VBox vb_categorie =new VBox(lb_categorie,cb_categorie);


        VBox vb_prd =new VBox(lb_produit,cb_Produits);


        VBox vb_qt =new VBox(lb_quantite,sp_quantité);


        for (VBox vb : Arrays.asList(vb_fournisseur,vb_categorie,vb_prd,vb_qt)) {
            vb.setSpacing(10);
            vb.setAlignment(Pos.CENTER);
        }
        for (Label lb : Arrays.asList(lb_fournisseur,lb_categorie,lb_produit,lb_quantite)) {
            lb.setStyle("-fx-text-fill: #656f8e;");
            lb.setFont(Font.font("Georgia", FontWeight.MEDIUM, 13));
        }


        VBox formulaire = new VBox(vb_fournisseur,vb_categorie,vb_prd,vb_qt);
        formulaire.setAlignment(Pos.CENTER);
        formulaire.setSpacing(20);
        formulaire.setPadding(new Insets(20));


        Button btn_annuler =new Button("Annuler");
        btn_annuler.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   "); // Change la couleur du fond en bleu

        Button btn_Confirmer =new Button("Confirmer");
        btn_Confirmer.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   ");

        HBox hb_btn = new HBox(btn_annuler,btn_Confirmer);
        hb_btn.setSpacing(20);
        hb_btn.setPadding(new Insets(10));
        hb_btn.setAlignment(Pos.CENTER);

        HBox hb_left =new HBox();
        hb_left.setMaxSize(0,0);
        hb_left.setMinSize(0,0);

        this.setTop(lb_top);
        this.setBottom(hb_btn);
        this.setCenter(formulaire);
        this.setBackground(new Background(new BackgroundFill(Color.web("#EBE8DD"), null, null)));


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

        btn_Confirmer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Demande dem= new Demande();
            }
        });
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
