package com.example.demo.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
@AllArgsConstructor
public class Person {
    private int id;
    private String name;
    private int age;
}
