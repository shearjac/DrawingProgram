import java.awt.*;
import java.util.*;
public class SelectCommand extends Command {
  private Item item;
  public SelectCommand() {
  }
  public void setPoint(Point point) {
    Enumeration enumeration = model.getItems();
    while (enumeration.hasMoreElements()) {
      item = (Item)(enumeration.nextElement());
      if (item.includes(point)) {
        model.markSelected(item);
        break;
      } 
    }
    Enumeration<Item> items = model.getItems();
    Enumeration<Item> selectedItems = model.getSelectedItems();
    while( items.hasMoreElements()) {
    	items.nextElement().hidePoints();
    }
    while( selectedItems.hasMoreElements()) {
    	selectedItems.nextElement().hidePoints();
    }
  }
  public boolean undo() {
    model.unSelect(item);
    return true;
  }
  public boolean  redo() {
	model.markSelected(item);
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
}
