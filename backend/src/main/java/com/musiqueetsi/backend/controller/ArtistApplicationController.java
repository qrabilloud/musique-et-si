package com.musiqueetsi.backend.controller;

import java.io.IOException;

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

import com.musiqueetsi.backend.model.ArtistApplication;
import com.musiqueetsi.backend.service.ArtistApplicationService;
import com.musiqueetsi.backend.service.LoginService;

@RestController
@RequestMapping("/api")
public class ArtistApplicationController {

    @Autowired
    private ArtistApplicationService artistApplicationService;

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/{user}/artistApplication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllArtistApplication(@PathVariable String user) throws IOException {
        System.out.println("Here");
        if (!loginService.isAdmin(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User don't have the right");
        } else {
            return ResponseEntity.ok(artistApplicationService.getAllArtistApplication());
        }
    }

    @PutMapping(value = "/artistApplication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addArtistApplication(@RequestBody ArtistApplication application) {
        System.out.println("Dans endpoint");
        System.out.println(application.toString());
        try {
            ArtistApplication trueApplication = new ArtistApplication(application);
            artistApplicationService.addArtistApplication(trueApplication);
            return ResponseEntity.ok("Application have been send");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    public static class Id {

        private Long id;

        public Id() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

    }

    @PutMapping(value = "/{user}/artistApplication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addArtistApplication(@PathVariable String user,
            @RequestBody Id idApplication) {
        if (!loginService.isAdmin(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User don't have the right");
        } else {
            try {
                Boolean b = artistApplicationService.deleteArtistApplication(idApplication.getId());
                if (b) {
                    return ResponseEntity.ok("Application have been deleted");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Application not found.");
                }
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
            }
        }

    }

}
