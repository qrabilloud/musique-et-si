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

import com.musiqueetsi.backend.model.MusicProposition;
import com.musiqueetsi.backend.service.LoginService;
import com.musiqueetsi.backend.service.MusicPropositionService;

@RestController
@RequestMapping("/api")
public class MusicPropositionController {

    @Autowired
    private MusicPropositionService musicPropositionService;

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/{user}/MusicProposition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMusicEntity(@PathVariable String user) {
        if (!loginService.isAdmin(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User don't have the right");
        } else {
            try {
                return ResponseEntity.ok(musicPropositionService.getMusicProposition());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(e);
            }
        }
    }

    @GetMapping(value = "/{user}/MusicProposition/{theme}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMusicEntityByTheme(@PathVariable String user, @PathVariable String theme) {
        if (!loginService.isAdmin(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User don't have the right");
        } else {
            try {
                return ResponseEntity.ok(musicPropositionService.getMusicPropositionByTheme(theme));
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(e);
            }
        }
    }

    @PutMapping(value = "/MusicProposition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMusicProposition(@RequestBody MusicProposition prop) {
        try {
            System.out.println("In endpoint");
            MusicProposition trueMP = new MusicProposition(prop);
            System.out.println(trueMP.toString());
            musicPropositionService.addMusicProposition(trueMP);
            return ResponseEntity.ok("Proposition have been send");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @PutMapping(value = "{user}/MusicProposition/{idMP}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteMusicProposition(@PathVariable String user, @PathVariable Long idMP) {
        if (!loginService.isAdmin(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User don't have the right");
        } else {
            try {
                Boolean b = musicPropositionService.deleteMusicPropsition(idMP);
                if (b) {
                    return ResponseEntity.ok("Proposition have been deleted");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Proposition not found.");
                }
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
            }
        }
    }

}
