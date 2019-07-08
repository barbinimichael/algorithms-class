import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

// Represents statistical testing of percolation
public class PercolationStats {
	
	// z value for 95 percent confidence interval
		private static final double CONFIDENCE_95 = 1.96;
	
	private Percolation percolation;
	private int n;
	private int trials;
	// percolation thresholds for t trials
	private double[] thresholds;

	
	// perform independent trials on an n-by-n grid
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException();
		} else {
			this.n = n;
			this.trials = trials;
			this.thresholds = new double[this.trials];

			this.findThreshold();
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(this.thresholds);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(this.thresholds);
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		// sample mean - z * std / sqrt(n)
		return this.mean() - CONFIDENCE_95 * (this.stddev() / Math.sqrt(this.trials));
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		// sample mean + z * std / sqrt(n)
		return this.mean() + CONFIDENCE_95 * (this.stddev() / Math.sqrt(this.trials));
	}

	// test client
	public static void main(String[] args) {
		/*
		 * Take two command line arguments n and T where n represents the n x n grid and
		 * T represents the number of tests to perform
		 */
		int n = StdIn.readInt();
		int trials = StdIn.readInt();

		PercolationStats stats = new PercolationStats(n, trials);
		StdOut.println("mean " + stats.mean());
		StdOut.println("stddev " + stats.stddev());
		StdOut.println("95 % confidence interval = [" + stats.confidenceLo() + " , " + stats.confidenceHi() + "]");
		
		System.out.println((double) 15 / (5 * 5));

	}

	// determine threshold value for t trials
	private void findThreshold() {
		for (int i = 0; i < this.trials; i++) {
			this.percolation = new Percolation(n);
			// determine number of sites need to be opened before percolates
			int count = 0;
			while (!percolation.percolates()) {
				boolean notOpen = true;
				// choose a site at random that is blocked
				while (notOpen) {
					int randomRow = StdRandom.uniform(this.n) + 1;
					int randomCol = StdRandom.uniform(this.n) + 1;

					// open the site and track
					if (!percolation.isOpen(randomRow, randomCol)) {
						notOpen = false;
						percolation.open(randomRow, randomCol);
						count++;
					}
				}
			}
			this.thresholds[i] = (double) count / (double) (this.n * this.n);
			// System.out.println(this.thresholds[i]);
		}
	}
}
