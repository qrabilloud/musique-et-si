package com.musiqueetsi.backend.model;

import java.time.LocalDate;
import java.util.List;

public class ThemeForum {

    private static int idCounter = 0;

    public Long id;

    public String theme;

    public LocalDate startDate;

    public LocalDate endDate;

    public List<Comment> comments;

    public ThemeForum() {
    }

    public ThemeForum(String theme, LocalDate startDate, LocalDate endDate, List<Comment> comments) {
        ThemeForum.idCounter = idCounter + 1;
        this.id = Long.valueOf(idCounter);
        this.theme = theme;
        this.startDate = startDate;
        this.endDate = endDate;
        this.comments = comments;
    }

    public ThemeForum(Long id, String theme, LocalDate startDate, LocalDate endDate, List<Comment> comments) {
        this.id = id;
        this.theme = theme;
        this.startDate = startDate;
        this.endDate = endDate;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
