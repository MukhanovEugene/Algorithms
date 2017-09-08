import java.util.BitSet;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private final int count;
  private int openSites = 0;
  private final int size; // 0 - will be root, It's the abstract cell.
  private final WeightedQuickUnionUF quickUnionUF;
  private final BitSet open;
  private final BitSet filled;
  private boolean percolated;

  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException(String.format("Array size is %d", n));
    }
    count = n;
    size = n * n + 1;
    quickUnionUF = new WeightedQuickUnionUF(size + 1); // 0 - top root and count + 2 - bottom roor
    open = new BitSet(size + 1);
    filled = new BitSet(size + 1);
    open.set(0,true);
    filled.set(0,true);
  }

  private int index(int row, int col) {
    checkRow(row);
    checkCol(col);
    return (row - 1) * count + col;
  }

  private int[] rowCol(int idx) {
    int col = idx % count;
    int row = idx / count;
    if (col == 0) {
      --row;
      col = count;
    }
    return new int[]{++row, col};
  }

  private void checkRow(int row) {
    if (row == 0 || row > count) {
      throw new IllegalArgumentException(String.format("Row index is out of range %d.", --row));
    }
  }

  private void checkCol(int col) {
    if (col == 0 || col > count) {
      throw new IllegalArgumentException(String.format("Column index is out of range %d.", --col));
    }
  }

  public void open(int row, int col) {
    checkRow(row);
    checkCol(col);
    int idx = index(row, col);
    if (open.get(idx)) return;
    open.set(idx,true);
    ++openSites;
    if (row - 1 == 0 && !filled.get(idx)) {
      quickUnionUF.union(0, idx); // union with top root
      filled.set(idx,true);
      if (row == count) { // only for case with one row!!!
        unionToBottomRoot(idx);
      }
    }
    tryToUnion(row, col);
    tryToFillInCircle(row, col);
  }

  private void tryToFillInCircle(int row, int col) {
    int[] rc;
    int idx = index(row, col);
    for (int i = 1; i < idx + count + 1 && i <= size; i++) {
      if (filled.get(i)) {
        idx = i;
      } else if (open.get(i)) {
        rc = rowCol(i);
        if (tryToFill(rc[0], rc[1])) {
          idx = i;
          i = 1;
        }
      }
    }
  }

  private void tryToUnion(int row, int col) {
    try {
      union(row, col, row - 1, col);
    } catch (IllegalArgumentException ex) {
      /* dont' throw up */
    }
    try {
      union(row, col, row, col + 1);
    } catch (IllegalArgumentException ex) {
      /* dont' throw up */
    }
    try {
      union(row, col, row + 1, col);
    } catch (IllegalArgumentException ex) {
      /* dont' throw up */
    }
    try {
      union(row, col, row, col - 1);
    } catch (IllegalArgumentException ex) {
      /* dont' throw up */
    }
  }

  private void unionToBottomRoot(int idx) {
    quickUnionUF.union(size, idx); // union with top root
  }

  private void union(int row, int col, int row2, int col2) {
    int idx = index(row, col);
    int idx2 = index(row2, col2);
    if (open.get(idx) && open.get(idx2) && (!filled.get(idx) || !filled.get(idx2))) {
      quickUnionUF.union(idx, idx2);
      fillBoth(idx, idx2, row, row2);
    }
  }

  private boolean tryToFill(int row, int col) {
    boolean retVal = false, retVal2 = false, retVal3 = false, retVal4 = false;
    try {
      retVal = fill(row, col, row - 1, col);
    } catch (IllegalArgumentException ex) {
      /* dont' throw up */
    }
    try {
      retVal2 = fill(row, col, row, col + 1);
    } catch (IllegalArgumentException ex) {
      /* dont' throw up */
    }
    try {
      retVal3 = fill(row, col, row + 1, col);
    } catch (IllegalArgumentException ex) {
      /* dont' throw up */
    }
    try {
      retVal4 = fill(row, col, row, col - 1);
    } catch (IllegalArgumentException ex) {
      /* dont' throw up */
    }
    return retVal || retVal2 || retVal3 || retVal4;
  }

  private boolean fill(int row, int col, int row2, int col2) {
    int idx = index(row, col);
    int idx2 = index(row2, col2);
    return fillBoth(idx, idx2, row, row2);
  }

  private boolean fillBoth(int idx, int idx2, int row, int row2) {
    if (open.get(idx) && open.get(idx2)) {
      if (filled.get(idx) && !filled.get(idx2)) {
        filled.set(idx2, true);
        if (row2 == count) {
          unionToBottomRoot(idx2);
        }
        return true;
      } else if (!filled.get(idx) && filled.get(idx2)) {
        filled.set(idx, true);
        if (row == count) {
          unionToBottomRoot(idx);
        }
        return true;
      }
    }
    return false;
  }

  public boolean isOpen(int row, int col) {
    checkRow(row);
    checkCol(col);
    return open.get(index(row, col));
  }

  public boolean isFull(int row, int col) {
    checkRow(row);
    checkCol(col);
    return filled.get(index(row, col));
  }

  public int numberOfOpenSites() {
    return openSites;
  }

  public boolean percolates() {
    if (!percolated) {
      percolated = quickUnionUF.connected(0, size);
    }
    return percolated;
  }

}