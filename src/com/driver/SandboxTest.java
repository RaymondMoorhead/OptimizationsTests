package com.driver;

public class SandboxTest {

	public static void main(String[] args) {
		printBitShiftSamples();
	}

	
	public static void printBitShiftSamples() {
		// prints 0
		System.out.println("positive integer by 31:");
		System.out.println(33 >> 31);
		System.out.println(123231232 >> 31);
		
		// prints -1
		System.out.println("\nnegative integer by 31:");
		System.out.println(-21 >> 31);
		System.out.println(-2112323123 >> 31);
		
		// print 0
		System.out.println("\npositive character by 15:");
		System.out.println('a' >> 15);
		System.out.println('a' - 'A' >> 15);
		
		// prints -1
		System.out.println("\nnegative character by 15:");
		System.out.println('A' - 'a' >> 15);
		System.out.println('A' - 'C' >> 15);
	}
}
