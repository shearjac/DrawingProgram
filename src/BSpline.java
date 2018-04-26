import java.awt.*;
import java.util.Vector;
public class BSpline extends Item {
	private static final int PRECISION = 5;
	//How many control points between each regular point
	
	private Vector<Point> controlPoints = new Vector<>();
	private Vector<Point> points = new Vector<>();
  
  public BSpline(Point... newPoints) {
    for (Point p : newPoints) {
    	points.add(p);
    }
  }
  
  public BSpline() {
  }
  
  public boolean includes(Point p1) {
    for (Point p2 : points) {
    	if ( distance(p1, p2) < 10 ) {
    		return true;
    	}
    }
    return false;
  }
  
  public void render() {
    uiContext.draw(this);
  }
  
  //Getters & Setters for Regular Points
  public void setPoint(int index, Point point) {
	  try { 
		  points.set(index, point);
		  if (points.size() >= 3) {calculateBezierCurvePoints();}
	  }
	  catch (ArrayIndexOutOfBoundsException e) {
		  points.add(point);
	  }
  }
  
  public void addPoint(Point point) {
	  points.add(point);
	  if (points.size() >= 3) {calculateBezierCurvePoints();}
  }
  
  public Point getPoint(int index) {
    return points.get(index);
  }
  
  public int getPointQty() {
	  return points.size();
  }
  
  //Getters & Setters for Regular Points
  public void setControlPoint(int index, Point point) {
	  try { 
		  controlPoints.set(index, point); 
	  } catch (ArrayIndexOutOfBoundsException e) {
		  controlPoints.add(point);
	  }
  }
  
  public void addControlPoint(Point point) {
	  controlPoints.add(point);
  }
  
  public Point getControlPoint(int index) {
    return controlPoints.get(index);
  }
  
  public int getControlPointQty() {
	  return controlPoints.size();
  }
  
  public String toString() {
	  String string = "B-Spline between points ";
	  for(Point p : points) {string = string + p.toString() + ", ";};
	  return string;
  }
  
  //Bezier Functions
  private void calculateBezierCurvePoints() {
	  double t;
	  Point newPoint;
	  for (int i = 0; i < points.size(); i++) {
		  for (int h = 0; h < PRECISION; h++) {
			  t = ((double)h)/((double)PRECISION);
			  newPoint = new Point(bezier(t));
			  setControlPoint(i+h,newPoint);
		  }
	  }
  }
  
  private Point bezier(double t) {
	  Point result;
	  double px, py;
	  double term1, term2, term3;
	  double resultX=0, resultY=0;
	  int n = points.size()-1;
	  for (int i = 0; i < points.size(); i++) {
		  System.out.println("\nt Value: "+t);
		  System.out.println("n Value: "+n);
		  System.out.println("i Value: "+i);
		  //See: https://wikimedia.org/api/rest_v1/media/math/render/svg/04819d6d335226625dfb2e401355e70ea7996d7c
		  term1 = binomialCoefficient(n,i); System.out.println("Term 1 Value: " + term1);
		  System.out.println("n! = " + factorial(n));
		  System.out.println("i! = " + factorial(i));
		  System.out.println("(n-i)! = " + factorial(n-i));
		  term2 = Math.pow(((double)1)-t,n-i); System.out.println("Term 2 Value: " + term2);
		  term3 = Math.pow(t, i); System.out.println("Term 3 Value: "+ term3);
		  
		  px = points.get(i).getX(); System.out.println("X Value: " + px);
		  py = points.get(i).getY(); System.out.println("Y Value: " + py);
		  
		  px = term1 * term2 * term3 * px; System.out.println("Changed X Value: " + px);
		  py = term1 * term2 * term3 * px; System.out.println("Changed Y Value: " + py);
		  
		  resultX = resultX + px;
		  resultY = resultY + py;
	  }
	  result = new Point((int)resultX, (int)resultY);
	  System.out.println("New Control Point: " + result);
	  return result;
  }
  
  private long factorial(int x) {
	  int fact = 1;
	  for (int i = 1; i <= x; i++) {
		  fact = fact * i;
		  System.out.println(i+"! = "+fact);
	  }
	  return fact;
  }
  
  private double binomialCoefficient(int n, int k) { // n >= k >= 0
	  double result = (double)(factorial(n) / (factorial(k) * factorial(n-k)));
	  return result;
  }
}

