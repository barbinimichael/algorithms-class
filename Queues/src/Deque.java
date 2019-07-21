import java.util.Iterator;

/*
 *  double-ended queue or deque is a generalization 
 *  of a stack and a queue that supports adding and removing items 
 *  from either the front or the back of the data structure
 */
public class Deque<Item> implements Iterable<Item> {
  // data structure
  private Item[] deque;
  // remember which index front of list is
  private int front;
  // remember which index end of list is
  private int back;

  // construct an empty deque
  public Deque() {
    this.deque = (Item[]) new Object[4];

    this.front = 2;
    this.back = 1;
  }

  // is the deque empty?
  public boolean isEmpty() {
    return front > back;
  }

  // return the number of items on the deque
  public int size() {
    return back - front + 1;
  }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();

    } else {
      this.checkIncreaseSize();

      this.front--;
      this.deque[this.front] = item;
    }
  }

  // add the item to the end
  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();

    } else {
      this.checkIncreaseSize();

      // System.out.println("addLast " + this.front + " " + this.back + " " +
      // this.deque.length);

      this.back++;
      this.deque[this.back] = item;
    }
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (this.isEmpty()) {
      throw new java.util.NoSuchElementException();

    } else {
      this.checkDecreaseSize();

      Item firstItem = this.deque[front];
      this.deque[this.front++] = null;
      return firstItem;
    }
  }

  // remove and return the item from the end
  public Item removeLast() {
    if (this.isEmpty()) {
      throw new java.util.NoSuchElementException();
    } else {

      this.checkDecreaseSize();

      Item lastItem = this.deque[back];
      this.deque[this.back--] = null;
      return lastItem;
    }
  }

  // return an iterator over items in order from front to end
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  // check that entries (front or back) have not overtaken 75%
  // if so, double the size of the array
  private void checkIncreaseSize() {
    // System.out.println("Might increase " + deque.length);
    if (front >= (int) (this.deque.length * 0.75) || 
        back >= (int) (this.deque.length * 0.75) ||
        front <= (int) (this.deque.length * 0.25) ||
        back <= (int) (this.deque.length * 0.25)) {
      // System.out.println("Increasing " + deque.length);
      Item[] newDeque = (Item[]) new Object[this.deque.length * 2];
      int dequeSize = this.size();
      int newFront = newDeque.length - newDeque.length / 2;

      for (int i = 0; i < dequeSize; i++) {
        newDeque[newFront + i] = this.deque[front + i];
      }
      this.deque = newDeque;
      this.front = newFront;
      this.back = this.front + dequeSize - 1;

    }
  }

  // check that entries haven't dipped under 25% array size
  // if so, halve array
  private void checkDecreaseSize() {
    if (this.size() <= (this.deque.length + 1) * 0.25) {
      // System.out.println("Shortening " + deque.length);
      Item[] newDeque = (Item[]) new Object[(this.deque.length + 1) / 2];
      int dequeSize = this.size();
      int newFront = newDeque.length / 2 - dequeSize / 2;
      // System.out.println("shorten Newfront " + newFront);

      for (int i = 0; i < this.size(); i++) {
        newDeque[newFront + i] = this.deque[front + i];
      }
      this.deque = newDeque;
      this.front = newFront;
      this.back = this.front + dequeSize - 1;
      // System.out.println("shorten Newback " + this.back);
    }

  }

  /**
   * Unit testing provided
   * 
   * @param args
   */
  public static void main(String[] args) {
    Deque<Integer> deque = new Deque<Integer>();
    System.out.println(deque.iterator().hasNext());
  }

  // represents iterable deque
  private class DequeIterator implements Iterator<Item> {
    private int i = front;

    public boolean hasNext() {
      // System.out.println(i + " " + (front + size()));
      return i < front + size();
    }

    public Item next() {
      if(i >= front + size()) {
        throw new java.util.NoSuchElementException();
      } else {
        return deque[i++];
      }
    }

    // not to be implemented
    public void remove() {
      throw new UnsupportedOperationException();
    }

  }
}
