import java.awt.Point;
import java.util.Vector;

public class BezierCurve {
	private Vector<Double> xv = new Vector<>();
	private Vector<Double> yv = new Vector<>();
	private Vector<Vector<Double>> bxv = new Vector<>();
	private Vector<Vector<Double>> byv = new Vector<>();
	
	public BezierCurve( Vector<Point> pv ) {
		for (Point p : pv) {
			xv.add(p.getX());
			yv.add(p.getY());
		}
	}
	
	public BezierCurve( Point[] pa) {
		for(Point p: pa) {
			xv.add(p.getX());
			yv.add(p.getY());
		}
	}
	
	public BezierCurve() {
	}
	
	public void addPoint(Point p) {
		xv.add(p.getX());
		yv.add(p.getY());
	}
	
	public Point getPoint(int index) {
		int ipx, ipy;
		ipx = Math.round((float)(double)xv.get(index));
		ipy = Math.round((float)(double)yv.get(index));
		return new Point(ipx,ipy);
	}
	
	public int getSize() {
		assert( xv.size() == yv.size() );
		return xv.size();
	}
		
	public Point calculateCurvePointAt(double t) {
		Point result;
		double lop, rop;
		int ipx, ipy;
		bxv = new Vector<>(); 
		byv = new Vector<>(); 
		for (int i = 0; i<getSize(); i++) { //Add appropriate number of levels (n points - 1)
			bxv.add(new Vector<>());
			byv.add(new Vector<>());
		}
		for (int i=0; i<getSize(); i++) { //Fill top level with Points
			bxv.get(0).add(xv.get(i));
			byv.get(0).add(yv.get(i));
		}
		for (int row=1; row<getSize(); row++) { 
			//Iterate on rows starting at depth 1
			for (int col=0; col<(getSize()-row); col++) { 
				//Iterate on columns starting at 0
				//The length of each row is number of points - depth (row #)
				
				//Calculate X
				lop = (1.0-t) * bxv.get(row-1).get(col); //Left operand is one above
				rop = t * bxv.get(row-1).get(col+1); //Right operand is one above and to the right
				bxv.get(row).add(lop + rop);
				
				//Calculate Y
				lop = (1.0-t) * byv.get(row-1).get(col);
				rop = t * byv.get(row-1).get(col+1);
				byv.get(row).add(lop + rop);
			}
		}
		ipx = Math.round( (float)(double)bxv.get(getSize()-1).get(0) );
		ipy = Math.round( (float)(double)byv.get(getSize()-1).get(0) );
		result = new Point(ipx,ipy);
		return result;
	}
}
