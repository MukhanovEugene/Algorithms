import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private final int count;
  private int openSites = 0;
  private final int size; // 0 - will be root, It's the abstract cell.
  private final WeightedQuickUnionUF quickUnionUF;
  private final byte[] data;
  private boolean percolated;

  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException(String.format("Array size is %d", n));
    }
    count = n;
    size = n * n + 1;
    quickUnionUF = new WeightedQuickUnionUF(size + 1); // 0 - top root and count + 2 - bottom roor
    data = new byte[size + 1];
    data[0] = 2; // top root should be open and filled
  }

  private int index(int row, int col) {
    checkRow(row);
    checkCol(col);
    return (row - 1) * count + col;
  }

  private void checkRow(int row) {
    if (row <= 0 || row > count) {
      throw new IllegalArgumentException(String.format("Row index is out of range %d.", --row));
    }
  }

  private void checkCol(int col) {
    if (col <= 0 || col > count) {
      throw new IllegalArgumentException(String.format("Column index is out of range %d.", --col));
    }
  }

  public void open(int row, int col) {
    checkRow(row);
    checkCol(col);
    int idx = index(row, col);
    if (data[idx] > 0) return;
    data[idx] = 1;
    ++openSites;
    if (row - 1 == 0 && data[idx] < 2) {
      quickUnionUF.union(0, idx); // union with top root
      data[idx] = 2;
      if (row == count) {
        unionToBottomRoot(idx);
      }
    }
    tryToUnion(idx);
    if (tryToFillInCircle(idx)) {
      data[idx] = 2;
      int i = idx - count - 1;
      if (i < 1) {
        i = 1;
      }
      int reset = i;
      for (; i <= size; i++) {
        if (data[i] == 3) {
          if (tryToFillInCircle(i)) {
            data[i] = 2;
            i = i - count - 1;
            if (i < 1) {
              i = 1;
            }
            reset = i;
          } else {
            data[i] = 2;
          }
        }
        if (i - reset > count * 2 + 1) {
          break;
        }
      }
    }
  }

  private boolean tryToFillInCircle(int idx) {
    boolean changed = false;
    int retVal = fillBoth(idx, getRight(idx));
    if (retVal > -1) {
//      data[retVal] = 3;
      changed = true;
    }
    retVal = fillBoth(idx, getLeft(idx));
    if (retVal > -1) {
//      data[retVal] = 3;
      changed = true;
    }
    retVal = fillBoth(idx, getTop(idx));
    if (retVal > -1) {
//      data[retVal] = 3;
      changed = true;
    }
    retVal = fillBoth(idx, getBottom(idx));
    if (retVal > -1) {
//      data[retVal] = 3;
      changed = true;
    }
    return changed;
  }

  private void tryToUnion(int idx) {
    union(idx, getRight(idx));
    union(idx, getLeft(idx));
    union(idx, getTop(idx));
    union(idx, getBottom(idx));
  }

  private void unionToBottomRoot(int idx) {
    quickUnionUF.union(size, idx); // union with top root
  }

  private void union(int idx, int idx2) {
    if (idx != idx2 && data[idx] > 0 && data[idx2] > 0) {
      quickUnionUF.union(idx, idx2);
    }
  }

  private int fillBoth(int idx, int idx2) {
    if (idx == idx2) return -1;
    if (data[idx] > 1 && data[idx2] == 1) {
      data[idx2] = 3;
      if (idx2 > size - count - 1) {
        unionToBottomRoot(idx2);
      }
      return idx2;
    } else if (data[idx] == 1 && data[idx2] > 1) {
      data[idx] = 3;
      if (idx > size - count - 1) {
        unionToBottomRoot(idx);
      }
      return idx;
    }
    return -1;
  }

  public boolean isOpen(int row, int col) {
    checkRow(row);
    checkCol(col);
    return data[index(row, col)] > 0;
  }

  public boolean isFull(int row, int col) {
    checkRow(row);
    checkCol(col);
    return data[index(row, col)] > 1;
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

  // ---------------------
  private int getRight(int idx) {
    if (idx % count == 0) {
      return idx;
    }
    return ++idx;
  }

  private int getBottom(int idx) {
    int tmp = idx / count;
    if (tmp == count || (tmp == count - 1 && idx % count > 0)) {
      return idx;
    }
    return idx + count;
  }

  private int getLeft(int idx) {
    if (idx % count == 1) {
      return idx;
    }
    return --idx;
  }

  private int getTop(int idx) {
    if (idx / count == 0) {
      return idx;
    }
    return idx - count;
  }

}
