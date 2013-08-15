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

	public CredentialFactoryWorker(Credential credential,
			X509Certificate credentialCertificate, URN target) {
		this.credential = credential;
		this.userCertificate = credentialCertificate;
		this.target = getTargetURN(target);
		try {
			targetCertificate = getTargetCertificate();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private X509Certificate getTargetCertificate() throws Exception {
		KeyStoreManagement keyStoreManagement = KeyStoreManagement
				.getInstance();
		if (target.getType().equalsIgnoreCase("slice")) {
			GroupDBManager groupManager = GroupDBManager.getInstance();
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
			KeyStoreManagement keyStoreManagment = KeyStoreManagement
					.getInstance();
			return keyStoreManagment.getSliceAuthorityCert();
		}
		throw new RuntimeException();
	}

	public void setId() {
		credential.setId(UUID.randomUUID().toString());

	}

	public void setType() {
		credential.setType("privilege");

	}

	public void setOwnerGID() {
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
			returnString = X509Util.getCertficateEncoded(returnCert);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
		credential.setOwnerGid(returnString);

	}

	public void setOwnerURN() {
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

	public void setTargetGID() {
		try {
			credential.setTargetGid(X509Util
					.getCertficateEncoded(targetCertificate));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	public void setTargetURN() {
		credential.setTargetURN(target.toString());
	}

	public void setExpirationDate() {

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

	public void setPrivleges() {
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

}
