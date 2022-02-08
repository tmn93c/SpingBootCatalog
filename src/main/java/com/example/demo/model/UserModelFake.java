package com.example.demo.model;

import javax.persistence.Tuple;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class UserModelFake {

    private Long id;
    private String name;
    private String username;


    public UserModelFake(Long id, String name,String username){
        this.id = id;
        this.name = name;
        this.username = username;
    }


    

}

    