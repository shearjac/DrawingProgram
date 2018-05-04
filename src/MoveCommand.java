import java.awt.*;
import java.util.*;
public class MoveCommand extends Command {
  private Item selectedItem = null;
  private Item item;
  private Point movingPoint;
  private Point oldPoint;
  private Point newPoint;
  private boolean itemSelected;
  public MoveCommand() {
  }
  public void setPoint(Point point) {
	  System.out.println("setPoint");
	  System.out.println("itemSelected = " + itemSelected);
	  if (itemSelected == false) {
	    Enumeration enumeration = model.getItems();
	    while (enumeration.hasMoreElements() && itemSelected == false) {
	      item = (Item)(enumeration.nextElement());
	      if (item.includes(point)) {
	        selectedItem = item;
	        System.out.println("Item selected");
	        movingPoint = item.getClosestPoint(point);
	        oldPoint = new Point(item.getClosestPoint(point));
	        itemSelected = true;
	        item.isMoving();
	        
	        Enumeration<Item> items = model.getItems();
		    Item item;
		    while( items.hasMoreElements()) {
		    	item = items.nextElement();
		    	item.hidePoints();
		    }
		    model.setChanged();
	      }
	    }
	  } else {
		  newPoint = new Point(point);
		  item.isStill();
	  }
  }
  public boolean undo() {
	  int x, y;
	  x = Math.round((float)oldPoint.getX());
	  y = Math.round((float)oldPoint.getY());
    movingPoint.setLocation(x,y);
    model.setChanged();
    return true;
  }
  public boolean  redo() {
	  int x, y;
	  x = Math.round((float)newPoint.getX());
	  y = Math.round((float)newPoint.getY());
    movingPoint.setLocation(x,y);
    model.setChanged();
    return true;
  }
  public void execute() {
    Enumeration<Item> items = model.getItems();
    Item item;
    while( items.hasMoreElements()) {
    	item = items.nextElement();
    	item.showPoints();
    }
  }
  public boolean end() {
	  if (oldPoint == null) {
		  return false;
	  }
	  
	  if (newPoint == null) {
		  return false;
	  }
	  
	  if (selectedItem.getMoving()) {
		  return false;
	  }
	  System.out.println("Does it end?");
	    return true;
  }
  
  public void setMovingPoint(Point point) {
	  if (selectedItem != null) {
		  System.out.println("MovingPoint moved");
		  int x, y;
		  x = Math.round((float)point.getX());
		  y = Math.round((float)point.getY());
		  movingPoint.setLocation(x, y);;
	  }
  }
}
