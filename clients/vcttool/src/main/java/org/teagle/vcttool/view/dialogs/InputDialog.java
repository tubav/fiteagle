package org.teagle.vcttool.view.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class InputDialog extends Dialog implements SelectionListener {

	private String name = "";

	private final Group params;

	private final Button buttonOk;
	private final Button buttonCancel;

	public InputDialog(final Shell shell, final String name) {
		super(shell, name);
		final Composite container = this.getContainer();

		this.params = new Group(container, SWT.NONE);
		final GridLayout layout = new GridLayout(2, false);
		this.params.setLayout(layout);
		this.params.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				2, 1));

		this.buttonOk = new Button(container, SWT.PUSH);
		this.buttonOk.setText("OK");
		this.buttonOk.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false,
				false));
		this.buttonOk.addSelectionListener(this);

		this.buttonCancel = new Button(container, SWT.PUSH);
		this.buttonCancel.setText("Cancel");
		this.buttonCancel.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM,
				false, false));
		this.buttonCancel.addSelectionListener(this);
	}

	public void addInputField(final String name, final String defaultValue,
			final String description) {
		final Label label = new Label(this.params, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		label.setText(name);
		final Text text = new Text(this.params, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		if ((defaultValue != null) && !defaultValue.isEmpty())
			text.setText(defaultValue);

		text.setData(name);

		if ((description != null) && !description.isEmpty()) {
			label.setToolTipText(description);
			text.setToolTipText(description);
		}
	}

	public String getName() {
		return this.name;
	}

	public void widgetDefaultSelected(final SelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void widgetSelected(final SelectionEvent event) {
		if (event.widget == this.buttonOk) {
			for (final Control control : this.params.getChildren()) {
				final Object data = control.getData();
				if (data != null)
					if (control instanceof Text)
						this.name = ((Text) control).getText();
					else if (control instanceof Button)
						// configParams.put(data.toString(),
						// String.valueOf(((Button)control).getSelection()));
						System.out.println("moep");
			}
			this.hide(SWT.OK);
		} else
			this.hide(SWT.CANCEL);

	}

}
