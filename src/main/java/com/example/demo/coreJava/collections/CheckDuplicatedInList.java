package com.example.demo.coreJava.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
    private String firstName;
    private String lastName;
    private int age;
    private String city;
    private String country;
}

public class CheckDuplicatedInList {
    public static void main(String[] args) {
        Person[] persons = {
                new Person("John", "Doe", 25, "New York", "USA"),
                new Person("Jane", "Smith", 30, "London", "UK"),
                new Person("John", "Doe", 250, "Paris", "France"), // Duplicate
                new Person("Alice", "Johnson", 35, "Sydney", "Australia")
        };

        boolean hasDuplicates = containsDuplicates(persons);
        if (hasDuplicates) {
            System.out.println("The array contains duplicate elements.");
        } else {
            System.out.println("The array does not contain duplicate elements.");
        }
    }


    private static boolean containsDuplicates(Person[] persons) {
        Set<String> uniqueNames = new HashSet<>();
        for (Person person : persons) {
            String fullName = person.getFirstName() + person.getLastName() + person.getAge();
            if (!uniqueNames.add(fullName)) {
                return true;
            }
        }
        return false;
    }
}
