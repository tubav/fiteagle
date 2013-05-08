package org.fiteagle.core.aaa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.SQLException;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.security.auth.x500.X500Principal;

import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserDBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationHandler {

  private UserDBManager userManager;
  
  Logger log = LoggerFactory.getLogger(this.getClass());

  FiteaglePreferences preferences = new FiteaglePreferencesXML(this.getClass());
  public AuthenticationHandler(){
    try {
      this.userManager = new UserDBManager();
    } catch (SQLException e) {
     log.error(e.getMessage(),e);
    }
  }
  public void authenticateCertificates(X509Certificate[] certificates) {
    
      if(certificates.length == 1){
        X509Certificate cert= certificates[0];
        if(cert.getSubjectX500Principal().equals(cert.getIssuerX500Principal())){
          //self signed cert
          User identifiedUser = getUserFromCert(cert);
          verifyUserSignedCertificate(identifiedUser, cert);
          
        }else{
          String issuerPublicKey = getPublicKeyForIssuer(cert.getIssuerX500Principal());
          
          //Caveeats :
          //Identify issuer, getIssuerCert from keystore, issuerCert = ca? or chained aswell ? make this recursive?? 
          verifyCertificateWithPublicKey(cert, issuerPublicKey);
        }
      }else{
        
      }
//    
//    X509Certificate userCert = getUserCert(certificates);
//    User identifiedUser = getUserFromCert(userCert);
//    if(userCert.getSubjectX500Principal().equals(userCert.getIssuerX500Principal())){
//      log.info("self signed certificate");
//      log.info(userCert.toString());
//      verifyUserSignedCertificate(identifiedUser, userCert);
//      
//    }else{
//      log.info("someone else signed this");
//      KeyStore keystore = getKeyStore();
//      log.info(userCert.getIssuerX500Principal().getName());
//     // keystore.
//    }
  }
  
  
  //TODO runtime exceptions 
  
  private String getPublicKeyForIssuer(X500Principal issuerX500Principal) {
    X509Certificate issuerCert = getIssuerCert(issuerX500Principal);
   return null;
  }
  private X509Certificate getIssuerCert(X500Principal issuerX500Principal) {
    // TODO Auto-generated method stub
    return null;
  }
  private KeyStore getKeyStore() {
      KeyStore ks = null;
      try {
        ks = KeyStore.getInstance(KeyStore.getDefaultType());
        char[] password = preferences.get("keystore_pass").toCharArray();
        
        FileInputStream fis = fis = new java.io.FileInputStream(preferences.get("keystore"));
        ks.load(fis, password);
      } catch (KeyStoreException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (NoSuchAlgorithmException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (CertificateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return ks;
      
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
      User identifiedUser = userManager.get(userName);
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
