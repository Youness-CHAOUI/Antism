package com.injaz2019.antism.classes.Metier;

import java.util.List;

/**
 * Created by CY_15 on 28/04/2018.
 */
public class ListeAbsence
{
    int id;
    String date;
    String heure;
    int id_matiere;
    int id_classe;
    List<Presence> listePresence;

    public static String sqlCreateClasse="create table ListeAbsence " +
                                        "(id integer primary key autoincrement,"+
                                        "date TEXT," +
                                        "heure TEXT," +
                                        "id_matiere integer," +
                                        "id_classe integer)";
    public static String sqlDropClasse="drop table if exists ListeAbsence";

    public ListeAbsence() {}
    public ListeAbsence(int id, String date, String heure, int id_matiere, int id_classe) {
        this.id = id;
        this.date = date;
        this.heure = heure;
        this.id_matiere = id_matiere;
        this.id_classe = id_classe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getId_matiere() {
        return id_matiere;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    public int getId_classe() {
        return id_classe;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public List<Presence> getListePresence() {
        return listePresence;
    }

    public void setListePresence(List<Presence> listePresence) {
        this.listePresence = listePresence;
    }
}
