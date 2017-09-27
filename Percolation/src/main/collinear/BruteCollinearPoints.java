import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

  private LineSegment[] segments;
  private int lineCount = 0;
//  private Set<Point> unique1 = new HashSet<Point>();
//  private int existsCount;

  public BruteCollinearPoints(Point[] points) {  // finds all line segments containing 4 points
    if (points == null) {
      throw new IllegalArgumentException();
    }
    check(points);
    segments = new LineSegment[points.length * points.length - 1];

    double[] slopes;
    Point pointi, pointj;
    for (int i = 0; i < (points.length - 1); i++) {
//      StdOut.println("-------------------- i = " + i + " ----------------------");
      pointi = points[i];
      slopes = new double[points.length - 1 - i];
      if (pointi == null) {
        throw new IllegalArgumentException();
      }
      for (int j = i + 1; j < points.length; j++) {
        pointj = points[j];
        if (pointi.equals(pointj)) {
          throw new IllegalArgumentException();
        }

        slopes[j - i - 1] = pointi.slopeTo(pointj);

      }
//      existsCount = 0;
//      if (unique1.contains(points[i])) {
//        existsCount++;
//      }
      byte equals;
      Point first;// = points[i];
      Point last = null;
      Point[] edges;
      for (int k = 0; k < slopes.length - 1; k++) {
        first = points[i];
        last = points[k + i + 1];
        equals = 1;

//        if (unique1.contains(first)) {
//          existsCount++;
//        }
//        unique1.add(first);
//        if (unique1.contains(last)) {
//          existsCount++;
//        }
//        unique1.add(last);

        for (int p = k + 1; p < slopes.length; p++) {
          if (slopes[k] == slopes[p] ||
            slopes[k] == Double.NEGATIVE_INFINITY && slopes[p] == Double.NEGATIVE_INFINITY ||
            slopes[k] == Double.POSITIVE_INFINITY && slopes[p] == Double.POSITIVE_INFINITY
            ) {
//            if (unique1.contains(points[p + i + 1])) {
//              existsCount++;
//            }
//            if (existsCount > 2) {
//              break;
//            }
//            unique1.add(points[p + i + 1]);
            edges = comparison(first, points[p + i + 1], last);
            first = edges[0];
            last = edges[1];
            equals++;
          }
        }
        if (equals >= 3) {
          // make a slope
          segments[lineCount++] = new LineSegment(first, last);
//          StdOut.println("LineSegment: Start=" + first + " Finish=" + last);
        }
      }

    }

    this.segments = Arrays.copyOf(segments, lineCount);

  }

  private void check(Point[] points) {
    for (Point p: points) {
      if (p == null) throw new IllegalArgumentException();
    }
  }

  private Point[] comparison(Point a, Point b, Point c) {
    if (a.compareTo(b) == -1 && a.compareTo(c) == -1) {
//      a = a;
      if (b.compareTo(c) == -1) {
//        c = c;
      } else {
        c = b;
      }
    }
    if (b.compareTo(a) == -1 && b.compareTo(c) == -1) {
      if (a.compareTo(c) == -1) {
        //c = c; // biggest
      } else {
        c = a;
      }
      a = b; // smallest
    }
    if (c.compareTo(a) == -1 && c.compareTo(b) == -1) {
      if (a.compareTo(b) == -1) {
        a = c; // smallest
        c = b; // biggest
      } else {
        b = a; // temporary
        a = c;
        c = b;
      }
    }
    return new Point[]{a, c};
  }

  public int numberOfSegments() {       // the number of line segments
    return lineCount;
  }

  public LineSegment[] segments() {     // the line segments
    return segments;
  }

  public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
//      System.out.println("X: "+x+", Y: "+y);
      points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }

}