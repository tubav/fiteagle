package org.teagle.vcttool.control;

import java.net.MalformedURLException;
import java.net.URL;

public class VctToolConfig 
{
	private String repoUser = "";
	private String repoPass = "";
	private URL repoUrl = null;
	private URL reqprocUrl = null;
	private boolean doPrefeteching = false;
	private URL pe_url;
	
	public VctToolConfig(String username, String password, String repoUrl,
			String reqprocUrl, String pe_endpoint) 
	{
		setUsername(username);
		setPassword(password);
		
		try {
			setReqprocUrl(new URL(reqprocUrl));
		} catch (MalformedURLException e) {}
		
		try {
			setRepoUrl(new URL(repoUrl));
		} catch (MalformedURLException e) {}

		try {
			setPeEndpoint(new URL(pe_endpoint));
		} catch (MalformedURLException e) {}

	}

	public String getUsername() {
		return repoUser;
	}
	
	public void setUsername(String repoUser) {
		if (repoUser == null)
			repoUser = "";
		this.repoUser = repoUser;
	}
	
	public String getPassword() {
		return repoPass;
	}
	
	public void setPassword(String repoPass) {
		if (repoPass == null)
			repoPass = "";
		this.repoPass = repoPass;
	}
	
	public URL getRepoUrl() {
		return repoUrl;
	}
	
	public void setRepoUrl(URL repoUrl) {
		this.repoUrl = repoUrl;
	}
	
	public URL getReqprocUrl() {
		return reqprocUrl;
	}
	
	public void setReqprocUrl(URL reqprocUrl) {
		this.reqprocUrl = reqprocUrl;
	}
	
	public URL getPeEndpoint() {
		return pe_url;
	}
	
	public void setPeEndpoint(URL peurl) {
		this.pe_url = peurl;
	}
	
	public boolean isComplete()
	{
		return reqprocUrl != null && repoUrl != null && repoUser.length() != 0 && repoPass.length() != 0;
	}
	
	public boolean getDoPrefetching()
	{
		return doPrefeteching;
	}
	
	public void setDoPrefetching(boolean b)
	{
		doPrefeteching = b;
	}
}
