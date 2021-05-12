package com.example.demo.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Tips {
        public static <T, F> List<F> pluck(String fieldName, Class<F> fieldType, 
            List<T> list, Class<T> listType)
            throws NoSuchFieldException, IllegalAccessException {
        Field f = listType.getField(fieldName);
        ArrayList<F> result = new ArrayList<F>();
        for (T element : list) {
            result.add(fieldType.cast(f.get(element)));
        }
        return result;
    }

    
}