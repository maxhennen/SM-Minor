package com.example.tune.activities.models;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String email;
    private Genre[] genrePreferences;
    private String profilePic;

    public User(String name, String email, Genre[] genrePreferences, String profilePic){
        this.name = name;
        this.email = email;
        this.genrePreferences = genrePreferences;
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public String getProfilePic(){return profilePic;}

    public String getEmail() {
        return email;
    }

    public Genre[] getGenrePreferences() {
        return genrePreferences;
    }
}
