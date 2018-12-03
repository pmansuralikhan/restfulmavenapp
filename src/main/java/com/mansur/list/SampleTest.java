package com.mansur.list;

public class SampleTest {

	public static void main(String[] args) {
		for(int i = 0; i < 20; i++) {
			int index = (int) (Math.random() * 10) %  7 + 1;
			System.out.printf("%d ", index);
		}
	}
	
}
