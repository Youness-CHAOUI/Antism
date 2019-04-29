package com.injaz2019.antism.classes.Metier;

public class Routine {
    public static String ROUTINE_TABLE = "Routine";
    public static String sqlCreateRoutine = "create table " + ROUTINE_TABLE +
            " (id integer primary key autoincrement," +
                                            "date TEXT," +
            "nbMorningTasks integer," +
            "nbNightTasks integer)";
    public static String sqlDropRoutine = "drop table if exists " + ROUTINE_TABLE;
    private int id;
    private String date;
    private int nbMorningTasks;
    private int nbNightTasks;

    public Routine() {
    }

    public Routine(String date) {
        this.date = date;
        this.nbMorningTasks = 0;
        this.nbNightTasks = 0;
    }

    public Routine(int id, String date) {
        this.id = id;
        this.date = date;
        this.nbMorningTasks = 0;
        this.nbNightTasks = 0;
    }

    public Routine(int id, String date, int nbMorningTasks, int nbNightTasks) {
        this.id = id;
        this.date = date;
        this.nbMorningTasks = nbMorningTasks;
        this.nbNightTasks = nbNightTasks;
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

    public int getNbMorningTasks() {
        return nbMorningTasks;
    }

    public void setNbMorningTasks(int nbMorningTasks) {
        this.nbMorningTasks = nbMorningTasks;
    }

    public int getNbNightTasks() {
        return nbNightTasks;
    }

    public void setNbNightTasks(int nbNightTasks) {
        this.nbNightTasks = nbNightTasks;
    }

    @Override
    public String toString() {
        return "Routine{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", nbMorningTasks=" + nbMorningTasks +
                ", nbNightTasks=" + nbNightTasks +
                '}';
    }
}
