/**
 * 
 */
package org.teagle.vcttool.view;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Adapter;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.teagle.vcttool.control.VctController;

import teagle.vct.model.Vct;

/**
 * @author sim
 *
 */
public class VctToolView {

	abstract class VctToolViewSelectionAdapter extends SelectionAdapter
	{
		@Override
		public void widgetSelected(SelectionEvent event)
		{
			//tabFolderVcts.
			CTabItem tab = tabFolderVcts.getSelection();
			Vct vct = ((VctController)tab.getData()).getVct();
			
			fireEvent(vct);
		}
		
		protected abstract void fireEvent(Vct vct);
	}

	class NewVctAdapter extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent event) {
			fireNewEvent(event.widget.getData());
		}
	}
	
	class RefreshAdapter extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent event) {
			fireRefreshEvent(event.widget.getData());
		}
	}

	class SaveAdapter extends VctToolViewSelectionAdapter {
		@Override
		protected void fireEvent(Vct vct) {
			fireSaveEvent(vct);
		}
	}
	
	class SaveAsAdapter extends VctToolViewSelectionAdapter 
	{
		@Override
		protected void fireEvent(Vct vct) 
		{
			fireSaveAsEvent(vct);
		}
	}
	
	class BookAdapter extends VctToolViewSelectionAdapter
	{
		@Override
		protected void fireEvent(Vct vct) 
		{
			fireBookingBookEvent(vct);
		}
		
	}
	
	class ExportXmlAdapter extends VctToolViewSelectionAdapter
	{
		@Override
		protected void fireEvent(Vct vct) 
		{
			fireBookingExportXmlEvent(vct);
		}
	}
	
	class BookingRefreshStateAdapter extends VctToolViewSelectionAdapter
	{
		@Override
		protected void fireEvent(Vct vct) 
		{
			fireBookingRefreshStateEvent(vct);
		}
	}
	
	class BookingShowStateAdapter extends VctToolViewSelectionAdapter
	{
		@Override
		protected void fireEvent(Vct vct) 
		{
			fireBookingShowStateEvent(vct);
		}
	}
	
	class BookingCloneAsUnprovisionedAdapter extends VctToolViewSelectionAdapter
	{
		@Override
		protected void fireEvent(Vct vct) 
		{
			fireBookingCloneUnprovisionedEvent(vct);
		}
	}
	
	class DeleteAdapter extends SelectionAdapter
	{
		
		@Override
		public void widgetSelected(SelectionEvent event)
		{
			CTabItem tab = tabFolderVcts.getSelection();
			VctView view = (VctView)tab.getControl();
			Vct vct = ((VctController)tab.getData()).getVct();
			
			fireDeleteVctEvent(vct, view, tab);
		}
		
	}

	class StartAdapter extends SelectionAdapter
	{
		
		@Override
		public void widgetSelected(SelectionEvent event)
		{
			CTabItem tab = tabFolderVcts.getSelection();
			VctView view = (VctView)tab.getControl();
			Vct vct = ((VctController)tab.getData()).getVct();
			
			fireStartVctEvent(vct, view, tab);
		}
		
	}


	class StopAdapter extends SelectionAdapter
	{
		
		@Override
		public void widgetSelected(SelectionEvent event)
		{
			CTabItem tab = tabFolderVcts.getSelection();
			VctView view = (VctView)tab.getControl();
			Vct vct = ((VctController)tab.getData()).getVct();
			
			fireStopVctEvent(vct, view, tab);
		}
		
	}

	private Shell shell;
	
	private SashForm sashForm;
	
	private CTabFolder tabFolderVcts;
	
	private CTabFolder tabFolderSelection;
	
	private Set<CommandListener> commandListeners = new CopyOnWriteArraySet<CommandListener>();
	private Set<BookingListener> bookingListeners = new CopyOnWriteArraySet<BookingListener>();
	
	private final CoolBar coolBar;
	private final ToolBar toolbarFile;
	private ToolItem buttonStart;
	private ToolItem buttonStop;

	private ToolItem buttonDelete;

	
	public VctToolView() {
		initShell();
		initMainMenu();

		coolBar = new CoolBar(shell, SWT.FLAT);
		coolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));	
		toolbarFile = new ToolBar(coolBar, SWT.FLAT);
		
		createButtons();
		initToolbar();
		initTabs();
	}

	private void initTabs() {
		sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		tabFolderSelection = new CTabFolder(sashForm, SWT.BORDER);
		
		tabFolderVcts = new CTabFolder(sashForm, SWT.BOTTOM|SWT.BORDER);

		tabFolderVcts.addCTabFolder2Listener(new CTabFolder2Adapter() {			
			@Override
			public void close(CTabFolderEvent event) {
/*				if (tabFolderVcts.getItemCount() == 1)
					event.doit = false;*/
			}
		});
		
	    sashForm.setWeights(new int[] { 32, 68 });
	}

	private void initToolbar() {
		Point size = toolbarFile.getSize();
		
		CoolItem coolItemFile = new CoolItem(coolBar, SWT.NONE);
		coolItemFile.setControl(toolbarFile);

		Point preferred = coolItemFile.computeSize(size.x, size.y);
		coolItemFile.setPreferredSize(preferred);
		
		ToolBar toolbarRest = new ToolBar(coolBar, SWT.FLAT);
		
//		String[] icons = { "export", "showtsk_tsk", null, "refresh", "showchild_mode" };

//		for (String icon : icons) {
//			if (icon != null) {
//				ToolItem item = new ToolItem(toolbarRest, SWT.PUSH);
//				InputStream in = getClass().getResourceAsStream("/icons/" + icon + ".gif");
//				item.setImage(new Image(shell.getDisplay(), in));
//				item.setData(icon);
//				
//				item.setToolTipText(icon);
////				item.addSelectionListener(this);
//			} else {
//				new ToolItem(toolbarRest, SWT.SEPARATOR);
//			}
//		}

		toolbarRest.pack();
		size = toolbarRest.getSize();
		
		CoolItem coolItemRest = new CoolItem(coolBar, SWT.NONE);
		coolItemRest.setControl(toolbarRest);
		
		preferred = coolItemRest.computeSize(size.x, size.y);
		coolItemRest.setPreferredSize(preferred);

		coolBar.pack();
	}

	private void initMainMenu() {
		Menu mainMenu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(mainMenu);

		MenuItem menuItemFile = new MenuItem(mainMenu, SWT.CASCADE);
		menuItemFile.setText("&File");
		
		Menu menuFile = createFileMenu();
		menuItemFile.setMenu(menuFile);

		
		MenuItem menuItemTools = new MenuItem(mainMenu, SWT.CASCADE);
		menuItemTools.setText("&Tools");
		
		Menu menuTools = createToolsMenu();
		menuItemTools.setMenu(menuTools);

		
		MenuItem menuItemBooking = new MenuItem(mainMenu, SWT.CASCADE);
		menuItemBooking.setText("&Booking");
		
		Menu menuBooking = createBookingMenu();
		menuItemBooking.setMenu(menuBooking);
		
		// No content yet
		/*
		MenuItem menuItemHelp = new MenuItem(mainMenu, SWT.CASCADE);
		menuItemHelp.setText("&Help");
		
		Menu menuHelp = createHelpMenu();
		menuItemHelp.setMenu(menuHelp);
		 */
	
	}

	private void initShell() {
		Display display = new Display();
		shell = new Shell(display);
		shell.setText("OpenTeagle Controller | VCTTool");
		shell.setSize(900, 500);
			
		GridLayout layout = new GridLayout(1, false);
		shell.setLayout(layout);

		shell.setImage(new Image(shell.getDisplay(), getClass().getResourceAsStream("/icons/openteagle.png")));
		
		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent event) {
				fireExitEvent(event.data);
			}
		});
	}

	private void createButtons() {
		createSaveBookButtons();
		createLifecycleButtons();
		toolbarFile.pack();
	}

	private void createSaveBookButtons() {		
		createButton("/icons/new_wiz.gif", "Create new VCT", new NewVctAdapter());
		createButton("/icons/refresh.gif", "Refresh", new RefreshAdapter());
		
		new ToolItem(toolbarFile, SWT.SEPARATOR);
		createButton("/icons/save_edit.gif", "Save this VCT", new SaveAdapter()); 
		createButton("/icons/saveas_edit.gif", "Save this VCT as...", new SaveAsAdapter());		
		createButton("/icons/book.png", "Book this VCT", new BookAdapter());
	}

	private void createLifecycleButtons() {
		new ToolItem(toolbarFile, SWT.SEPARATOR);
		buttonDelete = createButton("/icons/close.png", "Delete this VCT", new DeleteAdapter());
		buttonStart = createButton("/icons/start.png", "Start this VCT", new StartAdapter());
		buttonStop = createButton("/icons/stop.png", "Stop this VCT", new StopAdapter());
		this.setLifecycleButtonsEnabled(false);
	}
	
	private ToolItem createButton(final String pathToIcon, final String tooltip,
			final SelectionAdapter selectionAdapter) {
		ToolItem button = new ToolItem(toolbarFile, SWT.PUSH);
		button.setImage(new Image(shell.getDisplay(), getClass().getResourceAsStream(pathToIcon)));
		button.setToolTipText(tooltip);
		button.addSelectionListener(selectionAdapter);
		return button;
	}

	public void showDialog(VctToolDialog d)
	{
		Shell dlg = d.getShell();
		dlg.setParent(shell);
		dlg.open();
		while (!dlg.isDisposed())
			if (!dlg.getDisplay().readAndDispatch ()) 
				dlg.getDisplay().sleep();
		
	}
	
	public void showMessage(String msg, String title, int style)
	{
		if (style < 0)
			style = SWT.ICON_INFORMATION;
		if (title == null)
			title = "";
		if (msg == null)
			msg = "";
		MessageBox box = new MessageBox(getShell(), style | SWT.PRIMARY_MODAL);
		box.setText(title);
		box.setMessage(msg);
		box.open();
	}
	
	public void showError(String msg, String title)
	{
		if (title == null)
			title = "Error";
		
		showMessage(msg, title, SWT.ICON_ERROR);
	}
	
	public void showError(String msg)
	{
		showError(msg, null);
	}
	
	public void showException(Throwable t, String title) {
		t.printStackTrace();
		showMessage(t.getClass().getSimpleName() + ": " + t.getMessage() + 
				" (java console has more details)",
				title, SWT.ICON_ERROR | SWT.OK);
	}
	
	public void showException(Throwable t)
	{
		showException(t, "An error has occured.");
	}
	
	public void run() {
		while (!shell.isDisposed())
			if (!shell.getDisplay().readAndDispatch())
				shell.getDisplay().sleep();
	}
	
	public void close() {
		shell.close();
	}
	
	public Shell getShell() {
		return shell;
	}

	private Menu createFileMenu() {
		Menu menuFile = new Menu(shell, SWT.DROP_DOWN);

		MenuItem menuItemNew = new MenuItem(menuFile, SWT.PUSH);
		menuItemNew.setText("&New Vct");
		menuItemNew.setImage(new Image(shell.getDisplay(), getClass().getResourceAsStream("/icons/new_wiz.gif")));
		menuItemNew.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				fireNewEvent(event.widget.getData());
			}
		});
		
		new MenuItem(menuFile, SWT.SEPARATOR);
	
