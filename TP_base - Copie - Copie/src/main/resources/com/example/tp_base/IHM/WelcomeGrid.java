package com.example.projet.IHM;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.util.Arrays;


public class WelcomeGrid extends GridPane {
    private final String email;
    private final String mdp;
    //public Connection con ;
    private int id;


    public int getIdd() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String role ;
    private String nom ;
    public BorderPane borderPane;
    public ColumnConstraints col1 ,  col2 ;
    public Button dashboard , produits , fournisseurs , demande, profil , deconexion ;
    public VBox menu ;
    public BackgroundFill backgroundFill ;
    public Background background ;


    public WelcomeGrid(int id, String nom  , String role,String email,String mdp) {
        this.id = id;
        this.role = role;
        this.nom = nom ;
        this.email=email;
        this.mdp=mdp;
        createContent();
    }

    private void createContent() {
        backgroundFill = new BackgroundFill(Color.web("#0175A2"), CornerRadii.EMPTY, Insets.EMPTY);
        background = new Background(backgroundFill);



        String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\dashboard.png";
        File file = new File(imagePath);
        Image image = new Image(file.toURI().toString());
        ImageView imageV = new ImageView(image);
        imageV.setFitHeight(16);
        imageV.setFitHeight(16);
        dashboard= new Button("Dashboard", imageV);
        dashboard.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 16px;   ");
        dashboard.setMaxWidth(Double.MAX_VALUE);

        VBox.setVgrow(dashboard, Priority.ALWAYS);

        imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\box-open.png";
        file = new File(imagePath);
        image = new Image(file.toURI().toString());
        imageV = new ImageView(image);
        imageV.setFitHeight(16);
        imageV.setFitHeight(16);
        produits= new Button("Produits",imageV);
        produits.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 16px;   ");
        produits.setMaxWidth(Double.MAX_VALUE);

        imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\truck-moving.png";
        file = new File(imagePath);
        image = new Image(file.toURI().toString());
        imageV = new ImageView(image);
        imageV.setFitHeight(16);
        imageV.setFitHeight(16);
        fournisseurs = new Button("Fournisseurs", imageV);
        fournisseurs.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 16px;   ");
        fournisseurs.setMaxWidth(Double.MAX_VALUE);

        imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\order-delivery.png";
        file = new File(imagePath);
        image = new Image(file.toURI().toString());
        imageV = new ImageView(image);
        imageV.setFitHeight(16);
        imageV.setFitHeight(16);
        demande = new Button("Demandes", imageV);
        demande.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 16px;   ");
        demande.setMaxWidth(Double.MAX_VALUE);

        imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\profil.png";
        file = new File(imagePath);
        image = new Image(file.toURI().toString());
        imageV = new ImageView(image);
        imageV.setFitHeight(16);
        imageV.setFitHeight(16);
        profil= new Button("Profil", imageV);
        profil.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 16px;   ");
        profil.setMaxWidth(Double.MAX_VALUE);


        imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\sign-out-alt.png";
        file = new File(imagePath);
        image = new Image(file.toURI().toString());
        imageV = new ImageView(image);
        imageV.setFitHeight(16);
        imageV.setFitHeight(16);
        deconexion= new Button("Déconnexion",imageV);
        deconexion.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 16px;   ");
        deconexion.setMaxWidth(Double.MAX_VALUE);

        menu = new VBox();
        menu.setPrefHeight(700);
        menu.setPrefWidth(160);
        //menu.setStyle("-fx-background-color: #01ss75A2;");
        menu.setBackground(background);
        menu.getChildren().addAll(dashboard, produits, fournisseurs, demande,profil, deconexion);

        Label lb_logo = new Label("Gestion de stock ");
        //lb_logo.setStyle("-fx-font-family: 'Roboto Condensed', sans-serif; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333;");
        lb_logo.setFont(Font.font("Georgia", FontWeight.BOLD, 20));


        String iconPath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\in-stock.png";
        File fil = new File(iconPath);
        Image icon= new Image(fil.toURI().toString());
        ImageView iconV= new ImageView(icon);

        HBox hb_logo = new HBox();
        hb_logo.getChildren().addAll(iconV,lb_logo);
        lb_logo.setStyle("-fx-text-fill: #0175A2;");
        hb_logo.setSpacing(7);

        Label lb_nom_prenom = new Label(this.nom);
        lb_nom_prenom.setFont(Font.font("Georgia", FontWeight.BOLD, 13));
        lb_nom_prenom.setStyle("-fx-text-fill: #656f8e;");
        Label lb_role = new Label(this.role);
        lb_role.setStyle("-fx-text-fill: #656f8e;");
        lb_role.setFont(Font.font("Georgia", FontWeight.MEDIUM, 13));
        iconPath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\user (1).png";
        fil = new File(iconPath);
        icon= new Image(fil.toURI().toString());
        iconV= new ImageView(icon);

        HBox hb_profil =new HBox();
        hb_profil.getChildren().addAll(iconV,lb_nom_prenom,lb_role);
        hb_profil.setSpacing(10);

        HBox hb_top =new HBox(hb_logo,hb_profil);
        hb_top.setPadding(new Insets(10));
        hb_top.setSpacing(350);
        //hb_top.setStyle("-fx-Border-color: black;");

        VBox vb_left =new VBox();
        vb_left.setMaxSize(0,0);

        borderPane = new BorderPane();
        borderPane.setTop(hb_top);
        borderPane.setLeft(menu);
        borderPane.setCenter(new Dashbord());

        /*logoutButton.setOnAction(event -> {
             Action du bouton de déconnexion
            com.example.projet.DataBaseQueries db = new com.example.projet.DataBaseQueries();
            con = db.getConnection();
            UserQueries userQueries = new UserQueries(con);
            LoginGrid loginGrid = new LoginGrid(userQueries);
            Scene newScene = new Scene(loginGrid);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(newScene);
        });*/

        dashboard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                borderPane.setCenter(new Dashbord());
            }
        });

        //changement du couleur du bouton

        dashboard.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\dashboard (1).png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                dashboard.setGraphic(imageV);
                dashboard.setStyle("-fx-background-color: white; -fx-text-fill: #0175A2; -fx-font-size: 16px;  "); // Change la couleur du fond en blanc
            }
        });

        dashboard.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dashboard.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 16px;   "); // Change la couleur du fond en bleu
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\dashboard.png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                dashboard.setGraphic(imageV);
            }
        });

        produits.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                borderPane.setCenter(new stockTable());
            }
        });
        //changement du couleur du bouton

        produits.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\box-openB.png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                produits.setGraphic(imageV);
                produits.setStyle("-fx-background-color: white; -fx-text-fill: #0175A2; -fx-font-size: 16px;  "); // Change la couleur du fond en blanc
            }
        });

        produits.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\box-open.png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                produits.setGraphic(imageV);
                produits.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 16px;   "); // Change la couleur du fond en bleu
            }
        });
        fournisseurs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                borderPane.setCenter(new FournisseursTable());
            }
        });
        //changement du couleur du bouton

        fournisseurs.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\truck-moving (1).png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                fournisseurs.setGraphic(imageV);
                fournisseurs.setStyle("-fx-background-color: white; -fx-text-fill: #0175A2; -fx-font-size: 16px;  "); // Change la couleur du fond en blanc
            }
        });

        fournisseurs.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\truck-moving.png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                fournisseurs.setGraphic(imageV);
                fournisseurs.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 16px;   "); // Change la couleur du fond en bleu
            }
        });

        demande.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                borderPane.setCenter(new DemandesTable());
            }
        });

        //changement du couleur du bouton

        demande.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\order-delivery (1).png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                demande.setGraphic(imageV);
                demande.setStyle("-fx-background-color: white; -fx-text-fill: #0175A2; -fx-font-size: 16px;  "); // Change la couleur du fond en blanc
            }
        });

        demande.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\order-delivery.png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                demande.setGraphic(imageV);
                demande.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 16px;   "); // Change la couleur du fond en bleu
            }
        });
        String nom =this.getNom();
        String role=this.getRole();
        String email=this.email;
        String mdp=this.mdp;
        profil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                borderPane.setCenter(new ProfilPane(id,nom,role,email,mdp));
            }
        });
        //changement du couleur du bouton

        profil.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                profil.setStyle("-fx-background-color: white; -fx-text-fill: #0175A2; -fx-font-size: 16px;  "); // Change la couleur du fond en blanc
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\profilB.png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                profil.setGraphic(imageV);
            }
        });

        profil.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\profil.png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                profil.setGraphic( imageV);
                profil.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 16px;   "); // Change la couleur du fond en bleu
            }
        });

        //changement du couleur du bouton

        deconexion.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\sign-out-alt (1).png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                deconexion.setGraphic( imageV);
                deconexion.setStyle("-fx-background-color: white; -fx-text-fill: #0175A2; -fx-font-size: 16px;  "); // Change la couleur du fond en blanc
            }
        });

        deconexion.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\sign-out-alt.png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                deconexion.setGraphic( imageV);
                deconexion.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 16px;   "); // Change la couleur du fond en bleu
            }
        });
        for (Button button : Arrays.asList(dashboard, produits, fournisseurs, demande, profil, deconexion)) {
            button.setContentDisplay(ContentDisplay.TOP);
            button.setGraphicTextGap(5);// Ajuster l'espace ici
            button.setPadding(new Insets(5));
        }

        this.setVgap(10);
        this.setHgap(10);
        //this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.web("#EBE8DD"), null, null)));
        this.getChildren().add(borderPane);

    }}