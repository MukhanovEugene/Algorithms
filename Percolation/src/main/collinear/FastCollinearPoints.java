import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

  private LineSegment[] segments;
  private int lineCount = 0;
  private int[] oritinalPointIndex;
  private Point[] currentSet = null;
  private double[] slopeSet = null;
  private int setCount = 0;
  //  private Point[] previousSet = null;
//  private int currentSize = 0;
//  private int previousSize = 0;
//  private int existsCount;
  private Point temp;

  private void check(Point[] points) {
    for (Point p : points) {
      if (p == null) throw new IllegalArgumentException();
    }
  }

  public FastCollinearPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException();
    }
    check(points);
    currentSet = new Point[points.length * 2];
    slopeSet = new double[currentSet.length];
    segments = new LineSegment[points.length * points.length - 1];
    double[] slopes;
    int[] originalPointIndex;
    Point pointi, pointj;
    int p;
    for (int i = 0; i < (points.length - 1); i++) {
//      StdOut.println("-------------------- i = " + i + " ----------------------");
      pointi = points[i];
      slopes = new double[points.length - 1 - i];
      originalPointIndex = new int[slopes.length];
      if (pointi == null) {
        throw new IllegalArgumentException();
      }
      for (int j = i + 1; j < points.length; j++) {
        pointj = points[j];
        if (pointi.equals(pointj)) {
          throw new IllegalArgumentException();
        }

        slopes[j - i - 1] = pointi.slopeTo(pointj);
        originalPointIndex[j - i - 1] = j;
      }

      sort(slopes, originalPointIndex);
      Point first_p = null;
      Point last_p = null;
      for (int k = 0; k < slopes.length - 2; k++) {
        if (originalPointIndex[k] == i) continue;
//        StdOut.println("K: "+k+" original index: "+originalPointIndex[k] + " Point: "+points[originalPointIndex[k]]);
        p = k + 1;
        while (p < slopes.length && (slopes[k] == slopes[p] ||
          slopes[k] == Double.NEGATIVE_INFINITY && slopes[p] == Double.NEGATIVE_INFINITY ||
          slopes[k] == Double.POSITIVE_INFINITY && slopes[p] == Double.POSITIVE_INFINITY)) {
          p++;
        }
        first_p = null;
        last_p = null;
        if (p - k > 2) {
          first_p = points[i];
          last_p = points[i];

//          existsCount = 0;
//          if (contains(previousSet,points[i])) {
//            existsCount++;
//          }
//          currentSet = new Point[slopes.length+1];
//          currentSize = 0;
//          currentSize = add(currentSet,currentSize,points[i]);

          Point[] firstLast;
          for (int t = k; t < p; t++) {
            temp = points[originalPointIndex[t]];
//            StdOut.println("Original index: "+originalPointIndex[t]+" Point: "+temp);
            firstLast = comparison(
              temp,
              last_p,
              first_p);
            first_p = firstLast[0];
            last_p = firstLast[1];
          }
          if (!find(first_p, last_p)) {
            segments[lineCount++] = new LineSegment(first_p, last_p);
//            StdOut.println("Line: " + first_p + " - " + last_p);
            addSlopePoint(first_p, last_p);
//            test(segments);
          }
        }
        k = p - 1;
      }
    }
    slopes = null;
    originalPointIndex = null;
    segments = Arrays.copyOf(segments, lineCount);
  }

  private boolean find(Point first_p, Point last_p) {
    double slope = first_p.slopeTo(last_p);
    for (int i = 0; i < setCount; i++) {
      if (slopeSet[i] == slope) {
        if (currentSet[i].compareTo(first_p) == 0 || currentSet[i].compareTo(last_p) == 0) {
          return true;
        }
      }
    }
    return false;
  }

  private void addSlopePoint(Point first_p, Point last_p) {
    double slope = first_p.slopeTo(last_p);
    if (setCount == slopeSet.length) {
      increase();
    }
    slopeSet[setCount] = slope;
    currentSet[setCount] = first_p;
    setCount++;
    slopeSet[setCount] = slope;
    currentSet[setCount] = last_p;

  }

  private void increase() {
    slopeSet = Arrays.copyOf(slopeSet, setCount * 2);
    currentSet = Arrays.copyOf(currentSet, slopeSet.length);
//    setCount = currentSet.length;
  }

//  private int add(Point[] set, int size, Point val) {
//    if (!contains(set,val)) {
//      if (size >= set.length) {
//        throw new ArrayIndexOutOfBoundsException();
//      }
//      set[size++] = val;
//    }
//    return size;
//  }
//
//  private boolean contains(Point[] set, Point val) {
//    for (Point current: set) {
//      if (val.equals(current)) {
//        return true;
//      }
//    }
//    return false;
//  }

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

  public int numberOfSegments() {
    return lineCount;
  }

  public LineSegment[] segments() {
//    test(segments);
    return segments;
  }

//  private void test(LineSegment[] segments) {
//    LineSegment first, second;
//    for (int f = 0; f < segments.length - 2; f++) {
//      first = segments[f];
//      if (first == null) continue;
//      for (int s = f+1; s < segments.length-1; s++) {
//        second = segments[s];
//        if (second == null) break;
//        if (equalz(first,second)) {
//          if (first.p.slopeTo(first.q) == second.p.slopeTo(second.q)) {
//            throw new IllegalArgumentException(
//              String.format("First ( %s, %s) Second (%s, %s)",first.p,first.q,second.p,second.q));
//          }
//        }
//      }
//    }
//  }
//
//  private boolean equalz(LineSegment first, LineSegment second) {
//    if (equalzz(second.p, first.p) || equalzz(second.p, first.q) ||
//      equalzz(second.q, first.p) || equalzz(second.q, first.q)) {
//      return true;
//    }
//    return false;
//  }
//
//  private boolean equalzz(Point fPoint, Point sPoint) {
//    return (fPoint.x == sPoint.x && fPoint.y == sPoint.y);
//  }

  public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
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
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }

  private void sort(double[] _input) {
    sort(_input, 0, _input.length - 1);
  }

  private void sort(double[] _input, int lo, int hi) {
    double mid = _input[lo + (hi - lo) / 2];
    int i = lo;
    int j = hi;
    while (i <= j) {
      while (_input[i] < mid) {
        i++;
      }
      while (_input[j] > mid) {
        j--;
      }
      if (i <= j) {
        exchange(_input, i, j);
        i++;
        j--;
      }
    }
    if (lo < j) {
      sort(_input, lo, j);
    }
    if (i < hi) {
      sort(_input, i, hi);
    }
  }

  private void exchange(double[] input, int i, int j) {
    double temp = input[i];
    input[i] = input[j];
    input[j] = temp;
    int tempi = oritinalPointIndex[i];
    oritinalPointIndex[i] = oritinalPointIndex[j];
    oritinalPointIndex[j] = tempi;
  }

  private void sort(double[] slopes, int[] originalPointIndex) {
    this.oritinalPointIndex = originalPointIndex;
    sort(slopes);
  }

}