//		
//		MenuItem menuItemOpen = new MenuItem(menuFile, SWT.PUSH);
//		menuItemOpen.setText("&Open Vct");
////		menuItemNew.setImage(new Image(shell.getDisplay(), getClass().getResourceAsStream("/icons/new_wiz.gif")));
//		menuItemOpen.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent event) {
////				fireOpenEvent(event.widget.getData());
//				fireOpenEvent();
//			}
//		});
//		
//		new MenuItem(menuFile, SWT.SEPARATOR);
		

		MenuItem menuItemSave = new MenuItem(menuFile, SWT.PUSH);
		menuItemSave.setText("&Save");
		menuItemSave.addSelectionListener(new SaveAdapter());
		
		MenuItem menuItemSaveAs = new MenuItem(menuFile, SWT.PUSH);
		menuItemSaveAs.setText("Save &As...");
		menuItemSaveAs.addSelectionListener(new SaveAsAdapter());
		
		new MenuItem(menuFile, SWT.SEPARATOR);

		MenuItem menuItemExit = new MenuItem(menuFile, SWT.PUSH);
		menuItemExit.setText("E&xit");
		menuItemExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				fireExitEvent(event.widget.getData());
				shell.close();
			}
		});
		
		return menuFile;
	}

	private Menu createToolsMenu() {
		Menu menuTools = new Menu(shell, SWT.DROP_DOWN);

		MenuItem menuItemConsole = new MenuItem(menuTools, SWT.PUSH);
		menuItemConsole.setText("&Console");
		menuItemConsole.setEnabled(false);
		
		new MenuItem(menuTools, SWT.SEPARATOR);

		MenuItem menuItemPreferences = new MenuItem(menuTools, SWT.PUSH);
		menuItemPreferences.setText("&Preferences...");
		menuItemPreferences.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				firePreferencesEvent(event.data);
			}
		});
		
		return menuTools;
	}

	private Menu createBookingMenu() {
		Menu menuBooking = new Menu(shell, SWT.DROP_DOWN);

		MenuItem menuItemBook = new MenuItem(menuBooking, SWT.PUSH);
		menuItemBook.setText("&Book");
		menuItemBook.addSelectionListener(new BookAdapter());
		
		MenuItem menuItemExport = new MenuItem(menuBooking, SWT.PUSH);
		menuItemExport.setText("&Export to XML");
		menuItemExport.addSelectionListener(new ExportXmlAdapter());
		
		new MenuItem(menuBooking, SWT.SEPARATOR);

		MenuItem menuItemRefresh = new MenuItem(menuBooking, SWT.PUSH);
		menuItemRefresh.setText("&Refresh booking state");
		menuItemRefresh.addSelectionListener(new BookingRefreshStateAdapter());
		
		MenuItem menuItemShowState = new MenuItem(menuBooking, SWT.PUSH);
		menuItemShowState.setText("&Show booking state");
		menuItemShowState.addSelectionListener(new BookingShowStateAdapter());

		new MenuItem(menuBooking, SWT.SEPARATOR);

		MenuItem menuItemClone = new MenuItem(menuBooking, SWT.PUSH);
		menuItemClone.setText("&Clone all resources as unprovisioned");
		menuItemClone.addSelectionListener(new BookingCloneAsUnprovisionedAdapter());

		return menuBooking;
	}
