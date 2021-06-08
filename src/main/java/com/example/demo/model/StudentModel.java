package com.example.demo.model;

import javax.validation.constraints.Size;

public class StudentModel {
    private int order;
    
    @Size(max = 240)
    private String username;

    private int age;

    public StudentModel(int order, String username, int age) {
        this.order = order;
        this.username = username;
        this.age = age;
    }

}
