package org.fiteagle.interactors.sfa.util;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.fiteagle.core.aaa.CertificateAuthority;
import org.fiteagle.core.aaa.KeyStoreManagement;
import org.fiteagle.core.aaa.KeyStoreManagement.CertificateNotFoundException;
import org.fiteagle.core.aaa.x509.X509Util;
import org.fiteagle.core.config.InterfaceConfiguration;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Credential;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Privilege;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Privileges;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CredentialFactoryWorker {

	private Credential credential;
	private X509Certificate userCertificate;
	URN target;
	private X509Certificate targetCertificate;

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private GroupDBManager groupManager;
	private KeyStoreManagement keyStoreManagement;

	public CredentialFactoryWorker(
			X509Certificate credentialCertificate, URN target) {
	
		this.userCertificate = credentialCertificate;
		this.target = getTargetURN(target);
		
	}

	private void setTargetCertificate() {
		try {
			targetCertificate = getTargetCertificate();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private X509Certificate getTargetCertificate() throws Exception {
		
		if (target.getType().equalsIgnoreCase("slice")) {
		
			Group group = groupManager.getGroup(target.getSubjectAtDomain());
			try {

				return keyStoreManagement.getResourceCertificate(target
						.getSubjectAtDomain());
			} catch (CertificateNotFoundException e) {
				X509Certificate groupCertificate = CertificateAuthority
						.getInstance().createCertificate(group);
				keyStoreManagement.storeResourceCertificate(groupCertificate);
				return groupCertificate;
			}

		}
		if (target.getType().equalsIgnoreCase("authority")) {
			
			return keyStoreManagement.getSliceAuthorityCert();
		}
		throw new RuntimeException();
	}

	private void setId() {
		credential.setId(UUID.randomUUID().toString());

	}

	private void setType() {
		credential.setType("privilege");

	}

	private void setOwnerGID() {
		X509Certificate returnCert = userCertificate;
		CertificateAuthority ca = CertificateAuthority.getInstance();
		if (X509Util.isSelfSigned(userCertificate)) {
			try {
				returnCert = ca.createCertificate(userCertificate);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new RuntimeException(e.getMessage());
			}
		}
		String returnString;
		try {
			returnString = X509Util.getCertificateBodyEncoded(returnCert);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
		userCertificate = returnCert;
		credential.setOwnerGid(returnString);

	}

	private void setOwnerURN() {
		URN urn = getSubjectUrn();
		credential.setOwnerURN(urn.toString());

	}

	private URN getSubjectUrn() {
		try {
			return X509Util.getURN(userCertificate);
		} catch (CertificateParsingException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	private void setTargetGID() {
		try {
			credential.setTargetGid(X509Util
					.getCertificateBodyEncoded(targetCertificate));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	private void setTargetURN() {
		credential.setTargetURN(target.toString());
	}

	private void setExpirationDate() {

		GregorianCalendar gregCalendar = new GregorianCalendar();
		gregCalendar
				.setTimeInMillis(java.lang.System.currentTimeMillis() + 100000);
		XMLGregorianCalendar expirationDate = null;
		try {
			expirationDate = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(gregCalendar);
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
		credential.setExpires(expirationDate);

	}

	private void setPrivleges() {
		Privileges privileges = new Privileges();
		Privilege userPriv = new Privilege();
		userPriv.setCanDelegate(false);
		userPriv.setName("*");
		privileges.getPrivilege().add(userPriv);
		credential.setPrivileges(privileges);

	}

	private URN getTargetURN(URN target) {
		URN urn = null;
		if (target.getType().equalsIgnoreCase("user")) {
			urn = getSliceAuthorityURN();

		} else if (target.getType().equalsIgnoreCase("Slice")) {
			urn = target;
		} else {
			throw new UnsupportedTarget();
		}
		return urn;

	}

	private URN getSliceAuthorityURN() {

		return new URN(InterfaceConfiguration.getInstance().getSA_URN());

	}
	
	public class UnsupportedTarget extends RuntimeException {

		private static final long serialVersionUID = -7821229625163019933L;

	}

	public void setGroupManager(GroupDBManager groupManager) {
		this.groupManager = groupManager;
		
	}
	
	public void setKeyStoreManager(KeyStoreManagement keyStoreManagement){
		this.keyStoreManagement = keyStoreManagement;
	}

	public Credential getCredential() {
		credential = new Credential();
		setTargetCertificate();
		setId();
		setType();
		setOwnerGID();
		setOwnerURN();
		setTargetGID();
		setTargetURN();
		setExpirationDate();
		setPrivleges();
		return credential;
	}

}
