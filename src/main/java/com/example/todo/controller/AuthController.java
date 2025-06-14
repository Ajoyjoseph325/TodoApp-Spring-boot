package com.example.todo.controller;

import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.todo.entity.Users;
import com.example.todo.repository.Userrepository;
import com.example.todo.security.Customuserdetails;
import com.example.todo.service.JwtUtil;



@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    Userrepository userrepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

  
    @PostMapping("/register")
    public String register(@RequestBody AuthRequest authRequest) {
        if (userrepo.findByUsername(authRequest.getUsername()).isPresent()) {
            return "User already exists";
        }

        Users newUser = new Users();
        newUser.setUsername(authRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        newUser.setRole("USER");

        userrepo.save(newUser);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        // final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        Customuserdetails userDetails = (Customuserdetails) userDetailsService.loadUserByUsername(authRequest.getUsername());
        String token =  jwtUtil.generateToken(userDetails.getUsername(),userDetails.getId());
        return ResponseEntity.ok(Collections.singletonMap("token", token));

    }
}

class AuthRequest {
    private String username;
    private String password;
   
    public AuthRequest() {}

    // Parameterized constructor
    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
