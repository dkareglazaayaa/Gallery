package com.example.gallery.model;

import com.example.gallery.Utils.SoundManager;

import java.util.ArrayList;
import java.util.Arrays;

public class Gallery {
    private ArrayList<ImageFolder> images = new ArrayList<ImageFolder>();
    private static Gallery gallery = new Gallery();


    private Gallery() {
    }

    public static Gallery getGallery() {
        return gallery;
    }

    public void fillGallery() {
        images.addAll(Arrays.asList(
                new ImageFolder(1, "/sdcard/Pictures/фунтик.jpg", "/sdcard/Music/фунтик.mp3"),
                new ImageFolder(2, "/sdcard/Pictures/b.jpg", SoundManager.GenerateSound()),
                new ImageFolder(3, "/sdcard/Pictures/c.jpg", SoundManager.GenerateSound()),
                new ImageFolder(4, "/sdcard/Pictures/l.jpg", SoundManager.GenerateSound()),
                new ImageFolder(5, "/sdcard/Pictures/u.jpg", SoundManager.GenerateSound()),
                new ImageFolder(6, "/sdcard/Pictures/k.jpg", SoundManager.GenerateSound()),
                new ImageFolder(7, "/sdcard/Pictures/pin.jpg", "/sdcard/Music/pin.mp3"),
                new ImageFolder(8, "/sdcard/Pictures/n.jpg", SoundManager.GenerateSound()),
                new ImageFolder(9, "/sdcard/Pictures/p.jpg", SoundManager.GenerateSound()),
                new ImageFolder(10, "/sdcard/Pictures/f.jpg", SoundManager.GenerateSound()),
                new ImageFolder(11, "/sdcard/Pictures/t.jpg", SoundManager.GenerateSound()),
                new ImageFolder(12, "/sdcard/Pictures/d.jpg", SoundManager.GenerateSound()),
                new ImageFolder(13, "/sdcard/Pictures/сваты.jpg", "/sdcard/Music/сваты.mp3"),
                new ImageFolder(14, "/sdcard/Pictures/s.jpg", SoundManager.GenerateSound()),
                new ImageFolder(15, "/sdcard/Pictures/a.jpg", SoundManager.GenerateSound())));

    }


    public void addImageFolder(ImageFolder imageFolder){
        images.add(0,imageFolder);
    }
    public ArrayList<ImageFolder> getImages() {
        return images;
    }
    public ImageFolder getNewImageView() {
        return images.get(images.size()-1);
    }
    public void setImages(ArrayList<ImageFolder> images) {
        this.images = images;
    }
    public ImageFolder findById(int id){
        for(ImageFolder image:images){
            if(image.getId()==id)
                return  image;
        }
        return null;
    }
}
