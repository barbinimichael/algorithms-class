import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;


public class TestRandomizedQueue {

  @Test
  public void testEnqueue() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
    
    queue.enqueue(1);
    assertEquals(1, queue.size());
    
    queue.enqueue(2);
    assertEquals(2, queue.size());
    
    queue.enqueue(3);
    assertEquals(3, queue.size());
    
    queue.enqueue(3);
    assertEquals(4, queue.size());
    
    queue.enqueue(3);
    assertEquals(5, queue.size());
  }
  
}
