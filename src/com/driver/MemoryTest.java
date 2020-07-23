package com.driver;

// if nothing else these memory tests have proven that java's memory usage is quite
// complicated and nigh unpredictable

// SIZES:
//		byte 			= 1 byte
//		short, char 	= 2 bytes
//		int, float 		= 4 bytes
//		long, double 	= 8 bytes
//		Reference 		= 4 bytes if heap is under 32 GB with default VM settings and 8 bytes otherwise.
//
//		String 			= 40 + 2*n
//		ArrayList		= 40 + (4 +object_size) *n + 4 * unused_capacity
//		LinkedList		= 48 + (24 + object_size)
//		HashMap			= 56 + (32 + object_size + key_size) * n + 4 * capacity
//		LinkedHashMap	= 56 + (40 + object_size + key_size) * n + 4 capacity
//		TreeMap			= 56 + (40 + object_size + key_size) * n + 4 * capacity

// NOTES:
//		Heap allocations have a minimum size of 8 bytes
//		The Object header is 12 bytes in size
//			-which is 16 bytes due to minimum allocation size
//		Primitive allocations have a minimum size of 4 bytes 
//			-so individual byte/short allocations are useless, but arrays are fine

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.support.TestTimer;
import com.testfunc.FillList;

@SuppressWarnings("unused")
class MemoryTest {
	private final int samples = 1000000;
	private long totalMemStart, totalMemEnd;
	private long freeMemStart, freeMemEnd;
	private long usedMemStart, usedMemEnd;
	private long expectedSize;
	TestTimer timer = new TestTimer();
	
    @BeforeEach
    public void setUp(TestInfo testInfo) {
    	expectedSize = 0;
    	totalMemStart = Runtime.getRuntime().totalMemory();
    	freeMemStart = Runtime.getRuntime().freeMemory();
    	usedMemStart = totalMemStart - freeMemStart;
    	timer.startClock();
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
    	timer.stopClock(testInfo);
    	totalMemEnd = Runtime.getRuntime().totalMemory();
    	freeMemEnd = Runtime.getRuntime().freeMemory();
    	usedMemEnd = totalMemEnd - freeMemEnd;
    	
    	System.out.println("\tExpected Difference: " + expectedSize);
    	System.out.println("\tTrue Difference:     " + (usedMemEnd - usedMemStart));
    	System.out.println("\t\tTotal Memory Difference: " + (totalMemEnd - totalMemStart) + "(" + totalMemStart + " -> " + totalMemEnd+ ")");
    	System.out.println("\t\tFree Memory Difference:  " + (freeMemEnd - freeMemStart) + "(" + freeMemStart + " -> " + freeMemEnd+ ")");
    	System.out.println("\t\tUsed Memory Difference:  " + (usedMemEnd - usedMemStart) + "(" + usedMemStart + " -> " + usedMemEnd+ ")");
    	System.out.println();
    }

	@Test
	void testDoNothing() {
	}

	@Test
	void testAllocateStack2Bytes() {
		expectedSize = 2 * 1;
		char data = 'a';
	}
	
	@Test
	void testAllocateStack32Bytes() {
		expectedSize = 2 * 16;
		char[] data = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p'};
	}
	
	@Test
	void testAllocateHeap42Bytes() {
		expectedSize = 40 + 2 * 1;
		String data = new String("a");
	}
	
	@Test
	void testAllocateHeap72Bytes() {
		expectedSize = 40 + 2 * 16;
		String data = new String("abcdefghijklmnop");
	}
	
	@Test
	void testCreateArray() {
		expectedSize = 4 * samples;
		int[] data = new int[samples];
		for(int i = samples - 1; i >= 0; --i)
			data[i] = 0;
	}
	
	@Test
	void testCreateList() {
		expectedSize = 40 + ((4 + 16) * samples);
		ArrayList<Integer> data = new ArrayList<Integer>(samples);
		for(int i = 0; i < samples; ++i)
			data.add(i);
	}
	
	@Test
	void testCreateListTrimmed() {
		expectedSize = 40 + ((4 + 16) * samples);
		ArrayList<Integer> data = new ArrayList<Integer>(samples);
		for(int i = 0; i < samples; ++i)
			data.add(i);
		data.trimToSize();
	}
	
	@Test
	void testListOperations() {
		expectedSize = 40 + ((4 + 16) * samples);
		List<Integer> data = FillList.fillBulk(samples);
		for(int i = data.size() - 1; i >= 0; --i)
			data.set(i, data.get(i) * 2);
	}

}
