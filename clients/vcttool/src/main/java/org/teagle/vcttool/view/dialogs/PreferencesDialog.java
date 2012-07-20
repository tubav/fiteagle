/**
 * 
 */
package org.teagle.vcttool.view.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author sim
 * 
 */
public class PreferencesDialog extends Dialog implements SelectionListener {

	private final Group credentials;
	private final Group repository;
	private final Group policyengine;

	private final Button buttonOk;
	private final Button buttonCancel;

	private final Text textUsername;
	private final Text textPassword;
	private final Text textUrl;
	private final Text textReqprocUrl;
	private final Text textpe_endpoint;

	private final Button buttonStorePassword;

	private String username;
	private String password;

	private boolean storePassword = false;

	private String repositoryUrl;
	private String reqprocUrl;

	private String pe_endpoint;

	public PreferencesDialog(final Shell shell) {
		super(shell, "VctTool Preferences");

		final Composite container = this.getContainer();
		this.credentials = new Group(container, SWT.NONE);
		final GridLayout credLayout = new GridLayout(2, false);
		this.credentials.setLayout(credLayout);
		this.credentials.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 2, 1));
		this.credentials.setText("Credentials");

		this.repository = new Group(container, SWT.NONE);
		final GridLayout repoLayout = new GridLayout(2, false);
		this.repository.setLayout(repoLayout);
		this.repository.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 2, 1));
		this.repository.setText("Repository");

		this.policyengine = new Group(container, SWT.NONE);
		final GridLayout peLayout = new GridLayout(2, false);
		this.policyengine.setLayout(peLayout);
		this.policyengine.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 2, 1));
		this.policyengine.setText("Policy Engine");

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

		final Label labelUsername = new Label(this.credentials, SWT.NONE);
		labelUsername.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false));
		labelUsername.setText("Username");

		this.textUsername = new Text(this.credentials, SWT.BORDER);
		this.textUsername.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false));

		final Label labelPassword = new Label(this.credentials, SWT.NONE);
		labelPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false));
		labelPassword.setText("Password");

		this.textPassword = new Text(this.credentials, SWT.BORDER
				| SWT.PASSWORD);
		this.textPassword.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false));

		this.buttonStorePassword = new Button(this.credentials, SWT.CHECK
				| SWT.RIGHT_TO_LEFT);
		this.buttonStorePassword.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, false, 2, 1));
		this.buttonStorePassword.setText("Store password");

		final Label labelUrl = new Label(this.repository, SWT.NONE);
		labelUrl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		labelUrl.setText("Repository Url");

		this.textUrl = new Text(this.repository, SWT.BORDER);
		this.textUrl
				.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		final Label labelReqprocUrl = new Label(this.repository, SWT.NONE);
		labelReqprocUrl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false));
		labelReqprocUrl.setText("Request Processor Url");

		this.textReqprocUrl = new Text(this.repository, SWT.BORDER);
		this.textReqprocUrl.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, false));

		final Label labelPeEndpoint = new Label(this.policyengine, SWT.NONE);
		labelPeEndpoint.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false));
		labelPeEndpoint.setText("Endpoint");
		this.textpe_endpoint = new Text(this.policyengine, SWT.BORDER);
		this.textpe_endpoint.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, false));

	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(final String username) {
		this.username = username;
		this.textUsername.setText(username);
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(final String password) {
		if (password != null) {
			this.password = password;
			this.textPassword.setText(password);
		}
	}

	/**
	 * @return the storePassword
	 */
	public boolean isStorePassword() {
		return this.storePassword;
	}

	/**
	 * @param storePassword
	 *            the storePassword to set
	 */
	public void setStorePassword(final boolean storePassword) {
		this.storePassword = storePassword;
		this.buttonStorePassword.setSelection(storePassword);
	}

	/**
	 * @return the repositoryUrl
	 */
	public String getRepositoryUrl() {
		return this.repositoryUrl;
	}

	public String getReqprocUrl() {
		return this.reqprocUrl;
	}

	/**
	 * @param repositoryUrl
	 *            the repositoryUrl to set
	 */
	public void setRepositoryUrl(final String repositoryUrl) {
		this.repositoryUrl = repositoryUrl;
		this.textUrl.setText(repositoryUrl);
	}

	public void setReqprocUrl(final String reqprocUrl) {
		this.reqprocUrl = reqprocUrl;
		this.textReqprocUrl.setText(reqprocUrl);
	}

	/**
	 * @param pe
	 *            endpoint to set
	 */
	public void setPeEndpoint(final String endpoint) {
		this.pe_endpoint = endpoint;
		this.textpe_endpoint.setText(this.pe_endpoint);
	}

	public String getPeEndpoint() {
		return this.pe_endpoint;
	}

	public void widgetDefaultSelected(final SelectionEvent arg0) {
	}

	public void widgetSelected(final SelectionEvent event) {
		if (event.widget == this.buttonOk) {
			this.username = this.textUsername.getText();
			this.password = this.textPassword.getText();
			this.repositoryUrl = this.textUrl.getText();
			this.reqprocUrl = this.textReqprocUrl.getText();
			this.storePassword = this.buttonStorePassword.getSelection();
			this.pe_endpoint = this.textpe_endpoint.getText();
			this.hide(SWT.OK);
		} else
			this.hide(SWT.CANCEL);
	}

}
