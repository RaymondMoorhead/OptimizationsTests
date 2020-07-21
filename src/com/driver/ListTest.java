package com.driver;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.support.TestTimer;
import com.testfunc.FillList;

class ListTest {
	
	List<Integer> result;
	TestTimer timer = new TestTimer();
	private final int samples = 1000000;
	
    @BeforeEach
    public void setUp() {
        timer.startClock();
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
    	timer.stopClock(testInfo);
    	result = null;
    }

	@Test
	void testFillIndividual() {
		result = FillList.fillIndividual(samples);
	}
	
	@Test
	void testFillPreAllocated() {
		result = FillList.fillPreAllocated(samples);
	}
	
	@Test
	void testFillBulk() {
		result = FillList.fillBulk(samples);
	}
}
