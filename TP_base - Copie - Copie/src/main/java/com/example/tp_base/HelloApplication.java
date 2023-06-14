package com.example.tp_base;
import com.example.tp_base.IHM.LoginGrid;
import com.example.tp_base.DAO.MyConnexion;
import com.example.tp_base.DAO.UserQueries;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloApplication extends Application {
    Connection con=null;
    Statement st=null;
    String nom_driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://127.0.0.1/gestion du stock",user="root",pwd="";//url=jdbc:type base de données:addresse

    @Override
    public void start(Stage stage) {

        con= MyConnexion.connect(nom_driver,url,user,pwd);
        try {
            st= con.createStatement();
        } catch (SQLException e) {
            System.out.println("Erreur statment");
        }
        UserQueries userQueries = new UserQueries(con);
        LoginGrid loginGrid = new LoginGrid(userQueries);

        Scene sc =new Scene(loginGrid,400, 300);
        stage.setScene(sc);
        stage.setTitle("Connexion");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}