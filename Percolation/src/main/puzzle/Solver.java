import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

  private MinPQ<SolveNode> queue = new MinPQ<>();

  private Board[] solution;
  private boolean solvable = false;
  private int moves = -1;

  public Solver(Board initial) {          // find a solution to the initial board (using the A* algorithm)
    if (initial == null) throw new IllegalArgumentException();
    Iterator<Board> temp = null;
    Board tempBoard;
    SolveNode parentNode;
    int counter = (int) Math.pow(initial.dimension(), 10);
    queue.insert(new SolveNode(null, initial, 0));
    SolveNode currentNode = null;
    while (!queue.isEmpty() && (currentNode = queue.delMin()) != null && currentNode.getBoard().manhattan() > 0 &&
      (counter-- > 0)) {
      temp = currentNode.getBoard().neighbors().iterator();
      parentNode = currentNode.getParent();
      while (temp.hasNext()) {
        tempBoard = temp.next();
        if (parentNode == null || !parentNode.getBoard().equals(tempBoard)) {
          queue.insert(new SolveNode(currentNode, tempBoard, currentNode.getMoves() + 1));
        }
      }
    }
    solvable = (currentNode != null && currentNode.getBoard().manhattan() == 0);
    if (solvable) {
      moves = currentNode.getMoves();
      solution = new Board[moves + 1];
      int number = solution.length;
      do {
        solution[--number] = currentNode.getBoard();
      } while ((currentNode = currentNode.getParent()) != null);
    }
  }


  public boolean isSolvable() {
    return solvable;
  }

  public int moves() {                    // min number of moves to solve initial board; -1 if unsolvable
    return moves;
  }

  public Iterable<Board> solution() {     // sequence of boards in a shortest solution; null if unsolvable
    return new Iterable<Board>() {
      @Override
      public Iterator<Board> iterator() {
        return new Iterator<Board>() {

          private int idx = 0;

          @Override
          public boolean hasNext() {
            return solution == null ? false : idx < solution.length;
          }

          @Override
          public Board next() {
            return solution[idx++];
          }
        };
      }
    }; // temp
  }

  public static void main(String[] args) {

    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }

  private class SolveNode implements Comparable<SolveNode> {

    private Board b;
    private int manhattan;
    private int moves;
    private SolveNode parent;

    public SolveNode(SolveNode parent, Board b, int m) {
      this.parent = parent;
      this.b = b;
      this.manhattan = b.manhattan();
      this.moves = m;
    }

    public int getMoves() {
      return moves;
    }

    public Board getBoard() {
      return b;
    }

    public SolveNode getParent() {
      return parent;
    }

    public int priority() {
      return manhattan + moves;
    }

    public String toString() {
      return "M: " + manhattan + " Mo: " + moves;
    }

    @Override
    public int compareTo(SolveNode o) {
//      if (priority() == o.priority()) {
//        if (manhattan == o.manhattan) {
//          return Integer.compare(moves, o.moves);
//        } else {
//          return Integer.compare(manhattan, o.manhattan);
//        }
//      } else {
      return Integer.compare(priority(), o.priority());
//      }
    }
  }

}