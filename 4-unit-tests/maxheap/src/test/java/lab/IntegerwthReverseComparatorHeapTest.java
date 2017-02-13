package pl.edu.agh.kis.java2015.domain;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

/**
 * Created by Monika_ on 2016-11-02.
 */
public class IntegerwthReverseComparatorHeapTest {
	final static Comparator<Integer> comparator = Comparator.<Integer>reverseOrder();
	
	@Test
	public void insert0And2And3And5And6intoNewHeap_topIs0() {
		
		Heap<Integer> heap = new Heap<Integer>(comparator);
		heap.insert(0);
		heap.insert(2);
		heap.insert(3);
		heap.insert(5);
		heap.insert(6);
		
		assertEquals(Integer.valueOf(0), heap.top());
		assertEquals("size should be 5", 5, heap.size());
	}
	
	@Test
	public void replaceTopwith3_TopIs2() {
		Heap<Integer> heap = new Heap(comparator);
		heap.insert(0);
		heap.insert(2);
		heap.insert(3);
		heap.insert(5);
		heap.insert(6);
		
		heap.replaceTopWith(3);
		assertEquals("size should be 5", 5, heap.size());
		assertEquals("top should be 2", Integer.valueOf(2), heap.top());
	}
	
	@Test
	public void extractMaxfromHeapWith8onTop_return8() {
		Heap<Integer> heap = new Heap(comparator);
		heap.insert(-6);
		heap.insert(2);
		heap.insert(3);
		heap.insert(5);
		heap.insert(6);
		
		assertEquals("extractMax should return -6", Integer.valueOf(-6), heap.extractMax());
		assertEquals("top should be 2", Integer.valueOf(2), heap.top());
		assertEquals("size should be 4", 4, heap.size());
	}
}
