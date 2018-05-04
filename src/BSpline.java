import java.awt.*;
import java.util.Vector;
public class BSpline extends Item {
	private static final int PRECISION = 10;
	//How many control points between each regular point
	private BezierCurve bc;
	private Vector<Point> tempPoints;
  
  public BSpline(Point... newPoints) {
    bc = new BezierCurve( newPoints );
    tempPoints = new Vector<>();
    for(Point p : newPoints) {
    	tempPoints.add(p);
    }
  }
  
  public BSpline() {
	  bc = new BezierCurve();
	  tempPoints = new Vector<>();
  }
  
  public boolean includes(Point p1) {
    for (int i=0; i<bc.getSize(); i++) {
    	if ( distance(p1, bc.getPoint(i)) < 10 ) {
    		return true;
    	}
    }
    return false;
  }
  
  public void render() {
    uiContext.draw(this);
  }
  
  public void addPoint(Point point) {
	  bc.addPoint(point);
	  tempPoints.add(point);
  }
  
  public Point getPoint(int index) {
    return bc.getPoint(index);
  }
  
  public int getPointQty() {
	  return bc.getSize();
  }
  
  public Point getTemporaryPoint(int index) {
	  return tempPoints.get(index);
  }
  
  public int getTemporaryPointQty() {
	  return tempPoints.size();
  }
  
  public void clearTemporaryPoints( ) {
	  tempPoints = new Vector<>();
  }
  
  public Point getBezierCurvePoint(double t) {
	  return bc.calculateCurvePointAt(t);
  }
  
  private double B0(double t) {
	  return Math.pow(1.0-t, 3.0) / 6.0;
  }
  
  private double B1(double t) {
	  return (3.0*Math.pow(t, 3.0) - 6.0*Math.pow(t, 2.0) + 4.0)/6.0;
  }
  
  private double B2(double t) {
	  return (-(3.0*Math.pow(t, 3.0))+3.0*Math.pow(t,2.0)+3.0*t+1.0)/6.0;
  }
  
  private double B3(double t) {
	  return Math.pow(t,3.0)/6.0;
  }
  
  public String toString() {
	  String string = "B-Spline between points ";
	  for(int i=0; i<bc.getSize(); i++) {string = string + bc.getPoint(i).toString() + ", ";};
	  return string;
  }
}

