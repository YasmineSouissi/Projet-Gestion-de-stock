package com.example.tp_base.OO;

import java.io.Serializable;
import java.util.Date;

public class Demande  implements Serializable {
    private int id_demande;
    private User id_demandeur;
    private int id_fournisseur;
    private int id_produit;
    private int quantité;
    private String statut;
    private Date date ;

    private String nom ;
    private String nomFournisseur ;
    private String nomProduit ;




    public Demande(User user, int id_fournisseur, int id_produit, int quantite, Date date){

        System.out.println("demande crée !");
        this.id_demandeur=user;
        this.id_fournisseur=id_fournisseur;
        this.id_produit=id_produit;
        this.quantité=quantite;
        this.date=date;
        this.statut="En attente";
    }
    /*
    public Demande(int id_demande , User id_demandeur, int id_fournisseur, int id_produit, int quantité) {
        this.id_demande = id_demande ;
        this.id_demandeur = id_demandeur;
        this.id_fournisseur = id_fournisseur;
        this.id_produit = id_produit;
        this.quantité = quantité;
        this.statut = "en attente ";
    }
*/

    public Demande(int id_demande ,User id_demandeur, int id_fournisseur, int id_produit, int quantité, Date date) {
        this.id_demande = id_demande ;
        this.id_demandeur = id_demandeur;
        this.id_fournisseur = id_fournisseur;
        this.id_produit = id_produit;
        this.quantité = quantité;
        this.statut = "en attente ";
        this.date= date ;
    }

    public Demande(int id_demande , String nomProduit ,int quantité, String nom, String nomFournisseur, String statut , Date date) {
        this.id_demande = id_demande ;
        this.nom = nom;
        this.nomFournisseur = nomFournisseur;
        this.nomProduit = nomProduit;
        this.quantité = quantité;
        this.statut = statut;
        this.date= date ;
    }




    public int getId_demande() {
        return id_demande;
    }

    public void setIdDemande(int id_demande) {
        this.id_demande = id_demande;
    }

    public int getIdDemandeur() {
        return id_demandeur.getId();
    }

    public void setIdDemandeur(User id_demandeur) {
        this.id_demandeur = id_demandeur;
    }
    public void setFournisseur(int fournisseur) {
        this.id_fournisseur = fournisseur;
    }

    public int getIdFournisseur() {
        return id_fournisseur;
    }

    public void setIdFournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    public int getIdProduit() {
        return id_produit;
    }

    public void setIdProduit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getQuantite() {
        return quantité;
    }

    public void setQuantite(int quantite) {
        this.quantité = quantite;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    //nom produit
    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public String getNomDemandeur() {
        return nom;
    }

    public void setNomDemandeur(String nomDemandeur) {this.nom= nom;}




    @Override
    public String toString() {
        return "Demande{" +
                "id_demande=" + id_demande +
                ", id_demandeur=" + id_demandeur +
                ", id_fournisseur=" + id_fournisseur +
                ", id_produit=" + id_produit +
                ", quantité=" + quantité +
                ", statut=" + statut +
                '}';
    }
}