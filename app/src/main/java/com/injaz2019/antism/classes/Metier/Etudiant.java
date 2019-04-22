package com.injaz2019.antism.classes.Metier;

/**
 * Created by CY_15 on 28/04/2018.
 */
public class Etudiant
{
    String cne;
    String prenom;
    String nom;
    String email;
    int id_classe;

    public static String sqlCreateClasse="create table Etudiant " +
                                        "(cne TEXT primary key,"+
                                        "prenom TEXT," +
                                        "nom TEXT," +
                                        "email TEXT," +
                                        "id_classe integer)";
    public static String sqlDropClasse="drop table if exists Etudiant";

    public Etudiant() {}
    public Etudiant(String cne, String prenom, String nom, String email, int id_classe) {
        this.cne = cne;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.id_classe = id_classe;
    }

    public String getCne() {
        return cne;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_classe() {
        return id_classe;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "cne='" + cne + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", id_classe=" + id_classe +
                '}';
    }
}
