package org.fiteagle.interactors.sfa.getSelfCredential;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Credential;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.SignedCredential;

public class GetSelfCredentialRequestProcessor extends SFAv3RequestProcessor{

	public SignedCredential processRequest(String cert, String xrn, String type) {
		if (!Type.contains(type)) {
			throw new IllegalArgumentException();
		}
		
		return getSelfCredential(cert, xrn, type);
	}

	private SignedCredential getSelfCredential(String cert, String xrn, String type) {
		// TODO implement the get self credentials. check access rights etc.
		SignedCredential signedCredential = new SignedCredential();
		
		Credential credential = new Credential();
		credential.setId("testCredential");
		GregorianCalendar gregCalendar= new GregorianCalendar();
		gregCalendar.setTimeInMillis(java.lang.System.currentTimeMillis()+10000);
		XMLGregorianCalendar expirationDate=null;
		try {
			expirationDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregCalendar);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		credential.setExpires(expirationDate);
		signedCredential.setCredential(credential );
		
		return signedCredential;
	}

	@Override
	public AMResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		// TODO Auto-generated method stub
		return null;
	}

}
