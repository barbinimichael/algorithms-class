import java.util.ArrayList;
import java.util.Iterator;

public abstract class Sort<Item extends Comparable<Item>> implements Iterable<Item> {

  // List of items to sort
  protected ArrayList<Item> list;

  public Sort() {
    this.list = new ArrayList<Item>();
  }

  // Add an item to the list items to sort
  public void addItem(Item item) {
    this.list.add(item);
  }

  // Create a returnable iterator
  public Iterator<Item> iterator() {
    return new InsertionIterator();
  }

  // Swap two items in the list
  protected void swap(int i, int j) {
    Item temp = this.list.get(i);
    this.list.set(i, this.list.get(j));
    this.list.set(j, temp);
  }

  protected class InsertionIterator implements Iterator<Item> {

    int count = 0;

    public boolean hasNext() {
      return count < list.size();
    }

    public Item next() {
      Item item = list.get(count);
      count++;
      return item;
    }
  }

}
