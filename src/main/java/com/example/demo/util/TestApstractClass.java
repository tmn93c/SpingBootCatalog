package com.example.demo.util;

public abstract class TestApstractClass {

    public boolean isSupportOperator() {
        return this.isSupportOperator(null);
    }

    public boolean isSupportOperator(String operator) {
        String operators = "(>|=|<|<=|>=)";
        return operator.toLowerCase().matches(operators);
    }
}
