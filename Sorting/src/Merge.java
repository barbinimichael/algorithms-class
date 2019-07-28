import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Merge<Item extends Comparable<Item>> extends Sort<Item> {

  public void sort() {
    sort(this.list, 0, this.list.size() - 1);
  }

  // merge split, sorted subarrays
  private void merge(ArrayList<Item> sublist, int low, int mid, int high) {
    ArrayList<Item> aux = new ArrayList<Item>();

    for (int k = 0; k < sublist.size(); k++)
      aux.add(sublist.get(k));

    int i = low, j = mid + 1;
    for (int k = low; k <= high; k++) {
      if (i > mid)
        sublist.set(k, aux.get(j++));
      else if (j > high)
        sublist.set(k, aux.get(i++));
      else if (aux.get(j).compareTo(aux.get(i)) < 0)
        sublist.set(k, aux.get(j++));
      else
        sublist.set(k, aux.get(i++));
    }
  }

  // split into subarrays and sort
  private void sort(ArrayList<Item> sublist, int low, int high) {
    if(low < high) {
      int mid = (low + high) / 2;

      sort(sublist, low, mid);
      sort(sublist, mid + 1, high);

      merge(sublist, low, mid, high);
    }
  }

  // test
  public static void main(String[] args) {
    Merge<Integer> mergeSort = new Merge<Integer>();
    ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    Collections.shuffle(list);

    for (int i = 0; i < list.size(); i++)
      mergeSort.addItem(list.get(i));

    System.out.println("Unsorted: ");
    for (Integer i : mergeSort)
      System.out.println(i);

    System.out.println("Sorted: ");
    mergeSort.sort();
    for (Integer i : mergeSort)
      System.out.println(i);

    // Edge cases
    mergeSort = new Merge<Integer>();

    // Check empty array call
    // shellSort.sort();

    // Check single item
    mergeSort.addItem(1);
    mergeSort.sort();

  }
}
