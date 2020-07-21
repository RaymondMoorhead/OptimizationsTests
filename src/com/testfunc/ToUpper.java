package com.testfunc;

public class ToUpper {
	
	public static final char lowerUpperGap = 'a' - 'A';
	
	public static char Basic(char data) {
		if(data >= 'a' && data <= 'z')
			data -= lowerUpperGap;
		return data;
	}
	
	public static char Branchless(char data) {
		// gets the sign bit of 'Z' - data
		// if data is greater than 'Z' then it will return -1
		return (char)(data + (lowerUpperGap * (('Z' - data) >> 15)));
	}

}
