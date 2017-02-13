package pl.edu.agh.kis.java2015.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Heap<T> {
	Comparator<T> comparator;
	private int heapSize = 0;
	private ArrayList<T> array = new ArrayList<>();
	
	public Heap(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
	public static <T> Heap createHeapFromList(List<T> array, Comparator<T> comparator) {
		Heap<T> heap = new Heap<>(comparator);
		heap.copyElementsFromArraylist(array);
		heap.repairHeap();
		return heap;
	}
	
	public static <T> Heap createHeapfromTwoHeaps(final Heap<T> firstHeap, final Heap<T> secondHeap,
										 Comparator<T> comparator) {
		Heap<T> newHeap = new Heap<T>(comparator);
		newHeap.copyElementsFromArraylist(firstHeap.array);
		newHeap.copyElementsFromArraylist(secondHeap.array);
		newHeap.repairHeap();
		return newHeap;
	}
	
	public void meld(final Heap<T> heap2) {
		this.copyElementsFromArraylist(heap2.array);
		this.repairHeap();
	}
	
	public void copyElementsFromArraylist(List<T> src) {
		for (T t : src) {
			this.array.add(t);
		}
		heapSize = heapSize + src.size();
	}
	
	public void repairHeap() {
		int lastElementIndex = heapSize - 1;
		int lastElementParentIndex = parentIndex(lastElementIndex);
		for (int i = lastElementParentIndex; i >= 0; i--) {
			heapify(i);
		}
	}
	
	public void heapify(int parentIndex) {
		int leftChildIndex = leftChildIndex(parentIndex);
		int rightChildIndex = rightChildIndex(parentIndex);
		int indexOfMaxValue = indexOfMaxValue(parentIndex, leftChildIndex, rightChildIndex);
		
		while (indexOfMaxValue != parentIndex) {
			swapElements(indexOfMaxValue, parentIndex);
			
			parentIndex = indexOfMaxValue;
			leftChildIndex = leftChildIndex(parentIndex);
			rightChildIndex = rightChildIndex(parentIndex);
			indexOfMaxValue = indexOfMaxValue(parentIndex, leftChildIndex, rightChildIndex);
		}
	}
	
	public int indexOfMaxValue(int parentIndex, int leftChildIndex, int rightChildIndex) {
		if (leftChildIndex >= heapSize) {
			return parentIndex;
		}
		if (rightChildIndex >= heapSize) {
			if (isChildGreaterThanParent(leftChildIndex, parentIndex)) {
				return leftChildIndex;
			} else return parentIndex;
		} else {
			if (isChildGreaterThanParent(leftChildIndex, parentIndex) || isChildGreaterThanParent(rightChildIndex,
																				 parentIndex)) {
				if (isLeafGreaterThanOther(leftChildIndex, rightChildIndex)) {
					return leftChildIndex;
				} else {
					return rightChildIndex;
				}
			} else {
				return parentIndex;
			}
		}
	}
	
	public void delete() {
		if (isEmpty()) {
			throw new EmptyHeapException("Heap is empty");
		}
		array.set(0, array.get(heapSize - 1));
		array.remove(heapSize - 1);
		heapSize--;
		int parentIndex = 0;
		heapify(parentIndex);
	}
	
	public T extractMax() {
		if (isEmpty()) {
			throw new EmptyHeapException("Heap is empty");
		}
		T maxValue = top();
		delete();
		return maxValue;
	}
	
	public void replaceTopWith(T t) {
		array.set(0, t);
		heapify(0);
	}
	
	public int leftChildIndex(int parentIndex) {
		return (parentIndex * 2) + 1;
	}
	
	public int rightChildIndex(int parentIndex) {
		return (parentIndex * 2) + 2;
	}
	
	private boolean isEmpty() {
		if (heapSize == 0) return true;
		else return false;
	}
	
	public boolean isLeafGreaterThanOther(int firstLeafIndex, int secondIndex) {
		if (comparator.compare(array.get(firstLeafIndex), array.get(secondIndex)) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void insert(T value) {
		int currentIndex = heapSize;
		int parentIndex = parentIndex(currentIndex);
		array.add(value);
		while (isChildGreaterThanParent(currentIndex, parentIndex)) {
			swapElements(currentIndex, parentIndex);
			currentIndex = parentIndex;
			parentIndex = parentIndex(currentIndex);
		}
		heapSize++;
	}
	
	public boolean isChildGreaterThanParent(int currentIndex, int parentIndex) {
		if (comparator.compare(array.get(currentIndex), array.get(parentIndex)) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void swapElements(int currentIndex, int parentIndex) {
		T parentValue = parentValue(currentIndex);
		T currentValue = array.get(currentIndex);
		array.set(parentIndex, currentValue);
		array.set(currentIndex, parentValue);
	}
	
	public T parentValue(int currentIndex) {
		T parentValue = array.get(parentIndex(currentIndex));
		return parentValue;
	}
	
	public int parentIndex(int currentIndex) {
		return currentIndex / 2;
	}
	
	public int size() {
		return heapSize;
	}
	
	public T top() {
		return array.get(0);
	}
}