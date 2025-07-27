package com.expressivemodel.modelprogresstracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expressivemodel.modelprogresstracker.model.User;
import com.expressivemodel.modelprogresstracker.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }
}