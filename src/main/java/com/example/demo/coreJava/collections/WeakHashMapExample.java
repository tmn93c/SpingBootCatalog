package com.example.demo.coreJava.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

public class WeakHashMapExample {

	public static void main(String[] args) {
		Map<Number, String> weak = new WeakHashMap<Number, String>();
		weak.put(1, "A");
		weak.put(2, "B");
		weak.put(3, "C");
		
		Set set1 = weak.entrySet();
		System.out.println("Set: "+set1);
		
		// Creating set for key
		Set keySet = weak.keySet();
		System.out.println("Key Set: " + keySet);
		
		Collection value  = weak.values();
		System.out.println("Map Values: " + value);

		Map<Integer, Object> map = new HashMap<>();
        map.put(1, "foo");
        map.put(2, "bar");
        map.put(3, "baz");
        String result = map.entrySet()
            .stream()
            .map(entry -> entry.getKey() + " - " + entry.getValue())
            .collect(Collectors.joining(", "));
        System.out.println(result);
	}
}
