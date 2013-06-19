package org.fiteagle.interactors.sfa.getSelfCredential;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.fiteagle.core.aaa.CertificateAuthority;
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
    credential.setOwnerGid(getOwnerGID(cert));
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
      throw new RuntimeException(e);
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
      String tmpsignedcredentialString = getJAXBString(signedCredential);
      InputSource is = new InputSource(new StringReader(tmpsignedcredentialString));
      ByteArrayOutputStream bout = signer.signContent(is, credential.getId());
      tmpsignedcredentialString = new String(bout.toByteArray());
      signedCredentialString = SFIFix.removeNewlinesFromCertificateInsideSignature(tmpsignedcredentialString);
    
    } catch (JAXBException e) {
      throw new RuntimeException(e.getMessage());
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
    signedCredentialString =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + signedCredentialString;
    return signedCredentialString;
	}


  private String getOwnerGID(String cert) throws Exception {
    CertificateAuthority ca = CertificateAuthority.getInstance();
    X509Certificate xCert = ca.buildX509Certificate(cert);
    X509Certificate returnCert = xCert;
    if(isSelfSigned(xCert))
      returnCert = ca.createCertificate(xCert);
    
    String returnString = ca.getCertficateEncoded(returnCert);
    return returnString;
    
  }

 

  private boolean isSelfSigned(X509Certificate xCert) {
    return xCert.getIssuerX500Principal().equals(xCert.getSubjectX500Principal());
  }

  private String getTargetURN(String type, String xrn) throws CertificateParsingException {
    String urn = "";
    if(type.equalsIgnoreCase("user")){
      urn = getSliceAuthorityURN();
      
    }else if(type.equalsIgnoreCase("Slice")){
       urn = xrn;
    }else{
      throw new UnsupportedTarget();
    }
    return urn;
   
  }

  private String getSliceAuthorityURN() throws CertificateParsingException {
    return interfaceConfig.getSA_URN();
  }

  private String getTargetGID(String type, String xrn) throws Exception {
    String cert = "";
    if(type.equalsIgnoreCase("user")){
      cert = getSliceAuthorityCert();
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

  private String getSliceAuthorityCert() throws Exception {
    CertificateAuthority ca =CertificateAuthority.getInstance();
    X509Certificate sliceAuthorityCert = ca.getSliceAuthorityCertificate();
    return ca.getCertficateEncoded(sliceAuthorityCert);
    
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
	
	private static class SFIFix{
	  
	  public static String removeNewlinesFromCertificateInsideSignature(String certificateString){
	    String begin = "<X509Certificate>";
	    String end = "</X509Certificate>";
	    certificateString = certificateString.replaceAll(begin+"\\n", begin);
	    certificateString = certificateString.replaceAll("\\n" +end, end);
	    return certificateString;

	    
	  }
	}
}
