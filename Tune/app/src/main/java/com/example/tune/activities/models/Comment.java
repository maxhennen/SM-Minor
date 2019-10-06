package com.example.tune.activities.models;

import java.io.Serializable;

public class Comment implements Serializable {

    private User user;
    private String content;

    public Comment(User user, String content) {
        this.user = user;
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }
}
