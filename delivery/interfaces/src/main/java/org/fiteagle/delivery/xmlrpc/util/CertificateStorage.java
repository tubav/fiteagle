package org.fiteagle.delivery.xmlrpc.util;

import java.security.cert.X509Certificate;
import java.util.Map;

public  class CertificateStorage {
  
  
  private static Map<Integer, X509Certificate> certificates =  new CacheMap<>(15);
  
  
  public static void addCert(int hash, X509Certificate cert){
    certificates.put(hash, cert);
  }
  
  
  public static X509Certificate getCert(int hash){
   return certificates.get(hash);
  }
  
  
  
 
  
}
