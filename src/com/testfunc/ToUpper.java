package com.testfunc;

// the question tested here is how much faster can lower->upper case be made
// without the use of branches, while still leaving already uppercase characters
// unchanged.

public class ToUpper {
	
	public static final char lowerUpperGap = 'a' - 'A';
	
	public static char Basic(char data) {
		if(data >= 'a' && data <= 'z')
			data -= lowerUpperGap;
		return data;
	}
	
	// This will not work with any symbols greater than 'Z'
	// however this means common punctuation will work
	public static char Branchless(char data) {
		// gets the sign bit of 'Z' - data
		// if data is greater than 'Z' then it will return -1, otherwise 0
		return (char)(data + (lowerUpperGap * (('Z' - data) >> 15)));
	}
	
	public static char Native(char data) {
		return Character.toUpperCase(data);
	}

}
