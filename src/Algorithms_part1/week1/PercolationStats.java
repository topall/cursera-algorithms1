package Algorithms_part1.week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_K = 1.96;
    private final int trials;
    private final double[] tresholds;

    public PercolationStats(int n, int t)
    {
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException("Arguments should be >0.");
        }
        trials = t;
        tresholds = new double[t];
        int randomRow, randomCol, openCount;
        for (int i = 0; i < t; i++) {
            openCount = 0;
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                randomRow = StdRandom.uniform(1, n + 1);
                randomCol = StdRandom.uniform(1, n + 1);
                if (percolation.isOpen(randomRow, randomCol)) {
                    continue;
                }
                percolation.open(randomRow, randomCol);
                openCount++;
            }
            tresholds[i] = (double) openCount / (n * n);
        }
    }

    public double mean()
    {
        return StdStats.mean(tresholds);
    }

    public double stddev()
    {
        return StdStats.stddev(tresholds);
    }

    public double confidenceLo()
    {
        return  mean() - CONFIDENCE_K * stddev() / Math.sqrt(trials);
    }

    public double confidenceHi()
    {
        return  mean() + CONFIDENCE_K * stddev() / Math.sqrt(trials);
    }

    public static void main(String[] args)
    {
        int row = Integer.parseInt(args[0]);
        int col = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(row, col);
        System.out.println("mean:" + percolationStats.mean());
        System.out.println("standard deviation:" + percolationStats.stddev());
        System.out.println(
            "95% confience interval:"
            + percolationStats.confidenceLo()
            + "," + percolationStats.confidenceHi()
        );
    }
}
