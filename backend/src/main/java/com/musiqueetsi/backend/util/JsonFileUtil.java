package com.musiqueetsi.backend.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musiqueetsi.backend.model.User;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonFileUtil {
    private static final String FILE_PATH = "backend/src/main/resources/users.json";

    private final ObjectMapper mapper = new ObjectMapper();

    // Read all users from the JSON file
    public List<User> readUsers() throws IOException {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return new ArrayList<>(); // Return an empty list if the file doesn't exist
        }

        return mapper.readValue(file, new TypeReference<List<User>>() {});
    }

    // Write a list of users to the JSON file
    public void writeUsers(List<User> users) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), users);
    }
}
