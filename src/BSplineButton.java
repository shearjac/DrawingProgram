import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class BSplineButton  extends JButton implements ActionListener {
  protected JPanel drawingPanel;
  protected View view;
  private UIContext uiContext;
  private MouseHandler mouseHandler;
  private BSplineCommand bSplineCommand;
  private UndoManager undoManager;
  public BSplineButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
    super("B-Spline");
    this.undoManager = undoManager;
    addActionListener(this);
    view = jFrame;
    uiContext = view.getUI();
    drawingPanel = jPanel;
    mouseHandler = new MouseHandler();
  }
  public void actionPerformed(ActionEvent event) {
    view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    // Change cursor when button is clicked
    drawingPanel.addMouseListener(mouseHandler);
  // Start listening for mouseclicks on the drawing panel
  }
  private class MouseHandler extends MouseAdapter {
    private int pointCount = 0;
    public void mouseClicked(MouseEvent event) {
    if (++pointCount == 1) {
        bSplineCommand = new BSplineCommand();
        bSplineCommand.setSplinePoint(View.mapPoint(event.getPoint()));
        undoManager.beginCommand(bSplineCommand);
    } else if (pointCount == 2) {
    	bSplineCommand.setSplinePoint(View.mapPoint(event.getPoint()));
    } else if (pointCount >= 3) {
    	if (event.getButton() != MouseEvent.BUTTON1) {
    		pointCount = 0;
        	drawingPanel.removeMouseListener(this);
        	view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        	undoManager.endCommand(bSplineCommand);
    	} else {
    		bSplineCommand.setSplinePoint(View.mapPoint(event.getPoint()));
    		drawingPanel.repaint();
    	}
      }
    }
  }
}