package com.musiqueetsi.backend.model;

import java.util.List;

public class MusicProposition {

    private static int idCounter = 0;

    private Long id;

    private String theme;

    private String title;

    private List<String> artists;

    private String location;

    private Boolean openSource;

    public MusicProposition() {
    }

    public MusicProposition(String title, List<String> artists, String location, Boolean openSource) {
        MusicProposition.idCounter = idCounter + 1;
        this.id = Long.valueOf(idCounter);
        this.title = title;
        this.artists = artists;
        this.location = location;
        this.openSource = openSource;
        this.theme = "";
    }

    public MusicProposition(String theme, String title, List<String> artists, String location, Boolean openSource) {
        MusicProposition.idCounter = idCounter + 1;
        this.id = Long.valueOf(idCounter);
        this.theme = theme;
        this.title = title;
        this.artists = artists;
        this.location = location;
        this.openSource = openSource;
    }

    public MusicProposition(Long id, String theme, String title, List<String> artists, String location,
            Boolean openSource) {
        this.id = id;
        this.theme = theme;
        this.title = title;
        this.artists = artists;
        this.location = location;
        this.openSource = openSource;
    }

    public MusicProposition(MusicProposition prop) {
        MusicProposition.idCounter = idCounter + 1;
        this.id = Long.valueOf(idCounter);
        this.theme = prop.getTheme();
        this.title = prop.getTitle();
        this.artists = prop.getArtists();
        this.location = prop.getLocation();
        this.openSource = prop.getOpenSource();
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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

    @Override
    public String toString() {
        return "MusicProposition [id=" + id + ", theme=" + theme + ", title=" + title + ", artists=" + artists
                + ", location=" + location + ", openSource=" + openSource + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((theme == null) ? 0 : theme.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((artists == null) ? 0 : artists.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((openSource == null) ? 0 : openSource.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MusicProposition other = (MusicProposition) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (theme == null) {
            if (other.theme != null)
                return false;
        } else if (!theme.equals(other.theme))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (artists == null) {
            if (other.artists != null)
                return false;
        } else if (!artists.equals(other.artists))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (openSource == null) {
            if (other.openSource != null)
                return false;
        } else if (!openSource.equals(other.openSource))
            return false;
        return true;
    }

}