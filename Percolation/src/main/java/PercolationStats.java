import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private static final double PERCENT95 = 1.96;
  private final int[] fractions;
  private final int size;
  private double threshold;
  private double deviation;
  private double confidenceLo;
  private double confidenceHi;

  public PercolationStats(int n, int trials) {
    if (n <= 0) {
      throw new IllegalArgumentException(String.format("Array size is wrong %d", n));
    }
    if (trials <= 0) {
      throw new IllegalArgumentException(String.format("Trials size is wrong %d", n));
    }
    size = trials;
    fractions = new int[trials];
    Percolation percolation;
    for (int i = 0; i < trials; i++) {
      percolation = new Percolation(n);
      while (!percolation.percolates()) {
        randomOpen(n, percolation);
      }
      fractions[i] = percolation.numberOfOpenSites();
    }
  }

  private void randomOpen(int n, Percolation percolation) {
    percolation.open(randomize(n), randomize(n));
  }

  private int randomize(int n) {
    int rnd = -1;
    while (rnd < 1 || rnd > n) {
      rnd = StdRandom.uniform(1, n+1);
    }
    return rnd;
  }

  public double mean() {
    if (threshold == 0d) {
      threshold = StdStats.mean(fractions);
    }
    return threshold;
  }

  public double stddev() {
    if (deviation == 0) {
      if (threshold == 0) {
        mean();
      }
      deviation = StdStats.stddev(fractions);
    }
    return deviation;
  }

  public double confidenceLo() {
    if (confidenceLo == 0) {
      confidenceLo = threshold - halfConfidence();
    }
    return confidenceLo;
  }

  public double confidenceHi() {
    if (confidenceHi == 0) {
      confidenceHi = threshold + halfConfidence();
    }
    return confidenceHi;
  }

  private double halfConfidence() {
    return PERCENT95 * deviation / Math.sqrt(size);
  }

  public static void main(String[] args) {
    PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    StdOut.printf("mean                     = %f\n", stats.mean());
    StdOut.printf("stddev                   = %f\n", stats.stddev());
    StdOut.printf("95%% confidence interval  = [ %f , %f ]\n", stats.confidenceLo(), stats.confidenceHi());
  }

}
