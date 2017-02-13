import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;

/**
 * Created by Monika_ on 2016-11-10.
 */
public class QuicksortTest {
	@Test
	public void quicksort_RandomOrder() throws Exception {
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(5);
		numbers.add(111);
		numbers.add(90);
		numbers.add(17);
		Comparator<Integer> naturalOrderComparator = Comparator.<Integer>naturalOrder();
		Quicksort.quicksort(numbers, naturalOrderComparator, 0, numbers.size() - 1);
		assertEquals((int) numbers.get(0), 111);
		assertEquals((int) numbers.get(1), 90);
		assertEquals((int) numbers.get(2), 17);
		assertEquals((int) numbers.get(3), 5);
	}
}