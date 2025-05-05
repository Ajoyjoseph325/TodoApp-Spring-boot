package com.example.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

   @GetMapping("/all")
   public List<Todoentity> getAll(){
    return todoservice.getAll();

   }
   @PostMapping("/addtask")
  public void addtask(@RequestBody Todoentity todo){
    System.out.println(todo);

  }
   
    
    
    
}
