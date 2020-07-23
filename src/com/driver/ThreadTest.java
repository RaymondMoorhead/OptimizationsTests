package com.driver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.support.TestThread;
import com.support.TestTimer;
import com.testfunc.FillList;
import com.testfunc.ThreadMultArray;
class ThreadTest {

	TestTimer timer = new TestTimer();
	private final int samples = 1000000;
	List<Integer> numbers;
	List<TestThread> threads;
	
	private void genThreads(int num) {
		int indicesPerThread = samples / num;
		int curIndex = 0;
		for(; (curIndex + indicesPerThread) < samples; curIndex += indicesPerThread + 1) {
			threads.add(new ThreadMultArray(curIndex, curIndex + indicesPerThread, numbers));
			threads.get(threads.size() - 1).start();
		}
		if(curIndex < samples) {
			threads.add(new ThreadMultArray(curIndex, samples, numbers));
			threads.get(threads.size() - 1).start();
		}
        for (TestThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
	
	private void checkWork() {
    	for(int i = numbers.size() - 1; i >= 0; --i)
    		assertEquals(i * 2, numbers.get(i));
	}
	
    @BeforeEach
    public void setUp() {
    	TestThread.resetId();
    	threads = new ArrayList<TestThread>();
    	numbers = FillList.fillBulk(samples);
    	timer.startClockThreads();
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
    	timer.stopClockThreads(testInfo, threads);
    	checkWork();
    	numbers = null;
    	threads = null;
    	System.out.println();
    }

	@Test
	void testMult1Thread() {
		genThreads(1);
	}
	
	@Test
	void testMult2Thread() {
		genThreads(2);
	}
	
	@Test
	void testMult3Thread() {
		genThreads(3);
	}
	
	@Test
	void testMult4Thread() {
		genThreads(4);
	}
	
	@Test
	void testMult5Thread() {
		genThreads(5);
	}
	
	@Test
	void testMult6Thread() {
		genThreads(6);
	}
	
	@Test
	void testMult7Thread() {
		genThreads(7);
	}

	@Test
	void testMult8Thread() {
		genThreads(8);
	}
	
	@Test
	void testMult9Thread() {
		genThreads(9);
	}
	
	@Test
	void testMult10Thread() {
		genThreads(10);
	}
}
