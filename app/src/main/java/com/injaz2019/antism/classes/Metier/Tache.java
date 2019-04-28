package com.injaz2019.antism.classes.Metier;

import static com.injaz2019.antism.classes.Utils.TimeUtils.periodFromTime;

/**
 * Created by CY_15 on 28/04/2018.
 */
public class Tache {
    public static String TACHE_TABLE = "Tache";
    public static String sqlCreateTache = "create table " + TACHE_TABLE +
            " (id INTEGER primary key autoincrement," +
            "image TEXT," +
            "audio TEXT," +
            "heure TEXT," +
            "period INTEGER ," +
            "isDone INTEGER ," +
            "rate INTEGER ," +
            "id_routine INTEGER )";
    public static String sqlDropTache = "drop table if exists " + TACHE_TABLE;
    private int id;
    private String image;
    private String audio;
    private String heure;
    private boolean period;
    private boolean isDone;
    private int rate;
    private int id_routine;

    public Tache() {
    }

    public Tache(String image, String audio, String heure, int id_routine) {
        this.image = image;
        this.audio = audio;
        this.heure = heure;
        this.isDone = false;
        this.rate = 0;
        this.id_routine = id_routine;

        this.period = periodFromTime(heure);
    }

    public Tache(int id, String image, String audio, String heure, boolean period, int id_routine) {
        this.id = id;
        this.image = image;
        this.audio = audio;
        this.heure = heure;
        this.period = period;
        this.isDone = false;
        this.rate = 0;
        this.id_routine = id_routine;
    }

    public Tache(int id, String image, String audio, String heure, boolean period, boolean isDone, int rate, int id_routine) {
        this.id = id;
        this.image = image;
        this.audio = audio;
        this.heure = heure;
        this.period = period;
        this.isDone = isDone;
        this.rate = rate;
        this.id_routine = id_routine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public boolean getPeriod() {
        return period;
    }

    public void setPeriod(boolean period) {
        this.period = period;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getId_routine() {
        return id_routine;
    }

    public void setId_routine(int id_routine) {
        this.id_routine = id_routine;
    }

    @Override
    public String toString() {
        return "Tache{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", audio='" + audio + '\'' +
                ", heure='" + heure + '\'' +
                ", period=" + period +
                ", isDone=" + isDone +
                ", rate=" + rate +
                ", id_routine=" + id_routine +
                '}';
    }
}
