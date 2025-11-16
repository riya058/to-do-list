package com.example.myapp.controller;

import com.example.myapp.dto.JwtResponse;
import com.example.myapp.dto.LoginRequest;
import com.example.myapp.dto.RegisterRequest;
import com.example.myapp.model.UserModel;
import com.example.myapp.repository.UserRepository;
import com.example.myapp.security.JwtUtils;
import com.example.myapp.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authManager,
                          UserRepository userRepository,
                          BCryptPasswordEncoder passwordEncoder,
                          JwtUtils jwtUtils) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userRepository.existsByUsername(req.username()))
            return ResponseEntity.badRequest().body("Username already taken");
        UserModel u = new UserModel();
        u.setUsername(req.username());
        u.setPassword(passwordEncoder.encode(req.password()));
        userRepository.save(u);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password())
        );
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getId()));
    }
}