/*
	private Menu createHelpMenu() {
		Menu menuHelp = new Menu(shell, SWT.DROP_DOWN);

		MenuItem menuItemContents = new MenuItem(menuHelp, SWT.PUSH);
		menuItemContents.setText("&Contents");
		//menuItemContents.addSelectionListener(this);
		menuItemContents.setEnabled(false);
		
		MenuItem menuItemAbout = new MenuItem(menuHelp, SWT.PUSH);
		menuItemAbout.setText("&About...");
		//menuItemAbout.addSelectionListener(this);
		menuItemAbout.setEnabled(false);
		
		return menuHelp;
	}
*/
	public void addCommandListener(CommandListener listener) {
		commandListeners.add(listener);
	}
	
	public void removeCommandListener(CommandListener listener) {
		commandListeners.remove(listener);
	}
	
	public void addBookingListener(BookingListener listener) {
		bookingListeners.add(listener);
	}
	
	public void removeBookingListener(BookingListener listener) {
		bookingListeners.remove(listener);
	}
	
	public CTabFolder getVctPane() {
		return tabFolderVcts;
	}
	
	
	
	public void addVctView(VctView vctView, VctController data, String name) {
		CTabItem tabItem = new CTabItem(tabFolderVcts, SWT.CLOSE);
	    tabItem.setText(name);
	    tabItem.setData(data);
	    
	    tabItem.setControl(vctView);
	    
	    tabFolderVcts.setSelection(tabItem);
	}

	public CTabFolder getSelectionPane() {
		return tabFolderSelection;
	}
	
	public void addSelectionControl(String name, Control control, int i) {

		CTabItem tabItem = i != -1 ? new CTabItem(tabFolderSelection, SWT.NONE, i) : new CTabItem(tabFolderSelection, SWT.NONE);
	    tabItem.setText(name);
		
	    tabItem.setControl(control);

	    tabFolderSelection.setSelection(i != -1 ? i : tabFolderSelection.indexOf(tabItem));
	}
	
	private void fireExitEvent(Object data) {
		for (Iterator<CommandListener> it = commandListeners.iterator(); it.hasNext(); ) {
			CommandListener listener = it.next();
			try {
				listener.onExit();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}

	private void firePreferencesEvent(Object data) {
		for (Iterator<CommandListener> it = commandListeners.iterator(); it.hasNext(); ) {
			CommandListener listener = it.next();
			try {
				listener.onPreferences();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}

	private void fireNewEvent(Object data) {
		for (Iterator<CommandListener> it = commandListeners.iterator(); it.hasNext(); ) {
			CommandListener listener = it.next();
			try {
				listener.onNew();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void fireRefreshEvent(Object data) {
		for (Iterator<CommandListener> it = commandListeners.iterator(); it.hasNext(); ) {
			CommandListener listener = it.next();
			try {
				listener.onRefresh();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}

	/*	
	private void fireOpenEvent() {
		for (Iterator<CommandListener> it = commandListeners.iterator(); it.hasNext(); ) {
			CommandListener listener = it.next();
			try {
				listener.onOpen();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}
	*/
	
	private void fireSaveEvent(Vct data) {
		for (Iterator<CommandListener> it = commandListeners.iterator(); it.hasNext(); ) {
			CommandListener listener = it.next();
			try {
				listener.onSave(this, data);
			} catch (RuntimeException e) {
				// log output
				e.printStackTrace();
			}
		}
	}

	private void fireSaveAsEvent(Vct data) {
		for (Iterator<CommandListener> it = commandListeners.iterator(); it.hasNext(); ) {
			CommandListener listener = it.next();
			try {
				listener.onSaveAs(this, data);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}

	private void fireBookingExportXmlEvent(Vct data) {
		for (Iterator<BookingListener> it = bookingListeners.iterator(); it.hasNext(); ) {
			BookingListener listener = it.next();
			try {
				listener.onExportXml(data);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}

	private void fireBookingRefreshStateEvent(Vct data) {
		for (Iterator<BookingListener> it = bookingListeners.iterator(); it.hasNext(); ) {
			BookingListener listener = it.next();
			try {
				listener.onRefreshState(data);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}

	private void fireBookingShowStateEvent(Vct data) {
		for (Iterator<BookingListener> it = bookingListeners.iterator(); it.hasNext(); ) {
			BookingListener listener = it.next();
			try {
				listener.onShowState(data);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}

	private void fireBookingCloneUnprovisionedEvent(Vct data) {
		for (Iterator<BookingListener> it = bookingListeners.iterator(); it.hasNext(); ) {
			BookingListener listener = it.next();
			try {
				listener.onCloneUnprovisioned(data);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void fireDeleteVctEvent(Vct data, VctView view, CTabItem tab) {
		for (Iterator<CommandListener> it = commandListeners.iterator(); it.hasNext(); ) {
			CommandListener listener = it.next();
			try {
				listener.onDelete(this, data, view, tab);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}

	private void fireStartVctEvent(Vct data, VctView view, CTabItem tab) {
		for (Iterator<BookingListener> it = bookingListeners.iterator(); it.hasNext(); ) {
			BookingListener listener = it.next();
			try {
				listener.onStart(this, data);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}

	private void fireStopVctEvent(Vct data, VctView view, CTabItem tab) {
		for (Iterator<BookingListener> it = bookingListeners.iterator(); it.hasNext(); ) {
			BookingListener listener = it.next();
			try {
				listener.onStop(this, data);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}

	private void fireBookingBookEvent(Vct data) {
		for (Iterator<BookingListener> it = bookingListeners.iterator(); it.hasNext(); ) {
			BookingListener listener = it.next();
			try {
				listener.onBook(this, data);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}

	public void setLifecycleButtonsEnabled(boolean bool) {
		this.buttonStart.setEnabled(bool);
		this.buttonStop.setEnabled(bool);
		this.buttonDelete.setEnabled(bool);
	}


}
