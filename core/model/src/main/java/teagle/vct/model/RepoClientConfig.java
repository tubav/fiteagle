package teagle.vct.model;

import java.net.URL;

public class RepoClientConfig {
	private URL url = null;
	private String username = "";
	private String password = "";
	private boolean doPrefetching = true;
	private boolean doAutoclear = false;
	private int autoClearInterval = 1800000;

	public RepoClientConfig(final URL repoUrl, final String username,
			final String password, final boolean doPrefetching) {
		this(repoUrl, username, password, doPrefetching, false);
	}

	public RepoClientConfig(final URL repoUrl, final String username,
			final String password, final boolean doPrefetching,
			final boolean doAutoclear) {
		this.url = repoUrl;
		this.username = username;
		this.password = password;
		this.doPrefetching = doPrefetching;
		this.doAutoclear = doAutoclear;
	}

	public URL getUrl() {
		return this.url;
	}

	public void setUrl(final URL url) {
		this.url = url;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public boolean getDoPrefetching() {
		return this.doPrefetching;
	}

	public void setDoPrefetching(final boolean doPrefetching) {
		this.doPrefetching = doPrefetching;
	}

	public boolean getDoAutoclear() {
		return this.doAutoclear;
	}

	public void setDoAutoclear(final boolean doAutoclear) {
		this.doAutoclear = doAutoclear;
	}

	public int getAutoClearInterval() {
		return this.autoClearInterval;
	}

	public void setAutoClearInterval(final int autoClearInterval) {
		this.autoClearInterval = autoClearInterval;
	}

}
