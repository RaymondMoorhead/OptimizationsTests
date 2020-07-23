package com.support;

import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class TestThread implements Runnable{
	public final int threadId;
	
	protected Thread thread = null;
	protected int start, end;
	
	private long time;
	private static AtomicInteger threadNum;
	
	static
	{
		threadNum = new AtomicInteger(0);
		ManagementFactory.getThreadMXBean().setThreadCpuTimeEnabled(true);
		if(!ManagementFactory.getThreadMXBean().isThreadCpuTimeSupported())
			System.out.println("CPU THREAD TIME NOT SUPPORTED");
	}
	
	{
		threadId = threadNum.incrementAndGet();
	}
	
	public static void resetId() {
		threadNum.set(0);
	}
	
	public void start () {
		   if (thread == null) {
		     thread = new Thread (this, "Thread " + Integer.toString(threadId));
		     thread.start();
		   }
	}
	
	// this is often 0 due to reasons not entirely clear to me, it
	// may have something to do with the accuracy or precision of
	// getThreadCpuTime
	public long getTime() {
		return time;
	}
	
	public void join() throws InterruptedException {
		stopClock();
		thread.join();
	}
	
	public String toString() {
		return "(Thread " + threadId + ", Range " + start + "->" + end + ')';
	}
	
	protected void stopClock() {
		time = ManagementFactory.getThreadMXBean().getThreadCpuTime(thread.getId());
	}
}
