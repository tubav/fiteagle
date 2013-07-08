package org.fiteagle.interactors.sfa.register;

import java.io.StringReader;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.SignedCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterRequestProcessor extends SFAv3RequestProcessor{
Logger log = LoggerFactory.getLogger(getClass()); 
  @Override
  public AMResult processRequest(ListCredentials credentials, Object... specificArgs) {
    
    throw new NotImplemented();
  }
  
  public HashMap<String, Object> register(HashMap<String, Object> parameters){
    String urn = getUrn(parameters);
    String type = getType(parameters);
    String credentialString = getCredential(parameters);
    SignedCredential cred = null;
    try {
       cred =buildCredential(credentialString);
    } catch (JAXBException e) {
      log.error(e.getMessage(),e);
    }
    
    if(isSliceType(type) && isURN(urn)){
     
      return new HashMap<String, Object>();
    }
    else {
      return new HashMap<String, Object>();
    }
    
  }
  
  public class NotImplemented extends RuntimeException{
    private static final long serialVersionUID = 1L;
  }

  public String getType(HashMap<String, Object> hashMap) {
    return hashMap.get("type").toString();
    
  }

  public boolean isSliceType(String string) {
   return string.equalsIgnoreCase("Slice");
  }

  
  public String getUrn(HashMap<String, Object> hashMap) {
    return hashMap.get("urn").toString();
  }

  public boolean isURN(String urn) {
    return true;
  }

  public String getCredential(HashMap<String, Object> hashMap) {
    return hashMap.get("credential").toString();
  }

  public SignedCredential buildCredential(String credential) throws JAXBException {
       JAXBContext context = JAXBContext.newInstance("org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses");
      Unmarshaller unmarshaller = context.createUnmarshaller();
      StringReader reader = new StringReader(credential);
      SignedCredential sc = (SignedCredential) unmarshaller.unmarshal(reader);
      return sc;
  }
  
}
