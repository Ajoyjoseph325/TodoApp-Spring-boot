package com.example.todo.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Todoentity {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;
    private String taskName;
    private boolean status;
    @ManyToOne
    @JoinColumn(name="userid",referencedColumnName = "id")
    private Users user;





    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;

    }
    public String getTaskName() {
        return this.taskName;
    }
    public void setTaskName(String t){
        this.taskName = t;

    }
    public boolean getStatus() {
        return this.status;
    }
    public void setStatus(Boolean s){
        this.status = s;

    }


    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    

    





    


    
}
