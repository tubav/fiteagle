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
private final String DEFAULT_KEYSTORE_LOCATION=System.getProperty("user.home")+System.getProperty("file.separator")+"fiteagle"+System.getProperty("file.separator")+"jetty-ssl.keystore";
private final String DEFAULT_KEYSTORE_PASSWORD = "jetty6";
private final String DEFAULT_CA_ALIAS ="root";

private static KeyStoreManagement keyStoreManagement;
public static KeyStoreManagement getInstance(){
  if(keyStoreManagement == null)
    keyStoreManagement = new KeyStoreManagement();
  return keyStoreManagement;
  
}
private KeyStoreManagement(){
  this.preferences = new FiteaglePreferencesXML(this.getClass());
  if(preferences.get("CAAlias") == null){
    preferences.put("CAAlias", DEFAULT_CA_ALIAS);
  }
  if(preferences.get("keystore_pass") == null){
    preferences.put("keystore_pass", DEFAULT_KEYSTORE_PASSWORD);
  }
  if(preferences.get("keystore") == null){
    preferences.put("keystore", DEFAULT_KEYSTORE_LOCATION);
  }
  
}
  

protected KeyStore loadKeyStore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
   
    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
    FileInputStream fis = new FileInputStream(getKeyStorePath());
    char[] pass = getPassword();
    ks.load(fis, pass);
    return ks;
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
  public X509Certificate getStoredCertificate(String alias) {
    try {
      KeyStore ks = loadKeyStore();
      return (X509Certificate) ks.getCertificate(alias);
    } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
      throw new CertificateNotFoundException();
    }
  }
 
  public class CertificateNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3514307715237455008L;
    
  }
  
  
}
