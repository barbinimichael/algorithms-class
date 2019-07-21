import java.util.Iterator;

/*
 * A randomized queue is similar to a stack or queue, 
 * except that the item removed is chosen
 * uniformly at random from items in the data structure
 */

public class RandomizedQueue<Item> implements Iterable<Item> {

  // data structure
  private Item[] queue;
  private int size;

  // construct an empty randomized queue
  public RandomizedQueue() {
    this.queue = (Item[]) new Object[1];
    this.size = 0;
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return this.size() == 0;
  }

  // return the number of items on the randomized queue
  public int size() {
    return this.size;
  }

  // add the item
  public void enqueue(Item item) {
    this.checkIncreaseSize();
    for (int i = this.queue.length - 1; i >= 0; i--) {
      if (this.queue[i] == null) {
        this.queue[i] = item;
        this.size++;
        break;
      }
    }
  }

  // remove and return a random item
  public Item dequeue() {
    this.checkDecreaseSize();
    while (true) {
      int entry = (int) (Math.random() * this.queue.length);
      Item value = this.queue[entry];

      if (this.queue[entry] != null) {
        size--;
        this.queue[entry] = null;
        return value;
      }
    }
  }

  // return a random item (but do not remove it)
  public Item sample() {
    if(this.size() > 0) {
      while (true) {
        int entry = (int) (Math.random() * this.queue.length);
        Item value = this.queue[entry];

        if (this.queue[entry] != null) {
          return value;
        }
      }
    } else {
      throw new java.util.NoSuchElementException();
    }
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandomQueueIterator();
  }

  // unit testing (optional)
  public static void main(String[] args) {
    RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
    queue.enqueue(1);
    System.out.println(queue.size());
    queue.enqueue(2);
    System.out.println(queue.size());
    queue.enqueue(3);
    System.out.println(queue.size());
    queue.enqueue(3);
    System.out.println(queue.size());
    queue.enqueue(3);
    System.out.println(queue.size());

    for (Integer i : queue) {
      System.out.print(i + " ");
    }

    System.out.println("-------------");

    System.out.print(queue.dequeue() + " ");
    System.out.println(queue.size());
    System.out.print(queue.dequeue() + " ");
    System.out.println(queue.size());
    System.out.print(queue.dequeue() + " ");
    System.out.println(queue.size());
    System.out.print(queue.dequeue() + " ");
    System.out.println(queue.size());
    System.out.print(queue.dequeue() + " ");
    System.out.println(queue.size());
    System.out.println(queue.isEmpty());
  }

  // check that entries (front or back) have not overtaken 75%
  // if so, double the size of the array
  private void checkIncreaseSize() {
    if (this.size() >= this.queue.length * 0.75) {
      Item[] newQueue = (Item[]) new Object[this.queue.length * 2];

      for (int i = 0; i < this.queue.length; i++) {
        newQueue[i] = this.queue[i];
      }
      this.queue = newQueue;
    }
  }

  // check that entries haven't dipped under 25% array size
  // if so, halve array
  private void checkDecreaseSize() {
    if (this.size() <= this.queue.length * 0.25) {
      Item[] newQueue = (Item[]) new Object[this.queue.length / 2];

      int current = 0;
      for (int i = 0; i < this.queue.length; i++) {
        if (this.queue[i] != null) {
          newQueue[current] = this.queue[i];
          current++;
        }
      }
      this.queue = newQueue;
    }
  }

  private class RandomQueueIterator implements Iterator<Item> {

    int count = 0;
    int current = 0;

    public boolean hasNext() {
      return this.current < queue.length;
    }

    public Item next() {
      while (this.hasNext()) {
        if (queue[current] != null) {
          count++;
          return queue[current++];
        } else {
          current++;
        }
      }
      throw new RuntimeException();
    }

  }
}
