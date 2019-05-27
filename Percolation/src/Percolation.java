import edu.princeton.cs.algs4.*;

/* Percolation
 * if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), 
 * what is the probability that the system percolates?
 */

class Percolation {

	// percolation system size is n x n grid
	int n;
	int[][] system;

	Percolation(int n) {
		this.n= n;
		this.system = new int[n][n];
		
		this.initializeSystem();
	}

	// open site (row, col) if it is not open already
	public void open(int row, int col) {

	}

	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		return false;
	}

	// is site (row, col) full?
	public boolean isFull(int row, int col) {
		return false;
	}

	// number of open sites
	public int numberOfOpenSites() {
		return 0;
	}

	// does the system percolate?
	public boolean percolates() {
		return false;
	}

	// initialize percolation system with only blocked sites
	private void initializeSystem() {
		for(int row = 0; row < n; row++) {
			for(int column = 0; column < n; column++) {
				this.system[row][column] = 0;
			}
		}
	}

	// test client
	public static void main(String[] args) {

	}
}
