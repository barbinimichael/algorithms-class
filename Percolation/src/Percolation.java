import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* Percolation
 * if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), 
 * what is the probability that the system percolates?
 */

class Percolation {

	// side length of grid
	int n;
	// size of grid
	int size;

	// using Weighted union-find for checking for percolation
	// contains a n x n array for storing connected components
	WeightedQuickUnionUF unionFind;

	// array to track whether site is open, true if open, false else
	// * better option would have been to track whether open by having
	// index be negative if closed, positive if open
	boolean[] statusSites;

	Percolation(int n) {
		this.n= n;
		this.size = n*n;

		this.unionFind  = new WeightedQuickUnionUF(size);
		this.statusSites = new boolean[size];
		this.initialize();
	}

	// open site (row, col) if it is not open already
	// connect to surrounding open sites
	public void open(int row, int col) {
		this.checkRange(row, col);

		int currentCell = this.findCell(row, col);
		
		this.statusSites[currentCell] = true;

		if(row > 1) {
			if(this.isOpen(row - 1, col))
				this.unionFind.union(currentCell, this.findCell(row - 1, col));
		}
		if(row < n) {
			if(this.isOpen(row + 1, col))
				this.unionFind.union(currentCell, this.findCell(row + 1, col));
		}
		if(col > 1) {
			if(this.isOpen(col - 1, col))
				this.unionFind.union(currentCell, this.findCell(row, col - 1));
		}
		if(col < n ) {
			if(this.isOpen(col + 1, col))
				this.unionFind.union(currentCell, this.findCell(row, col + 1));
		}
	}

	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		this.checkRange(row, col);

		return statusSites[this.findCell(row, col)];
	}

	// is site (row, col) full?
	// full if connected to the top row
	public boolean isFull(int row, int col) {
		this.checkRange(row, col);
		int currentCell = this.findCell(row, col);

		for(int n = 0; n < this.n; n++) {
			int currentTopCell = this.findCell(0, n);
			if(this.unionFind.connected(this.unionFind.find(currentCell), this.unionFind.find(currentTopCell))) {
				return true;
			}
		}
		
		return false;
	}

	// number of open sites
	public int numberOfOpenSites() {
		int count = 0;
		
		for(int n = 0; n < this.size; n++) {
			if(this.statusSites[n])
				count++;
		}
		return count;
	}

	// does the system percolate?
	public boolean percolates() {
		for(int n = 0; n < this.n; n++) {
			if(this.isFull(this.n, n)) {
				return true;
			}
		}
		return false;
	}

	// check that requested index is within range
	// by convention, sites range from 1 to n, where (1, 1) is the upper-left corner
	private void checkRange(int row, int column) {
		if(row < 0 || row > n || column < 0 || column > n) {
			throw new IllegalArgumentException("Requested index out of bounds");
		}
	}

	// initialize sites as all closed
	private void initialize() {
		for(int n = 0; n < this.size; n++) {
			this.statusSites[n] = false;
		}
	}

	// determine the cell given the row and column 
	private int findCell(int row, int col) {
		return (row - 1) * this.n + (col - 1);
	}

	// test client
	public static void main(String[] args) {
		Percolation percolation = new Percolation(4);
		System.out.println(percolation.unionFind.find(percolation.findCell(3, 4)));
	}
}
