import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Solver {

  private MinPQ<SearchNode> pqOriginal = new MinPQ<SearchNode>();
  private int moves = -1;

  private boolean solvable = false;

  private MinPQ<SearchNode> pqTwin = new MinPQ<SearchNode>();

  private ArrayList<Board> solution = new ArrayList<Board>();

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {

    if (initial == null)
      throw new java.lang.IllegalArgumentException();

    this.pqOriginal.insert(new SearchNode(initial, null, 0));

    this.pqTwin.insert(new SearchNode(initial.twin(), null, 0));

    this.solve();
  }

  private void solve() {

    boolean solved = false;

    while (!solved) {
      // for (int i = 0; i < 5; i++) {
      SearchNode originalMin = solveOne(pqOriginal);
      solution.add(originalMin.getBoard());

      // System.out.println(originalMin.getBoard());

      SearchNode twinMin = solveOne(pqTwin);

      if (originalMin.getBoard().isGoal()) {
        solved = true;
        this.solvable = true;
        this.moves = originalMin.getMoves();

      } else if (twinMin.getBoard().isGoal()) {
        solved = true;
      }
    }

  }

  private SearchNode solveOne(MinPQ<SearchNode> pQueue) {

    SearchNode min = pQueue.delMin();

    for (Board neighbor : min.getBoard().neighbors()) {
      if (min.getPreviousBoard() == null || !neighbor.equals(min.getPreviousBoard())) {
        pQueue.insert(new SearchNode(neighbor, min.getBoard(), min.getMoves() + 1));
      }
    }

    return min;

  }

  // is the initial board solvable?
  public boolean isSolvable() {
    return solvable;
  }

  // min number of moves to solve initial board
  public int moves() {
    return this.moves;
  }

  // sequence of boards in a shortest solution
  public Iterable<Board> solution() {
    return new SolverIterable();
  }

  // test client
  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        tiles[i][j] = in.readInt();
    Board initial = new Board(tiles);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }

  // Iterable
  private class SolverIterable implements Iterable<Board> {

    public Iterator<Board> iterator() {
      return new SolverIterator();
    }

  }

  private class SolverIterator implements Iterator<Board> {

    private int count = 0;

    public boolean hasNext() {
      return this.count < solution.size();
    }

    public Board next() {
      return solution.get(count++);
    }

  }

  private class SearchNode implements Comparable<SearchNode> {

    private Board board;
    private Board previousBoard;
    private int priority;
    private int moves;

    private SearchNode(Board board, Board previousBoard, int moves) {
      this.board = board;
      this.previousBoard = previousBoard;
      this.priority = this.board.manhattan() + moves;
      this.moves = moves;
    }
    
    public int getMoves() {
      return this.moves;
    }

    public Board getBoard() {
      return this.board;
    }

    public Board getPreviousBoard() {
      return this.previousBoard;
    }

    public int compareTo(SearchNode o) {
      return this.priority - o.priority;
    }

  }
}
