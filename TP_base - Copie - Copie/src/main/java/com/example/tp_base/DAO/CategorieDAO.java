package com.example.tp_base.DAO;

import com.example.tp_base.OO.Categorie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAO {
    private Connection con;

    public CategorieDAO(Connection con) {
        this.con = con;
    }

    public List<Categorie> getAllCategories() {
        List<Categorie> categories = new ArrayList<>();
        String query = "SELECT * FROM categorie";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int idCategorie = rs.getInt(1);
                String nomCategorie = rs.getString(2);
                String description = rs.getString(3);
                Categorie categorie = new Categorie(idCategorie, nomCategorie, description);
                categories.add(categorie);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categories;
    }
    public Categorie getCategoryById(int idCategorie) {
        String query = "SELECT * FROM categorie WHERE idCategorie = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, idCategorie);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String nomCategorie = rs.getString("nomCategorie");
                String description = rs.getString("description");
                return new Categorie(idCategorie, nomCategorie, description);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public String getNomCategorie(int idCategorie) {
        String nomCategorie = null;
        String query = "SELECT nomCategorie FROM categorie WHERE idCategorie = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, idCategorie);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nomCategorie = rs.getString("nomCategorie");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomCategorie;
    }

    public List<String> getNomCategorie(){
        List<String> list= new ArrayList<String>();
        String query = "SELECT nomCategorie FROM categorie ";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String nomCategorie= rs.getString("nomCategorie");
                list.add(nomCategorie);
            }

            }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
}
    public int getIdCByName(String nomCategorie) {
        int idCategorie = 0;
        String query = "SELECT idCategorie FROM categorie WHERE nomCategorie = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, nomCategorie);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                idCategorie = rs.getInt("idCategorie");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idCategorie;
    }

    public String getNomCategorieById(int idCategorie) {
        String nomCategorie = null;
        String query = "SELECT nomCategorie FROM categorie WHERE idCategorie = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, idCategorie);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                nomCategorie = rs.getString("nomCategorie");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nomCategorie;
    }
    public void deleteCategorie(int idCategorie) {
        String query = "DELETE FROM categorie WHERE idCategorie = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, idCategorie);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
