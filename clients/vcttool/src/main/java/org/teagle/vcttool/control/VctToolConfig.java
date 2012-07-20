package org.teagle.vcttool.control;

import java.net.MalformedURLException;
import java.net.URL;

public class VctToolConfig {
	private String repoUser = "";
	private String repoPass = "";
	private URL repoUrl = null;
	private URL reqprocUrl = null;
	private boolean doPrefeteching = false;
	private URL pe_url;

	public VctToolConfig(final String username, final String password,
			final String repoUrl, final String reqprocUrl,
			final String pe_endpoint) {
		this.setUsername(username);
		this.setPassword(password);

		try {
			this.setReqprocUrl(new URL(reqprocUrl));
		} catch (final MalformedURLException e) {
		}

		try {
			this.setRepoUrl(new URL(repoUrl));
		} catch (final MalformedURLException e) {
		}

		try {
			this.setPeEndpoint(new URL(pe_endpoint));
		} catch (final MalformedURLException e) {
		}

	}

	public String getUsername() {
		return this.repoUser;
	}

	public void setUsername(String repoUser) {
		if (repoUser == null)
			repoUser = "";
		this.repoUser = repoUser;
	}

	public String getPassword() {
		return this.repoPass;
	}

	public void setPassword(String repoPass) {
		if (repoPass == null)
			repoPass = "";
		this.repoPass = repoPass;
	}

	public URL getRepoUrl() {
		return this.repoUrl;
	}

	public void setRepoUrl(final URL repoUrl) {
		this.repoUrl = repoUrl;
	}

	public URL getReqprocUrl() {
		return this.reqprocUrl;
	}

	public void setReqprocUrl(final URL reqprocUrl) {
		this.reqprocUrl = reqprocUrl;
	}

	public URL getPeEndpoint() {
		return this.pe_url;
	}

	public void setPeEndpoint(final URL peurl) {
		this.pe_url = peurl;
	}

	public boolean isComplete() {
		return (this.reqprocUrl != null) && (this.repoUrl != null)
				&& (this.repoUser.length() != 0)
				&& (this.repoPass.length() != 0);
	}

	public boolean getDoPrefetching() {
		return this.doPrefeteching;
	}

	public void setDoPrefetching(final boolean b) {
		this.doPrefeteching = b;
	}
}
