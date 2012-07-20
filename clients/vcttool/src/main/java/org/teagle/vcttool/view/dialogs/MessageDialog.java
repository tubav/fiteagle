package org.teagle.vcttool.view.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MessageDialog {

	private String myMessage = "";

	private int result = SWT.CANCEL;

	public MessageDialog(final Shell shell, final String message) {
		this(shell, message, SWT.ICON_INFORMATION);
	}

	public MessageDialog(final Shell shell, final String message,
			final int style) {
		this.myMessage = message;

		final MessageBox messageBox = new MessageBox(shell, style);
		messageBox.setMessage(this.myMessage);
		this.result = messageBox.open();

		// display.dispose();
	}

	public void setMessage(final String mes) {
		this.myMessage = mes;
	}

	public int getResult() {
		return this.result;
	}
}
