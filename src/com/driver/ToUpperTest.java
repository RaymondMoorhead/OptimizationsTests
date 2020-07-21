package com.driver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.support.TestTimer;
import com.testfunc.ToUpper;

class ToUpperTest {
	
    private StringBuilder[] words;
    private final int samples = 1000000;
    private int wordLength = 10;
    private TestTimer timer = new TestTimer();

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
        timer.startClock();
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
    	timer.stopClock(testInfo);
    	words = null;
    }
    
	@Test
	void testBasicFunctionality() {
		
		for(char i = 'a'; i < 'z'; ++i) {
			assertEquals(i - ToUpper.lowerUpperGap, ToUpper.Basic(i));
			assertEquals(i - ToUpper.lowerUpperGap, ToUpper.Basic((char)(i - ToUpper.lowerUpperGap)));
		}
	}
	
	@Test
	void testBranchlessFunctionality() {
		for(char i = 'a'; i < 'z'; ++i) {
			assertEquals(i - ToUpper.lowerUpperGap, ToUpper.Branchless(i));
			assertEquals(i - ToUpper.lowerUpperGap, ToUpper.Branchless((char)(i - ToUpper.lowerUpperGap)));
		}
	}
	
	@Test
	void testNativeSpeed() {
		for(int i = 0; i < words.length; ++i) {
			for(int j = 0; j < words[i].length(); ++j)
				words[i].setCharAt(j, ToUpper.Native(words[i].charAt(j)));
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
