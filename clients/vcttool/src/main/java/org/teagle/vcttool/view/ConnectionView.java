/**
 * 
 */
package org.teagle.vcttool.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

/**
 * @author sim
 * 
 */
public class ConnectionView {
	public final static double sq2 = Math.sqrt(2);
	public final static double[] neg45 = { sq2, sq2, -sq2, sq2 };
	public final static double[] pos45 = { sq2, -sq2, sq2, sq2 };

	private String type;

	private CLabel source;

	private ResourceInstanceWidget sourceWidget;
	private ResourceInstanceWidget targetWidget;

	private Point dragPosition;

	private Point handle;

	public ConnectionView(String type) {
		this.type = type;
	}

	public boolean selected(Point point) {
		return (dist(point, handle) < 13);
	}

	public void setSource(ResourceInstanceWidget widget, CLabel label) {
		source = label;
		sourceWidget = widget;
	}

	public ResourceInstanceWidget getSourceWidget() {
		return sourceWidget;
	}

	public CLabel getSourceLabel() {
		return source;
	}

	public void setTarget(ResourceInstanceWidget widget, CLabel label) {
		targetWidget = widget;
		dragPosition = null;
	}

	public ResourceInstanceWidget getTargetWidget() {
		return targetWidget;
	}

	public void setDragPosition(Point position) {
		dragPosition = position;
	}

	public void draw(GC gc) {
		if (sourceWidget == null) {
			return;
		}

		//EvaluationHandler handler = new EvaluationHandler();
//
//		if (!ValidateActions.validateConnection(
//				sourceWidget.getResourceInstance(),
//				targetWidget.getResourceInstance(), type, handler)) {
//			//some error message 
//			Util.showMsg(sourceWidget.getShell(), SWT.ERROR, "Resource Selection Failed", handler.getMessage());
//			System.out.println("Connection not allowed");
//		} else {

			if (type.equalsIgnoreCase("CONTAINS")) {
				gc.setBackground(sourceWidget.getDisplay().getSystemColor(
						SWT.COLOR_BLUE));
				gc.setForeground(sourceWidget.getDisplay().getSystemColor(
						SWT.COLOR_BLUE));
				gc.setLineStyle(SWT.LINE_DASH);
			} else {
				gc.setBackground(sourceWidget.getDisplay().getSystemColor(
						SWT.COLOR_BLACK));
				gc.setForeground(sourceWidget.getDisplay().getSystemColor(
						SWT.COLOR_BLACK));
				gc.setLineStyle(SWT.LINE_SOLID);
			}

			Point dest = dragPosition != null ? dragPosition : targetWidget
					.getParent().toControl(targetWidget.getDstPinPosition());

			Rectangle imageBounds = source.getImage().getBounds();
			Rectangle bounds = source.getBounds();
			Point src = sourceWidget.getParent().toControl(
					source.toDisplay(imageBounds.x + imageBounds.width,
							bounds.height / 2));
			Point[] p = bezierCubic(src, dest, 5);

			int[] coords = new int[p.length * 2];
			for (int i = 0; i < p.length; i++) {
				coords[i * 2] = p[i].x;
				coords[i * 2 + 1] = p[i].y;
			}
			gc.drawPolyline(coords);

			gc.setLineStyle(SWT.LINE_SOLID);
			// draw the arrow at the tip
			if (p.length >= 2) {
				Point a0 = p[p.length - 1];
				Point a1 = p[p.length - 2];
				Point v = new Point(a1.x - a0.x, a1.y - a0.y);
				double seglen = Math.sqrt(v.x * v.x + v.y * v.y) / 7;
				Point aa = mulMat(pos45, v);
				Point ab = mulMat(neg45, v);
				aa.x /= seglen;
				aa.y /= seglen;
				ab.x /= seglen;
				ab.y /= seglen;
				gc.drawLine(aa.x + a0.x, aa.y + a0.y, a0.x, a0.y);
				gc.drawLine(ab.x + a0.x, ab.y + a0.y, a0.x, a0.y);
			}

			// draw a handle in the middle for editing config/changing type/...
			handle = p[p.length / 2];
			gc.fillOval(handle.x - 7, handle.y - 7, 13, 13);
		//}
	}

	private double dist(Point a, Point b) {
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}

	private Point mulMat(double[] a, Point v) {
		return new Point((int) (a[0] * v.x + a[1] * v.y),
				(int) (a[2] * v.x + a[3] * v.y));
	}

	public Point[] bezierCubic(Point p0, Point p3, int step) {
		Point p1 = new Point(p0.x + 100, p0.y);
		Point p2 = new Point(p3.x - 100, p3.y);
		if (p0.y == p3.y) {
			p1.y -= 80;
			p2.y -= 80;
		}
		Point[] p = { p0, p1, p2, p3 };

		int mdist = Math.abs(p0.x - p3.x) + Math.abs(p0.y - p3.y);
		int pts = mdist / step;
		Point[] curve = new Point[pts + 1];

		for (int k = 0; k <= pts; k++) {
			double t = ((double) k) / pts;
			double t1 = 1.0 - t;
			double[] c = { t1 * t1 * t1, 3 * t1 * t1 * t, 3 * t1 * t * t,
					t * t * t };
			double x = c[0] * p[0].x + c[1] * p[1].x + c[2] * p[2].x + c[3]
					* p[3].x;
			double y = c[0] * p[0].y + c[1] * p[1].y + c[2] * p[2].y + c[3]
					* p[3].y;
			curve[k] = new Point((int) x, (int) y);
		}

		return curve;
	}
}
