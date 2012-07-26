package org.teagle.vcttool.view;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.teagle.vcttool.control.VctController;

public interface IVctToolView {

	public abstract void showDialog(VctToolDialog d);

	public abstract void showMessage(String msg, String title, int style);

	public abstract void showError(String msg, String title);

	public abstract void showError(String msg);

	public abstract void showException(Throwable t, String title);

	public abstract void showException(Throwable t);

	public abstract void run();

	public abstract void close();

	public abstract Shell getShell();

	/*
	 * private Menu createHelpMenu() { Menu menuHelp = new Menu(shell,
	 * SWT.DROP_DOWN);
	 * 
	 * MenuItem menuItemContents = new MenuItem(menuHelp, SWT.PUSH);
	 * menuItemContents.setText("&Contents");
	 * //menuItemContents.addSelectionListener(this);
	 * menuItemContents.setEnabled(false);
	 * 
	 * MenuItem menuItemAbout = new MenuItem(menuHelp, SWT.PUSH);
	 * menuItemAbout.setText("&About...");
	 * //menuItemAbout.addSelectionListener(this);
	 * menuItemAbout.setEnabled(false);
	 * 
	 * return menuHelp; }
	 */
	public abstract void addCommandListener(CommandListener listener);

	public abstract void removeCommandListener(CommandListener listener);

	public abstract void addBookingListener(BookingListener listener);

	public abstract void removeBookingListener(BookingListener listener);

	public abstract CTabFolder getVctPane();

	public abstract void addVctView(VctView vctView, VctController data,
			String name);

	public abstract CTabFolder getSelectionPane();

	public abstract void addSelectionControl(String name, Control control, int i);

	public abstract void setLifecycleButtonsEnabled(boolean bool);

}