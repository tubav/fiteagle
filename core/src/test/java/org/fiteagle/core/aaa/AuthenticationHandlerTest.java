package org.fiteagle.core.aaa;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.fiteagle.core.userdatabase.User;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationHandlerTest {
  X509Certificate testCertificate;
  X509Certificate[] testCertificateArray;
  AuthenticationHandler authHandler;
  User testUser;
  String dummyPublicKey;
  @Before
  public void setUp() throws Exception {
    InputStream fis = this.getClass().getResourceAsStream("/org/fiteagle/core/certificates/userCert.pem");
    BufferedInputStream bis = new BufferedInputStream(fis);

    CertificateFactory cf = CertificateFactory.getInstance("X.509");

   
    testCertificate= (X509Certificate)cf.generateCertificate(bis);
    
    testCertificateArray = new X509Certificate[]{testCertificate};
    authHandler = new AuthenticationHandler();
    dummyPublicKey = "AAAAB3NzaC1yc2EAAAADAQABAAABAQDFrEGAjMHYsmOeRmBaILZ6IbVW6v5bxYK24o45DTXBW/fxmP8quGiIlGY8Q4g50t5OR+tUTn0G4XMue5ahyyMVwLFhIC5JT2E3g9E1t5QlCOUmFOYzElcOlRUipAFRoRRgY4Te+JdcF+ZTwrHMYGPwPlnTsj8e3i/l1tLeb0nzsADn8oLdnps2XPVFFTF3hTPv7du/w1ewOBfVeWdkm3ugetGs8upq/g4ijxxAcaE+iyuqNxUvq0FzvcMi+Tmr9wGQXRcK50suh2ENLjl+pTLnfJNsBLkV3zgJpAJPm2cP4AkLZhFZqHNdK2Do9wLS9hsNbnogJtNqO6qxziKyP+LH";
    List<String> dummyPubKeys = new ArrayList<>(); 
    dummyPubKeys.add(dummyPublicKey);
    testUser  = new User("fiteagle.av.test", "test", "testUser",dummyPubKeys );
  
  }
  
  @Test 
  public void testVerifyCertificateWithPublicKey(){
    Assert.assertTrue(authHandler.verifyCertificateWithPublicKey(testCertificate, dummyPublicKey));
  }
 
 
  
  @Test
  public void testAuthenticateCertificates() {
    
    authHandler.authenticateCertificates(testCertificateArray);
    
  }
  
}
