package com.example.tp_base.IHM;

import com.example.tp_base.DAO.CategorieDAO;
import com.example.tp_base.DAO.DataBaseQueries;
import com.example.tp_base.DAO.FournisseurDAO;
import com.example.tp_base.DAO.ProductDAO;
import com.example.tp_base.OO.Fournisseur;
import com.example.tp_base.OO.Produit;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;

import javax.security.auth.callback.Callback;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class stockTable extends BorderPane {
    TextField tf_n;
    TextArea tf_des;
    TextField tf_p;
    TextField tf_qte;
    ComboBox cb_cat;
    ComboBox cb_f;
    //ObservableList<Produit> liste;
    Label lb_err;
    String msg_erreur;
    public TableView<Produit> tableProduit;
    public ObservableList<Produit> data;

    public stockTable() {

        Label lb_produits = new Label("Produits");
        lb_produits.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
        lb_produits.setStyle("-fx-text-fill: #0175A2;");
        lb_produits.setPadding(new Insets(20));


        Label lb_ajout = new Label("Ajouter un produit:");
        lb_ajout.setStyle("-fx-text-fill: #656f8e;-fx-font-size: 15px;");
        //lb_ajout.setFont(new Font());

        TextField tf_nomp = new TextField();
        tf_nomp.setPromptText("nom produit");
        this.tf_n = tf_nomp;


        TextArea tf_descripton = new TextArea();
        tf_descripton.setPromptText("description");
        //tf_descripton.setMaxSize(150,70);
        tf_descripton.setMaxHeight(100);
        this.tf_des = tf_descripton;


        TextField tf_prix = new TextField();
        tf_prix.setPromptText("prix unitaire");
        this.tf_p = tf_prix;

        TextField tf_quantite = new TextField();
        tf_quantite.setText("0");
        tf_quantite.setPromptText("Quantité");
        this.tf_qte = tf_quantite;


        Label lb_categorie = new Label("Catégorie");
        lb_categorie.setStyle("-fx-text-fill: #656f8e;-fx-font-size: 13px;");

        ComboBox cb_categorie = new ComboBox<>();
        DataBaseQueries dataBaseQueries = new DataBaseQueries();
        CategorieDAO categorieDAO = new CategorieDAO(dataBaseQueries.getConnection());
        List<String> optionsC = categorieDAO.getNomCategorie();
        cb_categorie.getItems().addAll(optionsC);
        cb_categorie.getSelectionModel().select(0);
        this.cb_cat = cb_categorie;

        Label lb_fournissuer = new Label("Fournisseur");
        lb_fournissuer.setStyle("-fx-text-fill: #656f8e;-fx-font-size: 13px;");

        ComboBox cb_fournissuer = new ComboBox();
        FournisseurDAO fournisseurDAO = new FournisseurDAO(dataBaseQueries.getConnection());
        List<String> optionsf = fournisseurDAO.getNomFournisseurs();
        cb_fournissuer.getItems().setAll(optionsf);
        cb_fournissuer.getSelectionModel().select(0);
        this.cb_f = cb_fournissuer;

        Button btn_modifier=new Button("Modifier");
        btn_modifier.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;");

        Button btn_ajouter = new Button("Ajouter");
        btn_ajouter.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;");
        btn_ajouter.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                /*String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\dashboard (1).png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                btn_ajouter.setGraphic(imageV);*/
                btn_ajouter.setStyle("-fx-background-color: white; -fx-text-fill: #0175A2; -fx-font-size: 13px;  "); // Change la couleur du fond en blanc
            }
        });

        btn_ajouter.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_ajouter.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   "); // Change la couleur du fond en bleu
                /*String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\dashboard.png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                btn_ajouter.setGraphic(imageV);*/
            }
        });

        Label lb_erreur = new Label("");
        lb_erreur.setTextFill(Color.RED);
        lb_erreur.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lb_erreur.setPadding(new Insets(10));


        VBox vb_ajout = new VBox();
        vb_ajout.setBorder(new Border(new BorderStroke(Color.web("#0175A2"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        vb_ajout.setPadding(new Insets(20));
        vb_ajout.setMaxWidth(250);
        vb_ajout.getChildren().addAll(lb_ajout, tf_nomp, tf_descripton, tf_prix, tf_quantite, lb_categorie, cb_categorie, lb_fournissuer, cb_fournissuer, btn_ajouter);
        vb_ajout.getChildren().add(btn_modifier);
        btn_modifier.setVisible(false);
        vb_ajout.setSpacing(14);
        vb_ajout.setPadding(new Insets(10));
        vb_ajout.setAlignment(Pos.TOP_CENTER);
        HBox hb_ajout = new HBox(vb_ajout);
        hb_ajout.setAlignment(Pos.CENTER);
        hb_ajout.setPadding(new Insets(20));


        tableProduit = initTab();

        this.setBottom(hb_ajout);
        this.setMinSize(700, 420);
        this.setTop(lb_produits);
        this.setCenter(tableProduit);
        this.setPadding(new Insets(20));
        // Bouton ajouter

        btn_ajouter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                int i = tableProduit.getItems().size();
                i++;
                if (tf_nomp.getText() != "" && tf_prix.getText() != "" && (tf_quantite.getText() != null && !tf_quantite.getText().isEmpty())) {
                    Produit prd = new Produit(i, tf_nomp.getText(), tf_descripton.getText(), Float.parseFloat(tf_prix.getText()), cb_categorie.getSelectionModel().getSelectedIndex(), cb_fournissuer.getSelectionModel().getSelectedIndex(), Integer.parseInt(tf_quantite.getText()));
                    //ajout dans la base
                    ProductDAO productDAO = new ProductDAO(dataBaseQueries.getConnection());
                    Boolean insertion = productDAO.insertProduit(prd);
                    //ajout dans la table
                    if (insertion) {
                        Produit produit=new Produit(i, tf_nomp.getText(), tf_descripton.getText(), Float.parseFloat(tf_prix.getText()), cb_categorie.getSelectionModel().getSelectedItem().toString(), cb_fournissuer.getSelectionModel().getSelectedItem().toString(),Integer.parseInt(tf_quantite.getText()));
                        tableProduit.getItems().add(produit);
                        tableProduit.refresh();
                    }


                }
                tf_nomp.setText("");
                tf_prix.setText("");
                tf_descripton.setText("");
                tf_quantite.setText("");
                cb_categorie.getSelectionModel().select(0);
                cb_fournissuer.getSelectionModel().select(0);

            }
        });
        tableProduit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Popup pop=new Popup();
                pop.setAutoHide(true);
                if (mouseEvent.getClickCount()==2 && mouseEvent.getButton()== MouseButton.PRIMARY){ //double click gauche
                    int selectedIndex = tableProduit.getSelectionModel().getSelectedIndex();
                    DataBaseQueries dataBaseQueries = new DataBaseQueries();
                    Produit produit=tableProduit.getItems().get(selectedIndex);
                    tf_nomp.setText(produit.getNomProduit());
                    tf_descripton.setText(produit.getDescription());
                    tf_prix.setText(String.valueOf(produit.getPrixUnitaire()));
                    tf_quantite.setText(String.valueOf(produit.getQuantite()));
                    cb_categorie.getSelectionModel().select(produit.getNomCategorie());
                    cb_fournissuer.getSelectionModel().select(produit.getNomFournisseur());
                    btn_modifier.setVisible(true);
                    btn_ajouter.setVisible(false);
                    lb_ajout.setText("Modifier un produit:");

                        // code susceptible de lever une exception


                    btn_modifier.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if (tf_nomp.getText() != "" && tf_prix.getText() != "" && (tf_quantite.getText() != null && !tf_quantite.getText().isEmpty())) {
                                CategorieDAO categorieDAO1=new CategorieDAO(dataBaseQueries.getConnection());
                                FournisseurDAO fournisseurDAO1=new FournisseurDAO(dataBaseQueries.getConnection());
                                int idC=categorieDAO1.getIdCByName(cb_categorie.getSelectionModel().getSelectedItem().toString());
                                System.out.println("Cat: "+idC);
                                int idF=fournisseurDAO1.getIdFByName(cb_fournissuer.getSelectionModel().getSelectedItem().toString());
                                System.out.println("fournisseur"+ idF);
                                Produit prd = new Produit(produit.getIdProduit(), tf_nomp.getText(), tf_descripton.getText(), Float.parseFloat(tf_prix.getText()),idC , idF, Integer.parseInt(tf_quantite.getText()));
                                //ajout dans la base
                                ProductDAO productDAO = new ProductDAO(dataBaseQueries.getConnection());
                                Boolean insertion = productDAO.updateProduit(prd);

                                //ajout dans la table
                                if (insertion) {
                                    // Mettre à jour les valeurs de la ligne existante dans la table
                                    produit.setNomProduit(tf_nomp.getText());
                                    produit.setDescription(tf_descripton.getText());
                                    produit.setPrixUnitaire(Float.parseFloat(tf_prix.getText()));
                                    produit.setNomCategorie(cb_categorie.getSelectionModel().getSelectedItem().toString());
                                    produit.setNomFournisseur(cb_fournissuer.getSelectionModel().getSelectedItem().toString());
                                    produit.setQuantite(Integer.parseInt(tf_quantite.getText()));
                                    tableProduit.refresh(); // Rafraîchir la table pour refléter les changements
                                    btn_modifier.setVisible(false);
                                    btn_ajouter.setVisible(true);
                                    lb_ajout.setText("Ajouter un produit:");
                                }
                            }


                            tf_nomp.setText("");
                            tf_prix.setText("");
                            tf_descripton.setText("");
                            tf_quantite.setText("");
                            cb_categorie.getSelectionModel().select(0);
                            cb_fournissuer.getSelectionModel().select(0);
                        }


                    });
                }
                if( mouseEvent.getButton()== MouseButton.SECONDARY){ //double click droite
                    Button btn_supp= new Button("Supprimer");
                    pop.getContent().add(btn_supp);
                    Point2D point= tableProduit.localToScreen(0,0);
                    pop.show(tableProduit,point.getX()+mouseEvent.getX(),point.getY()+ mouseEvent.getY());
                    btn_supp.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            // Récupération de l'index de la ligne sélectionnée
                            int selectedIndex = tableProduit.getSelectionModel().getSelectedIndex();
                            if (selectedIndex>0){
                                // Suppression de la ligne de la liste des items de la table view
                                DataBaseQueries dataBaseQueries = new DataBaseQueries();
                                ProductDAO productDAO = new ProductDAO(dataBaseQueries.getConnection());
                                Produit p=tableProduit.getItems().get(selectedIndex);
                                productDAO.deleteProduct(p.getIdProduit());
                                tableProduit.getItems().remove(selectedIndex);
                                btn_supp.setVisible(false);

                            }

                        }
                    });

                }
            }
        });


    }


        public TableView<Produit> initTab() {

            // Création des colonnes du TableView
            DataBaseQueries dataBaseQueries=new DataBaseQueries();
            ProductDAO productDAO =new ProductDAO(dataBaseQueries.getConnection());
            TableColumn<Produit, Integer> idCol = new TableColumn<>("IdProduit");
            idCol.setCellValueFactory(new PropertyValueFactory<>("idProduit"));

            TableColumn<Produit, String> nomProduitCol = new TableColumn<>("Nom produit");
            nomProduitCol.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));

            TableColumn<Produit, String> descriptionCol = new TableColumn<>("Description");
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

            TableColumn<Produit, Float> prixUnitaireCol = new TableColumn<>("PrixUnitaire");
            prixUnitaireCol.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));

            TableColumn<Produit, String> nomCategorieCol = new TableColumn<>("Nom catégorie");
            nomCategorieCol.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));

            TableColumn<Produit, String> nomFournisseurCol = new TableColumn<>("Nom fournisseur");
            nomFournisseurCol.setCellValueFactory(new PropertyValueFactory<>("nomFournisseur"));

            TableColumn<Produit, Integer> quantiteCol = new TableColumn<>("Quantité");
            quantiteCol.setCellValueFactory(new PropertyValueFactory<>("quantite"));

