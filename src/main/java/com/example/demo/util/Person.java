package com.example.demo.util;

public class Person {
    private int id;
    private String name;
    private int age;

    public Person(int id , String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }
}
