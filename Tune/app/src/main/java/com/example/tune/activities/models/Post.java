package com.example.tune.activities.models;

import android.provider.MediaStore;

import java.io.Serializable;

public class Post implements Serializable {

    private String location;
    private User user;
    private MediaStore.Audio audio;
    private Genre genre;
    private Comment[] comments;

    public Post(String location, User user, MediaStore.Audio audio, Genre genre, Comment[] comments) {
        this.location = location;
        this.user = user;
        this.audio = audio;
        this.genre = genre;
        this.comments = comments;
    }

    public String getLocation() {
        return location;
    }

    public User getUser() {
        return user;
    }

    public MediaStore.Audio getAudio() {
        return audio;
    }

    public Genre getGenre() {
        return genre;
    }

    public Comment[] getComments() {
        return comments;
    }
}
