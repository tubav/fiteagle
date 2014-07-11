package org.fiteagle.interactors.sfa.renewSlice;

import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.aaa.KeyStoreManagement;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Credential;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.SignedCredential;
import org.fiteagle.interactors.sfa.register.RegisterRequestProcessor;
import org.fiteagle.interactors.sfa.util.CredentialFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RenewSliceRequestProcessor extends SFAv3RequestProcessor {
	Logger log = LoggerFactory.getLogger(getClass());
	
	private KeyStoreManagement keyStoreManagement;
	private GroupDBManager groupDBManager;
	
	private ResourceAdapterManager resourceManager;


	public RenewSliceRequestProcessor(KeyStoreManagement keystoreInstance,
			GroupDBManager groupDBInstance) {
		setKeyStoreManagement(keystoreInstance);
		setGroupDBManager(groupDBInstance);
		
	}

	@Override
	public AMResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		// TODO Auto-generated method stub
		return null;
	}

//	public String processRequest(Date dateTime, ListCredentials credentials) {
//		System.out.println(credentials);
//		System.out.println(dateTime);
//		return null;
//	}

	public HashMap<String, Object> renew(HashMap<String, Object> parameters) {
		String dateTimeString = (String) parameters.get("expiration");
		Date dateTime = getDateFromString(dateTimeString);
		
		String credentialsString = (String) parameters.get("credential");
		
		RegisterRequestProcessor registerReqProcessor = new RegisterRequestProcessor(getKeyStoreManagement(), getGroupDBManager());
		
		SignedCredential credentials = null;
		try {
			credentials = registerReqProcessor.buildCredential(credentialsString);
		} catch (JAXBException e) {
			log.error(e.getMessage(),e);
		}
		System.out.println(credentials);
		System.out.println(dateTime);
		
		String sliceUrnStr = credentials.getCredential().getTargetURN();
		
		URN sliceUrn = new URN(sliceUrnStr);
		
		
//		---------------------------------------------------------
		X509Certificate userCert = getUserCertificate();
		URN target = new URN(sliceUrnStr);
		Credential credential = null;
		try{
			CredentialFactory.setGroupDBManager(GroupDBManager.getInstance());
			CredentialFactory.setKeystoreManagement(KeyStoreManagement.getInstance());
			 credential = CredentialFactory.newCredential(userCert,	target);
		}catch(RuntimeException e){
			return null;
		}
		
//		TimeZone utc = TimeZone.getTimeZone("Etc/UTC");
		GregorianCalendar c = new GregorianCalendar();
		c.setTimeInMillis(dateTime.getTime());
//		c.setTime(dateTime);
		XMLGregorianCalendar valueGreg=null;
		
		try {
			valueGreg = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
		credential.setExpires(valueGreg);
		String signedCredential = CredentialFactory.signCredential(credential);

		
		
		
//		----------------------------------------------------------
		
		HashMap<String, Object> returnMap =  new HashMap<String, Object>();
    	returnMap.put("code", new Integer(0));
    	returnMap.put("value", signedCredential);
//    	returnMap.put("value", "");
    	returnMap.put("output", "");
    	return returnMap;
		
	}
	
	public Date getDateFromString(String dateTimeString) {
//		SimpleDateFormat rfc3339 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		SimpleDateFormat rfc3339 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		rfc3339.setTimeZone(TimeZone.getTimeZone("UTC"));
		try {
			return  rfc3339.parse(dateTimeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setResourceManager(ResourceAdapterManager instance) {
		this.resourceManager = instance;
	}


	public KeyStoreManagement getKeyStoreManagement() {
		return keyStoreManagement;
	}

	public void setKeyStoreManagement(KeyStoreManagement keyStoreManagement) {
		this.keyStoreManagement = keyStoreManagement;
	}

	public GroupDBManager getGroupDBManager() {
		return groupDBManager;
	}

	public void setGroupDBManager(GroupDBManager groupDBManager) {
		this.groupDBManager = groupDBManager;
	}

}
