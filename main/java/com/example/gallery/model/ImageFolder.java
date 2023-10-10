package com.example.gallery.model;

public class ImageFolder {
    private int id;
    private  String imagePath;
    private  String soundPath;

    public ImageFolder(){

    }

    public ImageFolder(int id,String path, String folderName) {
        this.id=id;
        this.imagePath = path;
        this.soundPath = folderName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSoundPath() {
        return soundPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

