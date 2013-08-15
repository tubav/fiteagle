package org.fiteagle.core.aaa;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.security.auth.x500.X500Principal;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.fiteagle.core.aaa.KeyManagement.CouldNotParse;
import org.fiteagle.core.userdatabase.UserDBManager;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class AuthenticationHandlerTest {
  X509Certificate testCertificate;
  X509Certificate[] testCertificateArray;
  AuthenticationHandler authHandler;
  String knowUserCertPath = "/org/fiteagle/core/certificates/userCert.pem";
  String unknownUserCertPath = "/org/fiteagle/core/certificates/testCertUnknownUser.pem";
  String wrongPkeyCertPath =   "/org/fiteagle/core/certificates/testCertWrongPKey.pem";
  String signedByFiteagleCertPath = "/org/fiteagle/core/certificates/testCertSignedByFiteagle.pem";
  String certChainPath = "/org/fiteagle/core/certificates/chain.gid";
  String dummyPublicKey;
  X509Certificate trustedCertMock;
  
  @Before
  public void setUp() throws Exception {
   
    testCertificateArray = loadTestCert(signedByFiteagleCertPath);
    authHandler = AuthenticationHandler.getInstance();
    dummyPublicKey = "AAAAB3NzaC1yc2EAAAADAQABAAABAQDFrEGAjMHYsmOeRmBaILZ6IbVW6v5bxYK24o45DTXBW/fxmP8quGiIlGY8Q4g50t5OR+tUTn0G4XMue5ahyyMVwLFhIC5JT2E3g9E1t5QlCOUmFOYzElcOlRUipAFRoRRgY4Te+JdcF+ZTwrHMYGPwPlnTsj8e3i/l1tLeb0nzsADn8oLdnps2XPVFFTF3hTPv7du/w1ewOBfVeWdkm3ugetGs8upq/g4ijxxAcaE+iyuqNxUvq0FzvcMi+Tmr9wGQXRcK50suh2ENLjl+pTLnfJNsBLkV3zgJpAJPm2cP4AkLZhFZqHNdK2Do9wLS9hsNbnogJtNqO6qxziKyP+LH";
    trustedCertMock = EasyMock.createMock(X509Certificate.class);
    
    
  }
  
 
  
 // @Test
  public void testAuthenticateCertificates() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, InvalidAlgorithmParameterException, CertPathValidatorException, SQLException, InvalidKeySpecException, CouldNotParse {
    
    authHandler.areAuthenticatedCertificates(testCertificateArray);
    
  }
  @Ignore
  @Test
  public void testAuthenticateChain() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, InvalidAlgorithmParameterException, CertPathValidatorException, IOException, SQLException, InvalidKeySpecException, CouldNotParse{
    authHandler.areAuthenticatedCertificates(testCertificateArray);
  }
 // @Test(expected = RecordNotFoundException.class)
  public void testAuthWithUnstoredPkeyAndUntrustedCert() throws CertificateException, KeyStoreException, NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException, CertPathValidatorException, SQLException, InvalidKeySpecException, CouldNotParse {
    
    authHandler.areAuthenticatedCertificates(loadTestCert(unknownUserCertPath));
    
  }
  //@Test(expected = KeyDoesNotMatchException.class)
  public void testAuthWithWrongPkeyForUserAndUntrustedCert() throws CertificateException, KeyStoreException, NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException, CertPathValidatorException, SQLException, InvalidKeySpecException, CouldNotParse{
    authHandler.areAuthenticatedCertificates(loadTestCert(wrongPkeyCertPath));
  }
  
  // @Test
  public void testAuthWithCertByFiteagleCA() throws CertificateException, KeyStoreException, NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException, CertPathValidatorException, SQLException, InvalidKeySpecException, CouldNotParse{
    authHandler.areAuthenticatedCertificates(loadTestCert(signedByFiteagleCertPath));
    
  }
  @Ignore
  @Test
  public void testAddUserByTrustedCert() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, InvalidAlgorithmParameterException, CertPathValidatorException, InvalidKeySpecException, CouldNotParse, IOException, SQLException, InvalidKeyException, NoSuchProviderException, SignatureException{
    X500Principal subjectPrince = new X500Principal("CN=test");
    X500Principal issuerPrince = new X500Principal("CN=av.tu-berlin.de, OU=AV, O=TU Berlin, L=Berlin, ST=Berlin, C=DE");
    String urn = "urn:publicid:IDN+av.tu-berlin.de+user+test";
    Collection<List<?>> subjectAlternativeNames = createSubjectAlternativeNamesCollection(urn);
    PublicKey exptectedPkey = getTrustedCertPkey();
    EasyMock.expect(trustedCertMock.getIssuerX500Principal()).andReturn(issuerPrince);
    EasyMock.expectLastCall().anyTimes();
    EasyMock.expect(trustedCertMock.getSubjectX500Principal()).andReturn(subjectPrince);
    EasyMock.expectLastCall().times(3);
    trustedCertMock.verify(exptectedPkey);
    EasyMock.expectLastCall().andAnswer(new IAnswer(){
      public Object answer() {return null;};
    });
    EasyMock.expect(trustedCertMock.getSubjectAlternativeNames()).andReturn(subjectAlternativeNames);
    EasyMock.replay(trustedCertMock);
    authHandler.areAuthenticatedCertificates(new X509Certificate[]{trustedCertMock});
    UserDBManager dbManager = UserDBManager.getInstance();
    Assert.assertTrue(dbManager.get("test@av.tu-berlin.de")!= null);
  }
  private Collection<List<?>> createSubjectAlternativeNamesCollection(String urn) {
    List uris = new ArrayList();
    uris.add(6);
    uris.add(urn);
    Collection<List<?>> collection = new HashSet<>();
    collection.add(uris);
    return collection;
  }



  private PublicKey getTrustedCertPkey() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
    KeyStoreManagement keyStoreManagement = KeyStoreManagement.getInstance();
    List<X509Certificate>certs =  keyStoreManagement.getTrustedCerts();
    for(X509Certificate cert: certs){
      if(cert.getSubjectX500Principal().equals(new X500Principal("CN=av.tu-berlin.de, OU=AV, O=TU Berlin, L=Berlin, ST=Berlin, C=DE"))){
        return cert.getPublicKey();
      }
    }
    return null;
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
