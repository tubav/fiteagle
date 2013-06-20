package org.fiteagle.core.aaa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStrictStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.openssl.PEMWriter;
import org.fiteagle.core.aaa.x509.X509Util;
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
private final String DEFAULT_CA_PRK_PASS = "jetty6";
private final String DEFAULT_PRK_PASS= "jetty6";
private final String DEFAULT_TRUSTSTORE_LOCATION= System.getProperty("user.home")+System.getProperty("file.separator")+"fiteagle"+System.getProperty("file.separator")+"jetty-ssl.truststore";
private final String DEFAULT_TRUSTSTORE_PASSWORD =  DEFAULT_KEYSTORE_PASSWORD;
private final String DEFAULT_SERVER_ALIAS ="root";
private final String DEFAULT_SA_ALIAS="fiteagleSA";
private final String DEFAULT_SA_PASS="changeit";
private final String DEFAULT_RESOURCE_STORE_LOCATION = System.getProperty("user.home")+System.getProperty("file.separator")+"fiteagle"+System.getProperty("file.separator")+"resourceCertificateStore";
private final String DEFAULT_RESOURCE_STORE_PASS = "changeit";
private static KeyStoreManagement keyStoreManagement;

private enum StoreType{
  KEYSTORE,TRUSTSTORE,RESOURCESTORE;
}
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
  if(preferences.get("prk_pass") == null){
    preferences.put("prk_pass", DEFAULT_PRK_PASS);
  }
  if(preferences.get("truststore") == null){
    preferences.put("truststore", DEFAULT_TRUSTSTORE_LOCATION);
  }
  if(preferences.get("truststore_pass") == null){
    preferences.put("truststore_pass", DEFAULT_TRUSTSTORE_PASSWORD);
  }
  if(preferences.get("server_alias")== null){
    preferences.put("server_alias", DEFAULT_SERVER_ALIAS);
  }
  if(preferences.get("ca_prkey_pass")==null){
    preferences.put("ca_prkey_pass", DEFAULT_CA_PRK_PASS);
  }
  if(preferences.get("SAAlias")==null)
    preferences.put("SAAlias", DEFAULT_SA_ALIAS);

  if(preferences.get("sa_prkey_pass")==null)
    preferences.put("sa_prkey_pass", DEFAULT_SA_PASS);
  
  if(preferences.get("resourceStore")==null)
    preferences.put("resourceStore", DEFAULT_RESOURCE_STORE_LOCATION);
  
  if(preferences.get("resourceStore_pass")==null)
    preferences.put("resourceStore_pass", DEFAULT_RESOURCE_STORE_PASS);
  
}
 

