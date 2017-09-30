/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {

  private final int x;     // x-coordinate of this point
  private final int y;     // y-coordinate of this point
  /**
   * Initializes a new point.
   *
   * @param x the <em>x</em>-coordinate of the point
   * @param y the <em>y</em>-coordinate of the point
   */
  public Point(int x, int y) {
        /* DO NOT MODIFY */
    this.x = x;
    this.y = y;
  }

  /**
   * Draws this point to standard draw.
   */
  public void draw() {
        /* DO NOT MODIFY */
    StdDraw.point(x, y);
  }

  /**
   * Draws the line segment between this point and the specified point to standard draw.
   *
   * @param that the other point
   */
  public void drawTo(Point that) {
        /* DO NOT MODIFY */
    StdDraw.line(this.x, this.y, that.x, that.y);
  }

  /**
   * Returns the slope between this point and the specified point. Formally, if the two points are (x0, y0) and (x1,
   * y1), then the slope is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be +0.0 if the line segment
   * connecting the two points is horizontal; Double.POSITIVE_INFINITY if the line segment is vertical; and
   * Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
   *
   * @param that the other point
   * @return the slope between this point and the specified point
   */
  public double slopeTo(Point that) {

    if (x == that.x && y == that.y) {
      return Double.NEGATIVE_INFINITY;
    } else if (y == that.y) { // horizontal
      return 0.0d;
    } else if (x == that.x) {
      return Double.POSITIVE_INFINITY;
    }
    return (double) (that.y - y) / (that.x - x);
  }

  /**
   * Compares two points by y-coordinate, breaking ties by x-coordinate. Formally, the invoking point (x0, y0) is less
   * than the argument point (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
   *
   * @param that the other point
   * @return the value <tt>0</tt> if this point is equal to the argument point (x0 = x1 and y0 = y1); a negative integer
   * if this point is less than the argument point; and a positive integer if this point is greater than the argument
   * point
   */
  public int compareTo(Point that) {
    int x1 = that.x;
    int y1 = that.y;


    if (y == y1 && x == x1) {
      return 0;
    }

    if (y < y1 || (y == y1 && x < x1)) {
      return -1;
    }
    if (y > y1 || (y == y1 && x > x1)) {
      return 1;
    }
    return Integer.MAX_VALUE;
  }

  /**
   * Compares two points by the slope they make with this point. The slope is defined as in the slopeTo() method.
   *
   * @return the Comparator that defines this ordering on points
   */
  public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
    return new Comparator<Point>() {
      private final int x0 = x;
      private final int y0 = y;
      private Point t = new Point(x,y);

      @Override
      public int compare(final Point o1, final Point o2) {

        double slope1 = t.slopeTo(o1);
        double slope2 = t.slopeTo(o2);
        return Double.compare(slope1,slope2);
////        double x1diff = Math.abs(o1.x - x0);
////        double x2diff = Math.abs(o2.x - x0);
////        double y1diff = Math.abs(o1.y - y0);
////        double y2diff = Math.abs(o2.y - y0);
//
//        double x1diff = o1.x - x0;
//        double x2diff = o2.x - x0;
//        double y1diff = o1.y - y0;
//        double y2diff = o2.y - y0;
//
//        double first = 0;
//        if (x1diff !=0 && y1diff != 0) {
//          first = Math.abs(y1diff / x1diff);
//        } else if (x1diff == 0 && y1diff == 0) {
//          first = 0;
//        } else if (y1diff == 0) {
//          first = 0;
//        } else if (x1diff == 0) {
//          first = 0;
//        }
//
//        double second = 0;
//        if (x2diff !=0 && y2diff != 0) {
//          second = Math.abs(y2diff / x2diff);
//        } else if (x2diff ==0 && y2diff == 0) {
//          second = 0;
//        } else if (y2diff == 0) {
//          second = 0;
//        } else if (x2diff == 0) {
//          second = 0;
//        }
//
//        if (y1diff == y2diff && (x1diff != 0 || x2diff != 0)) {
//          return x1diff < x2diff ? -1 : x1diff > x2diff ? 1 : 0;
//        }
//
//        return first < second ? -1 : first > second ? 1 : 0;
      }
    };
  }

  /**
   * Returns a string representation of this point. This method is provide for debugging; your program should not rely
   * on the format of the string representation.
   *
   * @return a string representation of this point
   */
  public String toString() {
        /* DO NOT MODIFY */
    return "(" + x + ", " + y + ")";
  }

  /**
   * Unit tests the Point data type.
   */
  public static void main(String[] args) {
        /* YOUR CODE HERE */
  }
}
