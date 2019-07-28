import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ShellSort<Item extends Comparable<Item>> extends Sort<Item> {

  public void sort() {
    int maxH = this.list.size() - 1;

    // using increment n / 2 
    for (int i = maxH; i > 0; i /= 2) {
      variableInsertion(i);
    }
  }

  private void variableInsertion(int v) {
    for (int i = 1; i < this.list.size(); i++) {
      for (int j = i; j > v - 1; j -= v) {

        if (this.list.get(j).compareTo(this.list.get(j - 1)) < 0) {
          this.swap(j - 1, j);
        }
      }
    }
  }

  // test
  public static void main(String[] args) {
    ShellSort<Integer> shellSort = new ShellSort<Integer>();
    ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    Collections.shuffle(list);

    for (int i = 0; i < list.size(); i++)
      shellSort.addItem(list.get(i));

    System.out.println("Unsorted: ");
    for (Integer i : shellSort)
      System.out.println(i);

    System.out.println("Sorted: ");
    shellSort.sort();
    for (Integer i : shellSort)
      System.out.println(i);

    // Edge cases
    shellSort = new ShellSort<Integer>();

    // Check empty array call
    // shellSort.sort();

    // Check single item
    shellSort.addItem(1);
    shellSort.sort();

  }

}
