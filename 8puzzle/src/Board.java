import java.util.Arrays;
import java.util.Iterator;

public class Board {

  // n^2 integers where 0 represents the blank square
  private int[][] tiles;

  // create a board from an n-by-n array of tiles,
  // where tiles[row][col] = tile at (row, col)
  public Board(int[][] tiles) {
    this.tiles = tiles;
  }

  // string representation of this board
  public String toString() {
    String stringRep = this.tiles.length + "\n";

    for (int[] column : tiles) {
      for (int tile : column) {
        stringRep += (tile + " ");
      }
      stringRep += "\n";
    }

    return stringRep;
  }

  // board dimension n
  public int dimension() {
    return tiles.length;
  }

  // number of tiles out of place
  public int hamming() {
    int wrongCount = 0;

    for (int i = 0; i < this.tiles.length; i++) {
      for (int j = 0; j < this.tiles.length; j++) {

        if (this.tiles[i][j] != i * this.tiles.length + j + 1) {
          wrongCount++;
        }
      }
    }

    return wrongCount - 1;
  }

  // sum of Manhattan distances between tiles and goal
  public int manhattan() {
    int wrongCount = 0;

    for (int i = 0; i < this.tiles.length; i++) {
      for (int j = 0; j < this.tiles.length; j++) {

        int current = this.tiles[i][j];
        // System.out.println("current " + current);

        int expected = i * this.tiles.length + j + 1;
        // System.out.println("expected " + expected);

        if (current != 0 && current != expected) {

          int wantedRow = (int) Math.ceil((double) (current) / this.tiles.length) - 1;
          // System.out.println("wantedRow " + wantedRow);

          int wantedColumn = current - 1 - wantedRow * this.tiles.length;
          // System.out.println("wantedColumn " + wantedColumn);

          int a = (Math.abs(i - wantedRow) + Math.abs(j - Math.abs(wantedColumn)));
          // System.out.println("a " + a);
          wrongCount += a;
        }
      }
    }

    return wrongCount;
  }

  // is this board the goal board?
  public boolean isGoal() {
    return false;
  }

  // does this board equal y?
  public boolean equals(Object y) {

    if (y instanceof Board) {
      int[][] otherTiles = ((Board) y).tiles;

      for (int i = 0; i < this.tiles.length; i++) {
        for (int j = 0; j < this.tiles.length; j++) {

          if (this.tiles[i][j] != otherTiles[i][j]) {
            return false;
          }
        }
      }
    }

    return true;
  }

  // all neighboring boards
  public Iterable<Board> neighbors() {
    return new BoardIterable();
  }

  // a board that is obtained by exchanging any pair of tiles
  public Board twin() {
    return null;
  }

  // unit testing (not graded)
  public static void main(String[] args) {
    int[] colOne = { 1, 2, 3 };
    int[] colTwo = { 4, 5, 6 };
    int[] colThree = { 7, 8, 0 };

    int[][] grid = { colOne, colTwo, colThree };

    Board board = new Board(grid);

    System.out.println(board);

    System.out.println(board.hamming());
  }

  // returns coordinates of the blank square
  private int[] findBlank() {
    for (int i = 0; i < this.dimension(); i++) {
      for (int j = 0; j < this.dimension(); j++) {
        if (this.tiles[i][j] == 0) {
          
          return new int[] {i, j};
        }
      }
    }

    throw new RuntimeException();
  }

  // Iterable
  private class BoardIterable implements Iterable<Board> {

    public Iterator<Board> iterator() {
      return new BoardIterator();
    }

  }

  // Iterator
  private class BoardIterator implements Iterator<Board> {

    private int[][][] neighbors = new int[3][dimension()][dimension()];
    private int numNeighbors = 0;

    private int count = 0;

    private BoardIterator() {
      int[] blankSquare = findBlank();

      if (blankSquare[0] != 0) {
        this.neighbors[numNeighbors++] = this.createNeighbor(0, blankSquare);

      } else if (blankSquare[0] != dimension() - 1) {
        this.neighbors[numNeighbors++] = this.createNeighbor(1, blankSquare);
      }

      if (blankSquare[1] != 0) {
        this.neighbors[numNeighbors++] = this.createNeighbor(2, blankSquare);
        
      } else if (blankSquare[1] != dimension() - 1) {
        this.neighbors[numNeighbors++] = this.createNeighbor(3, blankSquare);
      }
    }

    public boolean hasNext() {
      return this.count < this.numNeighbors;
    }

    public Board next() {
      return new Board(this.neighbors[count++]);
    }

    private int[][] createNeighbor(int direction, int[] blankSquare) {
     
      int[][] newTiles = new int[dimension()][dimension()];
      
      for (int i = 0; i < dimension(); i++) {
        for (int j = 0; j < dimension(); j++) {
          newTiles[i][j] = tiles[i][j];
        }
      }

      if (direction == 0) {
        newTiles[blankSquare[0]][blankSquare[1]] = newTiles[blankSquare[0] - 1][blankSquare[1]];
        newTiles[blankSquare[0] - 1][blankSquare[1]] = 0;

      } else if (direction == 1) {
        newTiles[blankSquare[0]][blankSquare[1]] = newTiles[blankSquare[0] + 1][blankSquare[1]];
        newTiles[blankSquare[0] + 1][blankSquare[1]] = 0;

      } else if (direction == 2) {
        newTiles[blankSquare[0]][blankSquare[1]] = newTiles[blankSquare[0]][blankSquare[1] - 1];
        newTiles[blankSquare[0]][blankSquare[1] - 1] = 0;

      } else if (direction == 3) {
        newTiles[blankSquare[0]][blankSquare[1]] = newTiles[blankSquare[0]][blankSquare[1] + 1];
        newTiles[blankSquare[0]][blankSquare[1] + 1] = 0;

      }

      return newTiles;
    }

  }
}
