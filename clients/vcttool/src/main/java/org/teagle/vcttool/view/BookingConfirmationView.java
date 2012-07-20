package org.teagle.vcttool.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.teagle.vcttool.app.VctToolApp;

import teagle.vct.model.ModelManager;
import teagle.vct.model.Organisation;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.Vct;
import teagle.vct.util.Template;

public class BookingConfirmationView extends VctToolDialog implements
		SelectionListener {

	final VctToolApp.BookConfirmResult[] confirmResult = { VctToolApp.BookConfirmResult.CANCEL };
	private Button ok;

	public BookingConfirmationView(final Vct vct) {
		final Shell shell = this.getShell();

		shell.setText("Booking summary");
		shell.setLayout(new GridLayout(5, false));

		final Browser browser = new Browser(shell, SWT.BORDER);
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));

		String text;
		try {
			text = this.renderConfirmation(vct);
		} catch (final IOException e) {
			text = e.toString();
		}

		System.out.println("Setting text: " + text);
		browser.setText(text);

		final Button printBtn = new Button(shell, SWT.PUSH);
		printBtn.setText("Print");
		printBtn.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false));
		printBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("window.print();");
			}
		});

		final Button directCheckbox = new Button(shell, SWT.CHECK);
		directCheckbox.setSelection(false);
		directCheckbox.setText("Direct (FOKUS)");
		directCheckbox.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false));

		final Button asyncCheckbox = new Button(shell, SWT.CHECK);
		asyncCheckbox.setSelection(false);
		asyncCheckbox.setText("Asynchronous");
		asyncCheckbox.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false));

		final Button bookBtn = new Button(shell, SWT.PUSH);
		bookBtn.setText("Book");
		bookBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false));
		bookBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				BookingConfirmationView.this.confirmResult[0] = directCheckbox
						.getSelection() ? VctToolApp.BookConfirmResult.OK_DIRECT
						: (asyncCheckbox.getSelection() ? VctToolApp.BookConfirmResult.OK_ASYNC
								: VctToolApp.BookConfirmResult.OK_SYNC);
				shell.close();
			}
		});

		final Button cancelBtn = new Button(shell, SWT.PUSH);
		cancelBtn.setText("Cancel");
		cancelBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false));
		cancelBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				shell.close();
			}
		});

		shell.setSize(850, 700);
	}

	private String renderConfirmation(final Vct currentVct) throws IOException {
		final Template t_summary = new Template("templates/confirmation.html");
		final Template t_resource = new Template(
				"templates/confirmation-resource.html");
		final Template t_testbed = new Template(
				"templates/confirmation-testbed.html");

		// TODO refetch vct
		// ModelManager.getInstance().refetchVct(currentVct);

		final Map<String, Integer> testbedCounts = new HashMap<String, Integer>();

		final double total_price = 0;
		int n = 0;
		String res = "";

		for (final ResourceInstance instance : currentVct
				.getResourceInstances()) {

			String provider = "";
			for (final Organisation org : ModelManager.getInstance()
					.listOrganisations())
				if (org.getResourceSpecs().contains(instance.getResourceSpec())) {
					provider = org.getName();
					break;
				}
			final Integer count = testbedCounts.get(provider);
			testbedCounts.put(provider, 1 + (count == null ? 0 : count));

			res += t_resource.render("n", String.valueOf(++n), "name",
					instance.getCommonName(), "testbed", provider,
					"description", instance.getDescription(), "price", "n/a");
		}

		n = 0;
		String testbeds = "";
		for (final String testbed : testbedCounts.keySet())
			testbeds += t_testbed.render("n", String.valueOf(++n), "name",
					testbed, "count",
					String.valueOf(testbedCounts.get(testbed)), "price", "n/a");

		return t_summary.render("resources", res, "total_price",
				String.valueOf(total_price), "testbeds", testbeds);
	}

	public VctToolApp.BookConfirmResult getResult() {
		return this.confirmResult[0];
	}

	public void widgetDefaultSelected(final SelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void widgetSelected(final SelectionEvent event) {
		if (event.widget == this.ok) {
			System.out.println("ok");
			this.hide(SWT.OK);
		} else
			this.hide(SWT.CANCEL);

	}

}
