package org.fiteagle.interactors.sfa.getSelfCredential;

import java.io.StringWriter;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Capabilities;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Capability;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Credential;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Credentials;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Signature;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Signatures;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.SignedCredential;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Ticket;

public class GetSelfCredentialRequestProcessor extends SFAv3RequestProcessor{

	public String processRequest(String cert, String xrn, String type) {
		if (!Type.contains(type)) {
			throw new IllegalArgumentException();
		}
		
		return getSelfCredential(cert, xrn, type);
	}

	private String getSelfCredential(String cert, String xrn, String type) {
		// TODO implement the get self credentials. check access rights etc.
	  
	  
	  SignedCredential signedCredential = new SignedCredential();

    Credential credential = new Credential();
    credential.setId("testCredential");
    GregorianCalendar gregCalendar = new GregorianCalendar();
    gregCalendar
        .setTimeInMillis(java.lang.System.currentTimeMillis() + 10000);
    XMLGregorianCalendar expirationDate = null;
    try {
      expirationDate = DatatypeFactory.newInstance()
          .newXMLGregorianCalendar(gregCalendar);
    } catch (DatatypeConfigurationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    credential.setExpires(expirationDate);
    
    Capabilities capabilities=new Capabilities();
    Capability capability= new Capability();
    capability.setName("testCapabilityName");
    capability.setCanDelegate("CheckWhatThisCanBe!");
    capabilities.getCapability().add(capability);
    credential.setCapabilities(capabilities);
    credential.setOwnerGid("TESTOWNERGID");
    credential.setId("someTestId");
    Credentials otherCredentials=new Credentials();
    Credential otherCred=new Credential();
    otherCred.setId("otherId");
    Ticket ticket= new Ticket();
    ticket.getContent().add("SOmethingSomethingInTicket");
    otherCred.setTicket(ticket);
    otherCred.setType("user");
    otherCredentials.setCredential(otherCred);
    credential.setParent(otherCredentials);
    
    signedCredential.setCredential(credential);
    
    Signatures signatures= new Signatures();
    
    Signature signature= new Signature();
    signature.getContent().add("someTestStaff");
    signatures.getSignature().add(signature);
    signedCredential.setSignatures(signatures);
	  
//		SignedCredential signedCredential = new SignedCredential();
//		
//		Credential credential = new Credential();
//		credential.setId("testCredential");
//		GregorianCalendar gregCalendar= new GregorianCalendar();
//		gregCalendar.setTimeInMillis(java.lang.System.currentTimeMillis()+10000);
//		XMLGregorianCalendar expirationDate=null;
//		try {
//			expirationDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregCalendar);
//		} catch (DatatypeConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		credential.setExpires(expirationDate);
//		signedCredential.setCredential(credential );
		
		return getSignedCredentialString(signedCredential);
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

}
