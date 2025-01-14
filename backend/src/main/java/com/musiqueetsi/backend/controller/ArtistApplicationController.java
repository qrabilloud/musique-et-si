package com.musiqueetsi.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musiqueetsi.backend.model.User;
import com.musiqueetsi.backend.service.ArtistApplicationService;
import com.musiqueetsi.backend.service.LoginService;
import com.musiqueetsi.backend.util.JsonFileUtil;

@RestController
@RequestMapping("/api")
public class ArtistApplicationController {

    @Autowired
    private ArtistApplicationService artistApplicationService;

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/{user}/artistApplication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllArtistApplication(@PathVariable String user) throws IOException {
        if (!loginService.isAdmin(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User don't have the right");
        } else {
            return ResponseEntity.ok(artistApplicationService.getAllArtistApplication());
        }
    }

}
