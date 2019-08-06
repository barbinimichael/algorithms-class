import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

  private final List<LineSegment> segments;

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {

    checkInput(points);
    
    Point[] localPoints = Arrays.copyOf(points, points.length);

    this.segments = new ArrayList<>();

    Arrays.sort(localPoints);

    if (localPoints.length > 1) {
      for (int i = 0; i < localPoints.length - 3; i++) {
        for (int j = i + 1; j < localPoints.length - 2; j++) {
          for (int k = j + 1; k < localPoints.length - 1; k++) {
            for (int l = k + 1; l < localPoints.length; l++) {
              if (localPoints[i].slopeTo(localPoints[j]) == localPoints[i].slopeTo(localPoints[k])
                  && localPoints[i].slopeTo(localPoints[k]) == localPoints[i].slopeTo(localPoints[l])) {
                this.segments.add(new LineSegment(localPoints[i], localPoints[l]));
              }
            }
          }
        }
      }
    }
  }

  // the number of line segments
  public int numberOfSegments() {
    return this.segments.size();
  }

  // the line segments
  public LineSegment[] segments() {
    return segments.toArray(new LineSegment[segments.size()]);
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
}
