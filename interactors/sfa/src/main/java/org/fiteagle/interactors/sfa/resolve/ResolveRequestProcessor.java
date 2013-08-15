package org.fiteagle.interactors.sfa.resolve;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.fiteagle.core.aaa.KeyStoreManagement;
import org.fiteagle.core.aaa.x509.X509Util;
import org.fiteagle.core.config.InterfaceConfiguration;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResolveRequestProcessor extends SFAv3RequestProcessor {
  private Logger log = LoggerFactory.getLogger(getClass());
  
  @Override
  public AMResult processRequest(ListCredentials credentials, Object... specificArgs) {
    
    throw new RuntimeException("Not Implemented");
  }
  
  public List<Map<String, Object>> resolve(String hrn, String credential) {
    List<Map<String, Object>> returnList = new LinkedList<>();
    try {
      for (X509Certificate cert : getCertificateByHRN(hrn)) {
        ResolveResult result = buildResult(cert, credential);
        
        returnList.add(result.toMap());
      }
      
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return returnList;
  }
  
  private List<X509Certificate> getCertificateByHRN(String hrn) throws KeyStoreException, NoSuchAlgorithmException,
      CertificateException, IOException {
    InterfaceConfiguration config = InterfaceConfiguration.getInstance();
    List<X509Certificate> certificates = new LinkedList<>();
    String[] splitHRN = hrn.split("\\.");
    String domain = "";
    if (splitHRN.length > 1) {
      for (int i = 0; i < (splitHRN.length) - 1; i++) {
        if (i < splitHRN.length - 2) {
          domain += splitHRN[i] + ":";
        } else {
          domain += splitHRN[i];
        }
      }
    }
    if (domain == "")
      domain = config.getDomain();
    
    String name = splitHRN[splitHRN.length - 1];
    String userURN = config.getURN_Prefix() + "+" + domain + "+user+" + name;
    String sliceURN = config.getURN_Prefix() + "+" + domain + "+slice+" + name;
    String authorityURN = config.getURN_Prefix() + "+" + domain + "+authority+" + name;
    List<String> urns = new LinkedList<>();
    urns.add(userURN);
    urns.add(sliceURN);
    urns.add(authorityURN);
    KeyStoreManagement keyStoreManagement = KeyStoreManagement.getInstance();
    certificates = keyStoreManagement.getResourceCertificates(urns);
    return certificates;
    
  }
  
  private ResolveResult buildResult(X509Certificate cert, String credential) throws Exception {
    ResolveResult result = new ResolveResult();
    // result.setAuthority(getAuthority());
    // result.setClasstype(getClassType());
    // result.setDate_created(getDateCreated());
    // result.setEmail(getEmail());
    // result.setGid(getGid());
    // result.setHrn(getHRN());
    // result.setLast_updated(getLastUpdated());
    // result.setPeer_authority(getPeerAuthority());
    // result.setPointer(getPointer());
    // result.setRecord_id(getRecordID());
    // result.setReg_Keys(getRegKeys());
    // result.setReg_Pi_Authorities(getRegPiAuthorites());
    // result.setReg_Slices(getRegSlices());
    // result.setReg_Urn(getRegURN());
    result.setGid(X509Util.getCertficateEncoded(cert));
    result.setType(X509Util.getURN(cert).getType());
    return result;
  }
  
  
  private String getRegURN() {
    // TODO Auto-generated method stub
    return null;
  }
  
  private List<String> getRegSlices() {
    // TODO Auto-generated method stub
    return null;
  }
  
  private List<String> getRegPiAuthorites() {
    // TODO Auto-generated method stub
    return null;
  }
  
  private List<String> getRegKeys() {
    // TODO Auto-generated method stub
    return null;
  }
  
  private int getRecordID() {
    // TODO Auto-generated method stub
    return 0;
  }
  
  private int getPointer() {
    // TODO Auto-generated method stub
    return 0;
  }
  
  private String getPeerAuthority() {
    return null;
  }
  
  private Date getLastUpdated() {
    // TODO Auto-generated method stub
    return null;
  }
  
  private String getHRN() {
    // TODO Auto-generated method stub
    return null;
  }
  
  private String getGid(String urn) {
    KeyStoreManagement keyStoreManagement = KeyStoreManagement.getInstance();
    
    try {
      X509Certificate cert = keyStoreManagement.getResourceCertificate(urn);
      return X509Util.getCertficateEncoded(cert);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  private String getEmail() {
    // TODO Auto-generated method stub
    return null;
  }
  
  private Date getDateCreated() {
    // TODO Auto-generated method stub
    return null;
  }
  
  private String getClassType() {
    // TODO Auto-generated method stub
    return null;
  }
  
  private String getAuthority() {
    // TODO Auto-generated method stub
    return null;
  }
  
}
