package com.example.demo.util;

public interface AppConstants {
    String DEFAULT_PAGE_NUMBER = "0";
    String DEFAULT_PAGE_SIZE = "30";

    int MAX_PAGE_SIZE = 50;

    public enum SearchOperation {
        GREATER_THAN,
        LESS_THAN,
        GREATER_THAN_EQUAL,
        LESS_THAN_EQUAL,
        NOT_EQUAL,
        EQUAL,
        MATCH,
        MATCH_START,
        MATCH_END,
        IN,
        NOT_IN    }
}
