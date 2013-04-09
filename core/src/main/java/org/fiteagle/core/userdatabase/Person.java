package org.fiteagle.core.userdatabase;

import java.util.ArrayList;

public class Person {

	private String UID;
	private String firstName;
	private String lastName;
	private ArrayList<String> publicKeys;

	public Person(String UID, String firstName, String lastName, ArrayList<String> publicKeys) {
		this.UID = UID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.publicKeys = publicKeys;
	}

	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
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
	
	public ArrayList<String> getPublicKeys() {
		return publicKeys;
	}

	public void setPublicKeys(ArrayList<String> publicKeys) {
		this.publicKeys = publicKeys;
	}
	
	public void addPublicKey(String publicKey){
		this.publicKeys.add(publicKey);
	}

}
