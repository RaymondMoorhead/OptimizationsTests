package com.driver;

public class SandboxTest {

	public static void main(String[] args) {
		System.out.println(33);
		System.out.println(33 >> 31);
		System.out.println(123231232 >> 31);
		
		System.out.println(-21);
		System.out.println(-21 >> 31);
		System.out.println(-2112323123 >> 31);
		
		System.out.println('a');
		System.out.println('a' >> 15);
		System.out.println('a' >> 31);
		System.out.println('a' + 1 >> 31);
		System.out.println('a' - 'A' >> 31);
		System.out.println('A' - 'A' >> 31);
		System.out.println('C' - 'A' >> 31);
		System.out.println('a' - 'A' >> 15);
		System.out.println('A' - 'A' >> 15);
		System.out.println('C' - 'A' >> 15);
	}

}
