package com.driver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.testfunc.ToUpper;

class ToUpperTest {
	
    private StringBuilder[] words;
    private int samples = 10000;
    private int wordLength = 10;
    

    @Before
    public void setUp() {
    	words = new StringBuilder[samples];
    	Random rand = new Random(0);
    	int letterRange = (int)'z' - (int)'a';
    	
    	
        for(int i = 0; i < samples; ++i) {
        	words[i] = new StringBuilder(wordLength);
        	for(int j = 0; j < wordLength; ++j)
        		// pick a letter a-z, then pick lower or upper case
        		words[i].setCharAt(j, (char)(rand.nextInt(letterRange) + (int)(rand.nextBoolean() ? 'a' : 'A')));
        }
    }

    @After
    public void tearDown() {
    	words = null;
    }
    
	@Test
	void testBasicFunctionality() {
		
		for(char i = 'a'; i < 'z'; ++i) {
			assertEquals(i + 32, ToUpper.Basic(i));
			assertEquals(i, ToUpper.Basic((char)(i + 32)));
		}
	}
	
	@Test
	void testBranchlessFunctionality() {
		for(char i = 'a'; i < 'z'; ++i) {
			assertEquals(i + 32, ToUpper.Branchless(i));
			assertEquals(i, ToUpper.Branchless((char)(i + 32)));
		}
	}

	@Test
	void testBasicSpeed() {
		for(int i = 0; i < words.length; ++i) {
			for(int j = 0; j < words[i].length(); ++j)
				words[i].setCharAt(j, ToUpper.Basic(words[i].charAt(j)));
		}
	}
	
	@Test
	void testBranchlessSpeed() {
		for(int i = 0; i < words.length; ++i) {
			for(int j = 0; j < words[i].length(); ++j)
				words[i].setCharAt(j, ToUpper.Branchless(words[i].charAt(j)));
		}
	}

}
