package com.musiqueetsi.backend.model;

import java.util.List;

import org.springframework.stereotype.Indexed;

public class ArtistApplication {

    private static int idCounter = 0;

    private Long id;

    private List<String> artists;

    private String contact;

    private List<String> location;

    private String description;

    private String nameExample;

    public ArtistApplication() {
    }

    public ArtistApplication(List<String> artists, String contact, List<String> location, String description,
            String nameExample) {
        ArtistApplication.idCounter = idCounter + 1;
        this.id = Long.valueOf(idCounter);
        this.artists = artists;
        this.contact = contact;
        this.location = location;
        this.description = description;
        this.nameExample = nameExample;
    }

    public ArtistApplication(Long id, List<String> artists, String contact, List<String> location, String description,
            String nameExample) {
        this.id = id;
        this.artists = artists;
        this.contact = contact;
        this.location = location;
        this.description = description;
        this.nameExample = nameExample;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameExample() {
        return nameExample;
    }

    public void setNameExample(String nameExample) {
        this.nameExample = nameExample;
    }

}
