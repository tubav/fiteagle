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
	public final static double[] neg45 = { ConnectionView.sq2,
			ConnectionView.sq2, -ConnectionView.sq2, ConnectionView.sq2 };
	public final static double[] pos45 = { ConnectionView.sq2,
			-ConnectionView.sq2, ConnectionView.sq2, ConnectionView.sq2 };

	private final String type;

	private CLabel source;

	private ResourceInstanceWidget sourceWidget;
	private ResourceInstanceWidget targetWidget;

	private Point dragPosition;

	private Point handle;

	public ConnectionView(final String type) {
		this.type = type;
	}

	public boolean selected(final Point point) {
		return (this.dist(point, this.handle) < 13);
	}

	public void setSource(final ResourceInstanceWidget widget,
			final CLabel label) {
		this.source = label;
		this.sourceWidget = widget;
	}

	public ResourceInstanceWidget getSourceWidget() {
		return this.sourceWidget;
	}

	public CLabel getSourceLabel() {
		return this.source;
	}

	public void setTarget(final ResourceInstanceWidget widget,
			final CLabel label) {
		this.targetWidget = widget;
		this.dragPosition = null;
	}

	public ResourceInstanceWidget getTargetWidget() {
		return this.targetWidget;
	}

	public void setDragPosition(final Point position) {
		this.dragPosition = position;
	}

	public void draw(final GC gc) {
		if (this.sourceWidget == null)
			return;

		// EvaluationHandler handler = new EvaluationHandler();
		//
		// if (!ValidateActions.validateConnection(
		// sourceWidget.getResourceInstance(),
		// targetWidget.getResourceInstance(), type, handler)) {
		// //some error message
		// Util.showMsg(sourceWidget.getShell(), SWT.ERROR,
		// "Resource Selection Failed", handler.getMessage());
		// System.out.println("Connection not allowed");
		// } else {

		if (this.type.equalsIgnoreCase("CONTAINS")) {
			gc.setBackground(this.sourceWidget.getDisplay().getSystemColor(
					SWT.COLOR_BLUE));
			gc.setForeground(this.sourceWidget.getDisplay().getSystemColor(
					SWT.COLOR_BLUE));
			gc.setLineStyle(SWT.LINE_DASH);
		} else {
			gc.setBackground(this.sourceWidget.getDisplay().getSystemColor(
					SWT.COLOR_BLACK));
			gc.setForeground(this.sourceWidget.getDisplay().getSystemColor(
					SWT.COLOR_BLACK));
			gc.setLineStyle(SWT.LINE_SOLID);
		}

		final Point dest = this.dragPosition != null ? this.dragPosition
				: this.targetWidget.getParent().toControl(
						this.targetWidget.getDstPinPosition());

		final Rectangle imageBounds = this.source.getImage().getBounds();
		final Rectangle bounds = this.source.getBounds();
		final Point src = this.sourceWidget.getParent().toControl(
				this.source.toDisplay(imageBounds.x + imageBounds.width,
						bounds.height / 2));
		final Point[] p = this.bezierCubic(src, dest, 5);

		final int[] coords = new int[p.length * 2];
		for (int i = 0; i < p.length; i++) {
			coords[i * 2] = p[i].x;
			coords[(i * 2) + 1] = p[i].y;
		}
		gc.drawPolyline(coords);

		gc.setLineStyle(SWT.LINE_SOLID);
		// draw the arrow at the tip
		if (p.length >= 2) {
			final Point a0 = p[p.length - 1];
			final Point a1 = p[p.length - 2];
			final Point v = new Point(a1.x - a0.x, a1.y - a0.y);
			final double seglen = Math.sqrt((v.x * v.x) + (v.y * v.y)) / 7;
			final Point aa = this.mulMat(ConnectionView.pos45, v);
			final Point ab = this.mulMat(ConnectionView.neg45, v);
			aa.x /= seglen;
			aa.y /= seglen;
			ab.x /= seglen;
			ab.y /= seglen;
			gc.drawLine(aa.x + a0.x, aa.y + a0.y, a0.x, a0.y);
			gc.drawLine(ab.x + a0.x, ab.y + a0.y, a0.x, a0.y);
		}

		// draw a handle in the middle for editing config/changing type/...
		this.handle = p[p.length / 2];
		gc.fillOval(this.handle.x - 7, this.handle.y - 7, 13, 13);
		// }
	}

	private double dist(final Point a, final Point b) {
		return Math.sqrt(((a.x - b.x) * (a.x - b.x))
				+ ((a.y - b.y) * (a.y - b.y)));
	}

	private Point mulMat(final double[] a, final Point v) {
		return new Point((int) ((a[0] * v.x) + (a[1] * v.y)),
				(int) ((a[2] * v.x) + (a[3] * v.y)));
	}

	public Point[] bezierCubic(final Point p0, final Point p3, final int step) {
		final Point p1 = new Point(p0.x + 100, p0.y);
		final Point p2 = new Point(p3.x - 100, p3.y);
		if (p0.y == p3.y) {
			p1.y -= 80;
			p2.y -= 80;
		}
		final Point[] p = { p0, p1, p2, p3 };

		final int mdist = Math.abs(p0.x - p3.x) + Math.abs(p0.y - p3.y);
		final int pts = mdist / step;
		final Point[] curve = new Point[pts + 1];

		for (int k = 0; k <= pts; k++) {
			final double t = ((double) k) / pts;
			final double t1 = 1.0 - t;
			final double[] c = { t1 * t1 * t1, 3 * t1 * t1 * t, 3 * t1 * t * t,
					t * t * t };
			final double x = (c[0] * p[0].x) + (c[1] * p[1].x)
					+ (c[2] * p[2].x) + (c[3] * p[3].x);
			final double y = (c[0] * p[0].y) + (c[1] * p[1].y)
					+ (c[2] * p[2].y) + (c[3] * p[3].y);
			curve[k] = new Point((int) x, (int) y);
		}

		return curve;
	}
}
