package com.lucha.blogweb.c3;

public class Number {
	
	public static String str = "abc";
	
	public Number() {
		str = "qwe";
	}
	
	public static void main(String[] args) {
		System.out.println(Number.str);
		Number n = new Number();
		System.out.println(Number.str);
	}
}
