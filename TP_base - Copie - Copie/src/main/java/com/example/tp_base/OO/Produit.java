package com.example.tp_base.OO;


import com.example.tp_base.DAO.CategorieDAO;
import com.example.tp_base.DAO.DataBaseQueries;
import com.example.tp_base.DAO.FournisseurDAO;

public class Produit {
    private int idProduit;
    private String nomProduit;
    private String description;
    private float prixUnitaire;
    private int idCategorie;
    private String nomCategorie;
    private String nomFournisseur;
    private int fournisseur;
    private int quantite = 0;

    DataBaseQueries dataBaseQueries = new DataBaseQueries();
    CategorieDAO categorieDAO = new CategorieDAO(dataBaseQueries.con);
    FournisseurDAO fournisseurDAO = new FournisseurDAO(dataBaseQueries.con);


    public Produit(int idProduit, String nomProduit, String description, float prixUnitaire, int idCategorie, int fournisseur, int quantite) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
        this.idCategorie = idCategorie;
        this.fournisseur = fournisseur;
        this.quantite = quantite;
    }

    public Produit(int idProduit, String nomProduit, float prixUnitaire) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.prixUnitaire = prixUnitaire;

    }

    public Produit(int id, String nomProduit, String description, int idCategorie, int idFournisseur, int quantite) {
        this.idProduit = id;
        this.nomProduit = nomProduit;
        this.description = description;
        //this.NomCategorie = categorieDAO.getNomCategorie(idCategorie);
        this.nomCategorie = "categorie";
        //this.NomFournisseur = fournisseurDAO.getNomFournisseurById(idFournisseur);
        this.nomFournisseur = "ffffffff";
        this.quantite = quantite;
    }

    public Produit(int idProduit, String nomProduit, String description, Float prixUnitaire, String nomCategorie, String nomFournisseur, int quantite) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
        this.nomCategorie = nomCategorie;
        this.nomFournisseur = nomFournisseur;
        this.quantite = quantite;
    }


    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public int getCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(int fournisseur) {
        this.fournisseur = fournisseur;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String Categorie) {
        this.nomCategorie = Categorie;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String fournisseur) {
        this.nomFournisseur = fournisseur;
    }


    public String toString() {
        return "Produit{" +
                "idProduit=" + idProduit +
                ", nomProduit='" + nomProduit + '\'' +
                ", description='" + description + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", categorie=" + nomCategorie +
                ", fournisseur=" + nomFournisseur +
                ",quantité=" + quantite +
                '}';
    }

}