package com.musiqueetsi.backend.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musiqueetsi.backend.model.User;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class JsonFileUtil {
    private static final String FILE_PATH = "src/main/resources/users.json";

    public List<User> readUsers() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(FILE_PATH), new TypeReference<List<User>>() {});
    }
}
