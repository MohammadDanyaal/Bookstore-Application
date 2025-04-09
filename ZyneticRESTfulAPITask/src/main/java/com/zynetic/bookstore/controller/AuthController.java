package com.zynetic.bookstore.controller;

import com.zynetic.bookstore.dto.*;
import com.zynetic.bookstore.entity.User;
import com.zynetic.bookstore.repository.UserRepository;
import com.zynetic.bookstore.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest req) {
        User user = new User(null, req.getEmail(), encoder.encode(req.getPassword()));
        userRepo.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        return jwtUtils.generateToken(req.getEmail());
    }
}
