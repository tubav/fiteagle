package org.fiteagle.adapter.common;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class AdapterConfiguration {

	private List<AdapterUser> users;
	private Date expirationTime;
	
	public AdapterConfiguration(){
		this.users = new LinkedList<>();
		this.expirationTime = null;
	}
	public List<AdapterUser> getUsers() {
		return users;
	}
	public void setUsers(List<AdapterUser> users) {
		this.users = users;
	}
	public Date getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}
	
	
	
}
