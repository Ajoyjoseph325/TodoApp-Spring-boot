package com.example.todo.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Todoentity {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;
    private String taskName;
    private boolean status;



    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;

    }
    public String getTaskname() {
        return this.taskName;
    }
    public void setTaskname(String t){
        this.taskName = t;

    }
    public boolean getStatus() {
        return this.status;
    }
    public void setStatus(Boolean s){
        this.status = s;

    }
    

    





    


    
}
