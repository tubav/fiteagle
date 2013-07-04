package org.fiteagle.server;


import java.io.IOException;
import java.net.Socket;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;

import org.fiteagle.core.aaa.AuthenticationHandler;
import org.mortbay.log.Log;

public class FiteagleTrustmanager extends X509ExtendedTrustManager {


  private AuthenticationHandler authenticationHandler;
  public FiteagleTrustmanager(){
    this.authenticationHandler = AuthenticationHandler.getInstance();
  }
  @Override
  public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
   Log.debug("checkClientTrusted 1");
    
  }

  @Override
  public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    Log.debug("server trusted ");
    
  }

  @Override
  public X509Certificate[] getAcceptedIssuers() {
    Log.debug("accepted Issuers");
    return new X509Certificate[]{};
  }

  @Override
  public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {
    Log.debug("checkClientTrusted 2");
    try{
      authenticationHandler.authenticateCertificates(chain);
    }catch(InvalidKeySpecException | IOException | KeyStoreException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | CertPathValidatorException | SQLException  e){
      throw new ServerException();
    }
    
  }

  @Override
  public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
      throws CertificateException {
    Log.debug("checkClientTrusted 3");
    
  }

  @Override
  public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {
    Log.debug("checkserver trusted 2");
    
  }

  @Override
  public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
      throws CertificateException {
    Log.debug("server trusted 3");
    
  }

  public class ServerException extends RuntimeException {

    private static final long serialVersionUID = 3696231034900651182L;
    
  }
  
}
