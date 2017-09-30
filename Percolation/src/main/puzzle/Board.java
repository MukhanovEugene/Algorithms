import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class Board {

  private final int rows;
  private final int columns;
  private final int manhattan;
  private int hamming;
  private int neighborsCount = 0;
  private final int[][] blocks;
  private int[] zero;
  private int hashCode = 1;
  private int[] originPos;
  private Board[] neighbors;
  private int rTwin1;
  private int cTwin1;
  private int rTwin2;
  private int cTwin2;
  private int[][] twin = null;

  public Board(int[][] blocks) {         
    if (blocks == null) throw new IllegalArgumentException();
    rows = blocks.length;
    columns = blocks[0].length;
    originPos = new int[2];
    if (rows != columns) {
      throw new IllegalArgumentException();
    }
    this.blocks = blocks;
    this.manhattan = getManhattan(this.blocks);
    generateTwinIdxs();
  }

  private void generateTwinIdxs() {
    if (blocks[0][0] != 0 && blocks[0][1] != 0) {
      rTwin1 = 0;
      cTwin1 = 0;
      rTwin2 = 0;
      cTwin2 = 1;
    } else if (blocks[1][0] != 0 && blocks[1][1] != 0) {
      rTwin1 = 1;
      cTwin1 = 0;
      rTwin2 = 1;
      cTwin2 = 1;
    } else if (blocks[0][1] != 0 && blocks[1][1] != 0) {
      rTwin1 = 0;
      cTwin1 = 1;
      rTwin2 = 1;
      cTwin2 = 1;
    } else if (blocks[0][0] != 0 && blocks[1][0] != 0) {
      rTwin1 = 0;
      cTwin1 = 0;
      rTwin2 = 1;
      cTwin2 = 0;
    }
  }

  private Board[] getNeighbors() {
    if (neighbors == null || neighbors.length < 1) {
      neighbors = getNeighbors(blocks, zero);
    }
    return neighbors;
  }

  private Board[] getNeighbors(int[][] blocks, int[] z) {
    Board temp1 = null;
    Board temp2 = null;
    Board temp3 = null;
    Board temp4 = null;

    if (z[0] < (rows - 1)) {
      temp1 = new Board(exchangeInRows(blocks, z[0], z[0] + 1, z[1]));
      neighborsCount++;
    }
    if (z[0] > 0) {
      temp2 = new Board(exchangeInRows(blocks, z[0], z[0] - 1, z[1]));
      neighborsCount++;
    }
    if (z[1] < (columns - 1)) {
      temp3 = new Board(exchangeInCols(blocks, z[0], z[1], z[1] + 1));
      neighborsCount++;
    }
    if (z[1] > 0) {
      temp4 = new Board(exchangeInCols(blocks, z[0], z[1], z[1] - 1));
      neighborsCount++;
    }

    Board[] neighbors = new Board[neighborsCount];
    int idx = neighborsCount;

    if (temp1 != null && idx > 0) {
      neighbors[--idx] = temp1;
    }
    if (temp2 != null && idx > 0) {
      neighbors[--idx] = temp2;
    }
    if (temp3 != null && idx > 0) {
      neighbors[--idx] = temp3;
    }
    if (temp4 != null && idx > 0) {
      neighbors[--idx] = temp4;
    }

    return neighbors;
  }

  private int[][] exchangeInCols(int[][] blocks, int w, int cEx, int cNew) {
    int[][] oneStep = new int[rows][columns];
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        if (r == w && c == cEx) {
          oneStep[r][cEx] = blocks[r][cNew];
        } else if (r == w && c == cNew) {
          oneStep[r][cNew] = blocks[r][cEx];
        } else {
          oneStep[r][c] = blocks[r][c];
        }
      }
    }
    return oneStep;
  }

  private int[][] exchangeInRows(int[][] blocks, int rEx, int rNew, int l) {
    int[][] oneStep = new int[rows][columns];
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        if (r == rEx && c == l) {
          oneStep[rEx][c] = blocks[rNew][c];
        } else if (r == rNew && c == l) {
          oneStep[rNew][c] = blocks[rEx][c];
        } else {
          oneStep[r][c] = blocks[r][c];
        }
      }
    }
    return oneStep;
  }

  private int getManhattan(int[][] b) {
    int _m = 0;
    int val;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        val = b[r][c];
        if (zero == null && val == 0) {
          zero = new int[]{r, c};
        }
        hashCode = 31 * hashCode + val * val;
        if (val != 0) {
          getOriginPosition(val);
          if (originPos[0] != r || originPos[1] != c) {
            hamming++;
          }
          _m += difeerence(r, c, originPos[0], originPos[1]);
        }
      }
    }
    hashCode = 31 * hashCode * _m;
    return _m;
  }

  private int difeerence(int r0, int c0, int r1, int c1) {
    return Math.abs(r0 - r1) + Math.abs(c0 - c1);
  }

  private int[] getOriginPosition(int v) {
    originPos[0] = rows - 1;
    originPos[1] = columns - 1;
    if (v == 0) {
      return originPos;
    }
    v--;
    originPos[0] = v / rows;
    originPos[1] = v % rows;

//    StdOut.println("V: "+(v+1)+" X: "+originPos[0]+" Y: "+originPos[1]);

    return originPos;
  }

  public int dimension() {                // board dimension n
    return rows;
  }

  public int manhattan() {                // sum of Manhattan distances between blocks and goal
    return manhattan;
  }

  public int hamming() {
    return hamming;
  }


  public boolean isGoal() {                // is this board the goal board?
    return manhattan == 0;
  }

  public Board twin() {                   // a board that is obtained by exchanging any pair of blocks
    if (twin == null) {
      twin = new int[rows][columns];
      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < rows; c++) {
          if (r == rTwin1 && c == cTwin1) {
            twin[rTwin1][cTwin1] = blocks[rTwin2][cTwin2];
          } else if (r == rTwin2 && c == cTwin2) {
            twin[rTwin2][cTwin2] = blocks[rTwin1][cTwin1];
          } else {
            twin[r][c] = blocks[r][c];
          }
        }
      }
    }
    return new Board(twin);
  }

  public boolean equals(Object y) {
    if (this == y) {
      return true;
    }
    if (y == null) {
      return false;
    }
    if (y.getClass() != Board.class) {
      return false;
    }
    Board yy = (Board) y;
    if (this.manhattan != yy.manhattan()) {
      return false;
    }

    return yy.hashCode == hashCode;
  }

  public Iterable<Board> neighbors() {    // all neighboring boards
    getNeighbors();
    return new Iterable<Board>() {
      @Override
      public Iterator<Board> iterator() {
        return new Iterator<Board>() {
          int idx = 0;

          @Override
          public boolean hasNext() {
            return idx < neighbors.length;
          }

          @Override
          public Board next() {
            return neighbors[idx++];
          }
        };
      }
    };
  }

  public String toString() {              // string representation of this board (in the output format specified below)
    String result = "" + rows + "\n";
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        result += " " + blocks[i][j];
      }
      result += "\n";
    }
    return result;
  }

}