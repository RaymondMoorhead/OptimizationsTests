package com.testfunc;

// this encompasses a series of tests involving mathematical
// calculations such as multiplication, square roots, etc.

public class Algorithms {
	
	public static int doubleBasic(int a) {
		return a * 2;
	}
	
	public static int doubleShift(int a) {
		return a << 1;
	}
	
	public static int halveBasic(int a) {
		return a / 2;
	}
	
	public static int halveShift(int a) {
		return a >> 1;
	}

	public static double sqrtNative(int a) {
		return Math.sqrt(a);
	}
	
	public static double sqrtApproximated(int a) {
		// bit manipulation
		double sqrt = Double.longBitsToDouble( ( ( Double.doubleToLongBits( a )-(1l<<52) )>>1 ) + ( 1l<<61 ) );
		
		// apply newton's method for further accuracy
		// this can be done multiple times
		 sqrt = (sqrt + (double)a / sqrt) * 0.5;
		
		return sqrt;
	}
	
	public static int sum1ToNBasic(int n) {
		int sum = 0;
		for(int i = 1; i < n; ++i)
			sum += i;
		return sum;
	}
	
	public static int sum1ToN1Op(int n) {
		return n * (1 + n) / 2;
	}
}
