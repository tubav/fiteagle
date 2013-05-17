package org.fiteagle.core.aaa;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.sql.SQLException;

import org.fiteagle.core.aaa.AuthenticationHandler.KeyDoesNotMatchException;
import org.fiteagle.core.userdatabase.UserDB.RecordNotFoundException;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationHandlerTest {
  X509Certificate testCertificate;
  X509Certificate[] testCertificateArray;
  AuthenticationHandler authHandler;
  String knowUserCertPath = "/org/fiteagle/core/certificates/userCert.pem";
  String unknownUserCertPath = "/org/fiteagle/core/certificates/testCertUnknownUser.pem";
  String wrongPkeyCertPath =   "/org/fiteagle/core/certificates/testCertWrongPKey.pem";
  String signedByFiteagleCertPath = "/org/fiteagle/core/certificates/testCertSignedByFiteagle.pem";
  String dummyPublicKey;
  
  @Before
  public void setUp() throws Exception {
   
    testCertificateArray = loadTestCert(knowUserCertPath);
    authHandler = new AuthenticationHandler();
    dummyPublicKey = "AAAAB3NzaC1yc2EAAAADAQABAAABAQDFrEGAjMHYsmOeRmBaILZ6IbVW6v5bxYK24o45DTXBW/fxmP8quGiIlGY8Q4g50t5OR+tUTn0G4XMue5ahyyMVwLFhIC5JT2E3g9E1t5QlCOUmFOYzElcOlRUipAFRoRRgY4Te+JdcF+ZTwrHMYGPwPlnTsj8e3i/l1tLeb0nzsADn8oLdnps2XPVFFTF3hTPv7du/w1ewOBfVeWdkm3ugetGs8upq/g4ijxxAcaE+iyuqNxUvq0FzvcMi+Tmr9wGQXRcK50suh2ENLjl+pTLnfJNsBLkV3zgJpAJPm2cP4AkLZhFZqHNdK2Do9wLS9hsNbnogJtNqO6qxziKyP+LH";
  
  }
  
 
  
 // @Test
  public void testAuthenticateCertificates() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, InvalidAlgorithmParameterException, CertPathValidatorException, SQLException {
    
    authHandler.authenticateCertificates(testCertificateArray);
    
  }
//  @Test(expected = RecordNotFoundException.class)
  public void testAuthWithUnstoredPkeyAndUntrustedCert() throws CertificateException, KeyStoreException, NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException, CertPathValidatorException, SQLException {
    
    authHandler.authenticateCertificates(loadTestCert(unknownUserCertPath));
    
  }
  //@Test(expected = KeyDoesNotMatchException.class)
  public void testAuthWithWrongPkeyForUserAndUntrustedCert() throws CertificateException, KeyStoreException, NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException, CertPathValidatorException, SQLException{
    authHandler.authenticateCertificates(loadTestCert(wrongPkeyCertPath));
  }
  
  @Test
  public void testAuthWithCertByFiteagleCA() throws CertificateException, KeyStoreException, NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException, CertPathValidatorException, SQLException{
    authHandler.authenticateCertificates(loadTestCert(signedByFiteagleCertPath));
    
  }
  
  private X509Certificate[] loadTestCert(String path) throws CertificateException{
    InputStream fis = this.getClass().getResourceAsStream(path);
    BufferedInputStream bis = new BufferedInputStream(fis);
    CertificateFactory cf = CertificateFactory.getInstance("X.509");
    X509Certificate cert = (X509Certificate) cf.generateCertificate(bis);
    X509Certificate[] certArray = new X509Certificate[]{cert};
    return certArray;
  }
}
