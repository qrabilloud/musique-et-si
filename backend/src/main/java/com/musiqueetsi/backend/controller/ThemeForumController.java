package com.musiqueetsi.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musiqueetsi.backend.model.Comment;
import com.musiqueetsi.backend.model.ThemeForum;
import com.musiqueetsi.backend.service.ThemeForumService;

@RestController
@RequestMapping("/api/ThemeForum")
public class ThemeForumController {

    @Autowired
    private ThemeForumService themeForumService;

    @GetMapping(value = "/themes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getThemes() {
        try {
            return ResponseEntity.ok(themeForumService.getThemeOfForums());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }

    @GetMapping(value = "/{theme}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getForumByTheme(@PathVariable String theme) {
        try {
            List<ThemeForum> forums = themeForumService.getThemeForumByTheme(theme);
            if (forums.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No forum with this theme");
            } else {
                return ResponseEntity.ok(forums.get(0));
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }

    @PutMapping(value = "/{theme}/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCommentaire(@PathVariable String theme, @PathVariable String username,
            @RequestBody Comment comment) {
        try {
            boolean b = themeForumService.addComment(theme, username, comment.getBody());
            if (b) {
                return ResponseEntity.ok("Comment added");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No forum for this theme or the user doesn't exist");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }

}
