package teagle.vct.model;

import java.net.URL;

public class RepoClientConfig {
	private URL url = null;
	private String username = "";
	private String password = "";
	private boolean doPrefetching = true;
	private boolean doAutoclear = false;
	private int autoClearInterval = 1800000;
	
	public RepoClientConfig(URL repoUrl, String username, String password,
			boolean doPrefetching)
	{
		this(repoUrl, username, password, doPrefetching, false);
	}
	
	public RepoClientConfig(URL repoUrl, String username, String password,
			boolean doPrefetching, boolean doAutoclear)	
	{
		this.url = repoUrl;
		this.username = username;
		this.password = password;
		this.doPrefetching = doPrefetching;
		this.doAutoclear = doAutoclear;
	}
	public URL getUrl()
	{
		return url;
	}
	public void setUrl(URL url)
	{
		this.url = url;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public boolean getDoPrefetching()
	{
		return doPrefetching;
	}
	public void setDoPrefetching(boolean doPrefetching)
	{
		this.doPrefetching = doPrefetching;
	}
	public boolean getDoAutoclear()
	{
		return doAutoclear;
	}
	public void setDoAutoclear(boolean doAutoclear)
	{
		this.doAutoclear = doAutoclear;
	}
	public int getAutoClearInterval()
	{
		return autoClearInterval;
	}
	public void setAutoClearInterval(int autoClearInterval)
	{
		this.autoClearInterval = autoClearInterval;
	}
	
	
}
