import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuickSort<Item extends Comparable<Item>> extends Sort<Item> {

	public void sort() {
		sort(this.list, 0, this.list.size() - 1);

	}

	private int pivot(ArrayList<Item> array, int low, int high) {
		int i = low;
		int j = high + 1;

		while (true) {
			while (array.get(++i).compareTo(array.get(low)) < 0)
				if (i == high)
					break;
			while (array.get(--j).compareTo(array.get(low)) >= 0)
				if (j == low)
					break;
			if (i >= j)
				break;
			swap(array, i, j);
		}
		swap(array, low, j);

		return (j);
	}

	private void swap(ArrayList<Item> array, int i, int j) {
		Item temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);
	}

	private void sort(ArrayList<Item> array, int low, int high) {
		if (low < high) {
			int j = pivot(array, low, high);
			sort(array, low, j - 1);
			sort(array, j + 1, high);
		}
	}

	// test
	public static void main(String[] args) {
		QuickSort<Integer> quickSort = new QuickSort<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		Collections.shuffle(list);

		for (int i = 0; i < list.size(); i++)
			quickSort.addItem(list.get(i));

		System.out.println("Unsorted: ");
		for (Integer i : quickSort)
			System.out.println(i);

		System.out.println("Sorted: ");
		quickSort.sort();
		for (Integer i : quickSort)
			System.out.println(i);

		// Edge cases
		quickSort = new QuickSort<Integer>();

		// Check empty array call
		// shellSort.sort();

		// Check single item
		quickSort.addItem(1);
		quickSort.sort();

	}

}
