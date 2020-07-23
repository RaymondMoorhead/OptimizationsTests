package com.support;

import java.lang.management.ManagementFactory;
import java.util.List;

import org.junit.jupiter.api.TestInfo;

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
	
	public void stopClockThreads(TestInfo testInfo, List<TestThread> threads) {
		endTimeTrue = System.currentTimeMillis();
		long totalTime = 0;
		
		System.out.println("Test " + testInfo.getDisplayName() + ":");
		System.out.println("\tReal Time (Ms): " + (endTimeTrue - startTimeTrue));
		for(int i = 0; i < threads.size(); ++i) {
			totalTime += threads.get(i).getTime();
		    System.out.println('\t' + threads.get(i).toString() + " Time (Ns): " + threads.get(i).getTime());
		}
		System.out.println("\t Total Thread Time (Ns): " + totalTime);
	}
}
