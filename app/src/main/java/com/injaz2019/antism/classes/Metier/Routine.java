package com.injaz2019.antism.classes.Metier;

public class Routine {
    private int id;
    private String date;
    private String desc;

    public static String sqlCreateRoutine = "create table Routine " +
                                            "(id integer primary key autoincrement," +
                                            "date TEXT," +
                                            "desc TEXT)";
    public static String sqlDropRoutine = "drop table if exists Routine";

    public Routine() {
    }

    public Routine(int id, String date, String desc) {
        this.id = id;
        this.date = date;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "";
    }
}
