package com.example.demo.util;

import com.example.demo.model.UserModel;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class HashSetWithModelMapToString {
    public static void main(String[] args) {
        Set<UserModel> hashset = new HashSet<>();
        hashset.add(new UserModel("test","test","test","test",true));
        hashset.add(new UserModel("test","test","test","test",true));
        String test = hashset.stream().map(UserModel::getName).collect(joining(","));
        System.out.println(test);
    }
}
