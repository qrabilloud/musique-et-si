package com.musiqueetsi.backend.service;

import com.musiqueetsi.backend.model.User;
import com.musiqueetsi.backend.util.JsonFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private JsonFileUtil jsonFileUtil;

    public boolean authenticate(String username, String password) {
        try {
            List<User> users = jsonFileUtil.readUsers();

            // Check if the username and password match
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Login failed
    }
}
