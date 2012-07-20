/**
 * 
 */
package org.teagle.vcttool.app;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.eclipse.swt.SWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final String DEFAULT_REPO_URL = "http://teagle.av.tu-berlin.de:8080/repository/rest";// to
																										// be
																										// changed!!!
	private static final String DEFAULT_REQPROC_URL = "http://www.fire-teagle.org/reqproc";
	// private static final String DEFAULT_REPO_URL =
	// "http://tefis1.inria.fr/repository/rest";//to be changed!!!
	// private static final String DEFAULT_REQPROC_URL =
	// "http://tefis1.inria.fr/reqproc";//to be changed!!!
	private static final String DEFAULT_PE_Endpoint = "http://www.fire-teagle.org/openpe/services/PolicyEngineService";
	private RootController rootController;

	private final XMLConfiguration config;
	private VctToolView view;

	public VctToolApp(final String configFileName) {
		this.config = VctToolApp.loadConfig(configFileName);
	}

	private void start() {
		this.view = new VctToolView();

		final CommandAdapter c = new CommandAdapter(null) {
			@Override
			public void onPreferences() {
				VctToolApp.this.doConfigDialog(true);
			}
		};

		this.view.addCommandListener(c);

		this.view.getShell().open();

		final VctToolConfig vctConfig = this.doConfigDialog(false);
		if (!vctConfig.isComplete())
			return;

		System.out.println("cerating root controller");

		this.rootController = new RootController(this, this.view, vctConfig, c);

		System.out.println("rootcontroller created");

		this.rootController.init();

		System.out.println();

		c.setRootController(this.rootController);

		System.out.println("running view");

		this.view.run();
	}

	private VctToolConfig doConfigDialog(final boolean force) {
		String username = this.config.getString("repo.username");
		String password = this.config.getString("repo.password");
		String repoUrlString = this.config.getString("repo.url");
		String reqprocUrlString = this.config.getString("reqproc.url");

		String pe_endpoint = this.config.getString("pe.endpoint");

		boolean storePass = this.config.getBoolean("repo.storePassword", false);
		if (repoUrlString == null)
			repoUrlString = VctToolApp.DEFAULT_REPO_URL;
		if (reqprocUrlString == null)
			reqprocUrlString = VctToolApp.DEFAULT_REQPROC_URL;
		if (pe_endpoint == null)
			pe_endpoint = VctToolApp.DEFAULT_PE_Endpoint;

		final VctToolConfig vctConfig = new VctToolConfig(username, password,
				repoUrlString, reqprocUrlString, pe_endpoint);

		if (force || !vctConfig.isComplete()) {
			do {
				final PreferencesDialog dlg = new PreferencesDialog(
						this.view.getShell());
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

				try {
					reqprocUrl = new URL(reqprocUrlString);
				} catch (final MalformedURLException e) {
					this.view.showError("Invalid request processor URL");
					continue;
				}

				try {
					repoUrl = new URL(repoUrlString);
				} catch (final MalformedURLException e) {
					this.view.showError("Invalid repository URL");
					continue;
				}

				if ((pe_endpoint != null) && !pe_endpoint.equals(""))
					try {
						pe_endpointURL = new URL(pe_endpoint);
					} catch (final MalformedURLException e) {
						this.view.showError("Invalid Policy Engine endpoint.");
						continue;
					}

				vctConfig.setUsername(username);
				vctConfig.setPassword(password);
				vctConfig.setRepoUrl(repoUrl);
				vctConfig.setReqprocUrl(reqprocUrl);
				vctConfig.setPeEndpoint(pe_endpointURL);

				if (!vctConfig.isComplete())
					this.view.showError("Configuration is incomplete.");

			} while (!vctConfig.isComplete());

			this.config.setProperty("repo.username", username);
			this.config.setProperty("repo.storePassword", storePass);
			this.config.setProperty("repo.url", repoUrlString);
			this.config.setProperty("reqproc.url", reqprocUrlString);
			if (storePass)
				this.config.setProperty("repo.password", password);
			else
				this.config.clearProperty("repo.password");

			this.config.setProperty("pe.endpoint", pe_endpoint);
			try {
				this.config.save();
			} catch (final ConfigurationException e) {
				e.printStackTrace();
			}
		}
		// ValidateActions.init(config.getString("repo.username"),
		// config.getString("pe.endpoint"),this);
		return vctConfig;
	}

	public static XMLConfiguration loadConfig(final String fileName) {
		final XMLConfiguration config = new XMLConfiguration();
		config.setAutoSave(true);

		File xmlFile;

		if (fileName == null) {
			final String userHome = System.getProperty("user.home");
			final File configDir = new File(userHome + "/.vcttool");
			configDir.mkdirs();

			xmlFile = new File(configDir, "vcttool.xml");
		} else
			xmlFile = new File(fileName);

		try {
			config.setFile(xmlFile);
			if (xmlFile.createNewFile()) {
				config.setProperty("repo.url", VctToolApp.DEFAULT_REPO_URL);
				config.setProperty("reqproc.url",
						VctToolApp.DEFAULT_REQPROC_URL);
				config.setProperty("pe.endpoint",
						VctToolApp.DEFAULT_PE_Endpoint);
			} else
				config.load();
		} catch (final IOException e) {
			VctToolApp.log.error("create config file", e);
		} catch (final ConfigurationException e) {
			VctToolApp.log.error("create config file", e);
		}

		return config;
	}

	/**
	 * @param args
	 * @throws ConfigurationException
	 */
	public static void main(final String[] args) throws ConfigurationException {
		String arg0 = null;
		if (args.length > 0)
			arg0 = args[0];
		final VctToolApp app = new VctToolApp(arg0);
		app.start();
	}

}
