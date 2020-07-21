package com.support;

import org.junit.jupiter.api.TestInfo;

public class TestTimer {

	private long startTime;
	
	public void startClock() {
		startTime = System.currentTimeMillis();
	}
	
	public void stopClock(TestInfo testInfo) {
		startTime = System.currentTimeMillis() - startTime;
		System.out.println("Test " + testInfo.getDisplayName() + " has run in " + startTime + " milliseconds");
	}
}
