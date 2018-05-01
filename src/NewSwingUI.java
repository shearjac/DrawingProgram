import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
public class NewSwingUI implements UIContext {
  private Graphics graphics;
  private static NewSwingUI swingUI;
  private NewSwingUI() {
  }
  public static NewSwingUI getInstance() {
    if (swingUI == null) {
      swingUI = new NewSwingUI();
    }
    return swingUI;
  }
  public  void setGraphics(Graphics graphics) {
    this.graphics = graphics;
  }
  public void draw(Label label) {
    if (label.getStartingPoint() != null) {
      if (label.getText() != null) {
        graphics.drawString(label.getText(), (int) label.getStartingPoint().getX(), (int) label.getStartingPoint().getY());
        
        if (label.getPointVisibility()) {
        	Color lastColor = graphics.getColor();
	    	graphics.setColor(Color.BLACK);
        	int x, y;
        	x = Math.round((float)label.getStartingPoint().getX());
        	y = Math.round((float)label.getStartingPoint().getY());
        	graphics.drawRect(x-5, y-5, 10, 10);
        	graphics.setColor(lastColor);
        }
      }
    }
    int length = graphics.getFontMetrics().stringWidth(label.getText());
    graphics.drawString("_", (int) label.getStartingPoint().getX() + length, (int) label.getStartingPoint().getY());
  }
  public void draw(Line line) {
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    if (line.getPoint1() != null && line.getPoint2() != null) {
    	i1 = Math.round((float) (line.getPoint1().getX()));
      	i2 = Math.round((float) (line.getPoint1().getY()));
	    i3 = Math.round((float) (line.getPoint2().getX()));
	    i4 = Math.round((float) (line.getPoint2().getY()));
	    
	    if (line.getPointVisibility()) {
	    	Color lastColor = graphics.getColor();
	    	graphics.setColor(Color.BLACK);
	    	graphics.drawRect(i1-5,i2-5,10,10);
	    	graphics.drawRect(i3-5,i4-5,10,10);
	    	graphics.setColor(lastColor);
	    }
	    
	    graphics.drawLine(i1, i2, i3, i4);
  }
}
  public void draw(Ellipse ellipse) {
	  int x = 0;
	  int y = 0;
	  int w = 0;
	  int h = 0;
	  
	  float p1x = 0;
	  float p1y = 0;
	  float p2x = 0;
	  float p2y = 0;
	  
	  if(ellipse.getPoint1() != null && ellipse.getPoint2() != null) {
		  p1x = (float) ellipse.getPoint1().getX();
		  p1y = (float) ellipse.getPoint1().getY();
		  p2x = (float) ellipse.getPoint2().getX();
		  p2y = (float) ellipse.getPoint2().getY();
		  
		  if (ellipse.getPointVisibility()) {
			  Color lastColor = graphics.getColor();
			  graphics.setColor(Color.BLACK);
			  int x1, y1, x2, y2;
			  x1 = Math.round(p1x);
			  y1 = Math.round(p1y);
			  x2 = Math.round(p2x);
			  y2 = Math.round(p2y);
			  
			  graphics.drawRect(x1-5, y1-5, 10, 10);
			  graphics.drawRect(x2-5, y2-5, 10, 10);
			  graphics.setColor(lastColor);
		  }
		  
		  x = Math.round( Math.min(p1x, p2x) );
		  y = Math.round( Math.min(p1y, p2y) );
		  w = Math.abs( Math.round( Math.max(p1x, p2x) - Math.min(p1x, p2x) ) );
		  h = Math.abs( Math.round( Math.max(p1y, p2y) - Math.min(p1y, p2y) ) );
		  graphics.drawOval(x, y, w, h);
	  }
}
  
  
  public void draw(BSpline spline) {
	  
	  if (spline.getPointVisibility()) {
		  Color lastColor = graphics.getColor();
		  graphics.setColor(Color.BLACK);
		  Point p1, p2;
		  int ix, iy;
		  Point dotP1, dotP2;
		  for(int i=0; i<spline.getPointQty(); i++) {
			  p1 = new Point(spline.getPoint(i));
			  ix = Math.round((float)p1.getX());
			  iy = Math.round((float)p1.getY());
			  graphics.drawRect(ix-5, iy-5, 10, 10);
		  }
		  for(int i=0; i<spline.getPointQty()-1; i++) {
		    	p1 = new Point( spline.getPoint(i) );
		    	p2 = new Point( spline.getPoint(i+1) );
		    	draw(new Line(p1, p2));
		    }
		  graphics.setColor(lastColor);
	  }
	  
	  if(spline.getPointQty() >= 3) {
		  int prc = spline.getPointQty()*50;
		  
		  for (int i = 1; i <= prc; i++) {
			  double lt = (double)(i-1)/(double)prc;
			  double rt = (double)i/(double)prc;
			  int ax, ay, bx, by;
			  Point curvePoint = spline.getBezierCurvePoint(lt);
			  ax = Math.round((float)curvePoint.getX());
			  ay = Math.round((float)curvePoint.getY());
			  curvePoint = spline.getBezierCurvePoint(rt);
			  bx = Math.round((float)curvePoint.getX());
			  by = Math.round((float)curvePoint.getY());
			  
			  graphics.drawLine(ax, ay, bx, by);
		  }
	  }
  }
  
  public void draw(Item item) {
    System.out.println( "Cant draw unknown Item \n");
  }
}