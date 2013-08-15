package org.fiteagle.interactors.sfa.util;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.cert.X509Certificate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.fiteagle.core.aaa.SignatureCreator;
import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Credential;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.Signatures;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.SignedCredential;
import org.xml.sax.InputSource;

public class CredentialFactory {

	public static Credential newCredential(X509Certificate userCert, URN target) {
		Credential c =  new Credential();
		CredentialFactoryWorker worker = new CredentialFactoryWorker(c, userCert, target);
		worker.setId();
		worker.setType();
		worker.setOwnerGID();
		worker.setOwnerURN();
		worker.setTargetGID();
		worker.setTargetURN();
		worker.setExpirationDate();
		worker.setPrivleges();
		return c;
	}
	
	public static String signCredential(Credential credential){
		SignedCredential signedCredential = new SignedCredential();
		signedCredential.setCredential(credential);
		  
	    Signatures signatures = new Signatures();
	    signedCredential.setSignatures(signatures);
	    SignatureCreator signer = new SignatureCreator();
	    String signedCredentialString = "";
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
	
	private static String getJAXBString(Object jaxbObject) throws JAXBException {
	    JAXBContext context = JAXBContext
	        .newInstance("org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses");
	    Marshaller marshaller = context.createMarshaller();
	    StringWriter stringWriter = new StringWriter();
	    marshaller.marshal(jaxbObject, stringWriter);

	    return stringWriter.toString();

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
