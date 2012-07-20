package org.teagle.vcttool.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class ProgressDialogView {
	private final Shell dlg;
	private final Label label;

	public ProgressDialogView(final Shell parent, final String text) {
		this.dlg = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL
				| SWT.RESIZE);
		this.dlg.setLayout(new GridLayout());
		this.dlg.setText("Operation in progress");

		this.label = new Label(this.dlg, SWT.NONE);
		this.label.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		this.label.setText(text);

		final ProgressBar bar = new ProgressBar(this.dlg, SWT.INDETERMINATE);
		bar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		this.dlg.pack();
		this.dlg.open(); // a setVisible(false) is also probably needed here

		final Point p = this.dlg.getSize();
		if (p.x > 400)
			this.dlg.setSize(400, p.y);
	}

	public void close() {
		this.dlg.close();
	}

	public void setVisible(final boolean b) {
		this.dlg.setVisible(b);

	}

	public boolean isDisposed() {
		return this.dlg.isDisposed();
	}
}
