package com.example.tp_base.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnexion {
    public  static Connection connect(String nom_driver,String url,String user,String pwd){
        Connection con=null;
        try {
            Class.forName(nom_driver);//chragement du driver
            System.out.println("driver charger");            con= DriverManager.getConnection(url,user,pwd);
            System.out.println("Connecté");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur driver"+e.getMessage());
        } catch (SQLException e) {
            System.out.println("erreur connexion"+e.getMessage());
        }
        return con;
    }
}
