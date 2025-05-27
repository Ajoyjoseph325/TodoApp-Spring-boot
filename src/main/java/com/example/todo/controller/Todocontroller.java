package com.example.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import com.example.todo.service.Todoservice;
import com.example.todo.entity.Todoentity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.servlet.http.HttpServletRequest;



@RestController
@CrossOrigin
public class Todocontroller {
    @Autowired
    Todoservice todoservice;

    private String secret = "4qQpxkDmZa0yJ2JH3P+uwzY6gGZJZVjyk6E3hZG2zNOxlT1quQkk9XK2oKPhT7Ys8FX6epwRPmZK25r+bA8vXA==";

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

   @GetMapping("/alltasks")
   public List<Todoentity> getAll(HttpServletRequest request){

    String authHeader = request.getHeader("Authorization");
    String token = authHeader.substring(7); // Remove "Bearer "
    
    Claims claims = Jwts.parser()
            .setSigningKey(secret) // same key used to sign token
            .parseClaimsJws(token)
            .getBody();
        //     Long userId = (Long) claims.get("userId");
        //     System.out.println(userId);
        Number userIdNum = (Number) claims.get("userId");
        Long userId = userIdNum.longValue();


    return todoservice.getAll(userId);

   }
   @PostMapping("/addtask")
  public Todoentity addtask(@RequestBody Todoentity todo,HttpServletRequest request){

    String authHeader = request.getHeader("Authorization");
    String token = authHeader.substring(7); // Remove "Bearer "
    
    Claims claims = Jwts.parser()
            .setSigningKey(secret) // same key used to sign token
            .parseClaimsJws(token)
            .getBody();
    
//     Long userId = (Long) claims.get("userId");
//     System.out.println(userId);

        Number userIdNum = (Number) claims.get("userId");
        Long userId = userIdNum.longValue();










    
    return todoservice.Savetask(todo,userId);

  }

   @PatchMapping("/patch/{id}")
   public Todoentity patchdata(@RequestBody Todoentity todo,@PathVariable int id,HttpServletRequest request){
    String authHeader = request.getHeader("Authorization");
    String token = authHeader.substring(7); // Remove "Bearer "
    
    Claims claims = Jwts.parser()
            .setSigningKey(secret) // same key used to sign token
            .parseClaimsJws(token)
            .getBody();
    
//     Long userId = (Long) claims.get("userId");
//     System.out.println(userId);

Number userIdNum = (Number) claims.get("userId");
Long userId = userIdNum.longValue();

    todo.setId(id);
    return todoservice.patchdata(todo,userId);
   }



   @GetMapping("/todo/{id}")
   public Todoentity getMethodName(@PathVariable int id ,HttpServletRequest request){
    String authHeader = request.getHeader("Authorization");
    String token = authHeader.substring(7); // Remove "Bearer "
    
    Claims claims = Jwts.parser()
            .setSigningKey(secret) // same key used to sign token
            .parseClaimsJws(token)
            .getBody();
    
//     Integer userId = (Integer) claims.get("userId");
//     System.out.println(userId);
        Number userIdNum = (Number) claims.get("userId");
        Long userId = userIdNum.longValue();
       return todoservice.getTodo(id,userId);
   }
   

@DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteTask(@PathVariable int id,HttpServletRequest request) {
    try {

        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7); // Remove "Bearer "
        
        Claims claims = Jwts.parser()
                .setSigningKey(secret) // same key used to sign token
                .parseClaimsJws(token)
                .getBody();
        
                // Long userId = (Long) claims.get("userId");
                Number userIdNum = (Number) claims.get("userId");
                Long userId = userIdNum.longValue();
       

        todoservice.Deletetask(id,userId);


        return ResponseEntity.ok("Todo deleted successfully");
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
    
    
    
}
