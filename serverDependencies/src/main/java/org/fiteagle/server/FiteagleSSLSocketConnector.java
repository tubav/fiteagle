package org.fiteagle.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.mortbay.jetty.security.Password;
import org.mortbay.jetty.security.SslSocketConnector;
import org.mortbay.log.Log;
import org.mortbay.resource.Resource;

public class FiteagleSSLSocketConnector extends SslSocketConnector {
  private boolean _allowRenegotiate =false;
  private String _truststore;
  private String _keystore;
  private String _truststoreType="JKS";
  private String _keystoreType="JKS";
  private transient Password _password;

  private transient Password _keyPassword;
  private transient Password _trustPassword;
 
  private String _secureRandomAlgorithm;
  private String _protocol="TLS";
  private String _provider;
  private String _sslKeyManagerFactoryAlgorithm = (Security.getProperty("ssl.KeyManagerFactory.algorithm")==null?"SunX509":Security.getProperty("ssl.KeyManagerFactory.algorithm")); // cert algorithm
  private String _sslTrustManagerFactoryAlgorithm = (Security.getProperty("ssl.TrustManagerFactory.algorithm")==null?"SunX509":Security.getProperty("ssl.TrustManagerFactory.algorithm")); // cert algorithm
//  public FiteagleSSLSocketConnector() {
//      super();
//  }
  private boolean _wantClientAuth = true;
  private boolean _needClientAuth = false;
  private String _excludeCipherSuites[];
  
  @Override
  protected SSLServerSocketFactory createFactory() 
      throws Exception
  {
      if (_truststore==null)
      {
          _truststore=_keystore;
          _truststoreType=_keystoreType;
      }

      _provider = "SunJSSE";
      KeyManager[] keyManagers = null;
      InputStream keystoreInputStream = null;
      if (_keystore != null)
        keystoreInputStream = Resource.newResource(_keystore).getInputStream();
      KeyStore keyStore = KeyStore.getInstance(_keystoreType);
      keyStore.load(keystoreInputStream, _password==null?null:_password.toString().toCharArray());

      KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(_sslKeyManagerFactoryAlgorithm);        
      keyManagerFactory.init(keyStore,_keyPassword==null?null:_keyPassword.toString().toCharArray());
      keyManagers = keyManagerFactory.getKeyManagers();

      TrustManager[] trustManagers = null;
      InputStream truststoreInputStream = null;
      if (_truststore != null)
        truststoreInputStream = Resource.newResource(_truststore).getInputStream();
      KeyStore trustStore = KeyStore.getInstance(_truststoreType);
      trustStore.load(truststoreInputStream,_trustPassword==null?null:_trustPassword.toString().toCharArray());
      
      TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(_sslTrustManagerFactoryAlgorithm);
      trustManagerFactory.init(trustStore);
      trustManagers = trustManagerFactory.getTrustManagers();
      for(int i = 0; i< trustManagers.length;i++){
        if(trustManagers[i] instanceof X509TrustManager){
          trustManagers[i] = new FiteagleTrustmanager();
        }
      }
 
      SecureRandom secureRandom = _secureRandomAlgorithm==null?null:SecureRandom.getInstance(_secureRandomAlgorithm);

      SSLContext context = _provider==null?SSLContext.getInstance(_protocol):SSLContext.getInstance(_protocol, _provider);
      SSLContext.setDefault(context);
      context.init(keyManagers, trustManagers, secureRandom);

      return context.getServerSocketFactory();
  }
  
  /* ------------------------------------------------------------ */
  public String getKeystore()
  {
      return _keystore;
  }

  /* ------------------------------------------------------------ */
  public String getKeystoreType() 
  {
      return (_keystoreType);
  }

  /* ------------------------------------------------------------ */
  public String getProvider() {
return _provider;
  }

  /* ------------------------------------------------------------ */
  public String getSecureRandomAlgorithm() 
  {
      return (this._secureRandomAlgorithm);
  }
  /* ------------------------------------------------------------ */
  public String getSslKeyManagerFactoryAlgorithm() 
  {
      return (this._sslKeyManagerFactoryAlgorithm);
  }

  /* ------------------------------------------------------------ */
  public String getSslTrustManagerFactoryAlgorithm() 
  {
      return (this._sslTrustManagerFactoryAlgorithm);
  }

  /* ------------------------------------------------------------ */
  public String getTruststore()
  {
      return _truststore;
  }

  /* ------------------------------------------------------------ */
  public String getTruststoreType()
  {
      return _truststoreType;
  }
  /* ------------------------------------------------------------ */
  public void setKeyPassword(String password)
  {
      _keyPassword = Password.getPassword(KEYPASSWORD_PROPERTY,password,null);
  }

  /* ------------------------------------------------------------ */
  /**
   * @param keystore The resource path to the keystore, or null for built in keystores.
   */
  public void setKeystore(String keystore)
  {
      _keystore = keystore;
  }

