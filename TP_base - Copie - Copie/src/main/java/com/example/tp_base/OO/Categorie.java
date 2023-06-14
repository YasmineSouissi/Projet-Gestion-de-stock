package com.example.tp_base.OO;

public class Categorie {
    private int idCategorie;
    private String nomCategorie;
    private String description;

    public Categorie(int idCategorie, String nom, String description) {
        this.idCategorie = idCategorie;
        this.nomCategorie = nom;
        this.description = description;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNom(String nom) {
        this.nomCategorie = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String toString() {
        return "Categorie{" +

                ", nom='" + nomCategorie  +
                '}';
    }
    /*public String toString() {
        return "Categorie{" +
                "idCategorie=" + idCategorie +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }*/

}
