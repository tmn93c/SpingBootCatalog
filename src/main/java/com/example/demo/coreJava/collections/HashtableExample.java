package com.example.demo.coreJava.collections;

import java.util.*;

public class HashtableExample {

	public static void main(String[] args) {
		Map<Integer, String> h = new Hashtable<>();
		
		h.put(10, "One");
		h.put(20, "Two");
		h.put(30, "Three");
		h.put(null, "30");
		h.putIfAbsent(100, "Ten");
		
		
		System.out.println("Hashtable: "+ h);

	}

}
