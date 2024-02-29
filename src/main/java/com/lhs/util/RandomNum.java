package com.lhs.util;

public class RandomNum {
	
	public static String randum() {
		String result =  "";
		result += (int)(Math.random()*9)+1;
		result += (int)(Math.random()*9)+1;
		result += (int)(Math.random()*9)+1;
		result += (int)(Math.random()*9)+1;
		result += (int)(Math.random()*9)+1;

		return result;
	}

}
