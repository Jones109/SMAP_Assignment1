package com.example.assignment1;

import android.os.Parcel;
import android.os.Parcelable;


/*
    Followed this to be able to save the word list as parcelable
    https://developer.android.com/reference/android/os/Parcelable
*/

public class Word implements Parcelable {
    private String name;
    private String description;
    private String pronounciation;
    private double rating;
    private String notes;

    public Word(String name, String pronounciation, String description, double rating) {
        this.name = name;
        this.description = description;
        this.pronounciation = pronounciation;
        this.rating = rating;
        this.notes = "";
    }

    protected Word(Parcel in) {
        name = in.readString();
        description = in.readString();
        pronounciation = in.readString();
        rating = in.readDouble();
        notes = in.readString();
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    public String getName(){
        return name;
    }

    public String getPronounciation() {
        return pronounciation;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(pronounciation);
        dest.writeDouble(rating);
        dest.writeString(notes);
    }
}
