import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestBoard {
  
  private static Board createRandomBoard(int n) {
    List<Integer> orderedTiles = new ArrayList<Integer>();
    
    for (int i = 0; i < n * n; i++) {
      orderedTiles.add(i);
    }
    
    Collections.shuffle(orderedTiles);
    
    int[][] tiles = new int[n][n];
    
    for (int i = 0; i < n; i++) {
      
      int[] row = new int[n];
      for (int j = 0; j < n; j++) {
        row[j] = orderedTiles.get(i * n + j);
      }
      tiles[i] = row;
    }
    
    return(new Board(tiles));
  }
  
  public static void main(String[] args) {
    Board a = createRandomBoard(1);
    System.out.println(a.toString());
    
    System.out.println("----------------------------");
    
    Board b = createRandomBoard(2);
    System.out.println(b.toString());
    System.out.println(b.dimension());
    
    for (Board neighbor : b.neighbors()) {
      System.out.println(neighbor);
    }
    
    System.out.println("Original");
    System.out.println(b.toString());
    
    System.out.println("----------------------------");
    
    Board bTwo = createRandomBoard(2);
    System.out.println(bTwo.toString());
    System.out.println(bTwo.hamming());
    System.out.println(bTwo.manhattan());
    
    System.out.println("----------------------------");
    
    System.out.println(b.equals(bTwo));
    
    System.out.println("----------------------------");
    
    Board c = createRandomBoard(3);
    System.out.println(c.toString());    
    System.out.println(c.hamming());
    System.out.println(c.manhattan());
  }
}
