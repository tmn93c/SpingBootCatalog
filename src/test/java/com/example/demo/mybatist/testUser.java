package com.example.demo.mybatist;

import java.util.List;

import com.example.demo.model.UserModel;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.util.ConnectionManager;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class testUser {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ConnectionManager.class);
        CustomUserDetailsService studentService = (CustomUserDetailsService) context.getBean("CustomUserDetailsService");
        List<UserModel> listUser = studentService.getAllActiveUserInfo();
        System.out.println("select all : ");
        for (UserModel student : listUser) {
            System.out.println(student);
        }
    }
}
