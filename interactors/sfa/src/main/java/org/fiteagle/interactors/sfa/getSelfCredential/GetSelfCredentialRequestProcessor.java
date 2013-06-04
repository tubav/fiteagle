package org.fiteagle.interactors.sfa.getSelfCredential;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.fiteagle.core.aaa.CertificateAuthority;
import org.fiteagle.core.aaa.KeyStoreManagement;
import org.fiteagle.core.aaa.SignatureCreator;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Credential;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Privilege;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Privileges;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Signatures;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.SignedCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

public class GetSelfCredentialRequestProcessor extends SFAv3RequestProcessor{

  Logger log = LoggerFactory.getLogger(getClass());
	public String processRequest(String cert, String xrn, String type) {
		if (!Type.contains(type)) {
			throw new IllegalArgumentException();
		}
		
		try {
      return getSelfCredential(cert, xrn, type);
    } catch (Exception e) {
     throw new RuntimeException();
    }
	}

	private String getSelfCredential(String cert, String xrn, String type) throws Exception {
		// TODO implement the get self credentials. check access rights etc.
	  
	  String signedCredentialString = "";
	  SignedCredential signedCredential = new SignedCredential();

    Credential credential = new Credential();
    credential.setId(createSignedCredentialId());
    credential.setType("privilege");
    credential.setOwnerGid(cert);
    credential.setOwnerURN(getOwnerURN(xrn));
    credential.setTargetGid(getTargetGID(type,xrn));
    credential.setTargetURN(getTargetURN(type,xrn));
    GregorianCalendar gregCalendar = new GregorianCalendar();
    gregCalendar
        .setTimeInMillis(java.lang.System.currentTimeMillis() + 100000);
    XMLGregorianCalendar expirationDate = null;
    try {
      expirationDate = DatatypeFactory.newInstance()
          .newXMLGregorianCalendar(gregCalendar);
    } catch (DatatypeConfigurationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    credential.setExpires(expirationDate);
    Privileges privileges = new Privileges();
    Privilege userPriv = new Privilege();
    userPriv.setCanDelegate(false);
    userPriv.setName("*");
    privileges.getPrivilege().add(userPriv);
    credential.setPrivileges(privileges);
  
    signedCredential.setCredential(credential);
    
    Signatures signatures = new Signatures();
    signedCredential.setSignatures(signatures);
    SignatureCreator signer = new SignatureCreator();
    try {
      String signedcredentialString = getJAXBString(signedCredential);
      InputSource is = new InputSource(new StringReader(signedcredentialString));
      ByteArrayOutputStream bout = signer.signContent(is, credential.getId());
      signedCredentialString = new String(bout.toByteArray());
      
    
    } catch (JAXBException e) {
      throw new RuntimeException(e.getMessage());
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
    signedCredentialString =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + signedCredentialString;
    return signedCredentialString;
	}


  private String getTargetURN(String type, String xrn) throws CertificateParsingException {
    String urn = "";
    if(type.equalsIgnoreCase("user")){
      urn = getServerURN();
      
    }else if(type.equalsIgnoreCase("Slice")){
       urn = xrn;
    }else{
      throw new UnsupportedTarget();
    }
    return urn;
   
  }

  private String getServerURN() throws CertificateParsingException {
    return interfaceConfig.getAM_URN();
  }

  private String getTargetGID(String type, String xrn) throws Exception {
    String cert = "";
    if(type.equalsIgnoreCase("user")){
      cert = getServerCert();
    }else if(type.equalsIgnoreCase("Slice")){
      cert = getSliceCert(xrn);
    }else{
      throw new UnsupportedTarget();
    }
    return cert;
  }

  private String getSliceCert(String xrn) {
    // TODO Auto-generated method stub
    return "";
  }

  //TODO refactor AM cert or SA cert !! Server Cert is wrong here!!
  private String getServerCert() throws Exception {
    CertificateAuthority ca =CertificateAuthority.getInstance();
    X509Certificate serverCert = ca.getServerCertificate();
    return ca.getCertficateEncoded(serverCert);
    
  }

  private String getOwnerURN(String xrn) {
    
    String[] split = xrn.split("\\.");
    String user = split[split.length-1];
    String returnString = "urn:publicid:IDN"; 
    String domain = "";
    for(int i = 0; i< split.length-1;i++){
      if(i>0){
        domain+= ":"+split[i];
      }
      else{
        domain+=split[i];
      }
    }
    
    String plus = domain.length()>0 ? "+" :"";
    returnString = returnString + plus + domain + "+user+" +user;
    return returnString;
  }

  private String getOwnerGID(String cert) {
    CertificateAuthority ca =CertificateAuthority.getInstance();
    String owner_gid = ca.getUserCertificateAsString(cert);
    return owner_gid;
  }

  private String getSignedCredentialString(SignedCredential signedCredential) {
    String signedCredentialSTR="";
    QName _SignedCredential_QNAME = new QName("", "signed-credential");
		JAXBElement<SignedCredential> signedCredJAXB = new JAXBElement<SignedCredential>(_SignedCredential_QNAME, SignedCredential.class, null, signedCredential);

    try {
      signedCredentialSTR = getJAXBString(signedCredJAXB);
    } catch (JAXBException e) {
      System.out.println("EXCEPTION!!!!!!!!1"+e);
    }
    
    return signedCredentialSTR;
  }
	
	private static String getJAXBString(Object jaxbObject) throws JAXBException {
    JAXBContext context = JAXBContext
        .newInstance("org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses");
    Marshaller marshaller = context.createMarshaller();
    StringWriter stringWriter = new StringWriter();
    marshaller.marshal(jaxbObject, stringWriter);

    return stringWriter.toString();

  }

	@Override
	public AMResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		// TODO Auto-generated method stub
		return null;
	}

	private String createSignedCredentialId(){
	  return UUID.randomUUID().toString();
	}

	
	public class UnsupportedTarget extends RuntimeException {

   
    private static final long serialVersionUID = -7821229625163019933L;
	  
	}
	
	
}
