/**
 * 
 */
package org.teagle.vcttool.view.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * @author sim
 * 
 */
public class Dialog {
	private final Shell dialog;

	protected int result = SWT.OK;

	protected Dialog(final Shell shell, final String name) {
		this.dialog = new Shell(shell, SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
		if (name != null)
			this.dialog.setText(name);

		final GridLayout layout = new GridLayout(2, false);
		this.dialog.setLayout(layout);
		this.dialog.setMinimumSize(300, -1);
	}

	public int show() {
		this.dialog.pack();
		this.dialog.open();
		while (!this.dialog.isDisposed())
			if (!this.dialog.getDisplay().readAndDispatch())
				this.dialog.getDisplay().sleep();
		return this.result;
	}

	protected void hide(final int result) {
		this.result = result;
		this.dialog.close();
	}

	protected Composite getContainer() {
		return this.dialog;
	}

	public int getResult() {
		return this.result;
	}
}
