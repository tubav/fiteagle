package org.fiteagle.core.aaa.x509;

import java.io.ByteArrayOutputStream;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.security.auth.x500.X500Principal;

import org.fiteagle.core.util.URN;
import org.fiteagle.core.util.URN.URNParsingException;

import net.iharder.Base64;

public class X509Util {
  
  public static String getUserNameFromX509Certificate(X509Certificate cert) throws CertificateParsingException {
    String username = "";
    URN urn = null;
    try{
    	 urn = getURN(cert);
    }catch(URNParsingException e){
    	
    }
   
    if (urn == null) {
      X500Principal prince = cert.getSubjectX500Principal();
      username = X509Util.getUserNameFromPrinicpal(prince);
    } else {
      urn = getURN(cert);
      username = urn.getSubjectAtDomain();
    }
    return username;
  }
  
  public static String getUserNameFromPrinicpal(X500Principal prince) {
    String username = "";
    String uuid = prince.getName();
    LdapName ldapDN = getLdapName(uuid);
    
    for (Rdn rdn : ldapDN.getRdns()) {
      if (rdn.getType().equals("CN")) {
        String fullCN = (String) rdn.getValue();
        if (fullCN.contains(".")) {
          username = fullCN.split("\\.")[fullCN.split("\\.").length - 1];
        } else {
          username = fullCN;
        }
        return username;
      }
    }
    throw new NonParsableNamingFormat();
  }
  
  private static LdapName getLdapName(String uuid) {
    try {
      LdapName ldapDN = new LdapName(uuid);
      return ldapDN;
    } catch (InvalidNameException e) {
      
      throw new NonParsableNamingFormat();
    }
    
  }
  
  
  private static class NonParsableNamingFormat extends RuntimeException {
    
    private static final long serialVersionUID = -3819932831236493248L;
    
  }
  
  public static URN getURN(X509Certificate cert) throws CertificateParsingException {
    URN urn = null;
    Collection<List<?>> alternativeNames = cert.getSubjectAlternativeNames();
    if (alternativeNames != null) {
      Iterator<List<?>> it = alternativeNames.iterator();
      while (it.hasNext()) {
        List<?> altName = it.next();
        if (altName.get(0).equals(Integer.valueOf(6))) {
          urn = new URN((String) altName.get(1));
        }
      }
    }
    return urn;
  }
  
  public static String getCertficateEncoded(X509Certificate cert) throws Exception {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    
    bout.write(Base64.encodeBytesToBytes(cert.getEncoded(), 0, cert.getEncoded().length, Base64.NO_OPTIONS));
    String prefix = "-----BEGIN CERTIFICATE-----\n";
    String suffix = "\n-----END CERTIFICATE-----\n";
    String encodedCert = new String(bout.toByteArray());
    bout.close();
    int i = 0;
    String returnCert="";
    while(i< encodedCert.length()){
      int max = i +64;
      if(max < encodedCert.length()){
        returnCert =returnCert +  encodedCert.subSequence(i, max)+"\n";
      }else{
        returnCert = returnCert + encodedCert.subSequence(i, encodedCert.length());
      }
      i+=64;
    }
    returnCert = prefix + returnCert + suffix;
    return returnCert;
  }
  
  public static String getCertificateBodyEncoded(X509Certificate cert) throws Exception {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    bout.write(Base64.encodeBytesToBytes(cert.getEncoded(), 0, cert.getEncoded().length, Base64.NO_OPTIONS));
    String encodedCert = new String(bout.toByteArray());
    bout.close();
    return encodedCert;
  }
  
  public static boolean isSelfSigned(X509Certificate xCert) {
	    return xCert.getIssuerX500Principal().equals(xCert.getSubjectX500Principal());
	  }
}