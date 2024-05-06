package com.example.demo.model;

import java.math.BigInteger;

import jakarta.persistence.Tuple;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class UserModelFakeTransfrom {

    private Long id;
    private String name;
    private String username;


    public static UserModelFakeTransfrom from(Tuple t){
        return UserModelFakeTransfrom.builder()
                .id(((BigInteger)t.get(0)).longValue())
                .name(t.get(1,String.class))
                .username(t.get(2,String.class))
                .build();
    }

    

}

    