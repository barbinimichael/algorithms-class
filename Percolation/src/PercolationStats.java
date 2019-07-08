import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

// Represents statistical testing of percolation
public class PercolationStats {

	// z value for 95 percent confidence interval
	private static final double CONFIDENCE_95 = 1.96;

	private double mean;
	private double stddev;
	private double confidenceLo;
	private double confidenceHi;

	// perform independent trials on an n-by-n grid
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException();
		} else {
			this.calcStats(n, trials);
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		return this.mean;
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return this.stddev;
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		return this.confidenceLo;
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return this.confidenceHi;
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
		 StdOut.println("95 % confidence interval = [" + stats.confidenceLo() + " , "
		 + stats.confidenceHi() + "]");

	}

	// calc all statistical values
	private void calcStats(int n, int trials) {
		double[] thresholds = this.findThreshold(n, trials);
		this.mean = this.calcMean(thresholds);
		this.stddev = this.calcStddev(thresholds);
		this.confidenceLo = this.calcConfidenceLo(trials);
		this.confidenceHi = this.calcConfidenceHi(trials);
	}

	// sample mean of percolation threshold
	private double calcMean(double[] thresholds) {
		return StdStats.mean(thresholds);
	}

	// sample standard deviation of percolation threshold
	private double calcStddev(double[] thresholds) {
		return StdStats.stddev(thresholds);
	}

	// low endpoint of 95% confidence interval
	private double calcConfidenceLo(int trials) {
		// sample mean - z * std / sqrt(n)
		return this.mean - CONFIDENCE_95 * (this.stddev / Math.sqrt(trials));
	}

	// high endpoint of 95% confidence interval
	private double calcConfidenceHi(int trials) {
		// sample mean + z * std / sqrt(n)
		return this.mean + CONFIDENCE_95 * (this.stddev / Math.sqrt(trials));
	}

	// determine threshold value for t trials
	private double[] findThreshold(int n, int trials) {
		double[] thresholds = new double[trials];

		for (int i = 0; i < trials; i++) {
			Percolation percolation = new Percolation(n);
			// determine number of sites need to be opened before percolates
			int count = 0;
			while (!percolation.percolates()) {
				boolean notOpen = true;
				// choose a site at random that is blocked
				while (notOpen) {
					int randomRow = StdRandom.uniform(n) + 1;
					int randomCol = StdRandom.uniform(n) + 1;

					// open the site and track
					if (!percolation.isOpen(randomRow, randomCol)) {
						notOpen = false;
						percolation.open(randomRow, randomCol);
						count++;
					}
				}
			}
			thresholds[i] = (double) count / (double) (n * n);
		}
		return thresholds;
	}
}
