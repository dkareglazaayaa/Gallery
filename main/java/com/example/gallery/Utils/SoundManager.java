package com.example.gallery.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class SoundManager {
    static {
        sounds = new ArrayList<>();
        fillSounds();
    }
    private static ArrayList<String> sounds;

    private static void fillSounds() {
        sounds.addAll(Arrays.asList(
                "/sdcard/Music/0.mp3",
                "/sdcard/Music/1.mp3",
                "/sdcard/Music/2.mp3",
                "/sdcard/Music/3.mp3",
                "/sdcard/Music/4.mp3",
                "/sdcard/Music/5.mp3",
                "/sdcard/Music/6.mp3"));
    }
    public static String GenerateSound(){
        int index= (int) (Math.random() * sounds.size());
        return sounds.get(index);
    }
}
