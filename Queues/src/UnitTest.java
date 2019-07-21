import static org.junit.Assert.assertEquals;

import org.junit.*;

public class UnitTest {
  
  @Test
  public void testAddLast() {
    Deque<Integer> deque = new Deque<Integer>();
    deque.addLast(2);
    assertEquals(1, deque.size());
    
    String inDeque = "";
    for (Integer i : deque) {
      inDeque += i;
    }
    assertEquals("2", inDeque);
    
    deque.addLast(3);
    assertEquals(2, deque.size());

    inDeque = "";
    for (Integer i : deque) {
      inDeque += i;
    }
    assertEquals("23", inDeque);
    
    for(int j = 0; j < 100; j++) {
      deque.addLast(1);
    }
    assertEquals(102, deque.size());
  }
  
  @Test
  public void testAddFirst() {
    Deque<Integer> deque = new Deque<Integer>();
    deque.addFirst(5);
    assertEquals(1, deque.size());
    
    String inDeque = "";
    for (Integer i : deque) {
      inDeque += i;
    }
    assertEquals("5", inDeque);
    
    deque.addFirst(9);
    assertEquals(2, deque.size());

    inDeque = "";
    for (Integer i : deque) {
      inDeque += i;
    }
    assertEquals("95", inDeque);
    
    for(int j = 0; j < 100; j++) {
      deque.addFirst(1);
    }
    assertEquals(102, deque.size());
  }

  @Test
  public void testRemoveFirst() {
    Deque<Integer> deque = new Deque<Integer>();
    deque.addFirst(1);
    assertEquals(1, deque.size());
    
    deque.addFirst(3);
    assertEquals(2, deque.size());
    
    assertEquals(3, (int)(deque.removeFirst()));
    assertEquals(1, deque.size());
    
    String inDeque = "";
    for (Integer i : deque) {
      inDeque += i;
    }
    assertEquals("1", inDeque);
    
    assertEquals(1, (int)(deque.removeFirst()));
    assertEquals(0, deque.size());
    
    inDeque = "";
    for (Integer i : deque) {
      inDeque += i;
    }
    assertEquals("", inDeque);
    
    deque.addLast(1);
    assertEquals(1, deque.size());
    
    deque.addLast(3);
    assertEquals(2, deque.size());
    
    assertEquals(1, (int)(deque.removeFirst()));
    assertEquals(1, deque.size());
    
    inDeque = "";
    for (Integer i : deque) {
      inDeque += i;
    }
    assertEquals("3", inDeque);
    
    assertEquals(3, (int)(deque.removeFirst()));
    assertEquals(0, deque.size());
    
    inDeque = "";
    for (Integer i : deque) {
      inDeque += i;
    }
    assertEquals("", inDeque);
  }
  
  @Test
  public void testRemoveLast() {
    Deque<Integer> deque = new Deque<Integer>();
    deque.addFirst(1);
    assertEquals(1, deque.size());
    
    deque.addFirst(3);
    assertEquals(2, deque.size());
    
    assertEquals(1, (int)(deque.removeLast()));
    assertEquals(1, deque.size());
    
    String inDeque = "";
    for (Integer i : deque) {
      inDeque += i;
    }
    assertEquals("3", inDeque);
    
    assertEquals(3, (int)(deque.removeLast()));
    assertEquals(0, deque.size());
    
    inDeque = "";
    for (Integer i : deque) {
      inDeque += i;
    }
    assertEquals("", inDeque);
    
    deque.addLast(1);
    assertEquals(1, deque.size());
    deque.addLast(3);
    assertEquals(2, deque.size());
    
    assertEquals(3, (int)(deque.removeLast()));
    assertEquals(1, deque.size());
    
    inDeque = "";
    for (Integer i : deque) {
      inDeque += i;
    }
    assertEquals("1", inDeque);
    
    assertEquals(1, (int)(deque.removeLast()));
    assertEquals(0, deque.size());
    
    inDeque = "";
    for (Integer i : deque) {
      inDeque += i;
    }
    assertEquals("", inDeque);
  }
  
}
