package com.injaz2019.antism.classes.Metier;

/**
 * Created by CY_15 on 28/04/2018.
 */
public class Classe
{
    int id;
    String libelle;

    public static String sqlCreateClasse="create table Classe " +
                                         "(id integer primary key autoincrement,"+
                                         "libelle TEXT)";
    public static String sqlDropClasse="drop table if exists Classe";

    public Classe() {}
    public Classe(int id, String libelle)
    {
        this.libelle = libelle;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Classe{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
