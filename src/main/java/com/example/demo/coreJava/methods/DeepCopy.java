package com.example.demo.coreJava.methods;

/**
 * In the deep copy, we create a clone which is independent of original object and making changes in the cloned object should not affect original object.
 * So for deep copy we need to ensure all the member class also implement the Cloneable interface and override the clone() method of the object class.
 */
import java.util.ArrayList;

class DeepCopy1 {
	int x, y;
}

class DeepCopy2 implements Cloneable {
	int a, b;
	DeepCopy1 c = new DeepCopy1();
	public Object clone() throws CloneNotSupportedException {
		DeepCopy2 t = (DeepCopy2)super.clone();
		t.c = new DeepCopy1();
		
		return t;
	}
}

public class DeepCopy {

	public static void main(String[] args) throws CloneNotSupportedException {
		DeepCopy2 t1 = new DeepCopy2();
		t1.a = 10;
		t1.b = 20;
		t1.c.x = 30;
		t1.c.y = 40;
		
		DeepCopy2 t3 = (DeepCopy2)t1.clone();
		t3.a = 100;
		t3.c.x = 300;
		
		DeepCopy2 t4 = (DeepCopy2)t1.clone();
		t4.a = 200;
		t4.c.x = 400;
		System.out.println(t1.a + " " + t1.b + " " + t1.c.x + " " +t1.c.y);
		System.out.println(t3.a + " " + t3.b + " " + t3.c.x + " " + t3.c.y);
		System.out.println(t4.a + " " + t4.b + " " + t4.c.x + " " + t4.c.y);
	}
}
