package com.pranav.security.controller;

import com.pranav.security.model.Users;
import com.pranav.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/register")
    public Users add(@RequestBody Users users) {
        return service.add(users);
    }

    @PostMapping("/login")
    public String verify(@RequestBody Users users) {
        return service.verify(users);
    }

    @GetMapping("/list")
    public List<Users> getAllUsers() {
        return service.getAllUsers();
    }
}
