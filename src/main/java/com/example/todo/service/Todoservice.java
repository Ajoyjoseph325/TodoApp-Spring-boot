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
    public Todoentity patchdata(Todoentity todo){
       
        Todoentity existing = todorepo.findById(todo.getId())
        .orElseThrow(() -> new RuntimeException("Todo with ID " + todo.getId() + " not found"));
        if(todo.getTaskName()!=null){
            existing.setTaskName(todo.getTaskName());

          
        }
       
            existing.setStatus(todo.getStatus());
       
        return todorepo.save(existing);

    }
    public void Deletetask(int id){
        Todoentity existing = todorepo.findById(id).orElse(null);
        todorepo.delete(existing);

    }
}
