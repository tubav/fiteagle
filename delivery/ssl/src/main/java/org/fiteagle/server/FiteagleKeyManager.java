package org.fiteagle.server;

import java.io.IOException;
import java.net.Socket;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509ExtendedKeyManager;

import org.fiteagle.core.aaa.KeyStoreManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FiteagleKeyManager extends X509ExtendedKeyManager {

  private Logger log = LoggerFactory.getLogger(getClass()); 
  
  @Override
  public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket) {
    log.info("client alias");
    for(String s: keyType){
      log.info(s);
    }
    return null;
  }

  @Override
  public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
    return "root";
  }

  @Override
  public X509Certificate[] getCertificateChain(String alias) {
    KeyStoreManagement keyStoreManager = KeyStoreManagement.getInstance();
    X509Certificate[] certChain =  keyStoreManager.getStoredCertificate(alias);
    return certChain;
  }

  @Override
  public String[] getClientAliases(String keyType, Principal[] issuers) {
    log.info("getClientAlias "+ keyType);
    return null;
  }

  @Override
  public PrivateKey getPrivateKey(String alias) {
    KeyStoreManagement keyStoreManager = KeyStoreManagement.getInstance();
    
    try {
      return keyStoreManager.getServerPrivateKey();
    } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | UnrecoverableEntryException
        | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String[] getServerAliases(String keyType, Principal[] issuers) {
    log.info("get server aliases for "+ keyType);
    return null;
  }
  
}
