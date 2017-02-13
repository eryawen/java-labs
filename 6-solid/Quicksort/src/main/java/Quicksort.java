import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Monika_ on 2016-11-10.
 */
public class Quicksort {
	public static <T> void quicksort(ArrayList<T> arraylist, Comparator<T> comparator, int beginIndex, int endIndex) {
		if (beginIndex >= endIndex) {
			return;
		}
		int pivotIndex = beginIndex;
		T pivot = arraylist.get(beginIndex);
		split(arraylist, comparator, beginIndex, endIndex, pivot);
		quicksort(arraylist, comparator, beginIndex, pivotIndex - 1);
		quicksort(arraylist, comparator, pivotIndex + 1, endIndex);
	}
	
	private static <T> void split(ArrayList<T> arraylist, Comparator<T> comparator, int beginIndex, int endIndex, T pivot) {
		int frombeginIterator = beginIndex + 1;
		int fromendIterator = endIndex;
		while (frombeginIterator <= fromendIterator) {
			while (frombeginIterator < endIndex && comparator.compare(arraylist.get(frombeginIterator), pivot) > 0) {
				frombeginIterator++;
			}
		}
	}
	
	private static <T> void swap(ArrayList<T> arrayList, int firstIndex, int secondIndex) {
		T temp = arrayList.get(firstIndex);
		arrayList.set(firstIndex, arrayList.get(secondIndex));
		arrayList.set(secondIndex, temp);
	}
}
