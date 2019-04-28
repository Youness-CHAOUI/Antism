package com.injaz2019.antism.classes.Metier;

/**
 * Created by CY_15 on 21/06/2018.
 */
public class Presence
{
// id
    int id; 
    String cne;
    int present;
    int id_listeAbsence;

    public static String sqlCreateClasse="create table Presence " +
                                        "(id integer primary key autoincrement,"+
                                        "cne TEXT," +
                                        "present integer," +
                                        "id_listeAbsence integer)";
    public static String sqlDropClasse="drop table if exists Presence";

    public Presence() {}

    public Presence(int id, String cne, int present, int id_listeAbsence) {
        this.id = id;
        this.cne = cne;
        this.present = present;
        this.id_listeAbsence = id_listeAbsence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getId_listeAbsence() {
        return id_listeAbsence;
    }

    public void setId_listeAbsence(int id_listeAbsence) {
        this.id_listeAbsence = id_listeAbsence;
    }
}
