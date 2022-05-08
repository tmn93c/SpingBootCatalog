package com.example.demo.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter @Builder
@AllArgsConstructor
public class Person2 {
    private int id;
    private String name;
    private int age;
    private LocalDate birthday;
}
