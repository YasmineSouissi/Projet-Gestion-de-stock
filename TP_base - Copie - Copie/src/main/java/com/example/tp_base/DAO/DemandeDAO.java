package com.example.tp_base.DAO;

import com.example.tp_base.OO.Demande ;
import com.example.tp_base.OO.Produit;
import com.example.tp_base.OO.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DemandeDAO {

    private Connection con;

    public DemandeDAO(Connection con) {
        this.con = con;
    }
    DataBaseQueries dataBaseQueries = new DataBaseQueries();
    UserQueries UserQueries = new UserQueries(dataBaseQueries.con);
    FournisseurDAO fournisseurDAO = new FournisseurDAO(dataBaseQueries.con);
    ProductDAO productDAO = new ProductDAO(dataBaseQueries.con);

    // méthode pour insérer une demande dans la base de données
    public boolean insertDemande(Demande demande) {
        boolean inserted = false;

        try {
            String query = "INSERT INTO demande (id_demandeur, id_fournisseur, id_produit, quantité, statut) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, demande.getIdDemandeur());
            preparedStatement.setInt(2, demande.getIdFournisseur());
            preparedStatement.setInt(3, demande.getIdProduit());
            preparedStatement.setInt(4, demande.getQuantite());
            preparedStatement.setString(5, demande.getStatut());

            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected == 1) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    demande.setIdDemande(rs.getInt(1));
                    inserted = true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return inserted;
    }
/*
    // méthode pour récupérer toutes les demandes dans la base de données
    public List<Demande> getAllDemande() {
        List<Demande> demandes = new ArrayList<>();

        try {
            String query = "SELECT * FROM demandes";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Demande demande = new Demande(new User(rs.getInt("id_demandeur")), rs.getInt("id_fournisseur"), rs.getInt("id_produit"), rs.getInt("quantite"));
                demande.setIdDemande(rs.getInt("id_demande"));
                demande.setStatut(rs.getString("acceptee"));

                demandes.add(demande);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return demandes;
    }*/


    public List<Demande> getDemandesByFournisseurId(int fournisseurId) {
        List<Demande> demandes = new ArrayList<>();
        DataBaseQueries dataBaseQueries = new DataBaseQueries();
        CategorieDAO categorieDAO1 = new CategorieDAO(dataBaseQueries.getConnection());
        FournisseurDAO fournisseurDAO1 = new FournisseurDAO(dataBaseQueries.getConnection());
        String query = "SELECT * FROM Demande WHERE id_fournisseur = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, fournisseurId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id_demande = rs.getInt(1);
                int id_produit = rs.getInt("id_produit");
                String nomProduit = productDAO.getNomProduitById(id_produit);
                int id_demandeur = rs.getInt("id_demandeur");
                String nom_demandeur = UserQueries.getUserNameById(id_demandeur);
                int idFournisseur = rs.getInt("id_fournisseur");
                String nomFournisseur = fournisseurDAO1.getNomFournisseurById(idFournisseur);
                String statut = rs.getString("statut");
                int quantité  = rs.getInt(6);
                Date date = rs.getDate("date");
                Demande demande = new Demande(id_demande, nomProduit, quantité, nom_demandeur, nomFournisseur, statut, date);
                demandes.add(demande);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return demandes;
    }


    public List<Demande> getAllDemandes() {
        List<Demande> Demandes= new ArrayList<>();
        DataBaseQueries dataBaseQueries =new DataBaseQueries();
        CategorieDAO categorieDAO1=new CategorieDAO(dataBaseQueries.getConnection());
        FournisseurDAO fournisseurDAO1=new FournisseurDAO(dataBaseQueries.getConnection());
        String query = "SELECT * FROM Demande";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id_demande = rs.getInt(1);
                int  id_produit = rs.getInt("id_produit");
                String nomProduit = productDAO.getNomProduitById(id_produit);
                int  id_demandeur = rs.getInt("id_demandeur");
                String nom_demandeur = UserQueries.getUserNameById(id_demandeur);
                int idFournisseur = rs.getInt("id_fournisseur");
                String nomFournisseur = fournisseurDAO1.getNomFournisseurById(idFournisseur)   ;
                String statut = rs.getString("statut");
                int quantité  = rs.getInt(6);
                Date date = rs.getDate("date");
                Demande demande = new Demande(id_demande, nomProduit, quantité,nom_demandeur ,  nomFournisseur, statut , date);
                Demandes.add(demande);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Demandes;
    }






    public int countTotalDemandes() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Demande";
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



}