package org.fiteagle.core.aaa;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.SQLException;
import java.util.List;

import javax.security.auth.x500.X500Principal;

import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserDBManager;
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
  
  public void authenticateCertificates(X509Certificate[] certificates) throws KeyStoreException,
      NoSuchAlgorithmException, CertificateException, IOException, InvalidAlgorithmParameterException,
      CertPathValidatorException, SQLException {
    
    X509Certificate cert = certificates[0];
    if (cert.getSubjectX500Principal().equals(cert.getIssuerX500Principal())) {
      
      UserDBManager userDBManager = UserDBManager.getInstance();
      User identifiedUser = userDBManager.getUserFromCert(cert);
      verifyUserSignedCertificate(identifiedUser, cert);
      
    } else {
      
      isValid(0, certificates, certificates[0].getSubjectX500Principal());
      // X500Principal issuerX500Principal = cert.getIssuerX500Principal();
      // PublicKey issuerPublicKey = getTrustedIssuerPublicKey(issuerX500Principal);
      //
      // verifyCertificateWithPublicKey(cert, issuerPublicKey);
    }
    
  }
  
  
  private boolean isValid(int i, X509Certificate[] certificates, X500Principal x500Principal) throws KeyStoreException,
      NoSuchAlgorithmException, CertificateException, IOException {
    if (i < certificates.length && x500Principal.equals(certificates[i].getSubjectX500Principal())) {
      
      if (i == certificates.length - 1) {
        return isTrustworthy(certificates[i]);
      } else {
        return isValid(i + 1, certificates, certificates[i].getIssuerX500Principal());
      }
      
    } else {
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
  
  private void verifyUserSignedCertificate(User identifiedUser, X509Certificate certificate) throws IOException {
    boolean verified = false;
    KeyManagement keydecoder = KeyManagement.getInstance();
    if (identifiedUser.getPublicKeys() == null || identifiedUser.getPublicKeys().size() == 0) {
      identifiedUser.addPublicKey(keydecoder.encodePublicKey(certificate.getPublicKey()));
    }
    for (String pubKeyString : identifiedUser.getPublicKeys()) {
      
      PublicKey pubKey = null;
      try {
        pubKey = keydecoder.decodePublicKey(pubKeyString);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      verified = verifyCertificateWithPublicKey(certificate, pubKey);
      if (verified) {
        return;
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
