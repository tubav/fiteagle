package org.fiteagle.interactors.sfa.common;

import java.util.ArrayList;

public class ListCredentials {

	private ArrayList<Credentials> credentialsList;
	
	public ListCredentials() {
		 this.credentialsList = new ArrayList<>();
	}
	
	public ArrayList<Credentials> getCredentialsList() {
		return credentialsList;
	}
	
	public void setCredentialsList(ArrayList<Credentials> credentialsList) {
		if(credentialsList != null)
		this.credentialsList = credentialsList;
	}
	
	public void addCredentials(Credentials credentials){
		if(credentials != null)
			this.credentialsList.add(credentials);
	}
	
	public Credentials getCredentials(int index){
		return credentialsList.get(index);
	}
}
