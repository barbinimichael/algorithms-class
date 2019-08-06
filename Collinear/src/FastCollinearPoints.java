import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

  private final List<LineSegment> segments;

  // finds all line segments containing 4 points
  public FastCollinearPoints(Point[] points) {

    checkInput(points);
    Point[] localPoints = Arrays.copyOf(points, points.length);
    Arrays.sort(localPoints);

    this.segments = new ArrayList<>();

    if (localPoints.length > 3) {
      for (int i = 0; i < points.length; i++) {
        createGroup(localPoints, points[i]);

      }
    }
  }

  // the number of line segments
  public int numberOfSegments() {
    return this.segments.size();
  }

  // the line segments
  public LineSegment[] segments() {
    return this.segments.toArray(new LineSegment[segments.size()]);
  }

  // check that legal input
  private void checkInput(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException();

    } else {
      for (int i = 0; i < points.length; i++) {
        if (points[i] == null)
          throw new IllegalArgumentException();
      }
    }

    Point[] testDups = Arrays.copyOf(points, points.length);

    Arrays.sort(testDups);

    for (int i = 1; i < testDups.length; i++) {
      if (testDups[i].compareTo(testDups[i - 1]) == 0)
        throw new IllegalArgumentException();
    }
  }

  // get line segment that collinear with point
  private void createGroup(Point[] points, Point p) {
    Point[] localPoints = Arrays.copyOf(points, points.length);
    
    Arrays.sort(localPoints, p.slopeOrder());

    // System.out.println("------------l-o-l----------");
    // for (Point point : points)
    // System.out.println(point);

    int start = 1;
    for (int i = 1; i < localPoints.length; i++) {
      if (p.slopeTo(localPoints[i]) != p.slopeTo(localPoints[i - 1])) {

        if (i - start >= 3) {
          createSegment(localPoints, start, i - 1);
          // System.out.println("MAKING " + start + " " + (i - 1));
        }
        start = i;
      } else if (i == localPoints.length - 1 && i - start >= 2) {
        createSegment(localPoints, start, i);
        // System.out.println("MAKING " + start + " " + i);
      }
    }

  }

  // create a segment from the given points
  // private void createSegment(Point[] points, int start, int end) {
  // Point[] pointsInSegment = new Point[end - start + 2];
  //
  // pointsInSegment[0] = points[0];
  //
  // for (int i = start; i <= end; i++) {
  // pointsInSegment[i - start + 1] = points[i];
  // }
  // Arrays.sort(pointsInSegment);
  //
  // // System.out.println("------------w-o-w----------");
  // // for (Point point : pointsInSegment)
  // // System.out.println(point);
  //
  // LineSegment newSegment = new LineSegment(pointsInSegment[0],
  // pointsInSegment[pointsInSegment.length - 1]);
  // if (!isDup(newSegment)) {
  // this.segments.add(newSegment);
  //
  // }
  // }

  // create a segment from the given points
  private void createSegment(Point[] points, int start, int end) {
    Point[] pointsInSegment = new Point[end - start + 2];

    pointsInSegment[0] = points[0];

    for (int i = start; i <= end; i++) {
      pointsInSegment[i - start + 1] = points[i];
    }

    // System.out.println("------------w-o-w----------");
    // for (Point point : pointsInSegment)
    // System.out.println(point);

    boolean ascending = true;
    for (int i = 1; i < pointsInSegment.length; i++) {
      if (pointsInSegment[i].compareTo(pointsInSegment[i - 1]) < 0) {
        ascending = false;
        break;
      }
    }
    LineSegment newSegment = new LineSegment(pointsInSegment[0], pointsInSegment[pointsInSegment.length - 1]);
    if (ascending) {
      this.segments.add(newSegment);

    }
  }

  // determine if there are duplicate points
  private boolean isDup(LineSegment ls) {
    String allLineSegment = "";

    for (int i = 0; i < this.segments.size(); i++) {
      allLineSegment += segments.get(i);
    }
    return allLineSegment.contains(ls.toString());
  }
}
