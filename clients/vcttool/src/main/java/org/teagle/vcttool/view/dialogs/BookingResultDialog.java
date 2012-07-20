/**
 * 
 */
package org.teagle.vcttool.view.dialogs;

import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

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

import teagle.vct.util.OrchestrateReturn;
import teagle.vct.util.OrchestrateReturn.LogBook;

//import teagle.util.OrchestrateReturn.LogEntry;

/**
 * @author sim
 * 
 */
public class BookingResultDialog extends Dialog implements SelectionListener {

	private final Map<String, String> configParams = new HashMap<String, String>();

	private final Group params;

	private final Button buttonOk;

	String combine(final String[] s, final String glue) {
		final int k = s.length;
		if (k == 0)
			return "";
		final StringBuilder out = new StringBuilder();
		out.append(s[0]);
		for (int x = 1; x < k; ++x)
			out.append(glue).append(s[x]);
		return out.toString();
	}

	public BookingResultDialog(final Shell shell, final int code,
			final String mesg, final String url,
			final OrchestrateReturn.LogBook log) {
		super(shell, "Booking finished");

		final Composite container = this.getContainer();

		this.params = new Group(container, SWT.NONE);

		final GridLayout layout = new GridLayout(2, false);
		this.params.setLayout(layout);
		final GridData data = new GridData(SWT.FILL, SWT.FILL, true, false, 2,
				1);
		// data.verticalSpan = 20;
		this.params.setLayoutData(data);

		this.buttonOk = new Button(container, SWT.PUSH);
		this.buttonOk.setText("OK");
		this.buttonOk.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false,
				false));
		this.buttonOk.addSelectionListener(this);

		final String[] lines = mesg.split("\n");
		int ms = 0;
		if ((lines != null) && (lines.length > 10))
			ms = 150;
		final int ls = 250;
		final String logstr = this.dropLog(log, "", "");

		this.addConfigurationParameter("result", Integer.toString(code),
				"the result code");
		this.addTextArea("message", mesg, "result description", ms);
		this.addConfigurationParameter("log url", url,
				"location of the OE log. Look here for more information.");
		this.addTextArea("log", logstr, "The operation's log.", ls);

		this.params.pack();
		container.pack();
	}

	private String dropLog(final LogBook book, String log, final String indent) {
		for (int i = 0; i < book.entries.length; ++i) {
			final Object entry = book.entries[i];
			if (entry instanceof LogBook) {
				final LogBook inner = (LogBook) entry;
				log += "\nBEGIN INNER LOG: name=" + inner.name + " component="
						+ inner.component + "\n";
				log += this.dropLog(inner, log, indent + "    ");
				log += "\nEND INNER LOG: name=" + inner.name + " component="
						+ inner.component + "\n";
			} else {
				log += indent + entry;
				if (i < (book.entries.length - 1))
					log += "\n";
			}
		}
		return log;
	}

	public void addTextArea(final String name, final String value,
			final String description, final int size) {
		final Label label = new Label(this.params, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		label.setText(name);
		final Text text = new Text(this.params, SWT.BORDER | SWT.READ_ONLY
				| SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);

		final GridData data = new GridData(SWT.FILL, SWT.TOP, true, false);
		if (size > 0)
			data.heightHint = size;
		// data.verticalSpan = 20;
		data.widthHint = Toolkit.getDefaultToolkit().getScreenSize().width / 2;

		text.setLayoutData(data);
		text.setText(value);

		text.setData(name);

		if ((description != null) && !description.isEmpty()) {
			label.setToolTipText(description);
			text.setToolTipText(description);
		}
	}

	public void addConfigurationParameter(final String name,
			final String defaultValue, final String description) {
		final Label label = new Label(this.params, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		label.setText(name);
		final Text text = new Text(this.params, SWT.BORDER | SWT.READ_ONLY);

		text.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		if ((defaultValue != null) && !defaultValue.isEmpty())
			text.setText(defaultValue);

		text.setData(name);

		if ((description != null) && !description.isEmpty()) {
			label.setToolTipText(description);
			text.setToolTipText(description);
		}
	}

	public void addConfigurationParameter(final String name,
			final boolean defaultValue, final String description) {
		final Button checkbox = new Button(this.params, SWT.CHECK
				| SWT.RIGHT_TO_LEFT);
		checkbox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2,
				1));
		checkbox.setText(name);
		checkbox.setSelection(defaultValue);

		checkbox.setData(name);

		if ((description != null) && !description.isEmpty())
			checkbox.setToolTipText(description);
	}

	public String getConfiguratonParameter(final String name) {
		return this.configParams.get(name);
	}

	public void widgetDefaultSelected(final SelectionEvent arg0) {
	}

	public void widgetSelected(final SelectionEvent event) {
		if (event.widget == this.buttonOk) {
			for (final Control control : this.params.getChildren()) {
				final Object data = control.getData();
				if (data != null)
					if (control instanceof Text)
						this.configParams.put(data.toString(),
								((Text) control).getText());
					else if (control instanceof Button)
						this.configParams.put(data.toString(), String
								.valueOf(((Button) control).getSelection()));
			}
			this.hide(SWT.OK);
		} else
			this.hide(SWT.CANCEL);
	}
}
