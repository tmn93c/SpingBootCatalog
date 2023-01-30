package com.example.demo.util;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Maphelper {

    private Maphelper() {}

    private static ObjectMapper mapper = null;

    public static Map<String, Object> convertObject(Object obj) {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }

        return mapper.convertValue(obj, new TypeReference<Map<String, Object>>() {});
    }

}