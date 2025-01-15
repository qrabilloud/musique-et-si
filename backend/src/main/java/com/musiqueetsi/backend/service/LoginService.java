package com.musiqueetsi.backend.service;

import com.musiqueetsi.backend.model.User;
import com.musiqueetsi.backend.util.JsonFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private JsonFileUtil jsonFileUtil;

    // Authenticate existing users
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

    // Register a new user
    public String register(User newUser) {
        try {
            List<User> users = jsonFileUtil.readUsers();

            // Check if the username already exists
            for (User user : users) {
                if (user.getUsername().equals(newUser.getUsername())) {
                    return "Username already exists.";
                }
            }

            // Add the new user to the list
            users.add(newUser);

            // Write the updated list back to the JSON file
            jsonFileUtil.writeUsers(users);

            return "User registered successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred during registration.";
        }
    }

    public Boolean isAdmin(String username) {
        try {
            List<User> users = jsonFileUtil.readUsers();

            // Check if the username and password match
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getAdmin()) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Optional<User> getUser(String username) {
        try {
            List<User> users = jsonFileUtil.readUsers();

            // Check if the username and password match
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return Optional.of(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