// Création du TableView
            TableView<Produit> tableView = new TableView<>();
            tableView.getColumns().addAll(idCol, nomProduitCol, descriptionCol, prixUnitaireCol, nomCategorieCol, nomFournisseurCol, quantiteCol);

// Récupération des données des produits
// Récupération des données des produits
            List<Produit> produits = productDAO.getAllProducts();
            ObservableList<Produit> observableProduits = FXCollections.observableArrayList(produits); // Ajout des données au TableView
            tableView.setItems(observableProduits);
            return tableView;

        }

}


    /*public TableView<Produit> initTab() {
        // Créer une instance de la classe DataBaseQueries pour obtenir la connexion
        DataBaseQueries dbq = new DataBaseQueries();
        Connection con = dbq.getConnection();

        // Créer une instance de la classe ProduitDAO pour accéder aux données de la table produit
        ProductDAO produitDAO = new ProductDAO(con);

        // Créer la TableView
        TableView<Produit> tableView = new TableView<>();

        // Créer les colonnes de la TableView
        TableColumn<Produit, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Produit, String> nomColumn = new TableColumn<>("Nom");
        TableColumn<Produit, String> descriptionColumn = new TableColumn<>("Description");
        TableColumn<Produit, Float> prixUnitaireColumn = new TableColumn<>("Prix unitaire");
        TableColumn<Produit, String> categorieColumn = new TableColumn<>("Catégorie");
        TableColumn<Produit, String> fournisseurColumn = new TableColumn<>("Fournisseur");
        TableColumn<Produit, Integer> quantiteColumn = new TableColumn<>("Quantité");

        // Définir les cellules de chaque colonne en utilisant des PropertyValueFactory
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixUnitaireColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        fournisseurColumn.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        // Ajouter les colonnes à la TableView
        tableView.getColumns().addAll(idColumn, nomColumn, descriptionColumn, prixUnitaireColumn, categorieColumn,
                fournisseurColumn, quantiteColumn);

        // Ajouter les données à la TableView
        List<Produit> produits = produitDAO.getAllProducts();
        tableView.getItems().addAll(produits);

        return tableView;
    }*/

   /* public TableView<Object[]> initTab() {
        // Créer une instance de la classe DataBaseQueries pour obtenir la connexion
        DataBaseQueries dbq = new DataBaseQueries();
        Connection con = dbq.getConnection();
        // Création d'une instance de la classe ProductDAO
        ProductDAO productDAO = new ProductDAO(con);
        CategorieDAO categorieDAO = new CategorieDAO(con);
        FournisseurDAO FournisseurDAO = new FournisseurDAO(con);
        // Créer la TableView
        TableView<Object[]> tableView = new TableView<>();

        TableColumn<Object[], Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Object[], String> nomColumn = new TableColumn<>("Nom");
        TableColumn<Object[], String> descriptionColumn = new TableColumn<>("Description");
        TableColumn<Object[], Float> prixUnitaireColumn = new TableColumn<>("Prix unitaire");
        TableColumn<Object[], String> categorieColumn = new TableColumn<>("Catégorie");
        TableColumn<Object[], String> fournisseurColumn = new TableColumn<>("Fournisseur");
        TableColumn<Object[], Integer> quantiteColumn = new TableColumn<>("Quantité");

        // Définir les cellules de chaque colonne en utilisant PropertyValueFactory
        idColumn.setCellValueFactory(new PropertyValueFactory<>("0"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("1"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("2"));
        prixUnitaireColumn.setCellValueFactory(new PropertyValueFactory<>("3"));
        categorieColumn.setCellValueFactory(new PropertyValueFactory<>("4"));
        fournisseurColumn.setCellValueFactory(new PropertyValueFactory<>("5"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("6"));

        // Ajouter les colonnes à la TableView
        tableView.getColumns().addAll(idColumn, nomColumn, descriptionColumn, prixUnitaireColumn, categorieColumn, fournisseurColumn, quantiteColumn);

        // Ajouter les données à la TableView
        List<Object[]> productsInfo = productDAO.getAllProductsInfo();
        tableView.getItems().addAll(productsInfo);
        return tableView;
    }
}*/



 /*public TableView<Produit> initTab() {
            // Créer une instance de la classe DataBaseQueries pour obtenir la connexion
            DataBaseQueries dbq = new DataBaseQueries();
            Connection con = dbq.getConnection();

            // Créer une instance de la classe ProduitDAO pour accéder aux données de la table produit
            ProductDAO produitDAO = new ProductDAO(con);

            // Créer la TableView
            TableView<Produit> tableView = new TableView<>();

            // Créer les colonnes de la TableView
            TableColumn<Produit, Integer> idColumn = new TableColumn<>("ID");
            TableColumn<Produit, String> nomColumn = new TableColumn<>("Nom");
            TableColumn<Produit, String> descriptionColumn = new TableColumn<>("Description");
            TableColumn<Produit, Float> prixUnitaireColumn = new TableColumn<>("Prix unitaire");
            TableColumn<Produit, String> categorieColumn = new TableColumn<>("Catégorie");
            TableColumn<Produit, String> fournisseurColumn = new TableColumn<>("Fournisseur");
            TableColumn<Produit, Integer> quantiteColumn = new TableColumn<>("Quantité");

            // Définir les cellules de chaque colonne en utilisant des PropertyValueFactory
            idColumn.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            prixUnitaireColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
            categorieColumn.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));
            fournisseurColumn.setCellValueFactory(new PropertyValueFactory<>("nomFournisseur"));
            quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));

            // Ajouter les colonnes à la TableView
            tableView.getColumns().addAll(idColumn, nomColumn, descriptionColumn, prixUnitaireColumn, categorieColumn,
                    fournisseurColumn, quantiteColumn);

            // Ajouter les données à la TableView
            List<Produit> produits = produitDAO.getAllProducts();
            tableView.getItems().addAll(produits);

            return tableView;
        }*/




        /*// Créer une instance de la classe DataBaseQueries pour obtenir la connexion
        DataBaseQueries dbq = new DataBaseQueries();
        Connection con = dbq.getConnection();
        // Création d'une instance de la classe ProductDAO
        ProductDAO productDAO = new ProductDAO(con);
        CategorieDAO categorieDAO = new CategorieDAO(con);
        FournisseurDAO FournisseurDAO = new FournisseurDAO(con);
        // Créer la TableView
        TableView<Produit> tableView = new TableView<>();

        ResultSet rs= productDAO.getAll();
        try {
            ResultSetMetaData metadata= rs.getMetaData();
            int nb_colonnes = metadata.getColumnCount();
            //initialisation des entetes
            for (int i=0;i<nb_colonnes;i++){
                TableColumn<Produit,Object>tc=new TableColumn<Produit, Object>(metadata.getColumnName(i+1));
                tc.setCellValueFactory(new PropertyValueFactory<Produit,Object>(metadata.getColumnName(i+1)));
                tableView.getColumns().add(tc);
            }
            //initialisation des données
            data = FXCollections.observableArrayList();
            while (rs.next()){
                int id= rs.getInt(metadata.getColumnName(1));
                String nom=rs.getString(metadata.getColumnName(2));
                String desc=rs.getString(metadata.getColumnName(3));
                float prix=rs.getFloat(metadata.getColumnName(4));
                String cat=rs.getString(metadata.getColumnName(5));
                String f=rs.getString(metadata.getColumnName(6));
                int q=rs.getInt(metadata.getColumnName(7));
                int idf=FournisseurDAO.getIdFByName(f);
                int idCat= categorieDAO.getIdCByName(cat);
                data.add(new Produit(id,nom,desc,prix,idCat,idf,q));

            }
            tableView.setItems(data);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/






