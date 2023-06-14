package com.example.tp_base.OO;

public class User {
    private int id;
    private String nom;
    private String mdp;
    private String mail;
    private String role;

    public User(int id, String nom, String mdp, String mail, String role) {
        this.id = id;
        this.nom = nom;
        this.mdp = mdp;
        this.mail = mail;
        this.role = role;
    }

    public User(int id_demandeur) {
        this.id = id_demandeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", mdp='" + mdp + '\'' +
                ", mail='" + mail + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
