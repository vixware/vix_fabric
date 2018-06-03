package com.vix.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TestList {

	public static void main(String[] args) {
		List<String> list = Arrays.asList(new String[] { "1", "232", "345", "32432" });
		List<String> list2 = Arrays.asList(new String[] { "A", "abc", "def", "ewrew", "hhh", "fff", "www" });
		for (int i = 0; i < list2.size(); i++) {
			for (int j = i; j <= i; j++) {
				if (j < list.size()) {
					System.out.println(list.get(j) + list2.get(j));
				}
			}
		}
		
		  List<String> modlist = new LinkedList<>();
	}
}