  /* ------------------------------------------------------------ */
  public void setKeystoreType(String keystoreType) 
  {
      _keystoreType = keystoreType;
  }

  /* ------------------------------------------------------------ */
  public void setPassword(String password)
  {
      _password = Password.getPassword(PASSWORD_PROPERTY,password,null);
  }
  
  /* ------------------------------------------------------------ */
  public void setTrustPassword(String password)
  {
      _trustPassword = Password.getPassword(PASSWORD_PROPERTY,password,null);
  }


  /* ------------------------------------------------------------ */
  public void setProvider(String _provider) {
this._provider = _provider;
  }

  /* ------------------------------------------------------------ */
  public void setSecureRandomAlgorithm(String algorithm) 
  {
      this._secureRandomAlgorithm = algorithm;
  }

  /* ------------------------------------------------------------ */
  public void setSslKeyManagerFactoryAlgorithm(String algorithm) 
  {
      this._sslKeyManagerFactoryAlgorithm = algorithm;
  }
  
  /* ------------------------------------------------------------ */
  public void setSslTrustManagerFactoryAlgorithm(String algorithm) 
  {
      this._sslTrustManagerFactoryAlgorithm = algorithm;
  }


  public void setTruststore(String truststore)
  {
      _truststore = truststore;
  }
  

 
  public void setTruststoreType(String truststoreType)
  {
      _truststoreType = truststoreType;
  }
  
  public void accept(int acceptorID)
      throws IOException, InterruptedException
  {   
      try
      {
          Socket socket = _serverSocket.accept();
          configure(socket);

          Connection connection=new SslConnection(socket);
          connection.dispatch();
      }
      catch(SSLException e)
      {
          Log.warn(e);
          try
          {
              stop();
          }
          catch(Exception e2)
          {
              Log.warn(e2);
              throw new IllegalStateException(e2.getMessage());
          }
      }
  }
  
  /* ------------------------------------------------------------ */
  protected void configure(Socket socket)
      throws IOException
  {   
      super.configure(socket);
  }

  protected ServerSocket newServerSocket(String host, int port,int backlog) throws IOException
  {
      SSLServerSocketFactory factory = null;
      SSLServerSocket socket = null;

      try
      {
          factory = createFactory();

          socket = (SSLServerSocket) (host==null?
                          factory.createServerSocket(port,backlog):
                          factory.createServerSocket(port,backlog,InetAddress.getByName(host)));

          if (_wantClientAuth)
              socket.setWantClientAuth(_wantClientAuth);
          if (_needClientAuth)
              socket.setNeedClientAuth(_needClientAuth);

          if (_excludeCipherSuites!= null && _excludeCipherSuites.length >0) 
          {
              List excludedCSList = Arrays.asList(_excludeCipherSuites);
              String[] enabledCipherSuites = socket.getEnabledCipherSuites();
            List enabledCSList = new ArrayList(Arrays.asList(enabledCipherSuites));
            Iterator exIter = excludedCSList.iterator();

              while (exIter.hasNext())
            {
                String cipherName = (String)exIter.next();
                  if (enabledCSList.contains(cipherName))
                  {
                      enabledCSList.remove(cipherName);
                  }
            }
              enabledCipherSuites = (String[])enabledCSList.toArray(new String[enabledCSList.size()]);

              socket.setEnabledCipherSuites(enabledCipherSuites);
          }
          
      }
      catch (IOException e)
      {
          throw e;
      }
      catch (Exception e)
      {
          Log.warn(e.toString());
          Log.debug(e);
          throw new IOException("!JsseListener: " + e);
      }
      
      return socket;
  }
  public class SslConnection extends Connection
  {
      public SslConnection(Socket socket) throws IOException
      {
          super(socket);
      }
      
      public void run()
      {
          try
          {
              int handshakeTimeout = getHandshakeTimeout();
              int oldTimeout = _socket.getSoTimeout();
              if (handshakeTimeout > 0)            
                  _socket.setSoTimeout(handshakeTimeout);

              final SSLSocket ssl=(SSLSocket)_socket;
              ssl.addHandshakeCompletedListener(new HandshakeCompletedListener()
              {
                  boolean handshook=false;
                  public void handshakeCompleted(HandshakeCompletedEvent event)
                  {
                      if (handshook)
                      {
                          if (!_allowRenegotiate)
                          {
                              Log.warn("SSL renegotiate denied: "+ssl);
                              try{ssl.close();}catch(IOException e){Log.warn(e);}
                          }
                      }
                      else
                          handshook=true;
                  }
              });
              ssl.startHandshake();

              if (handshakeTimeout>0)
                  _socket.setSoTimeout(oldTimeout);

              super.run();
          }
          catch (SSLException e)
          {
              Log.warn(e); 
              try{close();}
              catch(IOException e2){Log.ignore(e2);}
          }
          catch (IOException e)
          {
              Log.debug(e);
              try{close();}
              catch(IOException e2){Log.ignore(e2);}
          } 
      }
  }

  
  
  
 
}
