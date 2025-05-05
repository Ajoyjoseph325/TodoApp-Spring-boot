package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.entity.Todoentity;

@Repository



public interface Todorepository extends JpaRepository<Todoentity,Integer>{
    
    
}
