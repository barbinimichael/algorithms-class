import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Selection<Item extends Comparable<Item>> extends Sort<Item> {

  public void sort() {
    for (int i = 0; i < this.list.size() - 1; i++) {
      int min = i;

      for (int j = i + 1; j < this.list.size(); j++) {

        if (this.list.get(j).compareTo(this.list.get(min)) < 0) {
          min = j;
        }
      }

      this.swap(i, min);

    }
  }

  // test
  public static void main(String[] args) {
    Selection<Integer> selectionSort = new Selection<Integer>();
    ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    Collections.shuffle(list);

    for (int i = 0; i < list.size(); i++)
      selectionSort.addItem(list.get(i));

    System.out.println("Unsorted: ");
    for (Integer i : selectionSort)
      System.out.println(i);

    System.out.println("Sorted: ");
    selectionSort.sort();
    for (Integer i : selectionSort)
      System.out.println(i);

    // Edge cases
    selectionSort = new Selection<Integer>();

    // Check empty array call
    // insertionSort.sort();

    // Check single item, shouldn't throw error
    selectionSort.addItem(1);
    selectionSort.sort();

  }

}
