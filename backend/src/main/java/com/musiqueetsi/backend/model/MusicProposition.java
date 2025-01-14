package com.musiqueetsi.backend.model;

import java.util.List;

public class MusicProposition {

    private static int idCounter = 0;

    private Long id;

    private String title;

    private List<String> artists;

    private String location;

    private Boolean openSource;

    public MusicProposition() {
    }

    public MusicProposition(String title, List<String> artists, String location, Boolean openSource) {
        MusicProposition.idCounter = idCounter + 1;
        this.title = title;
        this.artists = artists;
        this.location = location;
        this.openSource = openSource;
    }

    public MusicProposition(Long id, String title, List<String> artists, String location, Boolean openSource) {
        this.id = id;
        this.title = title;
        this.artists = artists;
        this.location = location;
        this.openSource = openSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getOpenSource() {
        return openSource;
    }

    public void setOpenSource(Boolean openSource) {
        this.openSource = openSource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
