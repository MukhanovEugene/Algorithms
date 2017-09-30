import org.junit.Test;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class BoardTest {

//  @Test
//  public void testOrigin1() {
//    int[][] values = {{8,2,0},{7,6,1},{3,4,5}};
//    Board board = new Board(values);
//    int[] orig4 = board.getOriginPosition(4);
//    print(orig4);
//    int[] orig3 = board.getOriginPosition(3);
//    print(orig3);
//    int[] orig1 = board.getOriginPosition(1);
//    print(orig1);
//    int[] orig8 = board.getOriginPosition(8);
//    print(orig8);
//  }


  @Test
  public void testManhattan1() {

    int[][] values = {{1,  2,  3},
                      {0,  7,  6},
                      {5,  4,  8}};
    Board board = new Board(values);
    int manh1 = board.manhattan();
    assert manh1 == 7;
    StdOut.println(board.toString());
  }

  @Test
  public void testTwin1() {
    int[][] values = {{1,  0},
      {2,  3}};
    Board board = new Board(values);
    Board twin = board.twin();
    StdOut.println(board.toString());
    assert  !board.equals(twin);
  }

  @Test
  public void testTwinsEquation3() {
    int[][] values = {{0, 1, 3},
      {4, 2, 5},
      {7, 8, 6}};
    Board board = new Board(values);
    Board twin1 = board.twin();
    Board twin2 = board.twin();
    StdOut.println(board.toString());
    StdOut.println(twin1.toString());
    StdOut.println(twin2.toString());
    assert twin1.equals(twin2) && !board.equals(twin1);
  }

  @Test
  public void testTwinsEquation4() {
    int[][] values = {{0, 2, 3, 4},
      {1, 10, 6, 8},
      {5, 9, 7, 12},
      {13, 14, 11, 15}};
    Board board = new Board(values);
    Board twin1 = board.twin();
    Board twin2 = board.twin();
    StdOut.println(board.toString());
    StdOut.println(twin1.toString());
    StdOut.println(twin2.toString());
    assert twin1.equals(twin2) && !board.equals(twin1);
  }

  @Test
  public void testTwinsEquation() {
    int[][] values = {{1,  0},
      {2,  3}};
    Board board = new Board(values);
    Board twin1 = board.twin();
    Board twin2 = board.twin();
    StdOut.println(board.toString());
    StdOut.println(twin1.toString());
    StdOut.println(twin2.toString());
    assert twin1.equals(twin2);
  }

  @Test
  public void testTwinsEquation1() {
    int[][] values = {{0,  1},
      {2,  3}};
    Board board = new Board(values);
    Board twin1 = board.twin();
    Board twin2 = board.twin();
    StdOut.println(board.toString());
    StdOut.println(twin1.toString());
    StdOut.println(twin2.toString());
    assert twin1.equals(twin2);
  }

  @Test
  public void testTwin2() {
    int[][] values =   {{15, 13, 1, 12},
      {10, 0, 2, 8},
      {5, 6, 9, 7},
      {11, 3, 14, 4}};
    Board board = new Board(values);
    Board twin = board.twin();
    StdOut.println(board.toString());
    assert  !board.equals(twin);
  }

  @Test
  public void testSolution1() {

    int[][] values = {{1,  2,  3},
      {0,  7,  6},
      {5,  4,  8}};
    Board board = new Board(values);
    int manh1 = board.manhattan();
    assert manh1 == 7;
    Solver solver = new Solver(board);
    Iterator<Board> boardIterator = solver.solution().iterator();
    while (boardIterator.hasNext()) {
      StdOut.println(boardIterator.next().toString());
    }
  }

  @Test
  public void testEquals1() {
    int[][] first =
      {{5,  8,  7},
      {1,  4,  6},
      {3,  0,  2}};
    int[][] second =
      {{5,  8,  7},
      {1,  4,  6},
      {3,  2,  0}};

    Board firstBoard = new Board(first);
    Board secondBoard = new Board(second);

    assert firstBoard.equals(secondBoard);

  }


  private void print(int[] pos) {
    StdOut.println("R: "+pos[0]+" C: "+pos[1]);
  }

}
