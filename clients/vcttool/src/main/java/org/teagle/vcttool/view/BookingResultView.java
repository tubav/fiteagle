package org.teagle.vcttool.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;

import teagle.vct.model.Vct;

public class BookingResultView extends Shell {
	public BookingResultView(Shell parent, Vct vct)
	{
		super(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);
		
		setText("Booking summary");
		setLayout(new GridLayout(5, false));

		final Browser browser = new Browser(this, SWT.BORDER);
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				5, 1));


	}
	
}