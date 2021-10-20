package com.example.demo.util;

public class CheckString {

    public static boolean isSupportOperator(String operator) {
        String operators = "(>|=|<|<=|>=)";
        return operator.toLowerCase().matches(operators);
    }

}
