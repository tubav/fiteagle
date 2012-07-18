/**
 * 
 */
package org.teagle.vcttool.view;


import java.util.Iterator;
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

	private Group group;

	private Label state;
	private Label ptmName;

	private CLabel dstPin;
	private CLabel srcPin;

	private Composite sourceList;

	private Set<ConnectionListener> connectionListeners = new CopyOnWriteArraySet<ConnectionListener>();
	private Set<ResourceInstanceListener> instanceListeners = new CopyOnWriteArraySet<ResourceInstanceListener>();

	private Point posOrig;
	private Point posDragged;

	private VctView vctView;

	private ResourceInstance resourceInstance;
	
	public ResourceInstanceWidget(VctView parent) {
		super(parent.getContentView(), SWT.NONE);
		
		vctView = parent;

		
		RowLayout layout = new RowLayout();
		layout.wrap = false;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.marginTop = 0;
		layout.marginBottom = 0;
		
		setLayout(layout);
		
		Image imgOrange = new Image(getDisplay(), Thread.currentThread().getContextClassLoader().getResourceAsStream("icons/bu131.gif"));

		group = new Group(this, SWT.NONE);
		GridLayout groupLayout = new GridLayout(3, false);
		groupLayout.marginLeft = 0;
		groupLayout.marginRight = 0;
		groupLayout.marginWidth = 0;
		groupLayout.horizontalSpacing = 10;
		
		group.setLayout(groupLayout);
		group.addListener(SWT.MouseDown, this);
		group.addListener(SWT.MouseUp, this);
		group.addListener(SWT.MouseMove, this);
		group.addListener(SWT.MouseDoubleClick, this);

		dstPin = new CLabel(group, SWT.NONE);
		dstPin.setImage(imgOrange);
		dstPin.addListener(SWT.MouseUp, this);
		GridData data = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 2);
		data.horizontalIndent = 0;
		dstPin.setLayoutData(data);
		
		state = new Label(group, SWT.NONE);
		state.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));
		state.addListener(SWT.MouseDown, this);
		state.addListener(SWT.MouseUp, this);
		state.addListener(SWT.MouseMove, this);
		state.addListener(SWT.MouseDoubleClick, this);

		sourceList = new Composite(group, SWT.NONE);
		GridData data2 = new GridData(SWT.RIGHT, SWT.TOP, true, false, 1, 3);
		data2.horizontalIndent = 0;
		sourceList.setLayoutData(data2);
		FillLayout fillLayout = new FillLayout(SWT.VERTICAL|SWT.RIGHT);
		fillLayout.marginHeight = 0;
		fillLayout.marginWidth = 0;		
		sourceList.setLayout(fillLayout);

		ptmName = new Label(group, SWT.NONE);
		ptmName.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));
		ptmName.addListener(SWT.MouseDown, this);
		ptmName.addListener(SWT.MouseUp, this);
		ptmName.addListener(SWT.MouseMove, this);
		ptmName.addListener(SWT.MouseDoubleClick, this);
		
		srcPin = new CLabel(sourceList, SWT.RIGHT_TO_LEFT);
		srcPin.setImage(imgOrange);
		srcPin.addListener(SWT.MouseDown, this);
		srcPin.addListener(SWT.MouseUp, this);
		srcPin.addListener(SWT.MouseMove, this);
		
		
//		GridData data3 = new GridData(SWT.LEFT, SWT.BOTTOM, false, false);
		GridData data3 = new GridData();
		data3.horizontalAlignment = GridData.FILL;
		data3.horizontalIndent = 1;
		
		Button helpButton = new Button(group, SWT.PUSH);
		helpButton.setLayoutData(data3);
		helpButton.setText("?");
		helpButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				fireHelp(vctView);
			}
		});
		helpButton.setEnabled(true);
				
		Button configButton = new Button(group, SWT.PUSH);
		configButton.setLayoutData(data3);
		configButton.setText("config");
		configButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				fireConfig(vctView);
			}
		});
		configButton.setEnabled(true);
		
		Button deleteButton = new Button(group, SWT.PUSH);
