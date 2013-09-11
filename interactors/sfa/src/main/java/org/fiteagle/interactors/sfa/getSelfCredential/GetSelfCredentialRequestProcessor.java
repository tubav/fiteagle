package org.fiteagle.interactors.sfa.getSelfCredential;

import java.security.cert.X509Certificate;

import org.fiteagle.core.aaa.KeyStoreManagement;
import org.fiteagle.core.aaa.x509.X509Util;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.userdatabase.UserPersistable.UserNotFoundException;
import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Credential;
import org.fiteagle.interactors.sfa.util.CredentialFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetSelfCredentialRequestProcessor extends SFAv3RequestProcessor {

	Logger log = LoggerFactory.getLogger(getClass());

	public String processRequest(String cert, String xrn, String type) {
		if (!Type.contains(type)) {
			throw new IllegalArgumentException();
		}

		try {
			return getSelfCredential(cert, xrn, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String getSelfCredential(String cert, String xrn, String type)
			throws Exception {
		
		X509Certificate userCert = getUserCertificate(cert);
		URN target = new URN(xrn);
		Credential credential = null;
		try{
			CredentialFactory.setGroupDBManager(GroupDBManager.getInstance());
			CredentialFactory.setKeystoreManagement(KeyStoreManagement.getInstance());
			 credential = CredentialFactory.newCredential(userCert,
				target);
		}catch(RuntimeException e){
			return "";
		}
		String signedCredential = CredentialFactory.signCredential(credential);

		return signedCredential;
	}

	private X509Certificate getUserCertificate(String cert) {
		String theCertificate = parseSentCertificate(cert);
	
		X509Certificate xCert = X509Util.buildX509Certificate(theCertificate);
		return xCert;
	}

	private String parseSentCertificate(String cert) {
		String startString = "-----BEGIN CERTIFICATE-----";
		int start = cert.indexOf(startString);
		String endString = "-----END CERTIFICATE-----";
		int end = cert.lastIndexOf(endString);
		String sub = cert.substring(start, end + endString.length());
		return sub;
	}


	@Override
	public AMResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
