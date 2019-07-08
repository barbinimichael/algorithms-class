import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* Percolation
 * if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), 
 * what is the probability that the system percolates?
 */

public class Percolation {

	// index representing the top layer of cells
	private int top;

	// side length of grid
	private int n;

	// using Weighted union-find for checking for percolation
	// contains a n x n array for storing connected components
	// if cell in first layer open, connect to n*n + 1 entry
	private WeightedQuickUnionUF unionFind;

	// array to track whether site is open, true if open, false else
	// * better option would have been to track whether open by having
	// index be negative if closed, positive if open
	private boolean[] statusSites;

	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("n must be positive");
		} else {
			this.n = n;
			this.top = n * n;

			this.unionFind = new WeightedQuickUnionUF(n * n + 1);

			this.statusSites = new boolean[n * n];
			this.initialize();
		}
	}

	// open site (row, col) if it is not open already
	// connect to surrounding open sites
	public void open(int row, int col) {
		this.checkRange(row, col);

		int currentCell = this.findCell(row, col);

		// mark as open
		this.statusSites[currentCell] = true;

		// connect to top layer node if in top layer
		if(row == 1) {
			this.unionFind.union(currentCell, this.top);
		}

		if (row > 1) {
			if (this.isOpen(row - 1, col))
				this.unionFind.union(currentCell, this.findCell(row - 1, col));
		}
		if (row < n) {
			if (this.isOpen(row + 1, col))
				this.unionFind.union(currentCell, this.findCell(row + 1, col));
		}
		if (col > 1) {
			if (this.isOpen(row, col - 1))
				this.unionFind.union(currentCell, this.findCell(row, col - 1));
		}
		if (col < n) {
			if (this.isOpen(row, col + 1))
				this.unionFind.union(currentCell, this.findCell(row, col + 1));
		}
	}

	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		this.checkRange(row, col);

		return this.statusSites[this.findCell(row, col)];
	}

	// is site (row, col) full?
	// full if connected to the top row
	public boolean isFull(int row, int col) {
		this.checkRange(row, col);

		return this.isOpen(row, col) && this.unionFind.connected(this.findCell(row, col), this.top);
	}

	// number of open sites
	public int numberOfOpenSites() {
		int count = 0;

		for (int i = 1; i <= this.n; i++) {
			for (int j = 1; j <= this.n; j++) {
				if (this.isOpen(i, j)) {
					count++;
				}
			}
		}
		return count;
	}

	// does the system percolate?
	public boolean percolates() {
		for (int i = 1; i <= this.n; i++) {
			if (this.isFull(this.n, i)) {
				return true;
			}
		}
		return false;
	}

	// check that requested index is within range
	// by convention, sites range from 1 to n, where (1, 1) is the upper-left corner
	private void checkRange(int row, int column) {
		if (row <= 0 || row > n || column <= 0 || column > n) {
			throw new IllegalArgumentException("Requested index out of bounds");
		}
	}

	// initialize sites as all closed
	private void initialize() {
		for (int i = 0; i < this.n * this.n; i++) {
			this.statusSites[i] = false;
		}
	}

	// determine the cell given the row and column
	private int findCell(int row, int col) {
		return (row - 1) * this.n + (col - 1);
	}

	// test client
	public static void main(String[] args) {
		Percolation percolation = new Percolation(10);
		/*
		 * System.out.println(percolation.isFull(1, 1));
		 * System.out.println(percolation.isOpen(1, 1));
		 */
		percolation.open(2, 1);
		// System.out.println(percolation.isFull(2, 1));
		percolation.open(1, 1);
		/*
		 * System.out.println(percolation.isFull(1, 1));
		 * System.out.println(percolation.isFull(2, 1));
		 * System.out.println(percolation.isFull(2, 2));
		 */
		percolation.open(2, 2);
		/*
		 * System.out.println(percolation.isFull(1, 1));
		 * System.out.println(percolation.isFull(2, 1));
		 * System.out.println(percolation.isFull(2, 2));
		 */
		System.out.println(percolation.isFull(2, 3));
		percolation.open(2, 3);
		System.out.println(percolation.isFull(2, 3));
		System.out.println(percolation.isFull(2, 4));
		percolation.open(2, 4);
		System.out.println(percolation.isFull(2, 4));
		System.out.println(percolation.isFull(1, 4));
		percolation.open(1, 4);
		System.out.println(percolation.isFull(1, 4));
		System.out.println(percolation.numberOfOpenSites());
		System.out.println(percolation.percolates());
		percolation.open(3, 4);
		percolation.open(4, 4);
		percolation.open(5, 4);
		percolation.open(6, 4);
		percolation.open(7, 4);
		percolation.open(8, 4);
		percolation.open(9, 4);
		percolation.open(10, 4);
		System.out.println(percolation.numberOfOpenSites());
		System.out.println(percolation.percolates());
		System.out.println(percolation.isFull(1, 10));
		percolation.open(2, 6);
		System.out.println(percolation.isFull(2, 6));
	}
}
