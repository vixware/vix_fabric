package com.vix.test;

public class TestUpdateFirst {

	public static void main(String[] args) {
		String str = "hello";
		str = str.replaceFirst(str.substring(0, 1), "8");
		System.out.println(str);
	}
}
