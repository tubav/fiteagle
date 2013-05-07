package org.fiteagle.core.aaa;

import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.sql.SQLException;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.security.auth.x500.X500Principal;

import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationHandler {

  private UserManager userManager;
  
  Logger log = LoggerFactory.getLogger(this.getClass());

  public AuthenticationHandler(){
    this.userManager = new UserManager();
  }
  public void authenticateCertificates(X509Certificate[] certificates) {
    X509Certificate userCert = getUserCert(certificates);
    User identifiedUser = getUserFromCert(userCert);
    if(userCert.getSubjectX500Principal().equals(userCert.getIssuerX500Principal())){
      log.info("self signed certificate");
      log.info(userCert.toString());
      verifyUserSignedCertificate(identifiedUser, userCert);
      
    }else{
      log.info("someone else signed this");
      log.info(userCert.getIssuerX500Principal().getName());
    }
  }
  private void verifyUserSignedCertificate(User identifiedUser, X509Certificate certificate)  {
    boolean verified = false;
    for(String pubKeyString: identifiedUser.getPublicKeys()){
      
      verified = verifyCertificateWithPublicKey(certificate,pubKeyString);
      if(verified){
        return ;
      }
    }
    throw new RuntimeException();
  }
  
  //method made public for unittesting
  public boolean verifyCertificateWithPublicKey(X509Certificate certificate, String pubKeyString) {
    KeyDecoder keyDecoder = new KeyDecoder();
   
    try {
      PublicKey key =keyDecoder.decodePublicKey(pubKeyString);
      certificate.verify(key);
      return true;
    } catch (Exception e){
        return false;
    }
  
  }

  private User getUserFromCert(X509Certificate userCert) {
   
    X500Principal prince = userCert.getSubjectX500Principal();
    String userName = getCN(prince);
    try {
      User identifiedUser = userManager.getUserById(userName);
      return identifiedUser;
    } catch (SQLException e) {
      throw new UserNotFound();
    }
  }

  private String getCN(X500Principal prince) {
    String uuid = prince.getName();
    LdapName ldapDN = getLdapName(uuid);
   
    for(Rdn rdn: ldapDN.getRdns()) {
        if(rdn.getType().equals("CN")){
          return (String) rdn.getValue();
        }
    }
    throw new NonParsableNamingFormat();
  }

  private LdapName getLdapName(String uuid) {
    try {
      LdapName ldapDN = new LdapName(uuid);
      return ldapDN;
    } catch (InvalidNameException e) {
  
       throw new NonParsableNamingFormat();
    }
   
  }

  private X509Certificate getUserCert(X509Certificate[] certificates) {
    for(int i = 0 ; i< certificates.length; i++){
      if(isUserCertificate(certificates[i]))
        return certificates[i];
    }
    throw new NoUserCertificateProvided();
  }

  private boolean isUserCertificate(X509Certificate x509Certificate) {
     if(x509Certificate.getBasicConstraints() == -1){
       return true;
     }
     else{
       return false;
     }
  }
  

  private class NoUserCertificateProvided extends RuntimeException{
    
    private static final long serialVersionUID = 6847089692886665544L;
   
  }
  
  private class NonParsableNamingFormat extends RuntimeException{
    
    private static final long serialVersionUID = -3819932831236493248L;
    
  }
  
  private class UserNotFound extends RuntimeException {

    private static final long serialVersionUID = -8002909402748409082L;
    
  }
  
  
}
