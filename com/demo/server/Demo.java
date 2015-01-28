package com.demo.server;

public class Demo {

	public int[] add(int a, int b) {
		int[] sa = { a, b, a + b };
		return sa;
	}

	public Integer increase(Integer a) {
		return ++a;
	}

	public int sub(int a, int b) {
		return a - b;
	}
}
