package com.example.todo.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.entity.Users;

import java.util.Optional;

public interface Userrepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
