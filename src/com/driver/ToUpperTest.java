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
		
		for(char i = 'a'; i <= 'z'; ++i) {
			assertEquals(i - ToUpper.lowerUpperGap, ToUpper.Basic(i));
			assertEquals(i - ToUpper.lowerUpperGap, ToUpper.Basic((char)(i - ToUpper.lowerUpperGap)));
		}
	}
	
	@Test
	void testBranchlessFunctionality() {
		for(char i = 'a'; i <= 'z'; ++i) {
			assertEquals(i - ToUpper.lowerUpperGap, ToUpper.Branchless(i));
			assertEquals(i - ToUpper.lowerUpperGap, ToUpper.Branchless((char)(i - ToUpper.lowerUpperGap)));
		}
	}
	
	// helper method
	private String upperWordBranchless(String data) {
		StringBuilder result = new StringBuilder(data.length());
		for(int j = 0; j < data.length(); ++j)
			result.append(ToUpper.Branchless(data.charAt(j)));
		return result.toString();
	}
	
	// helper method
	private String upperWordBranchlessCorrected(String data) {
		StringBuilder result = new StringBuilder(data.length());
		for(int j = 0; j < data.length(); ++j)
			result.append(ToUpper.BranchlessCorrected(data.charAt(j)));
		return result.toString();
	}
	
	// helper method
	private String upperWordNative(String data) {
		StringBuilder result = new StringBuilder(data.length());
		for(int j = 0; j < data.length(); ++j)
			result.append(ToUpper.Native(data.charAt(j)));
		return result.toString();
	}
	
	@Test
	void testBranchlessFunctionality2() {
		// there are known points of failure in the branchless toUpper method, but fortunately they
		// lie in uncommon punctuation marks such as tilda ('`') and pipe ('|'). More specifically,
		// any and all non-alphabetical characters after 'Z' on the ascii table
		
		String[] works = { "Hello, my name is George.",
							"The quick brown fox jumped over the lazy dog.",
							"In my town (Alswellington) we say, \"Greetings!\"",
							"myComparator = (a1, a2) -> return a1 > a2;",
							"1234567890" };
		
		String[] doesntWork = {	"{ return true; }",
								"echo Hello | test.txt",
								"~Object()" };
		
		for(String str : works)
			assertTrue(upperWordNative(str).equals(upperWordBranchless(str)));
		
		for(String str : doesntWork)
			assertFalse(upperWordNative(str).equals(upperWordBranchless(str)));
	}
	
	@Test
	void testBranchlessCorrectedFunctionality() {
		for(char i = 'a'; i < 'z'; ++i) {
			assertEquals(i - ToUpper.lowerUpperGap, ToUpper.BranchlessCorrected(i));
			assertEquals(i - ToUpper.lowerUpperGap, ToUpper.BranchlessCorrected((char)(i - ToUpper.lowerUpperGap)));
		}
	}
	
	@Test
	void testBranchlessCorrectedFunctionality2() {
		String[] samples = { "Hello, my name is George.",
							"The quick brown fox jumped over the lazy dog.",
							"In my town (Alswellington) we say, \"Greetings!\"",
							"myComparator = (a1, a2) -> return a1 > a2;",
							"1234567890",
							"{ return true; }",
							"echo Hello | test.txt",
							"~Object()" };
		
		for(String str : samples)
			assertTrue(upperWordNative(str).equals(upperWordBranchlessCorrected(str)));
	}
	
	@Test
	void testBranchlessCorrectedFunctionality3() {
		StringBuilder everyAsciiCharacter = new StringBuilder(128);
		for(char i = 0; i < 128; ++i)
			everyAsciiCharacter.append(i);
		assertTrue(upperWordNative(everyAsciiCharacter.toString()).equals(upperWordBranchlessCorrected(everyAsciiCharacter.toString())));
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
	
	@Test
	void testBranchlessCorrectedSpeed() {
		for(int i = 0; i < words.length; ++i) {
			for(int j = 0; j < words[i].length(); ++j)
				words[i].setCharAt(j, ToUpper.BranchlessCorrected(words[i].charAt(j)));
		}
	}
}
