/**
 * 
 */
package org.teagle.vcttool.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import teagle.vct.model.ConfigParamAtomic;

/**
 * @author sim
 * 
 */
public class VctView extends Composite implements PaintListener, MouseListener,
		SelectionListener {

	private final Set<VctListener> vctListeners = new CopyOnWriteArraySet<VctListener>();

	private final List<ConnectionView> connections = new ArrayList<ConnectionView>();

	private ConnectionView dragConnection;

	private final Composite content;
	private final ScrolledComposite scrolled;

	private final Menu connectionMenu;
	private final MenuItem connectionDeleteMenuItem;

	public VctView(final Composite parent) {
		super(parent, SWT.NONE);

		final GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		this.setLayout(layout);

		this.scrolled = new ScrolledComposite(this, SWT.H_SCROLL | SWT.V_SCROLL);
		this.scrolled
				.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		this.scrolled.setExpandHorizontal(true);
		this.scrolled.setExpandVertical(true);

		this.content = new Composite(this.scrolled, SWT.BORDER
				| SWT.DOUBLE_BUFFERED);
		this.content.setBackground(this.getDisplay().getSystemColor(
				SWT.COLOR_WHITE));
		this.content.setBackgroundMode(SWT.INHERIT_FORCE);

		this.content.addMouseListener(this);

		final DropTarget dropTarget = new DropTarget(this.content,
				DND.DROP_COPY | DND.DROP_MOVE);
		dropTarget.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		dropTarget.addDropListener(new DropTargetAdapter() {
			@Override
			public void drop(final DropTargetEvent event) {
				VctView.this.fireResourceSpecDropped((String) event.data,
						VctView.this.content.toControl(event.x, event.y));
			}
		});

		this.scrolled.setContent(this.content);

		this.content.addPaintListener(this);

		this.connectionMenu = new Menu(this.getShell(), SWT.POP_UP);

		this.connectionDeleteMenuItem = new MenuItem(this.connectionMenu,
				SWT.PUSH);
		this.connectionDeleteMenuItem.setText("Delete");
		this.connectionDeleteMenuItem.addSelectionListener(this);
	}

	public Composite getContentView() {
		return this.content;
	}

	public void removeResourceInstanceWidget(final ResourceInstanceWidget widget) {
		for (final Control control : this.content.getChildren())
			if ((control instanceof ResourceInstanceWidget)
					&& (control == widget)) {
				for (final Iterator<ConnectionView> it = this.connections
						.iterator(); it.hasNext();) {
					final ConnectionView connection = it.next();
					if ((connection.getSourceWidget() == widget)
							|| (connection.getTargetWidget() == widget))
						it.remove();
				}
				widget.dispose();
				this.updateContent();
			}
	}

	public void addConnectionView(final ConnectionView view) {
		this.connections.add(view);
	}

	public boolean removeConnectionView(final ConnectionView view) {
		return this.connections.remove(view);
	}

	public void dragConnectionBegin(final ConnectionView connection) {
		this.dragConnection = connection;
	}

	public void dragConnectionUpdate(final Point update) {
		if (this.dragConnection != null) {
			this.dragConnection.setDragPosition(this.content.toControl(update));
			this.updateContent();
		}
	}

	public ResourceInstanceWidget dragConnectionEnd(final Point end,
			final ResourceInstanceWidget src, final CLabel cLabel) {
		ResourceInstanceWidget found = null;
		ResourceInstanceWidget source = null;
		// ResourceInstanceWidget destination = null;
		if (this.dragConnection != null) {
			for (final Control control : this.content.getChildren())
				if (control instanceof ResourceInstanceWidget) {
					final ResourceInstanceWidget widget = (ResourceInstanceWidget) control;
					final CLabel target = widget.findTargetLabel(end);
					if (target != null) {
						source = src;
						// destination = widget;
						String connectionType = "contains";
						if (cLabel.getData() != null) {
							connectionType = ((ConfigParamAtomic) cLabel
									.getData()).getType();
							if (connectionType == null)
								System.out
										.println("Error: Error when attemptig to get connection type");
						}
						/*
						 * EvaluationHandler handler = new EvaluationHandler();
						 * if(! ValidateActions.validateConnection(source.
						 * getResourceInstance(),
						 * destination.getResourceInstance(), connectionType,
						 * handler)) { Util.showMsg(this.getShell(), SWT.ERROR,
						 * "Connection Failed", handler.getMessage());
						 * System.out.println("connection failed"); break; }
						 */
						this.dragConnection.setTarget(widget, target);
						this.connections.add(this.dragConnection);
						found = widget;
						break;
					}
					if (source == null)
						source = widget;
				}
			this.dragConnection = null;
			this.updateContent();
		}
		return found;
	}

	public void addVctListener(final VctListener listener) {
		this.vctListeners.add(listener);
	}

	public void removeVctListener(final VctListener listener) {
		this.vctListeners.remove(listener);
	}

	private void fireResourceSpecDropped(final String specName,
			final Point position) {
		for (VctListener listener : this.vctListeners) {
			try {
				if (specName.startsWith("instance:"))
					listener.onResourceInstanceDropped(specName.substring(9),
							position);
				else
					listener.onResourceSpecDropped(specName, position);
			} catch (final RuntimeException e) {
				e.printStackTrace();
				// log output
				// vctListeners.remove(listener);
			}
		}
	}

	public void updateContent() {
		this.content.redraw();
		this.scrolled.setMinSize(this.content.computeSize(SWT.DEFAULT,
				SWT.DEFAULT));
	}

	public void paintControl(final PaintEvent event) {

		if (!this.connections.isEmpty() || (this.dragConnection != null)) {
			event.gc.setLineWidth(2);
			event.gc.setAntialias(SWT.ON);
		}

		if (this.dragConnection != null)
			this.dragConnection.draw(event.gc);

		for (final ConnectionView connection : this.connections)
			connection.draw(event.gc);
	}

	public void mouseDoubleClick(final MouseEvent arg0) {
	}

	public void mouseDown(final MouseEvent event) {
		for (final ConnectionView connection : this.connections)
			if (connection.selected(new Point(event.x, event.y))) {
				this.connectionMenu.setLocation(this.content.toDisplay(event.x,
						event.y));
				this.connectionMenu.setVisible(true);
				this.connectionMenu.setData(connection);
			}
	}

	public void mouseUp(final MouseEvent arg0) {
	}

	public void widgetDefaultSelected(final SelectionEvent arg0) {
	}

	public void widgetSelected(final SelectionEvent event) {
		if (event.widget == this.connectionDeleteMenuItem) {
			final ConnectionView connection = (ConnectionView) this.connectionMenu
					.getData();
			this.connections.remove(connection);

			connection.getSourceWidget().deleteConnection(connection);
		}
		this.updateContent();
	}

}
