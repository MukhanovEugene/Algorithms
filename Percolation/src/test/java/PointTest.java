import org.junit.Test;

public class PointTest {

  @Test
  public void testPointCompareTo1() {
    Point p = new Point(31303, 12593);
    Point q = new Point(8562, 28260);
    Point r = new Point(31303, 4052);
    int result = p.slopeOrder().compare(q,r);
    assert result == -1;
  }

  @Test
  public void testPointCompareTo2() {
    Point p = new Point(5, 0);
    Point q = new Point(5, 1);
    Point r = new Point(7, 6);
    int result = p.slopeOrder().compare(q,r);
    assert result == 1;
  }


  @Test
  public void testPointCompareTo3() {
    Point p = new Point(272, 461);
    Point q = new Point(227, 214);
    Point r = new Point(272, 346);
    int result = p.slopeOrder().compare(q,r);
    assert result == -1;
  }



  @Test
  public void testPointCompareTo4() {
    Point p = new Point(6, 9);
    Point q = new Point(7, 9);
    Point r = new Point(6, 9);
    int result = p.slopeOrder().compare(q,r);
    assert result == 1;
  }

  @Test
  public void testPointCompareTo5() {
//    Failed on trial 4 of 100000
//    p                         = (8, 9)
//    q                         = (9, 6)
//    r                         = (5, 2)
//    student   p.compare(q, r) = 1
//    reference p.compare(q, r) = -1
//    reference p.slopeTo(q)    = -3.0
//    reference p.slopeTo(r)    = 2.3333333333333335
//      * throw java.lang.NullPointerException if either argument is null
    Point p = new Point(8, 9);
    Point q = new Point(9, 6);
    Point r = new Point(5, 2);
    int result = p.slopeOrder().compare(q,r);
    assert result == -1;
  }

  @Test
  public void testPointCompareTo6() {
//    Failed on trial 4 of 100000
//    p                         = (13991, 28639)
//    q                         = (28781, 21772)
//    r                         = (7564, 26309)
//    student   p.compare(q, r) = 1
//    reference p.compare(q, r) = -1
//    reference p.slopeTo(q)    = -0.4643002028397566
//    reference p.slopeTo(r)    = 0.3625330636377781
    Point p = new Point(13991, 28639);
    Point q = new Point(28781, 21772);
    Point r = new Point(7564, 26309);
    int result = p.slopeOrder().compare(q,r);
    assert result == -1;
  }


  @Test
  public void testPointCompareTo7() {
//    Failed on trial 1 of 100000
//    p                         = (6, 499)
//    q                         = (353, 439)
//    r                         = (449, 160)
//    student   p.compare(q, r) = -1
//    reference p.compare(q, r) = 1
//    reference p.slopeTo(q)    = -0.1729106628242075
//    reference p.slopeTo(r)    = -0.7652370203160271
    Point p = new Point(6, 499);
    Point q = new Point(353, 439);
    Point r = new Point(449, 160);
    int result = p.slopeOrder().compare(q,r);
    assert result == 1;
  }

  @Test
  public void testPointCompareTo8() {
//    Failed on trial 1 of 100000
//    p                         = (5, 1)
//    q                         = (4, 1)
//    r                         = (4, 2)
//    student   p.compare(q, r) = -1
//    reference p.compare(q, r) = 1
//    reference p.slopeTo(q)    = 0.0
//    reference p.slopeTo(r)    = -1.0
    Point p = new Point(5, 1);
    Point q = new Point(4, 1);
    Point r = new Point(4, 2);
    int result = p.slopeOrder().compare(q,r);
    assert result == 1;
  }

  @Test
  public void testPointCompareTo9() {
//  Failed on trial 2 of 100000
//  p                         = (3, 6)
//  q                         = (0, 7)
//  r                         = (2, 5)
//  student   p.compare(q, r) = 1
//  reference p.compare(q, r) = -1
//  reference p.slopeTo(q)    = -0.3333333333333333
//  reference p.slopeTo(r)    = 1.0
    Point p = new Point(3, 6);
    Point q = new Point(0, 7);
    Point r = new Point(2, 5);
    int result = p.slopeOrder().compare(q,r);
    assert result == -1;
}

  @Test
  public void testPointCompareTo10() {
//    Failed on trial 2 of 100000
//    p                         = (29372, 18202)
//    q                         = (4728, 8392)
//    r                         = (32707, 23496)
//    student   p.compare(q, r) = 1
//    reference p.compare(q, r) = -1
//    reference p.slopeTo(q)    = 0.3980684953741276
//    reference p.slopeTo(r)    = 1.5874062968515743
    Point p = new Point(29372, 18202);
    Point q = new Point(4728, 8392);
    Point r = new Point(32707, 23496);
    int result = p.slopeOrder().compare(q,r);
    assert result == -1;
  }

  @Test
  public void testPointCompareTo11() {
//    p                         = (474, 499)
//    q                         = (181, 348)
//    r                         = (464, 283)
//    student   p.compare(q, r) = 1
//    reference p.compare(q, r) = -1
//    reference p.slopeTo(q)    = 0.515358361774744
//    reference p.slopeTo(r)    = 21.6
    Point p = new Point(474, 499);
    Point q = new Point(181, 348);
    Point r = new Point(464, 283);
    int result = p.slopeOrder().compare(q,r);
    assert result == -1;
  }

}
