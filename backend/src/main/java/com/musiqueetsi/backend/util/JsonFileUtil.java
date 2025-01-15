package com.musiqueetsi.backend.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musiqueetsi.backend.model.ArtistApplication;
import com.musiqueetsi.backend.model.MusicProposition;
import com.musiqueetsi.backend.model.User;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonFileUtil {
    private static final String FILE_PATH = "backend/src/main/resources/users.json";

    private static final String ARTIST_APPLICATION_PATH = "backend/src/main/resources/ArtistApplication.json";

    private static final String MUSIC_PROPOSITION_PATH = "backend/src/main/resources/MusicProposition.json";

    private final ObjectMapper mapper = new ObjectMapper();

    // Read all users from the JSON file
    public List<User> readUsers() throws IOException {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return new ArrayList<>(); // Return an empty list if the file doesn't exist
        }

        return mapper.readValue(file, new TypeReference<List<User>>() {
        });
    }

    // Write a list of users to the JSON file
    public void writeUsers(List<User> users) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), users);
    }

    // Read all Artist applications from the JSON file
    public List<ArtistApplication> readArtistApplication() throws IOException {
        File file = new File(ARTIST_APPLICATION_PATH);

        if (!file.exists()) {
            return new ArrayList<>(); // Return an empty list if the file doesn't exist
        }

        return mapper.readValue(file, new TypeReference<List<ArtistApplication>>() {
        });
    }

    // Write a Artist applications in the JSON file
    public void writeArtistApplication(List<ArtistApplication> applications) throws IOException {
        System.out.println("Dans writeArtistApplication");
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARTIST_APPLICATION_PATH), applications);
        System.out.println("Fin de writeArtistApplication");
    }

    // Read all music propositions from the JSON file
    public List<MusicProposition> readMusicProposition() throws IOException {
        System.out.println("In readMusicProposition");
        File file = new File(MUSIC_PROPOSITION_PATH);

        System.out.println("Chemin absolu du fichier: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("File doesn't exist");
            return new ArrayList<>(); // Return an empty list if the file doesn't exist
        }

        try {
            return mapper.readValue(file, new TypeReference<List<MusicProposition>>() {
            });
        } catch (JsonProcessingException e) {
            System.out.println("Erreur de parsing JSON: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erreur d'accès au fichier: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // Write a Artist applications in the JSON file
    public void writeMusicProposition(List<MusicProposition> propositions) throws IOException {
        System.out.println("Dans writeArtistApplication");
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(MUSIC_PROPOSITION_PATH), propositions);
        System.out.println("Fin de writeArtistApplication");
    }
}
