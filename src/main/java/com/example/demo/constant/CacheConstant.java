package com.example.demo.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CacheConstant {
    public static final String MODE_DISTRIBUTED = "distributed";
    public static final String MODE_LOCAL = "local";
    @UtilityClass
    public static final class Distributed {
        public static final String HAZELCAST_BEAN_NAME = "hazelcastInstance";
    }

    public class CacheNames {
        public static final String COUNTRY_NAMES = "countryNames";
    }
}
