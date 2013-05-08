package org.fiteagle.server;

import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;

import org.fiteagle.core.aaa.AuthenticationHandler;
import org.mortbay.log.Log;

public class FiteagleTrustmanager extends X509ExtendedTrustManager {


  private AuthenticationHandler authenticationHandler;
  public FiteagleTrustmanager(){
    this.authenticationHandler = new AuthenticationHandler();
  }
  @Override
  public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
   Log.info("checkClientTrusted 1");
    
  }

  @Override
  public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public X509Certificate[] getAcceptedIssuers() {
    // TODO Auto-generated method stub
    return new X509Certificate[]{};
  }

  @Override
  public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {
    Log.info("checkClientTrusted 2");
    authenticationHandler.authenticateCertificates(chain);
   
    
  }

  @Override
  public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
      throws CertificateException {
    Log.info("checkClientTrusted 3");
    
  }

  @Override
  public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
      throws CertificateException {
    // TODO Auto-generated method stub
    
  }

  
}
