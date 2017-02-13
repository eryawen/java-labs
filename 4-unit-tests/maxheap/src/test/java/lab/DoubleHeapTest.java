package pl.edu.agh.kis.java2015.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DoubleHeapTest {
	final static double DELTA = 0.001;
	final static Comparator<Double> comparator = Comparator.<Double>naturalOrder();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void extractMax_HeapWithTop8_return8() {
		Heap<Double> heap = new Heap(comparator);
		heap.insert(0.0);
		heap.insert(2.0);
		heap.insert(3.0);
		heap.insert(7.0);
		heap.insert(8.0);
		
		assertEquals("extractMax should return 8", 8, heap.extractMax(), DELTA);
		assertEquals("top should be 7", 7, heap.top(), DELTA);
		assertEquals("size should be 4", 4, heap.size());
	}
	
	@Test
	public void extractMaxfromEmptyHeap_throwsEmptyStackException() throws EmptyHeapException {
		thrown.expect(EmptyHeapException.class);
		thrown.expectMessage("Heap is empty");
		Heap heap = new Heap(comparator);
		heap.extractMax();
	}
	
	@Test
	public void deleteMaxfromHeapWith8onTop_TopIs7() {
		Heap<Double> heap = new Heap(comparator);
		heap.insert(0.0);
		heap.insert(2.0);
		heap.insert(3.0);
		heap.insert(7.0);
		heap.insert(8.0);
		
		heap.delete();
		assertEquals("size should be 4", 4, heap.size());
		assertEquals("top should be 7", 7, heap.top(), DELTA);
	}
	
	@Test
	public void deleteMaxfromEmptyHeap_throwsEmptyHeapException() throws EmptyHeapException {
		thrown.expect(EmptyHeapException.class);
		thrown.expectMessage("Heap is empty");
		Heap heap = new Heap(comparator);
		heap.delete();
	}
	
	@Test
	public void deleteMaxFromHeapWithTwoElements_topIs0() {
		Heap<Double> heap = new Heap(comparator);
		heap.insert(0.0);
		heap.insert(8.0);
		heap.delete();
		
		assertEquals("size should be 1", 1, heap.size());
		assertEquals("top should be 0", 0, heap.top(), DELTA);
	}
	
	@Test
	public void replaceTopwith3_TopIs7() {
		Heap<Double> heap = new Heap(comparator);
		heap.insert(0.0);
		heap.insert(2.0);
		heap.insert(3.0);
		heap.insert(7.0);
		heap.insert(8.0);
		
		heap.replaceTopWith(3.0);
		assertEquals("size should be 5", 5, heap.size());
		assertEquals("top should be 7", 7, heap.top(), DELTA);
	}
	
	@Test
	public void createHeapFromListWithNumbersFrom2to8_Topis8() {
		List<Double> array = Arrays.asList(2.0, 2.2, 3.5, 4.0, 5.0, 6.0, 8.0);
		
		Heap<Double> heap = Heap.createHeapFromList(array, comparator);
		assertEquals("size should be 7", 7, heap.size());
		assertEquals("top should be 8", 8, heap.top(), DELTA);
	}
	
	@Test
	public void createHeapFromTwoHeapsWithTop17And22_TopIs22() {
		Heap heap1 = new Heap(comparator);
		heap1.insert(0.2);
		heap1.insert(12.3);
		heap1.insert(3.0);
		heap1.insert(17.0);
		heap1.insert(8.0);
		
		Heap heap2 = new Heap(comparator);
		heap2.insert(10.0);
		heap2.insert(22.0);
		heap2.insert(1.0);
		heap2.insert(-6.0);
		heap2.insert(5.0);
		
		Heap<Double> heap = Heap.createHeapfromTwoHeaps(heap1, heap2, comparator);
		assertEquals("size should be 10", 10, heap.size());
		assertEquals("top should be 22", 22, heap.top(), DELTA);
	}
	
	@Test
	public void createHeapfromTwoHeapsWithTop17AndEmpty_TopIs17() {
		Heap heap1 = new Heap(comparator);
		heap1.insert(0.9);
		heap1.insert(12.9);
		heap1.insert(3.9);
		heap1.insert(17.0);
		heap1.insert(8.5);
		
		Heap heap2 = new Heap(comparator);
		
		Heap<Double> heap = Heap.createHeapfromTwoHeaps(heap1, heap2, comparator);
		assertEquals("size should be 5", 5, heap.size());
		assertEquals("top should be 17", 17, heap.top(), DELTA);
	}
	
	@Test
	public void meldHeapWithOther_TopIs60() {
		Heap<Double> heap1 = new Heap(comparator);
		heap1.insert(0.0);
		heap1.insert(12.0);
		heap1.insert(3.0);
		heap1.insert(17.2);
		heap1.insert(8.1);
		
		Heap<Double> heap2 = new Heap(comparator);
		heap2.insert(10.0);
		heap2.insert(22.7);
		heap2.insert(60.9);
		heap2.insert(-2.2);
		heap2.insert(5.4);
		
		heap1.meld(heap2);
		assertEquals("size should be 10", 10, heap1.size());
		assertEquals("top should be 60.9", 60.9, heap1.top(), DELTA);
	}
	
	/////////////////
	
	@Test
	public void insert0intoNewHeap_topIs0() {
		
		Heap<Double> heap = new Heap(comparator);
		heap.insert(0.0);
		
		assertEquals("size should be 1", 1, heap.size());
		assertEquals(0, heap.top(), DELTA);
		assertEquals(1, heap.size(), DELTA);
	}
	
	@Test
	public void insert0AndThen2intoNewHeap_topIs2() {
		
		Heap heap = new Heap(comparator);
		heap.insert(0);
		heap.insert(2);
		
		assertEquals("size should be 2", 2, heap.size());
		assertEquals(2, heap.top());
	}
	
	@Test
	public void insert0And2And3And5And6intoNewHeap_topIs6() {
		
		Heap<Double> heap = new Heap(comparator);
		heap.insert(0.0);
		heap.insert(2.6);
		heap.insert(3.0);
		heap.insert(5.6);
		heap.insert(6.0);
		
		assertEquals(6, heap.top(), DELTA);
	}
}
