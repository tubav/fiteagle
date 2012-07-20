package org.teagle.vcttool.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class VctToolDialog {
	private Shell shell;
	protected int result = SWT.OK;
	
	public VctToolDialog()
	{
		shell = new Shell(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);
	}
	
	public VctToolDialog(Shell parent)
	{
		shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);
	}
	
	public Shell getShell()
	{
		return shell;
	}
	
	public int show() {
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch()) {
				shell.getDisplay().sleep();				
			}
		}
		return result;
	}
	
	protected void hide(int result) {
		this.result = result;
		shell.close();
	}
}
