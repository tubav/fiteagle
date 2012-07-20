package org.teagle.vcttool.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class VctToolDialog {
	private final Shell shell;
	protected int result = SWT.OK;

	public VctToolDialog() {
		this.shell = new Shell(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL
				| SWT.RESIZE);
	}

	public VctToolDialog(final Shell parent) {
		this.shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL
				| SWT.RESIZE);
	}

	public Shell getShell() {
		return this.shell;
	}

	public int show() {
		this.shell.pack();
		this.shell.open();
		while (!this.shell.isDisposed())
			if (!this.shell.getDisplay().readAndDispatch())
				this.shell.getDisplay().sleep();
		return this.result;
	}

	protected void hide(final int result) {
		this.result = result;
		this.shell.close();
	}
}
