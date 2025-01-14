package com.musiqueetsi.backend.controller;

import com.musiqueetsi.backend.model.User;
import com.musiqueetsi.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        boolean isAuthenticated = loginService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
            return "Login successful!";
        } else {
            return "Invalid username or password.";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody User newUser) {
        return loginService.register(newUser);
    }
}
