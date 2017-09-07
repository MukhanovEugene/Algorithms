import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private final boolean[][] data;
  private final int count;
  private int openSites = 0;
  private final int size; // 0 - will be root, It's the abstract cell.
  private final WeightedQuickUnionUF quickUnionUF;
  private final boolean[] open;
  private boolean percolated;

  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException(String.format("Array size is %d", n));
    }
    count = n;
    size = n * n + 1;
    data = new boolean[n][n];
    quickUnionUF = new WeightedQuickUnionUF(size + 1); // 0 - top root and count + 2 - bottom roor
    open = new boolean[size + 1];
    open[0] = true; // top root should be open
  }

  private int index(int row, int col) {
    checkRow(row);
    checkCol(col);
    return (row - 1) * count + col;
  }

  private void checkRow(int row) {
    --row;
    if (row < 0 || row >= count) {
      throw new IllegalArgumentException(String.format("Row index is out of range %d.", row));
    }
  }

  private void checkCol(int col) {
    --col;
    if (col < 0 || col >= count) {
      throw new IllegalArgumentException(String.format("Column index is out of range %d.", col));
    }
  }

  public void open(int row, int col) {
    checkRow(row);
    checkCol(col);
    if (open[index(row, col)]) return;
    open[index(row, col)] = true;
    ++openSites;
    if (row - 1 == 0) {
      quickUnionUF.union(0, index(row, col)); // union with top root
    }
//    if (row == count) {
//      quickUnionUF.union(size, index(row, col)); // union with bottom root
//    }
    tryToUnion(row, col);
    tryToFill(row, col);
  }

  private void tryToFill(int row, int col) {
    checkRow(row);
    checkCol(col);
    if (
      index(row, col) != size && open[index(row, col)] &&
      !data[row - 1][col - 1]
      && quickUnionUF.connected(0, index(row, col))) {
      data[row - 1][col - 1] = true;
      tryToFillNeighbors(row, col);
    }
  }

  private void tryToFillNeighbors(int row, int col) {
    try {
      tryToFillTop(row, col);
    } catch (IllegalArgumentException e) { /* Shouldn't throw it out */ }
    try {
      tryToFillBottom(row, col);
    } catch (IllegalArgumentException e) { /* Shouldn't throw it out */ }
    try {
      tryToFillLeft(row, col);
    } catch (IllegalArgumentException e) { /* Shouldn't throw it out */ }
    try {
      tryToFillRight(row, col);
    } catch (IllegalArgumentException e) { /* Shouldn't throw it out */ }
  }

  private void tryToFillRight(int row, int col) {
    tryToFill(row, col + 1);
  }

  private void tryToFillLeft(int row, int col) {
    tryToFill(row, col - 1);
  }

  private void tryToFillBottom(int row, int col) {
    tryToFill(row + 1, col);
  }

  private void tryToFillTop(int row, int col) {
    tryToFill(row - 1, col);
  }

  private void tryToUnion(int row, int col) {
    try {
      unionTop(row, col);
    } catch (IllegalArgumentException ex) {
      /* dont' throw up */
    }
    try {
      unionRight(row, col);
    } catch (IllegalArgumentException ex) {
      /* dont' throw up */
    }
    try {
      unionBottom(row, col);
    } catch (IllegalArgumentException ex) {
      /* dont' throw up */
    }
    try {
      unionLeft(row, col);
    } catch (IllegalArgumentException ex) {
      /* dont' throw up */
    }
  }

  private void unionTop(int row, int col) {
    union(row, col, row - 1, col);
  }

  private void union(int row, int col, int row2, int col2) {
    if (isOpen(row, col) && isOpen(row2, col2)) {
      quickUnionUF.union(index(row, col), index(row2, col2));
      tryToFill(row2, col2);
    }
  }

  private void unionRight(int row, int col) {
    union(row, col, row, col + 1);
  }

  private void unionBottom(int row, int col) {
    union(row, col, row + 1, col);
  }

  private void unionLeft(final int row, int col) {
    union(row, col, row, col - 1);
  }

  public boolean isOpen(int row, int col) {
    checkRow(row);
    checkCol(col);
    return open[index(row, col)];
  }

  public boolean isFull(int row, int col) {
    checkRow(row);
    checkCol(col);
    return data[row - 1][col - 1];
  }

  public int numberOfOpenSites() {
    return openSites;
  }

  public boolean percolates() {
    if (!percolated) {
      for (int i = size - count; i < size; i++) {
        if (quickUnionUF.connected(0, i)) {
          percolated = true;
          break;
        }
      }
    }
    return percolated;
  }

}