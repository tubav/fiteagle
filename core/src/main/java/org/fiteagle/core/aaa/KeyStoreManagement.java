package org.fiteagle.core.aaa;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyStoreManagement {
private FiteaglePreferences preferences;
private Logger log = LoggerFactory.getLogger(getClass()); 
public KeyStoreManagement(){
  this.preferences = new FiteaglePreferencesXML(this.getClass());
}
  

protected KeyStore loadKeyStore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
    if(preferences == null)
      createPreferences();
    
    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
    FileInputStream fis = new FileInputStream(getKeyStorePath());
    char[] pass = getPassword();
    ks.load(fis, pass);
    return ks;
  }
  
  void createPreferences() {
    preferences = new FiteaglePreferencesXML(getClass());
    
  }


  protected void storeCertificate(String alias, X509Certificate cert) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
    //TODO load truststore for storage of certificates
    KeyStore ks = loadKeyStore();
    ks.setCertificateEntry(alias, cert);
    FileOutputStream fos = new FileOutputStream(getKeyStorePath());
    char[] pass = getPassword();
    ks.store(fos, pass);
    
  }
  
  private String getCAAlias() {
    if(preferences == null)
         createPreferences();
  
    String alias = preferences.get("CAAlias");
    return alias;
  }
  
  
  private String getKeyStorePath() {
    return preferences.get("keystore");
  }

  private char[] getPassword() {
    return preferences.get("keystore_pass").toCharArray();
  }
  protected PrivateKey getCAPrivateKey() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableEntryException {
    PrivateKey privateKey = null;
    KeyStore  ks = loadKeyStore();
    PasswordProtection protection = new PasswordProtection(getPassword());
    Entry keyStoreEntry = ks.getEntry(getCAAlias(), protection);
    if(keyStoreEntry instanceof PrivateKeyEntry){
         PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry)keyStoreEntry;
         privateKey = privateKeyEntry.getPrivateKey();
      
    }else {
      throw new PrivateKeyException();
    }
    return privateKey;
  }
  
  protected X509Certificate getCACert() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException  {
    KeyStore ks = loadKeyStore();
    X509Certificate caCert = (X509Certificate) ks.getCertificate(getCAAlias());
    return caCert;
  }
  
  protected List<X509Certificate> getTrustedCerts() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
   
    KeyStore ks = loadKeyStore();
    List<X509Certificate> certificateList = new LinkedList<>();
    
    for(Enumeration<String> aliases=  ks.aliases(); aliases.hasMoreElements();){
            certificateList.add((X509Certificate)(ks.getCertificate(aliases.nextElement())));
    }
   
    return certificateList;
  }
  public class PrivateKeyException extends RuntimeException {
    private static final long serialVersionUID = 2842186524464171483L;
    
  }
 
  
  
}
