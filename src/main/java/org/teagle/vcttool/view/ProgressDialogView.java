package org.teagle.vcttool.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class ProgressDialogView {
	private Shell dlg;
	private Label label;
	
	public ProgressDialogView(Shell parent, String text) {	
		dlg = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);
		dlg.setLayout(new GridLayout());
		dlg.setText("Operation in progress");
		
		label = new Label(dlg, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		label.setText(text);
		
		ProgressBar bar = new ProgressBar(dlg, SWT.INDETERMINATE);
		bar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		dlg.pack();
		dlg.open(); // a setVisible(false) is also probably needed here 
		
		Point p = dlg.getSize();
		if (p.x > 400) 
			dlg.setSize(400, p.y);
	}
	
	public void close() {
		dlg.close();
	}

	public void setVisible(boolean b) {
		dlg.setVisible(b);
		
	}

	public boolean isDisposed() {
		return dlg.isDisposed();
	}
}
