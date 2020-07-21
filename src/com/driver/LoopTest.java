package com.driver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.support.TestTimer;

public class LoopTest {
	
	TestTimer timer = new TestTimer();
	private final int samples = 1000000;
	
    @BeforeEach
    public void setUp() {
        timer.startClock();
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
    	timer.stopClock(testInfo);
    }

	@Test
	void testLoopIterate() {
		int[] testNums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		int sum = 0;
		for(int i = 0; i < samples; ++i)
		  for(int it : testNums) // this is supposedly slower because a new iterator is created each loop
			sum += it;
	}
	
	@Test
	void testLoopIncrement() {
		int[] testNums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		int sum = 0;
		for(int i = 0; i < samples; ++i)
		  for(int j = 0; j < testNums.length; ++j)
			sum += testNums[j];
	}
	
	@Test
	void testLoopDecrement() {
		int[] testNums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		int sum = 0;
		for(int i = samples - 1; i-- >= 0;)
		  for(int j = testNums.length - 1; --j >= 0 ;)
			sum += testNums[j];
	}
}
