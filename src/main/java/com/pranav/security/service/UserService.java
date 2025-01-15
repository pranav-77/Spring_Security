package com.pranav.security.service;

import com.pranav.security.model.Users;
import com.pranav.security.repository.UserRepository;
import com.pranav.security.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Users add(Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        return repository.save(users);
    }

    public String verify(Users users) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
        System.out.println(users);

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(users.getUsername());
        }
        return "Fail";
    }

    public List<Users> getAllUsers() {
        return repository.findAll();
    }
}
