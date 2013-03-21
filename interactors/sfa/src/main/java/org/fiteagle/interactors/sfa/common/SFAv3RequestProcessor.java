package org.fiteagle.interactors.sfa.common;


public abstract class SFAv3RequestProcessor {

	public abstract AMResult processRequest(ListCredentials credentials,
			Object... specificArgs);

	
}
