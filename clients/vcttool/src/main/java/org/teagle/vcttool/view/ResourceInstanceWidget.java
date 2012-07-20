/**
 * 
 */
package org.teagle.vcttool.view;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import teagle.vct.model.ResourceInstance;

/**
 * @author sim
 * 
 */
public class ResourceInstanceWidget extends Composite implements Listener {

	private final Group group;

	private final Label state;
	private final Label ptmName;

	private final CLabel dstPin;
	private final CLabel srcPin;

	private final Composite sourceList;

	private final Set<ConnectionListener> connectionListeners = new CopyOnWriteArraySet<ConnectionListener>();
	private final Set<ResourceInstanceListener> instanceListeners = new CopyOnWriteArraySet<ResourceInstanceListener>();

	private Point posOrig;
	private Point posDragged;

	private final VctView vctView;

	private ResourceInstance resourceInstance;

	public ResourceInstanceWidget(final VctView parent) {
		super(parent.getContentView(), SWT.NONE);

		this.vctView = parent;

		final RowLayout layout = new RowLayout();
		layout.wrap = false;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.marginTop = 0;
		layout.marginBottom = 0;

		this.setLayout(layout);

		final Image imgOrange = new Image(this.getDisplay(), Thread
				.currentThread().getContextClassLoader()
				.getResourceAsStream("icons/bu131.gif"));

		this.group = new Group(this, SWT.NONE);
		final GridLayout groupLayout = new GridLayout(3, false);
		groupLayout.marginLeft = 0;
		groupLayout.marginRight = 0;
		groupLayout.marginWidth = 0;
		groupLayout.horizontalSpacing = 10;

		this.group.setLayout(groupLayout);
		this.group.addListener(SWT.MouseDown, this);
		this.group.addListener(SWT.MouseUp, this);
		this.group.addListener(SWT.MouseMove, this);
		this.group.addListener(SWT.MouseDoubleClick, this);

		this.dstPin = new CLabel(this.group, SWT.NONE);
		this.dstPin.setImage(imgOrange);
		this.dstPin.addListener(SWT.MouseUp, this);
		final GridData data = new GridData(SWT.LEFT, SWT.TOP, false, false, 1,
				2);
		data.horizontalIndent = 0;
		this.dstPin.setLayoutData(data);

		this.state = new Label(this.group, SWT.NONE);
		this.state
				.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));
		this.state.addListener(SWT.MouseDown, this);
		this.state.addListener(SWT.MouseUp, this);
		this.state.addListener(SWT.MouseMove, this);
		this.state.addListener(SWT.MouseDoubleClick, this);

		this.sourceList = new Composite(this.group, SWT.NONE);
		final GridData data2 = new GridData(SWT.RIGHT, SWT.TOP, true, false, 1,
				3);
		data2.horizontalIndent = 0;
		this.sourceList.setLayoutData(data2);
		final FillLayout fillLayout = new FillLayout(SWT.VERTICAL | SWT.RIGHT);
		fillLayout.marginHeight = 0;
		fillLayout.marginWidth = 0;
		this.sourceList.setLayout(fillLayout);

		this.ptmName = new Label(this.group, SWT.NONE);
		this.ptmName.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false,
				false));
		this.ptmName.addListener(SWT.MouseDown, this);
		this.ptmName.addListener(SWT.MouseUp, this);
		this.ptmName.addListener(SWT.MouseMove, this);
		this.ptmName.addListener(SWT.MouseDoubleClick, this);

		this.srcPin = new CLabel(this.sourceList, SWT.RIGHT_TO_LEFT);
		this.srcPin.setImage(imgOrange);
		this.srcPin.addListener(SWT.MouseDown, this);
		this.srcPin.addListener(SWT.MouseUp, this);
		this.srcPin.addListener(SWT.MouseMove, this);

		// GridData data3 = new GridData(SWT.LEFT, SWT.BOTTOM, false, false);
		final GridData data3 = new GridData();
		data3.horizontalAlignment = GridData.FILL;
		data3.horizontalIndent = 1;

		final Button helpButton = new Button(this.group, SWT.PUSH);
		helpButton.setLayoutData(data3);
		helpButton.setText("?");
		helpButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent event) {
				ResourceInstanceWidget.this
						.fireHelp(ResourceInstanceWidget.this.vctView);
			}
		});
		helpButton.setEnabled(true);

		final Button configButton = new Button(this.group, SWT.PUSH);
		configButton.setLayoutData(data3);
		configButton.setText("config");
		configButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent event) {
				ResourceInstanceWidget.this
						.fireConfig(ResourceInstanceWidget.this.vctView);
			}
		});
		configButton.setEnabled(true);

		final Button deleteButton = new Button(this.group, SWT.PUSH);
		// deleteButton.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false,
		// false));
		deleteButton.setLayoutData(data3);
		deleteButton.setText("X");
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent event) {
				ResourceInstanceWidget.this.fireDelete();
			}
		});
		deleteButton.setEnabled(true);

	}

	public void setResourceInstance(final ResourceInstance resInstance) {
		this.resourceInstance = resInstance;
	}

	public ResourceInstance getResourceInstance() {
		return this.resourceInstance;
	}

	public void setName(final String name) {
		this.group.setText(name);
	}

	public void setDescription(final String description) {
		this.group.setToolTipText(description);
	}

	public void setState(final String state) {
		this.state.setText("State: " + state);
	}

	public void setPtmName(final String name) {
		this.ptmName.setText("Ptm: " + name);
	}

	public Point getDstPinPosition() {
		final Rectangle imageBounds = this.dstPin.getImage().getBounds();
		final Rectangle bounds = this.dstPin.getBounds();
		return this.dstPin.toDisplay(imageBounds.x - 4, bounds.height / 2);
	}

	public CLabel getDstPin() {
		return this.dstPin;
	}

	public CLabel getSrcPin(final String identifier) {
		for (final Control control : this.sourceList.getChildren())
			if (control instanceof CLabel) {
				final String name = ((CLabel) control).getText();
				if ((name != null) && name.equals(identifier))
					return (CLabel) control;
			}
		return this.srcPin;
	}

	public CLabel findTargetLabel(final Point point) {
		if (this.dstPin.getBounds().contains(this.toControl(point)))
			return this.dstPin;
		return null;
	}

	public void deleteConnection(final ConnectionView connectionView) {
		final ConnectionEvent connectionEvent = new ConnectionEvent();
		connectionEvent.sourceLabel = connectionView.getSourceLabel();
		connectionEvent.source = this;
		connectionEvent.target = connectionView.getTargetWidget();
		connectionEvent.parent = this.vctView;

		this.fireConnectionDeletedEvent(new ConnectionEvent());
	}

	public void addConfigSource(final String name, final Object data,
			final String tooltip) {
		final CLabel source = new CLabel(this.sourceList, SWT.RIGHT_TO_LEFT
				| SWT.BORDER_SOLID);
		source.setText(name);
		final Image imgGrey = new Image(this.getDisplay(), Thread
				.currentThread().getContextClassLoader()
				.getResourceAsStream("icons/bu127.gif"));
		source.setImage(imgGrey);

		final Menu menu = new Menu(this.getShell(), SWT.POP_UP);
		final MenuItem details = new MenuItem(menu, SWT.PUSH);
		details.setText("Details...");
		final MenuItem clear = new MenuItem(menu, SWT.PUSH);
		clear.setText("Clear");

		source.setMenu(menu);

		if (data != null)
			source.setData(data);

		if (tooltip != null)
			source.setToolTipText(tooltip);

		source.addListener(SWT.MouseDown, this);
		source.addListener(SWT.MouseUp, this);
		source.addListener(SWT.MouseMove, this);
	}

	public void handleEvent(final Event event) {
		switch (event.type) {
		case SWT.MouseDoubleClick:
			this.fireEditConfig(this.vctView);
			this.posOrig = null;
			break;
		case SWT.MouseDown:
			if ((event.widget != null) && (event.widget instanceof CLabel)) {
				final ConnectionView connection = new ConnectionView(
						event.widget == this.srcPin ? "CONTAINS" : "REFERENCES");
				connection.setSource(this, (CLabel) event.widget);
				connection.setDragPosition(((CLabel) event.widget).toDisplay(
						event.x, event.y));
				this.vctView.dragConnectionBegin(connection);

			} else {
				this.posOrig = this.getLocation();
				this.posDragged = this.toDisplay(event.x, event.y);
			}
			break;
		case SWT.MouseMove:
			if ((event.widget != null) && (event.widget instanceof CLabel))
				this.vctView.dragConnectionUpdate(((CLabel) event.widget)
						.toDisplay(event.x, event.y));
			else if (this.posOrig != null) {
				final Point posNew = this.toDisplay(event.x, event.y);
				int x = (this.posOrig.x + posNew.x) - this.posDragged.x;
				int y = (this.posOrig.y + posNew.y) - this.posDragged.y;

				if (x < 0)
					x = 0;
				if (y < 0)
					y = 0;

				this.setLocation(x, y);

				this.getParent().redraw();
			}
			break;
		case SWT.MouseUp:
			if ((event.widget != null) && (event.widget instanceof CLabel)) {
				final ResourceInstanceWidget target = this.vctView
						.dragConnectionEnd(((CLabel) event.widget).toDisplay(
								event.x, event.y), this, (CLabel) event.widget);
				if (target != null) {
					final ConnectionEvent connectionEvent = new ConnectionEvent();
					connectionEvent.sourceLabel = (CLabel) event.widget;
					connectionEvent.source = this;
					connectionEvent.target = target;
					connectionEvent.parent = this.vctView;
					this.fireConnectionNewEvent(connectionEvent);
				}
			} else if (this.posOrig != null) {
				this.posOrig = null;
				this.fireMoved(this.getLocation());
			}
			break;
		default:
			break;
		}
	}

	public void addConnectionListener(final ConnectionListener listener) {
		this.connectionListeners.add(listener);
	}

	public void removeConnectionListener(final ConnectionListener listener) {
		this.connectionListeners.remove(listener);
	}

	public void addResourceInstanceListener(
			final ResourceInstanceListener listener) {
		this.instanceListeners.add(listener);
	}

	public void removeResourceInstanceListener(
			final ResourceInstanceListener listener) {
		this.instanceListeners.remove(listener);
	}

	private void fireConnectionNewEvent(final ConnectionEvent event) {
		for (ConnectionListener listener : this.connectionListeners) {
			try {
				listener.onConnectionNew(event);
			} catch (final RuntimeException e) {
				// log output
				this.connectionListeners.remove(listener);
			}
		}
	}

	private void fireConnectionDeletedEvent(final ConnectionEvent event) {
		for (ConnectionListener listener : this.connectionListeners) {
			try {
				listener.onConnectionDeleted(event);
			} catch (final RuntimeException e) {
				// log output
				this.connectionListeners.remove(listener);
			}
		}
	}

	private void fireMoved(final Point position) {
		for (ResourceInstanceListener listener : this.instanceListeners) {
			try {
				listener.onMoved(position);
			} catch (final RuntimeException e) {
				// log output
				this.instanceListeners.remove(listener);
			}
		}
	}

	private void fireEditConfig(final VctView vctView) {
		for (ResourceInstanceListener listener : this.instanceListeners) {
			try {
				listener.onEditConfig(vctView);
			} catch (final RuntimeException e) {
				// log output
				this.instanceListeners.remove(listener);
			}
		}
	}

	private void fireHelp(final VctView vctView) {
		for (ResourceInstanceListener listener : this.instanceListeners) {
			try {
				listener.onHelp(vctView);
			} catch (final RuntimeException e) {
				// log output
				this.instanceListeners.remove(listener);
			}
		}
	}

	private void fireDelete() {
		for (ResourceInstanceListener listener : this.instanceListeners) {
			try {
				listener.onDelete(this);
			} catch (final RuntimeException e) {
				// log output
				this.instanceListeners.remove(listener);
			}
		}
	}

	private void fireConfig(final VctView vctView) {
		// TODO
		System.out.println("configure");
		for (ResourceInstanceListener listener : this.instanceListeners) {
			try {
				listener.onEditConfig(vctView);
			} catch (final RuntimeException e) {
				e.printStackTrace();
				// instanceListeners.remove(listener);
			}
		}
	}

}
