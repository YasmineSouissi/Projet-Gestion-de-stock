package com.example.projet.IHM;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dashbord extends GridPane {
    public Dashbord(){
        String quantité_produits = "12,346";
        String nbFournissuers="75";
        String nbdemandes="139";


        Label lb_dashboard = new Label("Dashboard");
        lb_dashboard.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
        lb_dashboard.setStyle("-fx-text-fill: #0175A2;");

        // Récupérer la date actuelle
        LocalDate date = LocalDate.now();

        // Formater la date en chaîne de caractères
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateStr = date.format(formatter);
        Label lb_date= new Label(dateStr);
        lb_date.setStyle("-fx-text-fill: #656f8e;");
        lb_date.setFont(Font.font("Georgia", FontWeight.BOLD, 13));

        // Afficher la date

        // carte produits
        Label lb_produits = new Label("Produits");
        lb_produits.setFont(Font.font("Trebuchet MS", FontWeight.MEDIUM, 17));
        lb_produits.setTextFill(Color.rgb(185, 195, 233));

        Label nb_produits = new Label(quantité_produits);
        nb_produits.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        nb_produits.setTextFill(Color.rgb(87, 70, 251));


        VBox carte_produits=new VBox(nb_produits,lb_produits);
        carte_produits.setPadding(new Insets(15));
        carte_produits.setMinSize(150,150);
        carte_produits.setMaxSize(150,150);
        carte_produits.setSpacing(10);
        carte_produits.setAlignment(Pos.CENTER);
        carte_produits.setStyle(" -fx-border-width: 0 0 8 0; -fx-background-color: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0.0, 0, 5);");

        // carte fournissuers
        Label lb_fournissuers = new Label("Fournissuers");
        lb_fournissuers.setFont(Font.font("Trebuchet MS", FontWeight.MEDIUM, 17));
        lb_fournissuers.setTextFill(Color.rgb(189, 239, 221));

        Label nb_fournissuers = new Label(nbFournissuers);
        nb_fournissuers.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        nb_fournissuers.setTextFill(Color.rgb(63, 244, 178));


        VBox carte_fournissuers=new VBox(nb_fournissuers,lb_fournissuers);
        carte_fournissuers.setPadding(new Insets(15));
        carte_fournissuers.setMinSize(150,150);
        carte_fournissuers.setMaxSize(150,150);
        carte_fournissuers.setSpacing(10);
        carte_fournissuers.setAlignment(Pos.CENTER);
        carte_fournissuers.setStyle(" -fx-border-width: 0 0 8 0; -fx-background-color: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0.0, 0, 5);");

        // carte demandes
        Label lb_demandes = new Label("Demandes");
        lb_demandes.setFont(Font.font("Trebuchet MS", FontWeight.MEDIUM, 17));
        lb_demandes.setTextFill(Color.rgb(244, 181, 85));

        Label nb_demandes = new Label(nbdemandes);
        nb_demandes.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        nb_demandes.setTextFill(Color.rgb(237, 148, 7));


        VBox carte_demandes=new VBox(nb_demandes,lb_demandes);
        carte_demandes.setPadding(new Insets(15));
        carte_demandes.setMinSize(150,150);
        carte_demandes.setMaxSize(150,150);
        carte_demandes.setSpacing(10);
        carte_demandes.setAlignment(Pos.CENTER);
        carte_demandes.setStyle(" -fx-border-width: 0 0 8 0; -fx-background-color: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0.0, 0, 5);");

        //affichage graphique
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("Janvier", 10),
                new PieChart.Data("Février", 20),
                new PieChart.Data("Mars", 30),
                new PieChart.Data("Avril", 40),
                new PieChart.Data("Mai", 50),
                new PieChart.Data("Juin", 60),
                new PieChart.Data("Juillet", 70),
                new PieChart.Data("Août", 80),
                new PieChart.Data("Septembre", 90),
                new PieChart.Data("Octobre", 100),
                new PieChart.Data("Novembre", 90),
                new PieChart.Data("Décembre", 80)
        );

        // Création du graphique
        PieChart pieChart = new PieChart(data);
        pieChart.setTitle("Produits en stock");
        // Création de la mise en page
        VBox vb_graphe= new VBox(pieChart);

        this.setHgap(25);
        this.setVgap(25);
        this.setPadding(new Insets(20));
        this.add(lb_dashboard,0,0);
        this.add(lb_date,3,0);
        this.add(carte_produits,0,1);
        this.add(carte_fournissuers,1,1);
        this.add(carte_demandes,2,1);
        this.add(vb_graphe , 0, 2, 3, 1);

        //this.setStyle("-fx-Border-color: black;");
    }
}
