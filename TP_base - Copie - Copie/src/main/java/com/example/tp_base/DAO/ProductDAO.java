package com.example.tp_base.DAO;

import com.example.tp_base.OO.Produit ;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDAO {
    private Connection con;

    public ProductDAO(Connection con) {
        this.con = con;
    }
    CategorieDAO categorieDAO = new CategorieDAO(con);
    FournisseurDAO fournisseurDAO =new FournisseurDAO(con);

    // Sélectionner tous les produits
    public List<Produit> getAllProducts() {
        List<Produit> produits = new ArrayList<>();
        DataBaseQueries dataBaseQueries =new DataBaseQueries();
        CategorieDAO categorieDAO1=new CategorieDAO(dataBaseQueries.getConnection());
        FournisseurDAO fournisseurDAO1=new FournisseurDAO(dataBaseQueries.getConnection());
        String query = "SELECT * FROM produit";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int idProduit = rs.getInt("idProduit");
                String nomProduit = rs.getString("nomProduit");
                String description = rs.getString("description");
                Float prixUnitaire = rs.getFloat("prixUnitaire");
                int idCategorie = rs.getInt("categorie");
                String nomCategorie = categorieDAO1.getNomCategorieById( idCategorie)   ;
                int idFournisseur = rs.getInt("fournisseur");
                String nomFournisseur = fournisseurDAO1.getNomFournisseurById(idFournisseur)   ;
                int quantite = rs.getInt("quantite");
                Produit produit = new Produit(idProduit, nomProduit, description,prixUnitaire ,  nomCategorie, nomFournisseur, quantite);
                produits.add(produit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return produits;
    }

    public Produit getProduitById(int idProduit) {
        Produit produit = null;
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM produit WHERE idProduit = ?");
            pstmt.setInt(1, idProduit);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                produit = new Produit(rs.getInt("idProduit"), rs.getString("nomProduit"),rs.getString("description") ,rs.getFloat("prixUnitaire"), rs.getInt("categorie"), rs.getInt("fournisseur"), rs.getInt("quantite"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produit;
    }
    public List<Object[]> getAllProductsInfo() {
        List<Object[]> productsInfo = new ArrayList<>();

        try {
            String query = " SELECT p.idProduit, p.nomProduit, p.description, p.prixUnitaire, c.nomCategorie, " +
                    "f.nomFournisseur, p.quantite FROM produit p INNER JOIN categorie c" +
                    " ON p.categorie = c.idCategorie INNER JOIN fournisseur f ON p.fournisseur = f.idFournisseur" +
                    " GROUP BY p.idProduit " ;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Object[] productInfo = new Object[7];
                productInfo[0] = rs.getInt("idProduit");
                productInfo[1] = rs.getString("nomProduit");
                productInfo[2] = rs.getString("description");
                productInfo[3] = rs.getFloat("prixUnitaire");
                productInfo[4] = rs.getString("nomCategorie");
                productInfo[5] = rs.getString("nomFournisseur");
                productInfo[6] = rs.getInt("quantite");
                productsInfo.add(productInfo);



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productsInfo;
    }

    public ResultSet getAll() {
        try {
            String query = " SELECT p.idProduit, p.nomProduit, p.description, p.prixUnitaire, c.nomCategorie, " +
                    "f.nomFournisseur, p.quantite FROM produit p INNER JOIN categorie c" +
                    " ON p.categorie = c.idCategorie INNER JOIN fournisseur f ON p.fournisseur = f.idFournisseur" +
                    " GROUP BY p.idProduit ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getNomProduitById(int idProduit) {
        String nomProduit = null;
        String query = "SELECT nomProduit FROM produit WHERE idProduit = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, idProduit);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nomProduit = rs.getString("nomProduit");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomProduit;
    }
    public List<String> getAllProductNames(){
        List<String> list= new ArrayList<String>();
        String query = "SELECT nomProduit FROM produit ";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String nomPrd = rs.getString("nomProduit");
                list.add(nomPrd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public int getIdProduitByName(String nomPrd) {
        int idPrd = 0;
        String query = "SELECT idProduit FROM produit WHERE nomProduit = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,nomPrd );
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                idPrd = rs.getInt("idProduit");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idPrd;
    }

    // Supprimer un produit
    public boolean deleteProduct(int productId) {
        try {
            String query = "DELETE FROM produit WHERE idProduit=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, productId);

            int result = stmt.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int countTotalProduits() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM produit";
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


    public int getNumberOfProductsByCategory(int idCategorie) {
        int result = 0;
        String query = "SELECT COUNT(idProduit) as nombre_produits FROM produit WHERE categorie = ?;";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, idCategorie);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt("nombre_produits");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean insertProduit(Produit produit) {
        String query = "INSERT INTO produit (idProduit,nomProduit, description, prixUnitaire, categorie, fournisseur, quantite) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, produit.getIdProduit());
            pstmt.setString(2, produit.getNomProduit());
            pstmt.setString(3, produit.getDescription());
            pstmt.setDouble(4, produit.getPrixUnitaire());
            pstmt.setInt(5, produit.getIdCategorie());
            pstmt.setInt(6, produit.getFournisseur());
            pstmt.setInt(7, produit.getQuantite());

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



    public boolean updateProduit(Produit produit) {
        String query = "UPDATE produit SET nomProduit=?, description=?, prixUnitaire=?, categorie=?, fournisseur=?, quantite=? WHERE idProduit=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, produit.getNomProduit());
            pstmt.setString(2, produit.getDescription());
            pstmt.setDouble(3, produit.getPrixUnitaire());
            pstmt.setInt(4, produit.getIdCategorie());
            pstmt.setInt(5, produit.getFournisseur());
            pstmt.setInt(6, produit.getQuantite());
            pstmt.setInt(7, produit.getIdProduit());

            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Produit mis à jour : " + rowsUpdated);
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
