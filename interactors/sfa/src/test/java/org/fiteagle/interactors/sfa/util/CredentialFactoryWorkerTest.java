package org.fiteagle.interactors.sfa.util;

import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Credential;
import org.junit.Before;
import org.junit.Test;
import static org.easymock.EasyMock.createMock;

public class CredentialFactoryWorkerTest {
	Credential c ;
	
	
	@Before
	public void setUp(){
		c = createMock(Credential.class);
		
	}


}
