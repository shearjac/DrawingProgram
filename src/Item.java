import java.io.*;
import java.awt.*;
public abstract class Item implements Serializable {
  protected static UIContext uiContext;
  public static void setUIContext(UIContext uiContext) {
    Item.uiContext = uiContext;
  }
  public abstract boolean includes(Point point);
  protected double distance(Point point1, Point point2) {
    double xDifference = point1.getX() - point2.getX();
    double yDifference = point1.getY() - point2.getY();
    return ((double) (Math.sqrt(xDifference * xDifference + yDifference * yDifference)));
  }
  public  void render() {
    uiContext.draw(this);
  }
  private boolean pointVisibility = false;
  
  public void showPoints() {
	  pointVisibility = true;
  }
  
  public void hidePoints() {
	  pointVisibility = false;
  }
  
  public boolean getPointVisibility() {
	  return pointVisibility;
  }
  
  private boolean beingMoved = false;
  
  public void isMoving() {
	  beingMoved = true;
  }
  
  public void isStill() {
	  beingMoved = false;
  }
  
  public boolean getMoving() {
	  return beingMoved;
  }
  
  public abstract Point getClosestPoint(Point point);
}