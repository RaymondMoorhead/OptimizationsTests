package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.support.TestTimer;
import com.testfunc.Algorithms;
import com.testfunc.FillList;

class AlgorithmsTest {
	
	TestTimer timer = new TestTimer();
	private final int samples = 1000000;
	List<Integer> numbers;
	
    @BeforeEach
    public void setUp() {
    	numbers = FillList.fillBulk(samples);
    	timer.startClock();
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
    	timer.stopClock(testInfo);
    	numbers = null;
    }

	@Test
	void testDoubleBasic() {
		for(int i = 0; i < numbers.size(); ++i)
			numbers.set(i, Algorithms.doubleBasic(numbers.get(i)));
	}
	
	@Test
	void testDoubleShift() {
		for(int i = 0; i < numbers.size(); ++i)
			numbers.set(i, Algorithms.doubleShift(numbers.get(i)));
	}

	@Test
	void testSqrtNative() {
		for(int i = 0; i < numbers.size(); ++i)
			// cast doesn't matter, we don't care about the data integrity
			numbers.set(i, (int) Algorithms.sqrtNative(numbers.get(i)));
	}
	
	@Test
	void testSqrtApproximated() {
		for(int i = 0; i < numbers.size(); ++i)
			numbers.set(i, (int) Algorithms.sqrtApproximated(numbers.get(i)));
	}
	
	@Test
	void testSqrtApproximatedAccuracy() {
		List<Double> result = new ArrayList<Double>(samples);
		for(int i = 0; i < numbers.size(); ++i)
			result.add(Algorithms.sqrtApproximated(numbers.get(i)));
		
		// check error
		double minError = 1;
		double maxError = 0;
		double avgError = 0;
		double curError;
		for(int i = 1; i < numbers.size(); ++i) {
			curError = Math.abs(result.get(i) - Math.sqrt(numbers.get(i))) / Math.sqrt(numbers.get(i));
			avgError += curError / (double)samples;
			if(curError < minError)
				minError = curError;
			if(curError > maxError)
				maxError = curError;
		}
		System.out.println("testSqrtApproximatedAccuracy() error percentage:");
		System.out.println("\tMinimum Error: " + minError);
		System.out.println("\tMaximum Error: " + maxError);
		System.out.println("\tAverage Error: " + avgError);
	}
	
//	@Test
//	void testSumBasic() {
//		// takes far too long to be even worth considering, minutes at least
//		for(int i = 0; i < numbers.size(); ++i)
//			numbers.set(i, Algorithms.sum1ToNBasic(i));
//	}
	
	@Test
	void testSum1Op() {
		for(int i = 0; i < numbers.size(); ++i)
			numbers.set(i, Algorithms.sum1ToN1Op(i));
	}
}
