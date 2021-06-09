package com.example.demo.model;

import javax.validation.constraints.Size;

public class StudentModel {
    private int id;
    
    @Size(max = 240)
    private String name;

    private int gender;

    public StudentModel(int id, String name, int gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

}
