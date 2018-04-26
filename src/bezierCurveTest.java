

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class bezierCurveTest {

	@Test
	void testBezierCurve() {
		Point[] pa = new Point[4];
		pa[0]=new Point(100,200);
		pa[1]=new Point(150,350);
		pa[2]=new Point(175, 400);
		pa[3]=new Point(100, 50);
		
		BezierCurve bc = new BezierCurve( pa );
		assert((int)(float)bc.getPoint(0).getX() == (int)(float)pa[0].getX());
	}

	@Test
	void testAddPoint() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSize() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPointAt() {
		fail("Not yet implemented");
	}

	@Test
	void testCalculateCurvePointAt() {
		Point[] pa = new Point[4];
		pa[0]=new Point(100,200);
		pa[1]=new Point(150,350);
		pa[2]=new Point(175, 400);
		pa[3]=new Point(100, 50);
		
		BezierCurve bc = new BezierCurve( pa );
		double finalX = bc.calculateCurvePointAt(0.5).getX();
		assert((int)finalX == 147);
	}

}
