
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
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

  private final int x; // x-coordinate of this point
  private final int y; // y-coordinate of this point

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
   * Draws the line segment between this point and the specified point to standard
   * draw.
   *
   * @param that the other point
   */
  public void drawTo(Point that) {
    /* DO NOT MODIFY */
    StdDraw.line(this.x, this.y, that.x, that.y);
  }

  /**
   * Returns the slope between this point and the specified point. Formally, if
   * the two points are (x0, y0) and (x1, y1), then the slope is (y1 - y0) / (x1 -
   * x0). For completeness, the slope is defined to be +0.0 if the line segment
   * connecting the two points is horizontal; Double.POSITIVE_INFINITY if the line
   * segment is vertical; and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1)
   * are equal.
   *
   * @param that the other point
   * @return the slope between this point and the specified point
   */
  public double slopeTo(Point that) {
    if (this.x == that.x && this.y == that.y)
      return Double.NEGATIVE_INFINITY;
    else if (this.x == that.x)
      return Double.POSITIVE_INFINITY;
    else if (this.y == that.y)
      return 0;
    else
      return (double) (this.y - that.y) / (double) (this.x - that.x);
  }

  /**
   * Compares two points by y-coordinate, breaking ties by x-coordinate. Formally,
   * the invoking point (x0, y0) is less than the argument point (x1, y1) if and
   * only if either y0 < y1 or if y0 = y1 and x0 < x1.
   *
   * @param that the other point
   * @return the value <tt>0</tt> if this point is equal to the argument point (x0
   *         = x1 and y0 = y1); a negative integer if this point is less than the
   *         argument point; and a positive integer if this point is greater than
   *         the argument point
   */
  public int compareTo(Point that) {

    int difference = this.y - that.y;

    if (difference == 0) {
      return this.x - that.x;
    } else {
      return difference;
    }
  }

  /**
   * Compares two points by the slope they make with this point. The slope is
   * defined as in the slopeTo() method.
   *
   * @return the Comparator that defines this ordering on points
   */
  public Comparator<Point> slopeOrder() {
    return (p1, p2) -> Double.compare(slopeTo(p1), slopeTo(p2));
  }

  /**
   * Returns a string representation of this point. This method is provide for
   * debugging; your program should not rely on the format of the string
   * representation.
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
    Point p = new Point(0, 0);
    Point pCopy = new Point(0, 0);
    Point a = new Point(1, 1);
    Point b = new Point(0, 1);
    Point c = new Point(0, -1);
    Point d = new Point(-1, -1);
    Point e = new Point(2, 2);
    Point f = new Point(30, 30);
    Point g = new Point(-100, -100);

    System.out.println("Check point comparison");
    System.out.println(0 + " : " + p.compareTo(pCopy));
    System.out.println(-1 + " : " + p.compareTo(a));
    System.out.println(-1 + " : " + p.compareTo(b));
    System.out.println(1 + " : " + p.compareTo(c));
    System.out.println(1 + " : " + p.compareTo(d));

    System.out.println("\nCheck slope calculation");
    System.out.println("-Infinity : " + p.slopeTo(pCopy));
    System.out.println("1 : " + p.slopeTo(a));
    System.out.println("1 : " + a.slopeTo(p));

    Point[] points = new Point[8];
    points[0] = p;
    points[1] = a;
    points[2] = b;
    points[3] = c;
    points[4] = d;
    points[5] = e;
    points[6] = f;
    points[7] = g;
    
    for (Point point : points)
      System.out.println(point);

    System.out.println();
    
    System.out.println("____________CHECKING_____________");
    System.out.println("-100 to -1 " + g.slopeTo(d));
    System.out.println("-100 to 1 " + g.slopeTo(a));
    System.out.println("-100 to 0,-1 " + g.slopeTo(c));
    
    System.out.println("Comparing to -100");
    System.out.println();

    for (int i = 0; i < points.length; i++)
      System.out.println(g.slopeTo(points[i]));

    Arrays.sort(points, g.slopeOrder());
    System.out.println();
    
    System.out.println("Sorting to -100");
    
    for (Point point : points)
      System.out.println(point);    
    System.out.println("____________CHECKING_____________");

    System.out.println();

    Arrays.sort(points);

    for (Point point : points)
      System.out.println(point);

    for (int i = 0; i < points.length; i++)
      System.out.println(p.slopeTo(points[i]));

    BruteCollinearPoints brute = new BruteCollinearPoints(points);
    LineSegment[] ls = brute.segments();
    for (LineSegment l : ls)
      System.out.println(l);
    System.out.println("------------------------");
    FastCollinearPoints fast = new FastCollinearPoints(points);
    LineSegment[] fls = fast.segments();
    for (LineSegment l : fls)
      System.out.println(l);

    // check that throws correct exceptions
    // points = new Point[4];
    // points[0] = p;
    // fast = new FastCollinearPoints(points);
    
    Point aTwo = new Point(0, 0);
    Point bTwo = new Point(0, 1);
    Point cTwo = new Point(0, 2);
    Point dTwo = new Point(1, 0);
    Point eTwo = new Point(2, 0);
    Point fTwo = new Point(0, 3);
    Point gTwo = new Point(3, 0);
    Point hTwo = new Point(12, 10);
    Point iTwo = new Point(24, 20);
    Point jTwo = new Point(36, 30);
    Point kTwo = new Point(48, 40);
    Point lTwo = new Point(60, 50);
    
    Point[] pointsTwo = new Point[12];
    
    pointsTwo[0] = eTwo;
    System.out.println(pointsTwo[0] == null);
    
    pointsTwo[1] = dTwo;
    System.out.println(pointsTwo[1] == null);
    
    pointsTwo[2] = cTwo;
    System.out.println(pointsTwo[2] == null);
    
    pointsTwo[3] = bTwo;
    System.out.println(pointsTwo[3] == null);
    
    pointsTwo[4] = aTwo;
    System.out.println(pointsTwo[4] == null);
    
    pointsTwo[5] = fTwo;
    pointsTwo[6] = gTwo;
    pointsTwo[7] = hTwo;
    pointsTwo[8] = iTwo;
    pointsTwo[9] = jTwo;
    pointsTwo[10] = kTwo;
    pointsTwo[11] = lTwo;
    
    for (Point point : pointsTwo)
      System.out.println(point);
    
    System.out.println("Ordering");
    Arrays.sort(pointsTwo, pointsTwo[0].slopeOrder());
    
    for (Point point : pointsTwo)
      System.out.println(point);
    
    System.out.println("--------------------------------");
    
    fast = new FastCollinearPoints(pointsTwo);
    fls = fast.segments();
    System.out.println(fast.numberOfSegments());
    for (LineSegment l : fls)
      System.out.println("Line segment " + l);
    
    System.out.println("--------------------------------");
    // '(7419, 3187) -> (7419, 4361) -> (7419, 6089) -> (7419, 10712)'
    Point aThree = new Point(7419, 3187);
    Point bThree = new Point(7419, 4361);
    Point cThree = new Point(7419, 6089);
    Point dThree = new Point(7419, 10712);
    
    Point[] pointsThree = new Point[4];
    pointsThree[0] = aThree;
    pointsThree[1] = bThree;
    pointsThree[2] = cThree;
    pointsThree[3] = dThree;
    
    fast = new FastCollinearPoints(pointsThree);
    fls = fast.segments();
    System.out.println(fast.numberOfSegments());
    for (LineSegment l : fls)
      System.out.println("Line segment " + l);
    
    System.out.println("____________");
    for (Point point : pointsThree)
      System.out.println(point);

    for (int i = 0; i < pointsThree.length; i++)
      System.out.println(aThree.slopeTo(pointsThree[i]));
    
    System.out.println(aThree.slopeTo(bThree) == aThree.slopeTo(cThree));
  }
}
