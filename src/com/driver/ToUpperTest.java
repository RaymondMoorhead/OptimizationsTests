package com.driver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.testfunc.ToUpper;

class ToUpperTest {
	
    private StringBuilder[] words;
    private int samples = 1000000;
    private int wordLength = 10;
    private long startTime;

    @BeforeEach
    public void setUp() {
    	words = new StringBuilder[samples];
    	Random rand = new Random(0);
    	int letterRange = (int)'z' - (int)'a';
    	
        for(int i = 0; i < samples; ++i) {
        	words[i] = new StringBuilder(wordLength);
        	words[i].setLength(wordLength);
        	for(int j = 0; j < wordLength; ++j)
        		// pick a letter a-z, then pick lower or upper case
        		words[i].setCharAt(j, (char)(rand.nextInt(letterRange) + (int)(rand.nextBoolean() ? 'a' : 'A')));
        }
        startTime = System.currentTimeMillis();
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
    	startTime = System.currentTimeMillis() - startTime;
    	System.out.println("Test " + testInfo.getDisplayName() + " has run in " + startTime + " milliseconds");
    	words = null;
    }
    
	@Test
	@DisplayName("testBasicFunctionality")
	void testBasicFunctionality() {
		
		for(char i = 'a'; i < 'z'; ++i) {
			assertEquals(i - ToUpper.lowerUpperGap, ToUpper.Basic(i));
			assertEquals(i - ToUpper.lowerUpperGap, ToUpper.Basic((char)(i - ToUpper.lowerUpperGap)));
		}
	}
	
	@Test
	@DisplayName("testBranchlessFunctionality")
	void testBranchlessFunctionality() {
		for(char i = 'a'; i < 'z'; ++i) {
			assertEquals(i - ToUpper.lowerUpperGap, ToUpper.Branchless(i));
			assertEquals(i - ToUpper.lowerUpperGap, ToUpper.Branchless((char)(i - ToUpper.lowerUpperGap)));
		}
	}

	@Test
	@DisplayName("testBasicSpeed")
	void testBasicSpeed() {
		for(int i = 0; i < words.length; ++i) {
			for(int j = 0; j < words[i].length(); ++j)
				words[i].setCharAt(j, ToUpper.Basic(words[i].charAt(j)));
		}
	}
	
	@Test
	@DisplayName("testBranchlessSpeed")
	void testBranchlessSpeed() {
		for(int i = 0; i < words.length; ++i) {
			for(int j = 0; j < words[i].length(); ++j)
				words[i].setCharAt(j, ToUpper.Branchless(words[i].charAt(j)));
		}
	}

}
