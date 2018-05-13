package Algorithms_part1.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int TOP_INDEX = 0;
    private final int size;
    private final int bottomIndex;
    private final WeightedQuickUnionUF matrix;
    private boolean[] opened;
    private int openedCount = 0;

    /**
     * create n-by-n grid, with all sites blocked
     * @param n
     */
    public Percolation(int n)
    {
        if (n < 1) {
            throw new IllegalArgumentException("N shoul be >0");
        }
        size = n;
        bottomIndex = n * n + 1;
        opened = new boolean[bottomIndex];
        matrix = new WeightedQuickUnionUF(bottomIndex + 1);
        for (int i = 0; i < bottomIndex; i++) {
            opened[i] = false;
        }
    }

    /**
     * open site (row, col) if it is not open already
     * @param row
     * @param col
     */
    public void open(int row, int col)
    {
        if (!isOpen(row, col)) {
            int i = matrixIndexToRowIndex(row, col);
            if (row == 1) {
                matrix.union(TOP_INDEX, i);
            }
            if (row == size) {
                matrix.union(bottomIndex, i);
            }
            opened[i] = true;
            openedCount++;
            connectNeighbours(row, col);
        }
    }

    /**
     * connecting open site neighbours
     * @param row
     * @param col
     */
    private void connectNeighbours(int row, int col)
    {
        int i = matrixIndexToRowIndex(row, col);
        if (col > 1) {
            int left = matrixIndexToRowIndex(row, col - 1);
            if (isOpenByRowIndex(left)) {
                matrix.union(left, i);
            }
        }
        if (col < size) {
            int right = matrixIndexToRowIndex(row, col + 1);
            if (isOpenByRowIndex(right)) {
                matrix.union(i, right);
            }
        }
        if (row > 1) {
            int top = matrixIndexToRowIndex(row - 1, col);
            if (isOpenByRowIndex(top)) {
                matrix.union(top, i);
            }
        }
        if (row < size) {
            int bottom = matrixIndexToRowIndex(row + 1, col);
            if (isOpenByRowIndex(bottom)) {
                matrix.union(i, bottom);
            }
        }
    }

    /**
     * is site (row, col) open?
     * @param row
     * @param col
     * @return
     */
    public boolean isOpen(int row, int col)
    {
        checkIndexes(row, col);
        return isOpenByRowIndex(matrixIndexToRowIndex(row, col));
    }

    /**
     * is site (i) open?
     * @param i
     * @return
     */
    private boolean isOpenByRowIndex(int i)
    {
        return opened[i];
    }

    /**
     * is site (row, col) full?
     * @param row
     * @param col
     * @return
     */
    public boolean isFull(int row, int col)
    {
        checkIndexes(row, col);
        int i = matrixIndexToRowIndex(row, col);
        return matrix.connected(TOP_INDEX, i);
    }

    /**
     * number of open sites
     * @return
     */
    public int numberOfOpenSites()
    {
        return openedCount;
    }

    /**
     * does the system percolate?
     * @return
     */
    public boolean percolates()
    {
        return matrix.connected(TOP_INDEX, bottomIndex);
    }

    /**
     * converting (row, col) to flat index in find-union
     * @param row
     * @param col
     * @return
     */
    private int matrixIndexToRowIndex(int row, int col)
    {
        return (row - 1) * size + col;
    }

    /**
     * checking if indexes are not out of boundaries
     * @param row
     * @param col
     */
    private void checkIndexes(int row, int col)
    {
        if (row < 1) {
            throw new IllegalArgumentException(
                "The row# must be greater than 0."
            );
        }
        if (col < 1) {
            throw new IllegalArgumentException(
                "The column# must be greater than 0."
            );
        }
        if (row > size) {
            throw new IllegalArgumentException(
                "The row# must be less than ".concat(Integer.toString(size))
            );
        }
        if (col > size) {
            throw new IllegalArgumentException(
                "The column# must be less than ".concat(Integer.toString(size))
            );
        }
    }
}
