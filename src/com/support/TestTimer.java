package com.support;

import java.lang.management.ManagementFactory;
import java.util.List;

import org.junit.jupiter.api.TestInfo;

import com.testfunc.ThreadMultArray;

public class TestTimer {

	private long startTimeTrue;
	private long startTimeCPU;
	
	private long endTimeTrue;
	private long endTimeCPU;
	
	public void startClock() {
		startTimeTrue = System.currentTimeMillis();
		startTimeCPU = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
	}
	
	public void startClockThreads() {
		startTimeTrue = System.currentTimeMillis();
	}
	
	public void stopClock(TestInfo testInfo) {
		endTimeCPU = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
		endTimeTrue = System.currentTimeMillis();
		
		System.out.println("Test " + testInfo.getDisplayName() + " has taken in milliseconds:");
		System.out.println("\tReal Time: " + (endTimeTrue - startTimeTrue));
		System.out.println("\tCPU Time: " + ((endTimeCPU - startTimeCPU) / 1000000));
	}
	
	public void stopClockThreads(TestInfo testInfo, List<ThreadMultArray> threads) {
		endTimeTrue = System.currentTimeMillis();
		
		System.out.println("Test " + testInfo.getDisplayName() + " has taken in milliseconds:");
		System.out.println("\tReal Time: " + (endTimeTrue - startTimeTrue));
		for(int i = 0; i < threads.size(); ++i)
		  System.out.println("\t Thread " + Integer.toString(i) + " Time: " + ((threads.get(i).getTime()) / 1000000));
	}
}
