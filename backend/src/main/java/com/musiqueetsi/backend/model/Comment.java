package com.musiqueetsi.backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Comment {

    private static int idCounter = 0;

    public Long id;

    public User author;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime date;

    public String body;

    // public List<Comment> comments;

    public Comment() {
    }

    public Comment(User author, String body) {
        Comment.idCounter = idCounter + 1;
        this.id = Long.valueOf(idCounter);
        this.author = author;
        this.body = body;
        this.date = LocalDateTime.now();
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
