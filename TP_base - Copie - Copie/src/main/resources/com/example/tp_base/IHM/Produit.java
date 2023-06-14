package com.example.projet.IHM;

public class Produit {
    String nom;
    String prix;
    String description;
    String fournisseur;
    String categorie;
    int id;

    public Produit(int id , String nom, String des, String prix, String cat, String  four){
        this.id=id;
        this.prix=prix;
        this.nom=nom;
        this.description=des;
        this.fournisseur=four;
        this.categorie=cat;

    }
    @Override
    public String toString(){
        return this.nom;
    }
}
