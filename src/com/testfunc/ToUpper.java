package com.testfunc;

public class ToUpper {
	
	public static char Basic(char data) {
		if(data >= 'a' && data <= 'z')
			data += 'A' - 'a';
		return data;
	}
	
	public static char Branchless(char data) {
		// gets the sign bit of data - 'A', which determines if
		// data is less than 
		return (char)(data - (32 * ((data - 'A') >> 31)));
	}

}
