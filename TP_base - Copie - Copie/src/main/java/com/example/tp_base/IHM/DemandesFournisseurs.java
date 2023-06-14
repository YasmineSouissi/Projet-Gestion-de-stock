package com.example.tp_base.IHM;

import com.example.tp_base.DAO.DataBaseQueries;
import com.example.tp_base.DAO.DemandeDAO;
import com.example.tp_base.OO.Demande;
import com.example.tp_base.OO.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class DemandesFournisseurs extends BorderPane {
    public TableView<Demande> tableDemandes;
    public ObservableList<Demande> data;

    public  DemandesFournisseurs(User user){
        Label lb_demandes = new Label("Demandes");
        lb_demandes.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
        lb_demandes.setStyle("-fx-text-fill: #0175A2;");
        lb_demandes.setPadding(new Insets(20));


        tableDemandes = initTab();
        //tableDemandes.setMaxHeight(250);
        VBox vb_table = new VBox(tableDemandes);
        //vb_table.setMaxHeight(250);
        vb_table.setAlignment(Pos.TOP_LEFT);

        String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\TP_base - Copie\\src\\main\\resources\\com\\example\\tp_base\\add.png";
        File file = new File(imagePath);
        Image image = new Image(file.toURI().toString());
        ImageView imageV = new ImageView(image);
        imageV.setFitHeight(16);
        imageV.setFitHeight(16);
        Button btn_ajouter  = new Button("Demandes",imageV);
        btn_ajouter.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   ");



        //Button btn_modifier= new Button("Modifier");
        //btn_modifier.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   ");



        lb_demandes.setMaxHeight(100);

        HBox hb_btn= new HBox();
        hb_btn.setSpacing(20);
        HBox hb_bottom= new HBox(hb_btn,btn_ajouter);
        hb_bottom.setPadding(new Insets(15));
        hb_bottom.setSpacing(240);
        hb_bottom.setMaxHeight(100);

        HBox hb =new HBox();
        hb.setMaxSize(0,0);

        this.setCenter(vb_table);
        this.setBottom(hb_bottom);
        this.setTop(lb_demandes);
        this.setPadding(new Insets(20));
        this.setMinSize(700, 420);

        //event handelers
        btn_ajouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Scene sc = new Scene(new DemandesChat(user),350,500);
                Stage stage = new Stage();
                stage.setScene(sc);
                stage.setTitle("Nouvelle Demande");
                stage.show();
            }
        });
        btn_ajouter.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\TP_base - Copie\\src\\main\\resources\\com\\example\\tp_base\\add (1).png";
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
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\TP_base - Copie\\src\\main\\resources\\com\\example\\tp_base\\add.png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                btn_ajouter.setGraphic(imageV);
            }
        });

    }

    public TableView<Demande> initTab() {

        // Création des colonnes du TableView
        DataBaseQueries dataBaseQueries=new DataBaseQueries();
        DemandeDAO demandeDAO =new DemandeDAO(dataBaseQueries.getConnection());
        TableColumn<Demande, Integer> idCol = new TableColumn<>("IdDemande");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id_demande"));
        idCol.setPrefWidth(80);


        TableColumn<Demande, String> nomProduitCol = new TableColumn<>("Produit");
        nomProduitCol.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        nomProduitCol.setPrefWidth(120);

        TableColumn<Demande, Integer> quantiteCol = new TableColumn<>("Quantité");
        quantiteCol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        quantiteCol.setPrefWidth(120);


        TableColumn<Demande, String> FournisseurCol = new TableColumn<>("Fournisseur");
        FournisseurCol.setCellValueFactory(new PropertyValueFactory<>("nomFournisseur"));
        FournisseurCol.setPrefWidth(120);

        TableColumn<Demande, String> StatutCol = new TableColumn<>("Statut");
        StatutCol.setCellValueFactory(new PropertyValueFactory<>("Statut"));
        StatutCol.setPrefWidth(120);

        TableColumn<Demande, String> DateCol = new TableColumn<>("Date");
        DateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
        DateCol.setPrefWidth(120);






// Création du TableView
        TableView<Demande> tableView = new TableView<>();
        tableView.getColumns().addAll(idCol, nomProduitCol, quantiteCol, FournisseurCol, StatutCol, DateCol);

// Récupération des données des produits
// Récupération des données des produits
        List<Demande> Demandes = demandeDAO.getAllDemandes();
        ObservableList<Demande> observableDemandes = FXCollections.observableArrayList(Demandes); // Ajout des données au TableView
        tableView.setItems(observableDemandes);

        return tableView;

    }

}
