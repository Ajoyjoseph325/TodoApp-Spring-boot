package com.example.todo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.entity.Todoentity;

@Repository



public interface Todorepository extends JpaRepository<Todoentity,Integer>{

    List<Todoentity> findByUserId(Long userId);
    Optional<Todoentity> findByIdAndUserId(int id, Long userId);

    
    
}
