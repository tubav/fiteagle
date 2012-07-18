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
import org.teagle.vcttool.app.ValidateActions;

import teagle.vct.model.ConfigParamAtomic;
import teagle.vct.util.Util;
import de.fhg.fokus.ngni.openpe.pem1.EvaluationHandler;

/**
 * @author sim
 *
 */
public class VctView extends Composite implements PaintListener, MouseListener, SelectionListener {

	private Set<VctListener> vctListeners = new CopyOnWriteArraySet<VctListener>();

	private List<ConnectionView> connections = new ArrayList<ConnectionView>();
	
	private ConnectionView dragConnection;

	private Composite content;
	private ScrolledComposite scrolled;
	
	private Menu connectionMenu;
	private MenuItem connectionDeleteMenuItem;
	
	public VctView(Composite parent) {
		super(parent, SWT.NONE);
		
		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;		
		setLayout(layout);

		scrolled = new ScrolledComposite(this, SWT.H_SCROLL | SWT.V_SCROLL);
		scrolled.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		scrolled.setExpandHorizontal(true);
		scrolled.setExpandVertical(true);

		content = new Composite(scrolled, SWT.BORDER|SWT.DOUBLE_BUFFERED);
		content.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
		content.setBackgroundMode(SWT.INHERIT_FORCE);

		content.addMouseListener(this);
		
		DropTarget dropTarget = new DropTarget(content, DND.DROP_COPY|DND.DROP_MOVE);
		dropTarget.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		dropTarget.addDropListener(new DropTargetAdapter() {
			public void drop(DropTargetEvent event) {
				fireResourceSpecDropped((String)event.data, content.toControl(event.x, event.y));
			}
		});
		
		scrolled.setContent(content);
		
		content.addPaintListener(this);

		connectionMenu = new Menu(getShell(), SWT.POP_UP);

		connectionDeleteMenuItem = new MenuItem(connectionMenu, SWT.PUSH);
		connectionDeleteMenuItem.setText ("Delete");
		connectionDeleteMenuItem.addSelectionListener(this);
	}

	public Composite getContentView() {
		return content;
	}

	public void removeResourceInstanceWidget(ResourceInstanceWidget widget) {
		for (Control control : content.getChildren()) {
			if (control instanceof ResourceInstanceWidget && control == widget) {
				for (Iterator<ConnectionView> it = connections.iterator(); it.hasNext(); ) {
					ConnectionView connection = it.next();
					if (connection.getSourceWidget() == widget || connection.getTargetWidget() == widget) {
						it.remove();
					}
				}
				widget.dispose();
				updateContent();
			}		
		}		
	}
	
	public void addConnectionView(ConnectionView view) {
		connections.add(view);
	}
	
	public boolean removeConnectionView(ConnectionView view) {
		return connections.remove(view);
	}
	
	public void dragConnectionBegin(ConnectionView connection) {
		dragConnection = connection;
	}
	
	public void dragConnectionUpdate(Point update) {
		if (dragConnection != null) {
			dragConnection.setDragPosition(content.toControl(update));
			updateContent();
		}
	}
	
	public ResourceInstanceWidget dragConnectionEnd(Point end, ResourceInstanceWidget src,CLabel cLabel) {
		ResourceInstanceWidget found = null;
		ResourceInstanceWidget source = null;
		ResourceInstanceWidget destination = null;
		if (dragConnection != null) {
			for (Control control : content.getChildren()) {
				if (control instanceof ResourceInstanceWidget) {
					ResourceInstanceWidget widget = (ResourceInstanceWidget)control;
					CLabel target = widget.findTargetLabel(end);
					if (target != null) {	
						source = src;
						destination = widget;
						String connectionType = "contains";
						if(cLabel.getData() != null){
							connectionType = ((ConfigParamAtomic)cLabel.getData()).getType();
							if(connectionType == null)
								System.out.println("Error: Error when attemptig to get connection type");
						}
						EvaluationHandler handler = new EvaluationHandler();						
						if(! ValidateActions.validateConnection(source.getResourceInstance(), destination.getResourceInstance(), connectionType, handler))
						{
							Util.showMsg(this.getShell(), SWT.ERROR,
									"Connection Failed", handler.getMessage());
							System.out.println("connection failed");
							break;
						}
						dragConnection.setTarget(widget, target);
						connections.add(dragConnection);
						found = widget;
						break;
					}
					if(source == null)
						source = widget;
				}
			}
			dragConnection = null;
			updateContent();
		}
		return found;
	}

	public void addVctListener(VctListener listener) {
		vctListeners.add(listener);
	}
	
	public void removeVctListener(VctListener listener) {
		vctListeners.remove(listener);
	}
	
	private void fireResourceSpecDropped(String specName, Point position) {
		for (Iterator<VctListener> it = vctListeners.iterator(); it.hasNext(); ) {
			VctListener listener = it.next();
			try {
				if (specName.startsWith("instance:"))
					listener.onResourceInstanceDropped(specName.substring(9), position);
				else
					listener.onResourceSpecDropped(specName, position);
			} catch (RuntimeException e) {
				e.printStackTrace();
				// log output
				//vctListeners.remove(listener);
			}
		}				
	}

	public void updateContent() {
		content.redraw();
		scrolled.setMinSize(content.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	public void paintControl(PaintEvent event) {
		
		if (!connections.isEmpty() || dragConnection != null) {
			event.gc.setLineWidth(2);
			event.gc.setAntialias(SWT.ON);
		}

		if (dragConnection != null) {
			dragConnection.draw(event.gc);
		}
		
		for (ConnectionView connection : connections) {
			connection.draw(event.gc);
		}
	}


	public void mouseDoubleClick(MouseEvent arg0) {
	}


	public void mouseDown(MouseEvent event) {
		for (ConnectionView connection : connections) {
			if (connection.selected(new Point(event.x, event.y))) {
				connectionMenu.setLocation(content.toDisplay(event.x, event.y));
				connectionMenu.setVisible(true);
				connectionMenu.setData(connection);
			}
		}
	}

	public void mouseUp(MouseEvent arg0) {
	}

	public void widgetDefaultSelected(SelectionEvent arg0) {
	}

	public void widgetSelected(SelectionEvent event) {
		if (event.widget == connectionDeleteMenuItem) {
			ConnectionView connection = (ConnectionView)connectionMenu.getData();
			connections.remove(connection);
			
			connection.getSourceWidget().deleteConnection(connection);
		}
		updateContent();
	}

}
