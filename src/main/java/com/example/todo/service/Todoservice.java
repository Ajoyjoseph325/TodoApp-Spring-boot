package com.example.todo.service;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.repository.Todorepository;
import com.example.todo.repository.Userrepository;
import com.example.todo.entity.Todoentity;
import com.example.todo.entity.Users;

@Service
public class Todoservice {

    @Autowired
    Todorepository todorepo;

    @Autowired
    Userrepository userrepo;

    public  List<Todoentity> getAll(Long userId){

        return todorepo.findByUserId((long) userId);
        // return todorepo.findAll();

    }    
    public Todoentity Savetask(Todoentity todo,Long userId){
        Users user = userrepo.findById((long) userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        todo.setUser(user);
        
        return todorepo.save(todo);

    }


    public Todoentity patchdata(Todoentity todo,Long userId){

        long uid = userId;

        Users user = userrepo.findById((long) userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
       
        // Todoentity existing = todorepo.findById(todo.getId())
        // .orElseThrow(() -> new RuntimeException("Todo with ID " + todo.getId() + " not found"));

        Todoentity existing = todorepo.findByIdAndUserId(todo.getId(), uid).orElseThrow(() -> new RuntimeException("Todo not found or does not belong to user"));;


        if(todo.getTaskName()!=null){
            existing.setTaskName(todo.getTaskName());

          
        }
       
            existing.setStatus(todo.getStatus());
            
            existing.setUser(user);
       
        return todorepo.save(existing);

    }


    // Delete Task
    public void Deletetask(int id,Long userId){

     
        long uid = userId;
        
        // Todoentity existing = todorepo.findById(id).orElse(null);
              
        Todoentity todoOpt= todorepo.findByIdAndUserId(id, uid).orElseThrow(() -> new RuntimeException("Todo not found or does not belong to user"));;
        
        todorepo.delete(todoOpt);

    }
    public Todoentity getTodo(int id,Long userId){
        long uid = userId;
        // Todoentity existing = todorepo.findById(id).orElse(null);
        Todoentity todoOpt= todorepo.findByIdAndUserId(id, uid).orElseThrow(() -> new RuntimeException("Todo not found or does not belong to user"));;
       return todoOpt;
        

    }
}
