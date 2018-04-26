import java.awt.*;
import java.util.Vector;
public class BSpline extends Item {
	private static final int PRECISION = 10;
	//How many control points between each regular point
	private BezierCurve bc;
  
  public BSpline(Point... newPoints) {
    bc = new BezierCurve( newPoints );
  }
  
  public BSpline() {
	  bc = new BezierCurve();
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
  }
  
  public Point getPoint(int index) {
    return bc.getPoint(index);
  }
  
  public int getPointQty() {
	  return bc.getSize();
  }
  
  public Point getBezierCurvePoint(double t) {
	  return bc.calculateCurvePointAt(t);
  }
  
  public String toString() {
	  String string = "B-Spline between points ";
	  for(int i=0; i<bc.getSize(); i++) {string = string + bc.getPoint(i).toString() + ", ";};
	  return string;
  }
}

