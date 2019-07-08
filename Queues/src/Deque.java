import java.util.Iterator;

/*
 *  double-ended queue or deque (pronounced “deck”) is a generalization 
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
	@SuppressWarnings("unchecked") // Java doesn't want generic arrays :(
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
		if(item.equals(null)) {
			throw new java.lang.IllegalArgumentException();
		} else {
			this.checkIncreaseSize();
			this.front--;
			this.deque[front] = item;
		}
	}

	// add the item to the end
	public void addLast(Item item) {
		if(item.equals(null)) {
			throw new java.lang.IllegalArgumentException();
		} else {
			this.checkIncreaseSize();
			this.back++;
			this.deque[back] = item;
		}
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if(this.isEmpty()) {
			throw new java.util.NoSuchElementException();
		} else {
			this.checkDecreaseSize();
			Item firstItem = this.deque[front];
			this.deque[front] = null;
			this.front++;
			return firstItem;
		}
	}

	// remove and return the item from the end
	public Item removeLast() {
		if(this.isEmpty()) {
			throw new java.util.NoSuchElementException();
		} else {
			this.checkDecreaseSize();
			Item lastItem = this.deque[back];
			this.deque[back] = null;
			this.back--;
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
		if(this.front <= this.deque.length * .25 || this.back >= this.deque.length * .75) {
			@SuppressWarnings("unchecked")
			Item[] newDeque = (Item[]) new Object[this.deque.length * 2];
			int dequeSize = this.size();
			int newFront = this.deque.length - dequeSize / 2;

			for(int i = 0; i < dequeSize; i++) {
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
		if(this.size() <= this.deque.length * .25) {
			@SuppressWarnings("unchecked")
			Item[] newDeque = (Item[]) new Object[this.deque.length / 2];
			int dequeSize = this.size();
			int newFront = this.deque.length / 4 - dequeSize / 2;
			System.out.println("shorten Newfront " + newFront);

			for(int i = 0; i < this.size(); i++) {
				newDeque[newFront + i] = this.deque[front + i];
			}
			this.deque = newDeque;
			this.front = newFront;
			this.back = this.front + dequeSize - 1;
			System.out.println("shorten Newback " + this.back);
		}

	}

	// represents iterable deque
	private class DequeIterator implements Iterator<Item> {

		private Item current = deque[front];
		private int i = front;

		public boolean hasNext() {
			return current != null;
		}

		public Item next() {
			Item thisItem = this.current;
			i++;
			this.current = deque[i];
			return thisItem;
		}
		
		// not to be implemented 
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

	}

	// unit testing (optional)
	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(1);
		System.out.println(deque.size());
		deque.addFirst(2);
		System.out.println(deque.size());
		deque.addFirst(3);
		System.out.println(deque.size());
		deque.addFirst(3);
		System.out.println(deque.size());
		deque.addFirst(3);
		System.out.println(deque.size());

		deque.addLast(10);
		System.out.println(deque.size());
		deque.addLast(9);
		System.out.println(deque.size());
		deque.addLast(8);
		System.out.println(deque.size());
		deque.addLast(7);
		System.out.println(deque.size());

		System.out.println(deque.removeFirst());
		System.out.println(deque.size());
		System.out.println(deque.removeFirst());
		System.out.println(deque.size());
		System.out.println(deque.removeFirst());
		System.out.println(deque.size());
		System.out.println(deque.removeFirst());
		System.out.println(deque.size());
		System.out.println(deque.removeFirst());
		System.out.println(deque.size());
		System.out.println(deque.removeFirst());
		System.out.println(deque.size());


		for(Integer i : deque) {
			System.out.print(i + " ");
		}
	}

}
