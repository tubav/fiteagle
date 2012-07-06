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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author sim
 *
 */
public class PreferencesDialog extends Dialog implements SelectionListener {
	
	private Group credentials;
	private Group repository;
	private Group policyengine;

	private Button buttonOk;
	private Button buttonCancel;
	
	private Text textUsername;
	private Text textPassword;
	private Text textUrl;
	private Text textReqprocUrl;
	private Text textpe_endpoint;

	
	private Button buttonStorePassword;
	
	private String username;
	private String password;

	private boolean storePassword = false;
	
	private String repositoryUrl;
	private String reqprocUrl;

	private String pe_endpoint;
	
	public PreferencesDialog(Shell shell) {
		super(shell, "VctTool Preferences");

		Composite container = getContainer();
		credentials = new Group(container, SWT.NONE);
		GridLayout credLayout = new GridLayout(2, false);
		credentials.setLayout(credLayout);
		credentials.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		credentials.setText("Credentials");
		
		repository = new Group(container, SWT.NONE);
		GridLayout repoLayout = new GridLayout(2, false);
		repository.setLayout(repoLayout);
		repository.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		repository.setText("Repository");

		policyengine = new Group(container, SWT.NONE);
		GridLayout peLayout = new GridLayout(2, false);
		policyengine.setLayout(peLayout);
		policyengine.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		policyengine.setText("Policy Engine");
		
		
		buttonOk = new Button(container, SWT.PUSH);
	    buttonOk.setText("OK");
	    buttonOk.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false));
	    buttonOk.addSelectionListener(this);

	    buttonCancel = new Button(container, SWT.PUSH);
	    buttonCancel.setText("Cancel");
	    buttonCancel.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false));
	    buttonCancel.addSelectionListener(this);

	    Label labelUsername = new Label(credentials, SWT.NONE);
	    labelUsername.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
	    labelUsername.setText("Username");
	    
	    textUsername = new Text(credentials, SWT.BORDER);
	    textUsername.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	    
	    Label labelPassword = new Label(credentials, SWT.NONE);
	    labelPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
	    labelPassword.setText("Password");
	    
	    textPassword = new Text(credentials, SWT.BORDER | SWT.PASSWORD);
	    textPassword.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	    
		buttonStorePassword = new Button(credentials, SWT.CHECK | SWT.RIGHT_TO_LEFT);
		buttonStorePassword.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		buttonStorePassword.setText("Store password");

		Label labelUrl = new Label(repository, SWT.NONE);
		labelUrl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		labelUrl.setText("Repository Url");
	    
	    textUrl = new Text(repository, SWT.BORDER);
	    textUrl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	    
		Label labelReqprocUrl = new Label(repository, SWT.NONE);
		labelReqprocUrl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		labelReqprocUrl.setText("Request Processor Url");
	    
	    textReqprocUrl = new Text(repository, SWT.BORDER);
	    textReqprocUrl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	    	       
	    
		Label labelPeEndpoint = new Label(policyengine, SWT.NONE);
		labelPeEndpoint.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		labelPeEndpoint.setText("Endpoint");
	    textpe_endpoint = new Text(policyengine, SWT.BORDER);
	    textpe_endpoint.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));		
	    
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
		textUsername.setText(username);
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		if (password != null) {
			this.password = password;
			textPassword.setText(password);			
		}
	}

	/**
	 * @return the storePassword
	 */
	public boolean isStorePassword() {
		return storePassword;
	}

	/**
	 * @param storePassword the storePassword to set
	 */
	public void setStorePassword(boolean storePassword) {
		this.storePassword = storePassword;
		buttonStorePassword.setSelection(storePassword);
	}

	/**
	 * @return the repositoryUrl
	 */
	public String getRepositoryUrl() {
		return repositoryUrl;
	}
	
	public String getReqprocUrl() {
		return reqprocUrl;
	}

	/**
	 * @param repositoryUrl the repositoryUrl to set
	 */
	public void setRepositoryUrl(String repositoryUrl) {
		this.repositoryUrl = repositoryUrl;
		textUrl.setText(repositoryUrl);
	}
	
	public void setReqprocUrl(String reqprocUrl) {
		this.reqprocUrl = reqprocUrl;
		textReqprocUrl.setText(reqprocUrl);
	}

	/**
	 * @param pe endpoint to set
	 */
	public void setPeEndpoint(String endpoint) {
		this.pe_endpoint = endpoint;
		textpe_endpoint.setText(pe_endpoint);
	}
	
	public String getPeEndpoint()
	{
		return this.pe_endpoint;
	}
	
	
	
	
	public void widgetDefaultSelected(SelectionEvent arg0) {
	}

	public void widgetSelected(SelectionEvent event) {
		if (event.widget == buttonOk) {
			username = textUsername.getText();
			password = textPassword.getText();
			repositoryUrl = textUrl.getText();
			reqprocUrl = textReqprocUrl.getText();
			storePassword = buttonStorePassword.getSelection();
			pe_endpoint = textpe_endpoint.getText();
			hide(SWT.OK);					
		} else {
			hide(SWT.CANCEL);			
		}
	}

}
