package org.fiteagle.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import javax.net.ssl.SSLServerSocketFactory;

public class FiteagleSSLServerSocketFactory extends SSLServerSocketFactory {
  
  @Override
  public String[] getDefaultCipherSuites() {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public String[] getSupportedCipherSuites() {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public ServerSocket createServerSocket(int arg0) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public ServerSocket createServerSocket(int arg0, int arg1) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public ServerSocket createServerSocket(int arg0, int arg1, InetAddress arg2) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }
  
}
