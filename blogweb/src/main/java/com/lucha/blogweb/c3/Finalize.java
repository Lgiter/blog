package com.lucha.blogweb.c3;

public class Finalize {
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("finalize ...");
	}
	
	public static void main(String[] args) {
		Finalize f = new Finalize();
		f = null;
		System.gc();
	}

}
