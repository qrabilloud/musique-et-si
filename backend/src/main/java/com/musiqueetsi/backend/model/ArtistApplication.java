package com.musiqueetsi.backend.model;

import java.util.List;

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

    public ArtistApplication(ArtistApplication application) {
        ArtistApplication.idCounter = idCounter + 1;
        this.id = Long.valueOf(idCounter);
        this.artists = application.getArtists();
        this.contact = application.getContact();
        this.location = application.getLocation();
        this.description = application.getDescription();
        this.nameExample = application.getNameExample();
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

    @Override
    public String toString() {
        return "ArtistApplication [id=" + id + ", artists=" + artists + ", contact=" + contact + ", location="
                + location + ", description=" + description + ", nameExample=" + nameExample + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((artists == null) ? 0 : artists.hashCode());
        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((nameExample == null) ? 0 : nameExample.hashCode());
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
        ArtistApplication other = (ArtistApplication) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (artists == null) {
            if (other.artists != null)
                return false;
        } else if (!artists.equals(other.artists))
            return false;
        if (contact == null) {
            if (other.contact != null)
                return false;
        } else if (!contact.equals(other.contact))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (nameExample == null) {
            if (other.nameExample != null)
                return false;
        } else if (!nameExample.equals(other.nameExample))
            return false;
        return true;
    }

}
