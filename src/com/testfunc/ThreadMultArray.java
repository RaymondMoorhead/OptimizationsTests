package com.testfunc;

import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadMultArray implements Runnable {
	private Thread thread;
	private static AtomicInteger threadNum = new AtomicInteger(0);
	private int start, end;
	private long startTime, endTime;
	private List<Integer> list;
	   
	public long getTime() {
		return endTime - startTime;
	}
	   
	public ThreadMultArray(int start, int end, List<Integer> list) {
		super();
		this.start = start;
		this.end = end;
		this.list = list;
	}

	public void run() {
		startTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
		for(int i = start; i < end; ++i)
			list.set(i, list.get(i) * 2);
		endTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
	}
	   
	public void start () {
	   if (thread == null) {
	     thread = new Thread (this, "Thread " + Integer.toString(threadNum.incrementAndGet()));
	     thread.start ();
	   }
	}
}