import java.awt.*;
import java.text.*;
public class BSplineCommand extends Command {
  private BSpline spline;
  public BSplineCommand() {
    spline = new BSpline();
  }
  public void setSplinePoint(Point point) {
    spline.addPoint(point);
  }
  public void execute() {
    model.addItem(spline);
    spline.showPoints();
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
    for (int i = 0; i < spline.getSize(); i++) {
    	if (spline.getPoint(i) == null) { return false; }
    }
    spline.hidePoints();
    return true;
  }
}
