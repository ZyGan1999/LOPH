package com.pigeonhunter.loph.handlers;

public class Song {
    private String name;
    private int imageId;

    public Song(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
