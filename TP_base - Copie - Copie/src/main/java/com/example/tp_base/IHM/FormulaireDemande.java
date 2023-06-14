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

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Date;

public class FormulaireDemande extends BorderPane {
    public FormulaireDemande(User user){
        /*BackgroundFill backgroundFill = new BackgroundFill(Color.web("#0175A2"), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);*/

        DataBaseQueries dataBaseQueries = new DataBaseQueries();
        FournisseurDAO fournisseurDAO=new FournisseurDAO(dataBaseQueries.getConnection());
        ProductDAO productDAO=new ProductDAO(dataBaseQueries.getConnection());
        CategorieDAO categorieDAO=new CategorieDAO(dataBaseQueries.getConnection());

        Label lb_top = new Label("Envoyer une nouvelle demande");
        lb_top.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        lb_top.setStyle("-fx-text-fill: #0175A2;");
        lb_top.setPadding(new Insets(20));
        lb_top.setAlignment(Pos.CENTER);

        Label lb_fournisseur = new Label("Fournisseur");

        Label lb_categorie=new Label("Catégorie");

        Label lb_produit= new Label("Produit");

        Label lb_quantite= new Label("Quantité");
        Label lb_cat =new Label();



        ComboBox cb_fournissuer=new ComboBox();
        cb_fournissuer.getItems().setAll(fournisseurDAO.getNomFournisseurs());
        cb_fournissuer.getSelectionModel().select(0);

        ComboBox cb_Produits=new ComboBox();
        cb_Produits.getItems().setAll(productDAO.getAllProductNames());
        cb_Produits.getSelectionModel().select(0);

        Spinner<Integer> sp_quantité = new Spinner<>(0, 100, 0); // Création d'un Spinner avec une plage de valeurs allant de 0 à 100 et une valeur initiale de 0.
        sp_quantité.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100)); // Définition de la plage de valeurs du Spinner.

        VBox vb_fournisseur =new VBox(lb_fournisseur,cb_fournissuer);



        VBox vb_categorie =new VBox(lb_categorie,lb_cat);


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


        VBox formulaire = new VBox(vb_fournisseur,vb_prd,vb_categorie,vb_qt);
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




        int idFournisseur=fournisseurDAO.getIdFByName(cb_fournissuer.getSelectionModel().getSelectedItem().toString());
        int idProduit= productDAO.getIdProduitByName(cb_Produits.getSelectionModel().getSelectedItem().toString());
        System.out.println("id fournisseur "+idFournisseur);



        cb_Produits.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            // Mettre à jour le texte du Label avec la valeur sélectionnée dans le ComboBox
            Produit prd=productDAO.getProduitById(productDAO.getIdProduitByName(cb_Produits.getSelectionModel().getSelectedItem().toString()));
            System.out.println("cat "+prd.getNomCategorie()+"   id prd "+productDAO.getIdProduitByName(cb_Produits.getSelectionModel().getSelectedItem().toString()));
            lb_cat.setText(categorieDAO.getNomCategorieById(prd.getIdCategorie()));
        });




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

                    System.out.println(user.toString());
                    DemandeDAO demandeDAO=new DemandeDAO(dataBaseQueries.getConnection());
                    Produit prd =productDAO.getProduitById(idProduit);
                    Date date= new Date();
                    Demande demande =new Demande(user,idFournisseur,idProduit,sp_quantité.getValue(),date);

                    demandeDAO.insertDemande(demande);
                    String demandeS="Nom client : "+user.getNom()+"\nProduit : "+prd.getNomProduit()+"\nCatégorie : "+prd.getNomCategorie()+"\nQuantité : "+sp_quantité.getValue()+"\nDate : "+date.toString();
                    EnvoyerDemande envoyerDemande = new EnvoyerDemande(demandeS);
                    System.out.println("Demande envouyée "+demandeS);


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
