package com.testfunc;

import java.util.List;

import com.support.TestThread;

public class ThreadMultArray extends TestThread {
	private List<Integer> list;
	   
	public ThreadMultArray(int start, int end, List<Integer> list) {
		super();
		this.start = start;
		this.end = end;
		this.list = list;
	}

	public void run() {
		for(int i = start; i <= end; ++i)
			list.set(i, list.get(i) * 2);
	}
}