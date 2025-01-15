package com.musiqueetsi.backend.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musiqueetsi.backend.model.Comment;
import com.musiqueetsi.backend.model.ThemeForum;
import com.musiqueetsi.backend.model.User;
import com.musiqueetsi.backend.util.JsonFileUtil;

@Service
public class ThemeForumService {

    @Autowired
    private JsonFileUtil jsonFileUtil;

    @Autowired
    private LoginService loginService;

    public List<ThemeForum> getAllThemeForum() throws IOException {
        return jsonFileUtil.readThemeForum();
    }

    public List<ThemeForum> getThemeForumByTheme(String theme) throws IOException {
        List<ThemeForum> forums = this.getAllThemeForum();
        List<ThemeForum> filteredForums = forums.stream().filter(f -> f.getTheme().equals(theme))
                .collect(Collectors.toList());
        return filteredForums;
    }

    public List<String> getThemeOfForums() throws IOException {
        return this.getAllThemeForum().stream().map(f -> f.getTheme()).collect(Collectors.toList());
    }

    public void addThemeForum(String theme, LocalDate startDate, LocalDate endDate) throws IOException {
        ThemeForum newThemeForum = new ThemeForum(theme, startDate, endDate);
        List<ThemeForum> list = this.getAllThemeForum();
        list.add(newThemeForum);
        jsonFileUtil.writeThemeForum(list);
    }

    public boolean addComment(String theme, String username, String body) throws IOException {
        Optional<User> oUser = loginService.getUser(username);
        if (oUser.isEmpty()) {
            return false;
        } else {
            User user = oUser.get();
            List<ThemeForum> forums = this.getThemeForumByTheme(theme);
            Optional<ThemeForum> forum = forums.stream().filter(f -> f.getTheme().equals(theme)).findFirst();
            if (forum.isEmpty()) {
                return false;
            } else {
                forums.remove(forum.get());
                List<Comment> comments = forum.get().getComments();
                comments.add(new Comment(user, body));
                forum.get().setComments(comments);
                forums.add(forum.get());
                jsonFileUtil.writeThemeForum(forums);
                return true;
            }
        }

    }

}
