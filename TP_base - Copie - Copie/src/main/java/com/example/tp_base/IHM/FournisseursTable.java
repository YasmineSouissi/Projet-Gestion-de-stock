package com.example.tp_base.IHM;

import com.example.tp_base.DAO.DataBaseQueries;
import com.example.tp_base.DAO.FournisseurDAO;
import com.example.tp_base.DAO.ProductDAO;
import  com.example.tp_base.OO.Fournisseur;
import com.example.tp_base.OO.Produit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Arrays;
import java.util.List;

public class FournisseursTable extends BorderPane {
    Button btn;
    Button btn_ajout;
    Button btn_modifier;

    public FournisseursTable() {
        TableView<Fournisseur> tableFournisseurs;
        TextField tf_nom;
        TextField tf_email;
        TextField tf_tel;
        TextField tf_adresse;


        Label lb_fournisseurs = new Label("Fournisseurs");
        lb_fournisseurs.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
        lb_fournisseurs.setStyle("-fx-text-fill: #0175A2;");
        lb_fournisseurs.setPadding(new Insets(20));

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
        btn_ajout = new Button("Ajouter");
        btn_ajout.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;");

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

        btn_modifier = new Button("Modifier");
        btn_modifier.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;");
        //updateButton.setOnAction(event -> updateSupplier());


        //création du tableau fournisseurs
        tableFournisseurs = initTab();

        // Création du panneau d'édition

        HBox hb_ajout = new HBox();
        hb_ajout.getChildren().addAll(tf_nom, tf_tel, tf_email, tf_adresse, btn_ajout);
        hb_ajout.getChildren().add(btn_modifier);
        btn_modifier.setVisible(false);
        hb_ajout.setSpacing(10);
        hb_ajout.setPadding(new Insets(15));

        VBox vb_top = new VBox(lb_fournisseurs, hb_ajout);

        for (TextField tf : Arrays.asList(tf_nom, tf_tel, tf_email, tf_adresse)) {
            tf.setStyle("-fx-pref-width: 100");
        }

        tableFournisseurs = initTab();

        this.setTop(vb_top);
        this.setCenter(tableFournisseurs);
        this.setPadding(new Insets(20));
        this.setMinSize(700, 420);

        TableView<Fournisseur> finalTableFournisseurs = tableFournisseurs;
        btn_ajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int i = finalTableFournisseurs.getItems().size();
                i++;
                if (tf_nom.getText() != "" && tf_adresse.getText() != "" && (tf_tel.getText() != null && !tf_email.getText().isEmpty())) {
                    Fournisseur f = new Fournisseur(i, tf_nom.getText(), tf_adresse.getText(), tf_tel.getText(), tf_email.getText());
                    //ajout dans la base
                    DataBaseQueries dataBaseQueries =new DataBaseQueries();
                    FournisseurDAO fournisseurDAO=new FournisseurDAO(dataBaseQueries.getConnection());
                    Boolean insertion = fournisseurDAO.addFournisseur(f);
                    //ajout dans la table
                    if (insertion) {
                        finalTableFournisseurs.getItems().add(f);
                    }


                }
                tf_nom.setText("");
                tf_adresse.setText("");
                tf_tel.setText("");
                tf_email.setText("");
            }


        });


        TableView<Fournisseur> finalTableFournisseurs1 = tableFournisseurs;
        tableFournisseurs.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Popup pop=new Popup();
                pop.setAutoHide(true);
                if (mouseEvent.getClickCount()==2 && mouseEvent.getButton()== MouseButton.PRIMARY){ //double click gauche
                    int selectedIndex = finalTableFournisseurs1.getSelectionModel().getSelectedIndex();
                    DataBaseQueries dataBaseQueries = new DataBaseQueries();
                    FournisseurDAO fournisseurDAO =new FournisseurDAO(dataBaseQueries.getConnection());
                    Fournisseur f=finalTableFournisseurs1.getItems().get(selectedIndex);
                    tf_nom.setText(f.getNomFournisseur());
                    tf_adresse.setText(f.getAdresse());
                    tf_email.setText(f.getEmail());
                    tf_tel.setText(f.getTelephone());
                    btn_modifier.setVisible(true);
                    btn_ajout.setVisible(false);

                    btn_modifier.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if (tf_nom.getText() != "" && tf_adresse.getText() != "" && (tf_tel.getText() != null && !tf_email.getText().isEmpty())) {
                                Fournisseur f = new Fournisseur(selectedIndex, tf_nom.getText(), tf_adresse.getText(), tf_tel.getText(), tf_email.getText());
                                //ajout dans la base
                                DataBaseQueries dataBaseQueries =new DataBaseQueries();
                                FournisseurDAO fournisseurDAO=new FournisseurDAO(dataBaseQueries.getConnection());
                                Boolean modification = fournisseurDAO.updateFournisseur(f);
                                finalTableFournisseurs1.getItems().set(selectedIndex,f);
                                finalTableFournisseurs1.refresh();
                                btn_modifier.setVisible(false);
                                btn_ajout.setVisible(true);

                            }
                            tf_nom.setText("");
                            tf_adresse.setText("");
                            tf_tel.setText("");
                            tf_email.setText("");
                            btn=btn_ajout;
                        }


                    });
                }
                if( mouseEvent.getButton()== MouseButton.SECONDARY){ //double click droite
                    Button btn_supp= new Button("Supprimer");
                    pop.getContent().add(btn_supp);
                    Point2D point= finalTableFournisseurs1.localToScreen(0,0);
                    pop.show(finalTableFournisseurs1,point.getX()+mouseEvent.getX(),point.getY()+ mouseEvent.getY());
                    btn_supp.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            // Récupération de l'index de la ligne sélectionnée
                            int selectedIndex = finalTableFournisseurs1.getSelectionModel().getSelectedIndex();
                            if (selectedIndex>0){
                                // Suppression de la ligne de la liste des items de la table view
                                DataBaseQueries dataBaseQueries = new DataBaseQueries();
                                FournisseurDAO fournisseurDAO =new FournisseurDAO(dataBaseQueries.getConnection());
                                Fournisseur f=finalTableFournisseurs1.getItems().get(selectedIndex);
                                fournisseurDAO.deleteFournisseur(f.getIdFournisseur());
                                finalTableFournisseurs1.getItems().remove(selectedIndex);
                                btn_supp.setVisible(false);

                            }

                        }
                    });

                }
            }
            });


    }

    public TableView<Fournisseur> initTab() {
        // Créer une instance de la classe DataBaseQueries pour obtenir la connexion
        DataBaseQueries dbq = new DataBaseQueries();
        Connection con = dbq.getConnection();
        // Création d'une instance de la classe FournisseurDAO
        FournisseurDAO fournisseurDAO = new FournisseurDAO(con);
        // Créer la TableView
        TableView<Fournisseur> tableView = new TableView<>();
        TableColumn<Fournisseur, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Fournisseur, String> nomColumn = new TableColumn<>("Nom");
        TableColumn<Fournisseur, String> adresseColumn = new TableColumn<>("Adresse");
        adresseColumn.setPrefWidth(300);
        TableColumn<Fournisseur, String> telephoneColumn = new TableColumn<>("Téléphone");
        TableColumn<Fournisseur, String> emailColumn = new TableColumn<>("Email");
        // Définir les cellules de chaque colonne en utilisant PropertyValueFactory
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idFournisseur"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nomFournisseur"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        // Ajouter les colonnes à la TableView
        tableView.getColumns().addAll(idColumn, nomColumn, adresseColumn, telephoneColumn, emailColumn);
        // Ajouter les données à la TableView
        List<Fournisseur> fournisseursInfo = fournisseurDAO.getAllFournisseurs();
        tableView.getItems().addAll(fournisseursInfo);
        return tableView;
    }
}