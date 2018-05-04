import java.awt.*;
public class Ellipse extends Item {
  private Point point1;
  private Point point2;
  public Ellipse(Point point1, Point point2) {
    this.point1 = point1;
    this.point2 = point2;
  }
  public Ellipse(Point point1) {
    this.point1 = point1;
  }
  public Ellipse() {
  }
  public boolean includes(Point point) {
    return ((distance(point, point1 ) < 10.0) || (distance(point, point2)< 10.0));
  }
  public void render() {
    uiContext.draw(this);
  }
  public void setPoint1(Point point) {
    point1 = point;
  }
  public void setPoint2(Point point) {
    point2 = point;
  }
  public Point getPoint1() {
    return point1;
  }
  public Point getPoint2() {
    return point2;
  }
  public String toString() {
    return "Ellipse  between " + point1 + " and " + point2;
  }
  public Point getClosestPoint(Point point) {
	  if (point.distance(this.getPoint1()) <= point.distance(this.getPoint2())) {
		  return this.getPoint1();
	  } else {
		  return this.getPoint2();
	  }
  }
}

