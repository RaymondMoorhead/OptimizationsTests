package com.testfunc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// it is obvious that pre-allocating an ArrayList is faster, but
// the question here is how much faster?

public class FillList {
	
	public static List fillIndividual(int indices) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < indices; ++i)
			list.add(i);
		return list;
	}

	public static List fillPreAllocated(int indices) {
		ArrayList<Integer> list = new ArrayList<Integer>(indices);
		for(int i = 0; i < indices; ++i)
			list.add(i);
		return list;
	}
	
	public static List fillBulk(int indices) {
		return IntStream.rangeClosed(0, indices).boxed().collect(Collectors.toList());
	}
}
