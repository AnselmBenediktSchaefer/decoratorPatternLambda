package org.anselm;

import java.util.*;
import java.util.function.*;

public class DecoratorPattern {
	//Problem with DecoratorPattern -> looks like
	//new BufferedInputStream(new DataInputStream(new ...)))
	// --> really heavyweight
	
	public static void doWork(int value, Function<Integer, Integer> func) {
		System.out.println(func.apply(value));
	}
	
	public static void main(String[] args) {
		Function<Integer, Integer> inc = e -> e + 1;
		Function<Integer, Integer> doubleIt = e -> e * 2;
		
		doWork(5, inc);
		doWork(5, doubleIt);
		
		doWork(5, inc.andThen(doubleIt));
		
	}
}
