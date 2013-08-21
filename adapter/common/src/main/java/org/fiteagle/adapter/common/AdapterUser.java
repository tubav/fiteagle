package org.fiteagle.adapter.common;

import java.util.List;

public class AdapterUser {

	String username;
	String firstName;
	String lastName;
	String email;
	List<String> sshPublicKeys;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getSshPublicKeys() {
		return sshPublicKeys;
	}
	public void setSshPublicKeys(List<String> sshPublicKeys) {
		this.sshPublicKeys = sshPublicKeys;
	}
	
	
}
