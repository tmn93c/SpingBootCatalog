package com.example.demo.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class UserModelFake {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private LocalDate createAt;

    public UserModelFake(Long id, String name,String username,String email,String password,LocalDate createAt){
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = name;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

    