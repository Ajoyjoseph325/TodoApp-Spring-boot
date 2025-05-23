package com.example.todo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.todo.entity.Users;
import com.example.todo.repository.Userrepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private Userrepository userRepository; // Assume you have a UserRepository

    @Override
    public UserDetails loadUserByUsername(String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),user.getId(), new ArrayList<>()
        );
    }
}









