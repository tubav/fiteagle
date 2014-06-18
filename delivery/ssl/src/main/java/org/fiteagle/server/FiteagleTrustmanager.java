package org.fiteagle.server;


import java.io.IOException;
import java.net.Socket;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;

import org.fiteagle.core.aaa.AuthenticationHandler;
import org.fiteagle.core.aaa.x509.X509Util;
import org.fiteagle.core.config.InterfaceConfiguration;
import org.fiteagle.core.userdatabase.JPAUserDB.UserNotFoundException;
import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserPublicKey;
import org.fiteagle.interactors.usermanagement.UserManager;
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
     if(authenticationHandler.areAuthenticatedCertificates(chain)){
    	 addClientIfNotExists(chain[0]);
     }
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

  private void addClientIfNotExists(X509Certificate x509Certificate) throws CertificateParsingException, User.NotEnoughAttributesException, IOException {
    UserManager usermanager = UserManager.getInstance();
	String username = X509Util.getUserNameFromX509Certificate(x509Certificate);
	
	String domain = InterfaceConfiguration.getInstance().getDomain();
	if(username.contains("@") && username.split("@")[1].equals(domain)){
		try{
			usermanager.get(username);
		}catch(UserNotFoundException e){
			User newUser = User.createDefaultUser(username);
			newUser.addPublicKey(new UserPublicKey(x509Certificate.getPublicKey(), "Certificate PublicKey"));
			usermanager.add(newUser);
		}
	}
  }
  
  public class ServerException extends RuntimeException {

    private static final long serialVersionUID = 3696231034900651182L;
    
  }
  
}