//		deleteButton.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false));
		deleteButton.setLayoutData(data3);
		deleteButton.setText("X");
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				fireDelete();
			}
		});
		deleteButton.setEnabled(true);
				
	}

	
	public void setResourceInstance(ResourceInstance resInstance)
	{
		this.resourceInstance = resInstance;
	}
	
	public ResourceInstance getResourceInstance()
	{
		return this.resourceInstance;
	}
	
	public void setName(String name) {
		group.setText(name);
	}
	
	public void setDescription(String description) {
		group.setToolTipText(description);
	}
	
	public void setState(String state) {
		this.state.setText("State: " + state);
	}

	public void setPtmName(String name) {
		ptmName.setText("Ptm: " + name);
	}
	
	public Point getDstPinPosition() {
		Rectangle imageBounds = dstPin.getImage().getBounds();
		Rectangle bounds = dstPin.getBounds();
		return dstPin.toDisplay(imageBounds.x - 4, bounds.height/2);
	}
	
	public CLabel getDstPin() {
		return dstPin;
	}

	public CLabel getSrcPin(String identifier) {
		for (Control control : sourceList.getChildren()) {
			if (control instanceof CLabel) {
				String name =  ((CLabel)control).getText();
				if (name != null && name.equals(identifier)) {
					return (CLabel)control;					
				}
			}
		}
		return srcPin;
	}
	
	public CLabel findTargetLabel(Point point) {
		if (dstPin.getBounds().contains(toControl(point))) {
			return dstPin;
		}
		return null;
	}
	
	public void deleteConnection(ConnectionView connectionView) {
		ConnectionEvent connectionEvent = new ConnectionEvent();
		connectionEvent.sourceLabel = connectionView.getSourceLabel();
		connectionEvent.source = this;
		connectionEvent.target = connectionView.getTargetWidget();
		connectionEvent.parent = vctView;

		fireConnectionDeletedEvent(new ConnectionEvent());		
	}
	
	public void addConfigSource(String name, Object data, String tooltip) {
		CLabel source = new CLabel(sourceList, SWT.RIGHT_TO_LEFT|SWT.BORDER_SOLID);
		source.setText(name);
		Image imgGrey = new Image(getDisplay(), Thread.currentThread().getContextClassLoader().getResourceAsStream("icons/bu127.gif"));
		source.setImage(imgGrey);

		Menu menu = new Menu(getShell(), SWT.POP_UP);
		MenuItem details = new MenuItem(menu, SWT.PUSH);
		details.setText("Details...");
		MenuItem clear = new MenuItem(menu, SWT.PUSH);
		clear.setText("Clear");
		
		source.setMenu(menu);
		
		if (data != null) {
			source.setData(data);
		}
		
		if (tooltip != null) {
			source.setToolTipText(tooltip);
		}
		
		source.addListener(SWT.MouseDown, this);
		source.addListener(SWT.MouseUp, this);
		source.addListener(SWT.MouseMove, this);
	}


	public void handleEvent(Event event) {
		switch (event.type) {
		case SWT.MouseDoubleClick:
			fireEditConfig(vctView);
			posOrig = null;
			break;
		case SWT.MouseDown:
			if (event.widget != null && event.widget instanceof CLabel) {
				ConnectionView connection = new ConnectionView(event.widget == srcPin ? "CONTAINS" : "REFERENCES");
				connection.setSource(this, (CLabel)event.widget);
				connection.setDragPosition(((CLabel)event.widget).toDisplay(event.x, event.y));
				vctView.dragConnectionBegin(connection);										

			} else {
				posOrig = getLocation();
				posDragged = toDisplay(event.x, event.y);
			}
			break;
		case SWT.MouseMove:
			if (event.widget != null && event.widget instanceof CLabel) {
				vctView.dragConnectionUpdate(((CLabel)event.widget).toDisplay(event.x, event.y));
			} else if (posOrig != null) {
				Point posNew = toDisplay(event.x, event.y);
				int x = posOrig.x + posNew.x - posDragged.x;
				int y = posOrig.y + posNew.y - posDragged.y;
				
				if (x < 0) x = 0;
				if (y < 0) y = 0;
				
				setLocation(x, y);

				getParent().redraw();
			}
			break;
		case SWT.MouseUp:
			if (event.widget != null && event.widget instanceof CLabel) {
				ResourceInstanceWidget target = vctView.dragConnectionEnd(((CLabel)event.widget).toDisplay(event.x, event.y), this,(CLabel)event.widget);
				if (target != null) {					
					ConnectionEvent connectionEvent = new ConnectionEvent();
					connectionEvent.sourceLabel = (CLabel)event.widget;
					connectionEvent.source = this;
					connectionEvent.target = target;
					connectionEvent.parent = vctView;
					fireConnectionNewEvent(connectionEvent);
				}
			} else if (posOrig != null) {
				posOrig = null;
				fireMoved(getLocation());
			}
			break;
		}		
	}

	public void addConnectionListener(ConnectionListener listener) {
		connectionListeners.add(listener);
	}
	
	public void removeConnectionListener(ConnectionListener listener) {
		connectionListeners.remove(listener);
	}
	
	public void addResourceInstanceListener(ResourceInstanceListener listener) {
		instanceListeners.add(listener);
	}
	
	public void removeResourceInstanceListener(ResourceInstanceListener listener) {
		instanceListeners.remove(listener);
	}
	
	private void fireConnectionNewEvent(ConnectionEvent event) {
		for (Iterator<ConnectionListener> it = connectionListeners.iterator(); it.hasNext(); ) {
			ConnectionListener listener = it.next();
			try {
				listener.onConnectionNew(event);
			} catch (RuntimeException e) {
				// log output
				connectionListeners.remove(listener);
			}
		}
	}
	
	private void fireConnectionDeletedEvent(ConnectionEvent event) {
		for (Iterator<ConnectionListener> it = connectionListeners.iterator(); it.hasNext(); ) {
			ConnectionListener listener = it.next();
			try {
				listener.onConnectionDeleted(event);
			} catch (RuntimeException e) {
				// log output
				connectionListeners.remove(listener);
			}
		}
	}
	
	private void fireMoved(Point position) {
		for (Iterator<ResourceInstanceListener> it = instanceListeners.iterator(); it.hasNext(); ) {
			ResourceInstanceListener listener = it.next();
			try {
				listener.onMoved(position);
			} catch (RuntimeException e) {
				// log output
				instanceListeners.remove(listener);
			}
		}		
	}
	
	private void fireEditConfig(VctView vctView) {
		for (Iterator<ResourceInstanceListener> it = instanceListeners.iterator(); it.hasNext(); ) {
			ResourceInstanceListener listener = it.next();
			try {
				listener.onEditConfig(vctView);
			} catch (RuntimeException e) {
				// log output
				instanceListeners.remove(listener);
			}
		}		
	}

	private void fireHelp(VctView vctView) {
		for (Iterator<ResourceInstanceListener> it = instanceListeners.iterator(); it.hasNext(); ) {
			ResourceInstanceListener listener = it.next();
			try {
				listener.onHelp(vctView);
			} catch (RuntimeException e) {
				// log output
				instanceListeners.remove(listener);
			}
		}		
	}
	
	private void fireDelete() {
		for (Iterator<ResourceInstanceListener> it = instanceListeners.iterator(); it.hasNext(); ) {
			ResourceInstanceListener listener = it.next();
			try {
				listener.onDelete(this);
			} catch (RuntimeException e) {
				// log output
				instanceListeners.remove(listener);
			}
		}		
	}
	
	private void fireConfig (VctView vctView) {
		// TODO
		System.out.println("configure");
		for (Iterator<ResourceInstanceListener> it = instanceListeners.iterator(); it.hasNext(); ) {
			ResourceInstanceListener listener = it.next();
			try {
				listener.onEditConfig(vctView);
			} catch (RuntimeException e) {
				e.printStackTrace();
				//instanceListeners.remove(listener);
			}
		}
	}
	
}
