package com.example.tp_base.OO;

public class Fournisseur {
    private int idFournisseur;
    private String nomFournisseur;
    private String adresse;
    private String telephone;
    private String email;
    private String mdp;

    public Fournisseur(int idFournisseur, String nom, String adresse, String telephone, String email) {
        this.idFournisseur = idFournisseur;
        this.nomFournisseur = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;

    }
    public Fournisseur(int idFournisseur, String nom, String adresse, String telephone, String email,String mdp) {
        this.idFournisseur = idFournisseur;
        this.nomFournisseur = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.mdp=mdp;

    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nom) {
        this.nomFournisseur = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String toString() {
        return "Fournisseur{" +
                "idFournisseur=" + idFournisseur +
                ", nom='" + nomFournisseur + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}

