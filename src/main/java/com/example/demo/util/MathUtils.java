package com.example.demo.util;

public class MathUtils {
    public static double average(long a, long b) {
        return (a + b) / new Long(2);
    }

    public static void main(String[] args) {
        System.out.println(average(2,1));
    }
}
