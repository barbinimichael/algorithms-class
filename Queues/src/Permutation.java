import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
  // test client
  public static void main(String[] args) {
    /*
     * Take two command line arguments k and S where S is a sequence of strings
     * where k are chosen randomly and printed
     */
    
    int k = Integer.parseInt(args[0]);
    RandomizedQueue<String> randQueue = new RandomizedQueue<String>();

    int c = 0;
    while (!StdIn.isEmpty() && c <= k) {
      randQueue.enqueue(StdIn.readString());
      c++;
    }

    if (k > randQueue.size())
      throw new IllegalArgumentException();

    for (int i = 0; i < k; i++)
      StdOut.println(randQueue.dequeue());

  }
}
