package com.example.tp_base.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseQueries {
    public Connection con;

    String nom_driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://127.0.0.1/gestion du stock",user="root",pwd="";
    public final String dbURL = "jdbc:mysql://127.0.0.1/gestion du stock";
    public final String username = "root";
    public final String password = "";

    public DataBaseQueries() {
        con = getConnection();
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("Driver charger");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    }
}
