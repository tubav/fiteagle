package org.fiteagle.interactors.sfa.common;

public class SFACredentialsService {

	ListCredentials credentialsList;
	
	public SFACredentialsService(ListCredentials credentialsList){
		this.credentialsList = credentialsList;
		
	}

	public boolean isPermitted(SFAv3MethodsEnum method) {
		// TODO Auto-generated method stub
		//User <-> Credential Check
		return true;
	}
	
	
	
}
	
	
