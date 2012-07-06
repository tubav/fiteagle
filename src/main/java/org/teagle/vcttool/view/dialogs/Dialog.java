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
	private Shell dialog;

	protected int result = SWT.OK;
	
	protected Dialog(Shell shell, String name) {
		dialog = new Shell(shell, SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
		if (name != null) {
			dialog.setText(name);			
		}

		GridLayout layout = new GridLayout(2, false);
		dialog.setLayout(layout);
		dialog.setMinimumSize(300, -1);
	}

	public int show() {
		dialog.pack();
		dialog.open();
		while (!dialog.isDisposed()) {
			if (!dialog.getDisplay().readAndDispatch()) {
				dialog.getDisplay().sleep();				
			}
		}
		return result;
	}

	protected void hide(int result) {
		this.result = result;
		dialog.close();
	}

	protected Composite getContainer() {
		return dialog;
	}

	public int getResult()
	{
		return result;
	}
}
