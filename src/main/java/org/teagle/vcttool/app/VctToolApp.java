/**
 * 
 */
package org.teagle.vcttool.app;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.eclipse.swt.SWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.teagle.vcttool.control.ProgressDialogController;
import org.teagle.vcttool.control.RootController;
import org.teagle.vcttool.control.VctToolConfig;
import org.teagle.vcttool.view.CommandAdapter;
import org.teagle.vcttool.view.VctToolView;
import org.teagle.vcttool.view.dialogs.PreferencesDialog;


/**
 * @author sim
 *
 */
public class VctToolApp {

	public enum BookConfirmResult {
		CANCEL, OK_DIRECT, OK_SYNC, OK_ASYNC,
	}
	
	private static final Logger log = LoggerFactory.getLogger(VctToolApp.class);

	private static final String DEFAULT_REPO_URL = "http://teagle.av.tu-berlin.de:8080/repository/rest";//to be changed!!!
	private static final String DEFAULT_REQPROC_URL = "http://www.fire-teagle.org/reqproc";
	//private static final String DEFAULT_REPO_URL = "http://tefis1.inria.fr/repository/rest";//to be changed!!!
	//private static final String DEFAULT_REQPROC_URL = "http://tefis1.inria.fr/reqproc";//to be changed!!!
	private static final String DEFAULT_PE_Endpoint = "http://www.fire-teagle.org/openpe/services/PolicyEngineService";
	private RootController rootController;

	private XMLConfiguration config;
	private VctToolView view;

	public VctToolApp(String configFileName) {
		config = loadConfig(configFileName);
	}

	private void start()
	{
		view = new VctToolView();

		CommandAdapter c = new CommandAdapter(null) {
			@Override
			public void onPreferences() {
				doConfigDialog(true);
			}
		};
		
		view.addCommandListener(c);		

		view.getShell().open();

		VctToolConfig vctConfig = doConfigDialog(false);
		if (!vctConfig.isComplete())
			return;
		
		System.out.println("cerating root controller");

		rootController = new RootController(this, view, vctConfig, c);
		
		System.out.println("rootcontroller created");
		
		rootController.init();
		
		System.out.println();
		
		c.setRootController(rootController);

		System.out.println("running view");
		
		view.run();		
	}

	private VctToolConfig doConfigDialog(boolean force)
	{
		String username = config.getString("repo.username");
		String password = config.getString("repo.password");
		String repoUrlString = config.getString("repo.url");
		String reqprocUrlString = config.getString("reqproc.url");
		

		String pe_endpoint = config.getString("pe.endpoint");

		
		boolean storePass = config.getBoolean("repo.storePassword", false);
		if (repoUrlString == null)
			repoUrlString = DEFAULT_REPO_URL;
		if (reqprocUrlString == null)
			reqprocUrlString = DEFAULT_REQPROC_URL;
		if (pe_endpoint == null)
				pe_endpoint = DEFAULT_PE_Endpoint;
				
		
		VctToolConfig vctConfig = new VctToolConfig(username, password, repoUrlString, reqprocUrlString, pe_endpoint);
		
		if (force || !vctConfig.isComplete()) 
		{
			do
			{
				PreferencesDialog dlg = new PreferencesDialog(view.getShell());
				if (username != null) 
					dlg.setUsername(username);

				if (password != null) 
					dlg.setPassword(password);

				dlg.setStorePassword(storePass);
				dlg.setRepositoryUrl(repoUrlString);
				dlg.setReqprocUrl(reqprocUrlString);
				dlg.setPeEndpoint(pe_endpoint);
				
				if (dlg.show() != SWT.OK)
					return vctConfig;
				
				username = dlg.getUsername();
				password = dlg.getPassword();
				repoUrlString = dlg.getRepositoryUrl();
				reqprocUrlString = dlg.getReqprocUrl();
				storePass = dlg.isStorePassword();
				
				pe_endpoint = dlg.getPeEndpoint();
				
				URL reqprocUrl = null;
				URL repoUrl = null;
				URL pe_endpointURL = null;
				
				try 
				{
					reqprocUrl = new URL(reqprocUrlString);
				} 
				catch (MalformedURLException e) 
				{
					view.showError("Invalid request processor URL");
					continue;
				}
				
				try 
				{
					repoUrl = new URL(repoUrlString);
				} 
				catch (MalformedURLException e) 
				{
					view.showError("Invalid repository URL");
					continue;
				}

				if (pe_endpoint != null && !pe_endpoint.equals(""))
					try {
						pe_endpointURL = new URL(pe_endpoint);
					} catch (MalformedURLException e) 
					{
						view.showError("Invalid Policy Engine endpoint.");
						continue;
					}
				
				vctConfig.setUsername(username);
				vctConfig.setPassword(password);
				vctConfig.setRepoUrl(repoUrl);
				vctConfig.setReqprocUrl(reqprocUrl);
				vctConfig.setPeEndpoint(pe_endpointURL);

				if (!vctConfig.isComplete())
					view.showError("Configuration is incomplete.");
				
			} while (!vctConfig.isComplete());
			
			config.setProperty("repo.username", username);
			config.setProperty("repo.storePassword", storePass);
			config.setProperty("repo.url", repoUrlString);
			config.setProperty("reqproc.url", reqprocUrlString);
			if (storePass)
				config.setProperty("repo.password", password);					
			else
				config.clearProperty("repo.password");
			
			config.setProperty("pe.endpoint", pe_endpoint);			
			try
			{
				config.save();
			}
			catch (ConfigurationException e)
			{
				e.printStackTrace();
			}
		}
		ValidateActions.init(config.getString("repo.username"), config.getString("pe.endpoint"),this);		
		return vctConfig;
	}

	public static XMLConfiguration loadConfig(String fileName) {
		XMLConfiguration config = new XMLConfiguration();
		config.setAutoSave(true);

		File xmlFile;
		
		if (fileName == null) 
		{
			String userHome = System.getProperty("user.home");
			File configDir = new File(userHome + "/.vcttool");
			configDir.mkdirs();

			xmlFile = new File(configDir, "vcttool.xml");			
		} 
		else
			xmlFile = new File(fileName);
		
		try 
		{
			config.setFile(xmlFile);
			if (xmlFile.createNewFile()) 
			{
				config.setProperty("repo.url", DEFAULT_REPO_URL);
				config.setProperty("reqproc.url", DEFAULT_REQPROC_URL);
				config.setProperty("pe.endpoint", DEFAULT_PE_Endpoint);
			} else
				config.load();
		} 
		catch (IOException e) 
		{
			log.error("create config file", e);
		} 
		catch (ConfigurationException e) 
		{
			log.error("create config file", e);
		}			
		
		return config;
	}

	/**
	 * @param args
	 * @throws ConfigurationException 
	 */
	public static void main(String[] args) throws ConfigurationException {
		String arg0 = null;
		if (args.length > 0) {
			arg0 = args[0];
		}
		VctToolApp app = new VctToolApp(arg0);
		app.start();
	}


}
