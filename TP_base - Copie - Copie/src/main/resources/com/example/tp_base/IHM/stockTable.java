package com.example.projet.IHM;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class stockTable extends BorderPane {
    TextField tf_n;
    TextArea tf_des;
    TextField tf_p;
    ComboBox cb_cat;
    ComboBox cb_f;
    ObservableList<Produit> liste;
    Label lb_err;
    String msg_erreur;
    public stockTable(){

        Label lb_produits= new Label("Produits");
        lb_produits.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
        lb_produits.setStyle("-fx-text-fill: #0175A2;");
        lb_produits.setPadding(new Insets(20));


        Label lb_ajout =new Label("Ajouter un produit:");
        lb_ajout.setStyle("-fx-text-fill: #656f8e;-fx-font-size: 15px;-fx-font-weight: semi-bold;");

        TextField tf_nomp= new TextField();
        tf_nomp.setPromptText("nom produit");
        this.tf_n=tf_nomp;

        TextArea tf_descripton=new TextArea();
        tf_descripton.setPromptText("description");
        tf_descripton.setMaxSize(150,70);
        this.tf_des=tf_descripton;


        TextField tf_prix= new TextField();
        tf_prix.setPromptText("prix unitaire");
        this.tf_p=tf_prix;

        Label lb_categorie=new Label("Catégorie");
        lb_categorie.setStyle("-fx-text-fill: #656f8e;-fx-font-size: 13px;");

        ComboBox cb_categorie=new ComboBox<>();
        cb_categorie.getItems().addAll("Cat1","Cat2");
        cb_categorie.getSelectionModel().select(0);
        this.cb_cat=cb_categorie;

        Label lb_fournissuer=new Label("Fournisseur");
        lb_fournissuer.setStyle("-fx-text-fill: #656f8e;-fx-font-size: 13px;");

        ComboBox cb_fournissuer=new ComboBox();
        cb_fournissuer.getItems().setAll("F1","F2");
        cb_fournissuer.getSelectionModel().select(0);
        this.cb_f=cb_fournissuer;

        Button btn_ajouter =new Button("Ajouter");
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

        Label lb_erreur= new Label("");
        lb_erreur.setTextFill(Color.RED);
        lb_erreur.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lb_erreur.setPadding(new Insets(10));
        this.lb_err=lb_erreur;
        this.msg_erreur="Ce produit existe dans le stock";

        VBox vb_ajout=new VBox();
        vb_ajout.getChildren().addAll(lb_ajout,tf_nomp,tf_descripton,tf_prix,lb_categorie,cb_categorie,lb_fournissuer,cb_fournissuer,btn_ajouter);
        vb_ajout.setSpacing(14);
        vb_ajout.setPadding(new Insets(10));
        vb_ajout.setAlignment(Pos.TOP_CENTER);

        this.setRight(vb_ajout);

        ObservableList<Produit> listProduit ;
        listProduit= FXCollections.observableArrayList();
        this.liste=listProduit;

        ListView<Produit> lvProduit;
        lvProduit=new ListView<>(listProduit);

        TabPane tp_produit= new TabPane();
        //Produit P =new Produit(1,"prd1","10.000","description","fournisseur A","cat1");
        //String titre=P.nom;
        //tp_produit.getTabs().add(new Tab(titre, new VbProduit(P)));

        SplitPane spCenter= new SplitPane(lvProduit,tp_produit) ;
        spCenter.setDividerPosition(0,0.3);

        this.setTop(lb_produits);
        this.setCenter(spCenter);
        /*//Bouton ajouter
        btn_ajouter.setOnAction(new EcoutuerListeview(this));
        this.setBottom(this.lb_err);
        btn_ajouter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                int i=0;
                if(tf_nomp.getText()!=""&&tf_prix.getText()!="")
                {
                    if (listProduit.size()>0)
                    {
                        while (i < listProduit.size() ) {
                            if(listProduit.get(i).nom.equals(tf_nomp.getText())){

                            }
                            else{
                                i++;
                            }

                        }
                        if (i == listProduit.size()) {
                            //i++;
                            listProduit.add(new Produit(i,tf_nomp.getText(), tf_descripton.getText(), tf_prix.getText(),cb_categorie.getValue().toString(),cb_fournissuer.getValue().toString()));}
                    }
                    else {
                        listProduit.add(new Produit(i,tf_nomp.getText(), tf_descripton.getText(), tf_prix.getText(),cb_categorie.getValue().toString(),cb_fournissuer.getValue().toString()));
                    }

                }
                tf_nomp.setText("");
                tf_prix.setText("");
                tf_descripton.setText("");
                cb_categorie.getSelectionModel().select(0);
                cb_fournissuer.getSelectionModel().select(0);

            }
        });*/

        //click sur la liste
        /*class  EcouteurListview implements EventHandler<MouseEvent>{

            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton()== MouseButton.PRIMARY&&mouseEvent.getClickCount()==2){
                    String titre =lvProduit.getSelectionModel().getSelectedItem().nom;
                    tp_produit.getTabs().add(new Tab(titre,new VbProduit(lvProduit.getSelectionModel().getSelectedItem())));
                }
            }
        }
        lvProduit.setOnMouseClicked(new EcouteurListview());


    }*/
}}
