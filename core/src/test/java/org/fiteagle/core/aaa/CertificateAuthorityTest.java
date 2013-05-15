package org.fiteagle.core.aaa;

import java.security.cert.X509Certificate;

import junit.framework.Assert;

import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserDBManager;
import org.junit.Before;
import org.junit.Test;

public class CertificateAuthorityTest {
  CertificateAuthority CA ;
  UserDBManager um;
  User dummyUser ;
  @Before
  public void setUp() throws Exception {
    CA = new CertificateAuthority();
    um = new UserDBManager();
    dummyUser = um.get("fiteagle.av.test");
  }
  
 
  @Test
  public void testCreateCertificateForDummyUser() throws Exception {
    X509Certificate userCert =   CA.createCertificate(dummyUser);
    System.out.println(userCert);
    Assert.assertTrue(userCert != null);   
  }
  
//  @Test 
//  public void testWriteCertificate() throws Exception{
//    X509Certificate cert = CA.createCertificate(dummyUser);
//    CA.saveCertificate("acceptedCert.pem", cert);
//  }
//  
}