protected KeyStore loadKeyStore(StoreType type) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
  KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
  FileInputStream fis = new FileInputStream(getStorePath(type));
  char[] pass = getStorePass(type);
  ks.load(fis, pass);
  return ks;
}

  private char[] getStorePass(StoreType type) {
    switch(type) {
      case KEYSTORE:
        return getKeyStorePassword();
  
      case TRUSTSTORE:
        return getTrustStorePassword();
        
      case RESOURCESTORE:
        return getResourceStorePass();
      
      default:
        return getTrustStorePassword();
      }
}
  private String getStorePath(StoreType type) {
    switch(type) {
    case KEYSTORE:
      return getKeyStorePath();
    case TRUSTSTORE:
      return getTrustStorePath();
      
    case RESOURCESTORE:
      return getResourceStorePath();
    
    default:
      return getTrustStorePath();
    }
 }
  private char[] getTrustStorePassword() {
    return preferences.get("truststore_pass").toCharArray();
}
  private String getTrustStorePath() {
    return preferences.get("truststore");
}
  protected void storeCertificate(String alias, X509Certificate cert, StoreType type) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
    KeyStore ks = loadKeyStore(type);
    ks.setCertificateEntry(alias, cert);
    FileOutputStream fos = new FileOutputStream(getStorePath(type));
    char[] pass = getStorePass(type);
    ks.store(fos, pass);
    
  }
  
  private String getCAAlias() {
    String alias = preferences.get("CAAlias");
    return alias;
  }
  
  
  private String getKeyStorePath() {
    return preferences.get("keystore");
  }

  public char[] getPrivateKeyPassword(){
    return preferences.get("prk_pass").toCharArray();
  }
  private char[] getKeyStorePassword() {
    return preferences.get("keystore_pass").toCharArray();
  }
  public PrivateKey getCAPrivateKey() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableEntryException {
    PrivateKey privateKey = null;
    KeyStore  ks = loadKeyStore(StoreType.TRUSTSTORE);
    PasswordProtection protection = new PasswordProtection(getCAPrivateKeyPassword());
    Entry keyStoreEntry = ks.getEntry(getCAAlias(), protection);
    if(keyStoreEntry instanceof PrivateKeyEntry){
         PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry)keyStoreEntry;
         privateKey = privateKeyEntry.getPrivateKey();
      
    }else {
      throw new PrivateKeyException();
    }
    return privateKey;
  }
  
  private char[] getCAPrivateKeyPassword() {
    return preferences.get("ca_prkey_pass").toCharArray();
  }
  protected X509Certificate getCACert() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException  {
    KeyStore ks = loadKeyStore(StoreType.TRUSTSTORE);
    X509Certificate caCert = (X509Certificate) ks.getCertificate(getCAAlias());
    return caCert;
  }
  
  protected List<X509Certificate> getTrustedCerts() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
   
    KeyStore ks = loadKeyStore(StoreType.TRUSTSTORE);
    List<X509Certificate> certificateList = new LinkedList<>();
    
    for(Enumeration<String> aliases=  ks.aliases(); aliases.hasMoreElements();){
            certificateList.add((X509Certificate)(ks.getCertificate(aliases.nextElement())));
    }
   
    return certificateList;
  }
  
  public List<String> getTrustedCertsCommonNames() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
    List<X509Certificate> certs;
    certs = getTrustedCerts();    
     
    List<String> certsAsStrings = new ArrayList<String>();
    for(X509Certificate cert : certs){     
      certsAsStrings.add(getCertificateCommonName(cert));
    }
    
    return certsAsStrings;
  }
  
  private String getCertificateCommonName(X509Certificate cert) throws CertificateEncodingException{
    X500Name x500name = new JcaX509CertificateHolder(cert).getSubject();
    RDN commonName = x500name.getRDNs(BCStrictStyle.CN)[0];
   return IETFUtils.valueToString(commonName.getFirst().getValue());
  }
  
  public String getTrustedCertificate(String commonName) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
    for(X509Certificate cert : getTrustedCerts()){
      if(getCertificateCommonName(cert).equals(commonName)){
        return convertToPem(cert);
      }
    }
    return null;
  }
  
  private String convertToPem(X509Certificate cert) throws CertificateEncodingException, IOException{
    StringWriter sw = new StringWriter();
    PEMWriter pemwriter = new PEMWriter(sw);
    pemwriter.writeObject(cert);
    pemwriter.close();
    
    return sw.toString();
  }
 
  public X509Certificate[] getStoredCertificate(String alias) {
    try {
      KeyStore ks = loadKeyStore(StoreType.KEYSTORE);
      Certificate[] certChain =  ks.getCertificateChain(alias);
      X509Certificate[] returnChain = new X509Certificate[certChain.length];
      for(int i = 0; i<certChain.length; i++){
        returnChain[i] = (X509Certificate) certChain[i];
      }
      return returnChain;
    } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
      throw new CertificateNotFoundException();
    }
  }
 

  public PrivateKey getServerPrivateKey() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableEntryException {
    PrivateKey privateKey = null;
    KeyStore  ks = loadKeyStore(StoreType.KEYSTORE);
    PasswordProtection protection = new PasswordProtection(getPrivateKeyPassword());
    Entry keyStoreEntry = ks.getEntry(getServerAlias(), protection);
    if(keyStoreEntry instanceof PrivateKeyEntry){
         PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry)keyStoreEntry;
         privateKey = privateKeyEntry.getPrivateKey();
      
    }else {
      throw new PrivateKeyException();
    }
    return privateKey;
  }
  private String getServerAlias() {
    return preferences.get("server_alias");
  }
  public X509Certificate getSliceAuthorityCert() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
      String sa_alias = preferences.get("SAAlias");
      X509Certificate cert = (X509Certificate) loadKeyStore(StoreType.TRUSTSTORE).getCertificate(sa_alias);
      return cert;
  }
  public Key getSAPrivateKey() throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException, UnrecoverableEntryException {
    PrivateKey privateKey = null;
    KeyStore  ks = loadKeyStore(StoreType.TRUSTSTORE);
    PasswordProtection protection = new PasswordProtection(getSA_PrivateKeyPassword());
    Entry keyStoreEntry = ks.getEntry(getSAAlias(), protection);
    if(keyStoreEntry instanceof PrivateKeyEntry){
         PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry)keyStoreEntry;
         privateKey = privateKeyEntry.getPrivateKey();
      
    }else {
      throw new PrivateKeyException();
    }
    return privateKey;
  }
  private String getSAAlias() {
    return preferences.get("SAAlias");
  }
  private char[] getSA_PrivateKeyPassword() {
    return preferences.get("sa_prkey_pass").toCharArray();
  }
  public X509Certificate getResourceCertificate(String alias) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
    KeyStore userCertStore = loadKeyStore(StoreType.RESOURCESTORE);
    if(userCertStore.containsAlias(alias)){
      return (X509Certificate) userCertStore.getCertificate(alias);
    }
    throw new CertificateNotFoundException();
  }
  private char[] getResourceStorePass() {
    return preferences.get("resourceStore_pass").toCharArray();
  }
  private String getResourceStorePath() {
    return preferences.get("resourceStore");
  }
  public void storeResourceCertificate(X509Certificate cert) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
    String alias  = X509Util.getURN(cert);
    storeCertificate(alias, cert, StoreType.RESOURCESTORE);
  }
  
  public class PrivateKeyException extends RuntimeException {
    private static final long serialVersionUID = 2842186524464171483L;
    
  }
  public class CertificateNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3514307715237455008L;
    
  }
  public List<X509Certificate> getResourceCertificates(List<String> urns) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
   KeyStore ks = loadKeyStore(StoreType.RESOURCESTORE);
   List<X509Certificate> certificates = new LinkedList<>();
   for(String urn: urns){
     if(ks.containsAlias(urn))
       certificates.add((X509Certificate) ks.getCertificate(urn));
   }
   
   return certificates;
  }
  
}
