package org.fiteagle.core.aaa;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

import javax.security.auth.x500.X500Principal;

import org.fiteagle.core.aaa.KeyManagement.CouldNotParse;
import org.fiteagle.core.aaa.x509.X509Util;
import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserDBManager;
import org.fiteagle.core.userdatabase.UserPublicKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationHandler {
  
  private KeyStoreManagement keyStoreManagement;
  Logger log = LoggerFactory.getLogger(this.getClass());
  
  private static AuthenticationHandler authHandler = null;
  
  public static AuthenticationHandler getInstance() {
    if (authHandler == null)
      authHandler = new AuthenticationHandler();
    
    return authHandler;
  }
  
  private AuthenticationHandler() {
    
    this.keyStoreManagement = KeyStoreManagement.getInstance();
  }
  
  public boolean areAuthenticatedCertificates(X509Certificate[] certificates) throws KeyStoreException,
      NoSuchAlgorithmException, CertificateException, IOException, InvalidAlgorithmParameterException,
      CertPathValidatorException, SQLException, InvalidKeySpecException, CouldNotParse {
   
    X509Certificate cert = certificates[0];
    try{
    if (cert.getSubjectX500Principal().equals(cert.getIssuerX500Principal())) {
      
      UserDBManager userDBManager = UserDBManager.getInstance();
      User identifiedUser = userDBManager.getUserFromCert(cert);
      return verifyUserSignedCertificate(identifiedUser, cert);
      
    } else {
      
      if(isValid(0, certificates, certificates[0].getSubjectX500Principal())){
//        if(userIsUnknown(cert))
//          storeNewUser(cert);
    	  return true;
      }

    }
    }catch(RuntimeException e){
    	return false;
    }
	return false;
    
  }
  
  
  private void storeNewUser(X509Certificate certificate) {
    // TODO Auto-generated method stub
    
  }

//  private boolean userIsUnknown(X509Certificate certificate) throws CertificateParsingException {
//    UserDBManager  userDBManager = UserDBManager.getInstance();
//    String userName = X509Util.getUserNameFromX509Certificate(certificate);
//    String domain = X509Util.getDomain(certificate);
//    try{
//      User u = userDBManager.get(userName);
//      return true;
//    }catch(UserNotFoundException e){
//      return false;
//    }
//    
//  }

  private boolean isValid(int i, X509Certificate[] certificates, X500Principal x500Principal) throws KeyStoreException,
      NoSuchAlgorithmException, CertificateException, IOException {
    boolean valid = false;
    if (i < certificates.length && x500Principal.equals(certificates[i].getSubjectX500Principal())) {
      
      if (i == certificates.length - 1) {
        valid=  isTrustworthy(certificates[i]);
        
      } else {
        valid = isValid(i + 1, certificates, certificates[i].getIssuerX500Principal());
      }
      
    }if(valid){
      return valid;
    }else {
      throw new CertificateNotTrustedException();
    }
    
  }
  
  private boolean isTrustworthy(X509Certificate trustworthyCert) throws KeyStoreException, NoSuchAlgorithmException,
      CertificateException, IOException {
    List<X509Certificate> storedCerts = keyStoreManagement.getTrustedCerts();
   
    for (X509Certificate cert : storedCerts) {
      if (cert.getIssuerX500Principal().equals(trustworthyCert.getIssuerX500Principal())) {
        
        try {
          trustworthyCert.verify(cert.getPublicKey());
          return true;
        } catch (Exception e) {
          log.error(e.getMessage(),e);
          return false;
        }
      }
    }
    return false;
  }
  
  private PublicKey getTrustedIssuerPublicKey(X500Principal issuerX500Principal) throws KeyStoreException,
      NoSuchAlgorithmException, CertificateException, IOException {
    List<X509Certificate> storedCerts = keyStoreManagement.getTrustedCerts();
    for (X509Certificate cert : storedCerts) {
      if (!isUserCertificate(cert)) {
        if (cert.getSubjectX500Principal().equals(issuerX500Principal)) {
          return cert.getPublicKey();
        }
      }
    }
    throw new CertificateNotTrustedException();
  }
  
  private boolean verifyUserSignedCertificate(User identifiedUser, X509Certificate certificate) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, CouldNotParse {
    boolean verified = false;
    KeyManagement keydecoder = KeyManagement.getInstance();
    if (identifiedUser.getPublicKeys() == null || identifiedUser.getPublicKeys().size() == 0) {
      identifiedUser.addPublicKey(new UserPublicKey(keydecoder.encodePublicKey(certificate.getPublicKey()), null));
      UserDBManager userDBManager = UserDBManager.getInstance();
      userDBManager.addKey(identifiedUser.getUsername(), identifiedUser.getPublicKeys().get(0));
    }
    for (UserPublicKey oldUserPublicKey : identifiedUser.getPublicKeys()) {      
      PublicKey pubKey = oldUserPublicKey.getPublicKey();
      
      verified = verifyCertificateWithPublicKey(certificate, pubKey);
      if (verified) {
        return true;
      }
    }
    throw new KeyDoesNotMatchException();
  }
  
  private boolean verifyCertificateWithPublicKey(X509Certificate certificate, PublicKey pubKey) {
    
    try {
      
      certificate.verify(pubKey);
      return true;
    } catch (Exception e) {
      return false;
    }
    
  }
  
  private boolean isUserCertificate(X509Certificate x509Certificate) {
    
    if (x509Certificate.getBasicConstraints() == -1) {
      return true;
    } else {
      return false;
    }
  }
  
  public class KeyDoesNotMatchException extends RuntimeException {
    
    private static final long serialVersionUID = -6825126254061827637L;
    
  }
  
  public class CertificateNotTrustedException extends RuntimeException {
    
    private static final long serialVersionUID = 6120670655966336971L;
    
  }
  
}
