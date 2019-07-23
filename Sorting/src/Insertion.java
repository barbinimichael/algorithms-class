import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Insertion<Item extends Comparable<Item>> extends Sort<Item> {

  public Insertion() {
    super();
  }

  // Perform an insertion sort on the list
  public void sort() {
    if (this.list.size() <= 0) {
      throw new RuntimeException();

    } else {
      for (int i = 1; i < this.list.size(); i++) {
        for (int j = i; j > 0; j--) {

          if (this.list.get(j).compareTo(this.list.get(j - 1)) < 0) {
            this.swap(j - 1, j);
          }
        }
      }
    }
  }

  // test
  public static void main(String[] args) {
    Insertion<Integer> insertionSort = new Insertion<Integer>();
    ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    Collections.shuffle(list);

    for (int i = 0; i < list.size(); i++)
      insertionSort.addItem(list.get(i));

    System.out.println("Unsorted: ");
    for (Integer i : insertionSort)
      System.out.println(i);

    System.out.println("Sorted: ");
    insertionSort.sort();
    for (Integer i : insertionSort)
      System.out.println(i);

    // Edge cases
    insertionSort = new Insertion<Integer>();

    // Check empty array call
    // insertionSort.sort();

    // Check single item
    insertionSort.addItem(1);
    insertionSort.sort();

  }
}
