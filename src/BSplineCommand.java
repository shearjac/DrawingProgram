import java.awt.*;
import java.text.*;
public class BSplineCommand extends Command {
  private BSpline spline;
  private int pointCount;
  public BSplineCommand() {
    spline = new BSpline();
    pointCount = 0;
  }
  public void setSplinePoint(Point point) {
    spline.addPoint(point);
  }
  public void execute() {
    model.addItem(spline);
  }
  public boolean undo() {
    model.removeItem(spline);
    return true;
  }
  public boolean redo() {
    execute();
    return true;
  }
  public boolean end() {
    for (int i = 0; i < spline.getPointQty(); i++) {
    	if (spline.getPoint(i) == null) { return false; }
    }
    
    spline.clearTemporaryPoints();
    
    return true;
  }
}
