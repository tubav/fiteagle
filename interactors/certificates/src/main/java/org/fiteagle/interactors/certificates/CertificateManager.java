package org.fiteagle.interactors.certificates;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

import org.fiteagle.core.aaa.KeyStoreManagement;
import org.fiteagle.interactors.api.CertificateManagerBoundary;

public class CertificateManager implements CertificateManagerBoundary {
  private KeyStoreManagement manager = KeyStoreManagement.getInstance();
  
  @Override
  public List<String> getTrustedCertsCommonNames() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
    return manager.getTrustedCertsCommonNames();
  }
  
  @Override
  public String getTrustedCertificate(String commonName) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
    return manager.getTrustedCertificate(commonName);
  }
}
