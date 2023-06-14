package com.example.projet.IHM;

public class Fournisseur {
    private  String nom;
    private  String adresse;
    private  String tel;
    private  String email;

    private int id_fournisseur;

    public static int getId() {
        return id;
    }

    private static int id;

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Fournisseur(String nom, String adresse, String telephone, String email) {;

        this.id_fournisseur=Fournisseur.id++;
        this.nom=nom;
        this.adresse=adresse;
        this.tel=telephone;
        this.email=email;
    }

    @Override
    public String toString() {
        return "identifiant: ";
    }
}
