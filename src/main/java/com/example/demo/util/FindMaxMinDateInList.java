package com.example.demo.util;


import java.time.LocalDate;
import java.util.*;

public class FindMaxMinDateInList {
    public static void main(String[] args) {
        // Replace with custom property
        List<Person2> persons = new ArrayList<>();
        for(int i = 1; i <= 20; i++ ){
            LocalDate date = LocalDate.of(2020, 1, 8).plusDays(i);
            Person2 t = new Person2(
                    i,
                    "name" + i,
                    19,
                    date
            );
            persons.add(t);
        }
        Comparator<Person2> personBithDayComparator = Comparator
                .comparing(Person2::getBirthday);
        Person2 maxDate = persons.stream().max(personBithDayComparator)
                .get();
        Person2 minDate = persons.stream().min(personBithDayComparator)
                .get();
    }

}
