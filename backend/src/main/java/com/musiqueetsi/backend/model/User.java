package com.musiqueetsi.backend.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private Boolean isAdmin;
    private List<String> favoriteMusicIds;
    // Constructors
    public User() {
    this.isAdmin = false;
    this.favoriteMusicIds = new ArrayList<String>();
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isAdmin = false;
        this.favoriteMusicIds = new ArrayList<String>();
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }
    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
    public List<String> getFavoriteMusicIds() {
        return favoriteMusicIds;
    }

    public void setFavoriteMusicIds(List<String> favoriteMusicIds) {
        this.favoriteMusicIds = favoriteMusicIds;
    }

}
