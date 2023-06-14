package com.example.tp_base.DAO;


import com.example.tp_base.OO.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserQueries {
    public Connection con ;
    public UserQueries(Connection con) {
        this.con = con;
    }

    public ResultSet getUserByUsernameAndPassword(String username, String password) {
        String query = "SELECT * FROM user WHERE nom = ? AND mdp = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
            return null;
        }
    }

    public  User connect(String email, String password) {
        String query = "SELECT * FROM user WHERE mail = ? AND mdp = ?";
        User user = null;
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("id");
                String name = rs.getString("nom");
                String role = rs.getString("role");
                user = new User(userId, name, email,password, role);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (user == null) {
            query = "SELECT * FROM fournisseur WHERE email = ? AND mdp = ?";
            try {
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int supplierId = rs.getInt("idFournisseur");
                    String supplierName = rs.getString("nomFournisseur");
                    String address = rs.getString("adresse");
                    String phoneNumber = rs.getString("telephone");
                    user = new User(supplierId, supplierName,password, email, "Fournisseur");
                }
                rs.close();
                pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return user;
    }

    public  String getUserNameById(int userId) {
        String nom = null;
        String query = "SELECT nom FROM user WHERE id=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                nom = rs.getString("nom");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nom ;
    }


    public User getUserById(int userId) {
        User user = null;
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM user WHERE id_user = ?");
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("role"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public  User getUser(int userId) {
        User user = null;
        String query = "SELECT * FROM user WHERE id=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String role = rs.getString("role");
                String email = rs.getString("email");
                String password = rs.getString("password");
                user = new User(id, name, role, email, password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    public boolean updateUser(User user) {
        String query = "UPDATE user SET nom=?,  mdp=? , mail=? WHERE id=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, user.getNom());
            pstmt.setString(2, user.getMdp());
            pstmt.setString(3, user.getMail());
            pstmt.setInt(4, user.getId());
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }




    public void insertion(int id,String nom_utilisateur,String mot_de_passe,String email,String role) {
        try {
            String query = "INSERT INTO utilisateurs (id, nom_utilisateur, mot_de_passe, email, role) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, nom_utilisateur);
            ps.setString(3, mot_de_passe);
            ps.setString(4, email);
            ps.setString(5, role);
            ps.executeUpdate();
            System.out.println("Utilisateur inséré avec succès !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
