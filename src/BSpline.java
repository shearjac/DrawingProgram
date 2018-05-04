import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Vector;
public class BSpline extends Item {
	private Vector<Point> points;
  
  public BSpline() {
	  points = new Vector<>();
  }
  
  public boolean includes(Point p1) {
    for (int i=0; i<points.size(); i++) {
    	if ( distance(p1, points.get(i)) < 10 ) {
    		return true;
    	}
    }
    return false;
  }
  
  public void render() {
    uiContext.draw(this);
  }
  
  public void addPoint(Point point) {
	  points.add(point);
  }
  
  public Point getPoint(int index) {
    return points.get(index);
  }
  
  private double B0(double t) {
	  return Math.pow(1-t, 3) / 6;
  }
  
  private double B1(double t) {
	  return (3*Math.pow(t, 3) - 6*Math.pow(t, 2) + 4)/6;
  }
  
  private double B2(double t) {
	  return (-(3*Math.pow(t, 3))+3*Math.pow(t,2)+3*t+1)/6;
  }
  
  private double B3(double t) {
	  return Math.pow(t,3)/6;
  }
  
  public int getSize() {
	  return points.size();
  }
  
  public Point solve(double t) {
	  Point2D[] pointArray = new Point2D[points.size()];
	  
	  for(int i=0; i<points.size(); i++) {
		  pointArray[i] = (Point)points.get(i);
	  }
	  Point2D result = solve(t, pointArray);
	  int resultX = Math.round((float)result.getX()),
		  resultY = Math.round((float)result.getY());
	  return new Point(resultX, resultY);
  }
  
  private Point2D solve(double t, Point2D[] pv) {
	  if(pv.length == 4) { //Solve for cubic curve
		  double term0 = Math.pow((double)1-t,(double)3),
				 term1 = (double)3 * Math.pow((double)1-t,(double)2) * t,
				 term2 = (double)3 * ((double)1-t) * Math.pow(t,(double)2),
				 term3 = Math.pow(t, (double)3);
		  double Qx = term0*pv[0].getX() +
				      term1*pv[1].getX() +
				      term2*pv[2].getX() +
				      term3*pv[3].getX();
		  double Qy = term0*pv[0].getY() +
			          term1*pv[1].getY() +
			          term2*pv[2].getY() +
			          term3*pv[3].getY();
		  return new Point2D.Double(Qx, Qy);
	  } else {
		  Point2D[] leftArray = new Point[pv.length-1], rightArray = new Point[pv.length-1];
		  for(int i=0; i<pv.length-1; i++) {
			  leftArray[i] = pv[i];
			  rightArray[i] = pv[i+1];
		  }
		  Point2D left, right;
		  left = solve(t, leftArray);
		  right = solve(t,rightArray);
		  double Qx = (1-t)*left.getX() + t*right.getX();
		  double Qy = (1-t)*left.getY() + t*right.getY();
		  return new Point2D.Double(Qx, Qy);
	  }
  }
  
  public String toString() {
	  String string = "B-Spline between points ";
	  for(int i=0; i<points.size(); i++) {string = string + points.get(i).toString() + ", ";};
	  return string;
  }
  
  public Point getClosestPoint(Point point) {
	  Point closestPoint = this.getPoint(0);
	  for (int i=1; i<points.size(); i++) {
		  if (point.distance(this.getPoint(i)) < point.distance(closestPoint)) {
			  closestPoint = this.getPoint(i);
		  }
	  }
	  return closestPoint;
  }
}

