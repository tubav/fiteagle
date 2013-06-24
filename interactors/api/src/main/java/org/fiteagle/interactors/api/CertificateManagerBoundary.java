package org.fiteagle.interactors.api;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

public interface CertificateManagerBoundary {
  
  public abstract List<String> getTrustedCertsCommonNames() throws KeyStoreException, NoSuchAlgorithmException,
      CertificateException, IOException;
  
  public abstract String getTrustedCertificate(String commonName) throws KeyStoreException, NoSuchAlgorithmException,
      CertificateException, IOException;
  
  public abstract String getAllTrustedCertificates() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException;
}