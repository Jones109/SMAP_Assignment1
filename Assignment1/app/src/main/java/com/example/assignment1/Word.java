package com.example.assignment1;

public class Word {
    private String name;
    private String picture;
    private String pronounciation;
    private double rating;

    public Word(String name, String picture, String pronounciation, double rating) {
        this.name = name;
        this.picture = picture;
        this.pronounciation = pronounciation;
        this.rating = rating;
    }

    public String getName(){
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getPronounciation() {
        return pronounciation;
    }

    public double getRating() {
        return rating;
    }
}
