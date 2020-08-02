import edu.princeton.cs.algs4.StdStats;

import java.util.Scanner;

public class PercolationStats {

    private final double[] res;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.res = new double[trials];
        java.util.Random rnd = new java.util.Random();

        for (int i = 0; i < trials; i++) {
            Percolation pcl = new Percolation(n);

            do {
                int row = rnd.nextInt(n) + 1;
                int col = rnd.nextInt(n) + 1;

                pcl.open(row, col);

            } while (!pcl.percolates());

            this.res[i] = (double) pcl.numberOfOpenSites() / (double) (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.res);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.res);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - this.stddev();
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + this.stddev();
    }

    // test client (see below)
    public static void main(String[] args) {
        int n;
        int trials;

        try {

            n = Integer.parseInt(args[0]);
            trials = Integer.parseInt(args[1]);

        } catch (RuntimeException e) {
            Scanner scn = new Scanner(System.in);

            System.out.println("Enter n:");
            n = scn.nextInt();

            System.out.println("Enter trials:");
            trials = scn.nextInt();
            scn.close();
        }

        PercolationStats ps = new PercolationStats(n, trials);

        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}