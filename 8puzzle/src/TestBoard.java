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

    return (new Board(tiles));
  }

  public static void main(String[] args) {
    Board a = createRandomBoard(1);
    System.out.println(a.toString());

    System.out.println("----------------------------");
    System.out.println("Solving");
    Solver solveA = new Solver(a);

    for (Board step : solveA.solution())
      System.out.println(step);

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
    System.out.println("Solving");
    Solver solveB = new Solver(b);

    for (Board step : solveB.solution())
      System.out.println(step);

    System.out.println("----------------------------");

    Board bTwo = createRandomBoard(2);
    System.out.println(bTwo.toString());
    System.out.println(bTwo.hamming());
    System.out.println(bTwo.manhattan());

    System.out.println("----------------------------");

    System.out.println(b.equals(bTwo));

    System.out.println("----------------------------");

    boolean found = false;
    Board bMaybe = null;
    while (!found) {
      bMaybe = createRandomBoard(2);

      if (bMaybe.isGoal())
        found = true;
    }

    System.out.println(bMaybe);

    System.out.println("----------------------------");

    Board c = createRandomBoard(3);
    System.out.println(c.toString());
    System.out.println(c.hamming());
    System.out.println(c.manhattan());

    System.out.println("----------------------------");
    System.out.println("Solving");

    Solver solveC = new Solver(c);
    System.out.println(solveC.isSolvable());
    System.out.println(solveC.moves());
    //
    // for (Board move : solveC.solution()) {
    // System.out.println(move);
    // }

    System.out.println("----------------------------");

    for (Board neighbor : c.neighbors()) {
      System.out.println(neighbor);
    }

    System.out.println("----------------------------");

    System.out.println(c.twin());

    System.out.println("----------------------------");

    int[][] exampleTiles = { { 0, 1, 3 }, { 4, 2, 5 }, { 7, 8, 6 } };

    Board example = new Board(exampleTiles);
    System.out.println(example);

    System.out.println("----------------------------");
    System.out.println("Solving");

    Solver solveExample = new Solver(example);

    System.out.println(solveExample.isSolvable());
    System.out.println(solveExample.moves());

    for (Board move : solveExample.solution()) {
      System.out.println(move);
    }
    
    System.out.println("----------------------------");
    System.out.println("Apple".equals(example));
    System.out.println(example.equals("Apple"));
    System.out.println(c.equals(b));
    System.out.println(b.equals(c));
    
    System.out.println("----------------------------");
    
    Board twinTest = new Board(exampleTiles);
    System.out.println(twinTest);
    System.out.println(twinTest.twin());
    System.out.println(twinTest.twin());
    System.out.println(twinTest.twin());
    
    System.out.println("----------------------------");
    int[][] e = {{6, 1, 3}, {4, 2, 5}, {7, 8, 0}};
    Board eBoard = new Board(e);
    
    System.out.println(eBoard.twin());
    System.out.println(eBoard.twin());
    System.out.println(eBoard.twin());
  }
}
