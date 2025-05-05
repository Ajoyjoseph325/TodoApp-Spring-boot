package com.example.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.repository.Todorepository;
import com.example.todo.entity.Todoentity;

@Service
public class Todoservice {

    @Autowired
    Todorepository todorepo;
    public  List<Todoentity> getAll(){
        return todorepo.findAll();

    }    
    public Todoentity Savetask(Todoentity todo){
        return todorepo.save(todo);

    }
}
