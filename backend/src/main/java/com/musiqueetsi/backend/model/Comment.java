package com.musiqueetsi.backend.model;

import java.util.ArrayList;
import java.util.List;

public class Comment {

    public User author;

    public String body;

    public List<Comment> comments;

    public Comment() {
    }

    public Comment(User author, String body, List<Comment> comments) {
        this.author = author;
        this.body = body;
        this.comments = comments;
    }

    public Comment(User author, String body) {
        this.author = author;
        this.body = body;
        this.comments = new ArrayList<Comment>();
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
