package com.injaz2019.antism.classes.Metier;

public class Video {
    int id;
    String title;
    String path;
    String duration;

    public Video() {
    }

    public Video(int id, String title, String path, String duration) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
