package com.example.tp_base.DAO;

import com.example.tp_base.OO.Fournisseur ;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDAO {
    private Connection con;

    public FournisseurDAO(Connection con) {
        this.con = con;
    }

    public List<Fournisseur> getAllFournisseurs() {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        String query = "SELECT * FROM fournisseur";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int idFournisseur = rs.getInt("idFournisseur");
                String nomFournisseur = rs.getString("nomFournisseur");
                String adresseFournisseur = rs.getString("adresse");
                String telephoneFournisseur = rs.getString("telephone");
                String mailFournisseur = rs.getString("email");
                Fournisseur fournisseur = new Fournisseur(idFournisseur, nomFournisseur, adresseFournisseur, telephoneFournisseur, mailFournisseur);
                fournisseurs.add(fournisseur);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fournisseurs;
    }

    public void deleteFournisseur(int idFournisseur) {
        String query = "DELETE FROM fournisseur WHERE idFournisseur = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, idFournisseur);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Fournisseur getSupplierById(int idFournisseur) {
        Fournisseur fournisseur = null;
        String query = "SELECT * FROM fournisseur WHERE id_fournisseur = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, idFournisseur);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String nomFournisseur = rs.getString("nomFournisseur");
                String adresse = rs.getString("adresse");
                String telephone = rs.getString("telephone");
                String mail = rs.getString("mail");
                fournisseur = new Fournisseur(idFournisseur, nomFournisseur, adresse, telephone,mail);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fournisseur;
    }
public List<String> getNomFournisseurs(){
    List<String> list= new ArrayList<String>();
    String query = "SELECT nomFournisseur FROM fournisseur ";
    try {
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String nomFournisseur = rs.getString("nomFournisseur");
            list.add(nomFournisseur);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return list;


}

public String getNomFournisseurById(int idF) {
        String nomf= null;
        String query = "SELECT nomFournisseur FROM fournisseur WHERE idFournisseur = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, idF);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                nomf = rs.getString("nomFournisseur");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nomf;
    }
    public int countTotalFournisseurs() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM fournisseur";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
    public int getIdFByName(String nomFournisseur) {
        int idFournisseur = 0;
        String query = "SELECT idFournisseur FROM fournisseur WHERE nomFournisseur = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, nomFournisseur);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                idFournisseur = rs.getInt("idFournisseur");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idFournisseur;
    }

    public boolean addFournisseur(Fournisseur fournisseur) {
        String query = "INSERT INTO fournisseur (idFournisseur,nomFournisseur,  adresse, telephone, Email) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, fournisseur.getIdFournisseur());
            pstmt.setString(2, fournisseur.getNomFournisseur());
            pstmt.setString(3, fournisseur.getAdresse());
            pstmt.setString(4, fournisseur.getTelephone());
            pstmt.setString(5, fournisseur.getEmail());


            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public boolean updateFournisseur(Fournisseur fournisseur) {
        String query = "UPDATE fournisseur SET nomFournisseur=?, adresse=?, telephone=?, email=? WHERE idFournisseur=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, fournisseur.getNomFournisseur());
            pstmt.setString(2, fournisseur.getAdresse());
            pstmt.setString(3, fournisseur.getTelephone());
            pstmt.setString(4, fournisseur.getEmail());
            pstmt.setInt(5, fournisseur.getIdFournisseur());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
