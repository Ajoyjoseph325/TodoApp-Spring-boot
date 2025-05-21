package com.example.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.service.Todoservice;
import com.example.todo.entity.Todoentity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class Todocontroller {
    @Autowired
    Todoservice todoservice;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

   @GetMapping("/alltasks")
   public List<Todoentity> getAll(){
    return todoservice.getAll();

   }
   @PostMapping("/addtask")
  public Todoentity addtask(@RequestBody Todoentity todo){
    return todoservice.Savetask(todo);

  }
   @PatchMapping("/patch/{id}")
   public Todoentity patchdata(@RequestBody Todoentity todo,@PathVariable int id){
    todo.setId(id);
    return todoservice.patchdata(todo);
   }


  public ResponseEntity<?> deleteTask(@PathVariable int id) {
    try {
        todoservice.Deletetask(id);
        return ResponseEntity.ok("Todo deleted successfully");
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
    
    
    
}
