package com.example.demo.mybatist;

import java.util.List;

import com.example.demo.model.UserModel;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.util.ConnectionManagerTest;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class testUser {

    @Test
    public void testUserBartist()
    {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ConnectionManagerTest.class);
        CustomUserDetailsService studentService = (CustomUserDetailsService) context
                .getBean("CustomUserDetailsService");
        List<UserModel> listUser = studentService.getAllActiveUserInfo();
        System.out.println("select all : ");
        for (UserModel student : listUser) {
            System.out.println(student);
        }
    }
}
