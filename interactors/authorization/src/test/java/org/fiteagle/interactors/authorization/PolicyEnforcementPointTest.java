package org.fiteagle.interactors.authorization;


import org.junit.Assert;
import org.junit.Test;

public class PolicyEnforcementPointTest {
  
  PolicyEnforcementPoint policyEnforcementPoint = PolicyEnforcementPoint.getInstance();
  
  @Test
  public void authorizeRequestWithoutAction() throws Exception{
    Assert.assertFalse(policyEnforcementPoint.isRequestAuthorized("anyUser", "mnikolaus", null, "USER", true, false));
    Assert.assertFalse(policyEnforcementPoint.isRequestAuthorized("anyUser", "mnikolaus", "", "USER", true, false));
  }
  
  @Test
  public void authorizePUTRequest() throws Exception{
    Assert.assertTrue(policyEnforcementPoint.isRequestAuthorized("anyUser", "mnikolaus", "PUT", "USER", false, false));
  }
  
  @Test
  public void authorizeGETRequestSameIDs() throws Exception{
    Assert.assertTrue(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "mnikolaus", "GET", "USER", true, false));
  }
  
  @Test
  public void authorizeGETRequestDifferntIDs() throws Exception{
    Assert.assertFalse(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "anotherUser", "GET", "USER", true, false));
  }
  
  @Test
  public void authorizePOSTRequestSameIDsAdmin() throws Exception{
    Assert.assertTrue(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "mnikolaus", "POST", "ADMIN", true, false));
  }
  
  @Test
  public void authorizeDELETERequestDifferntIDsAdmin() throws Exception{
    Assert.assertTrue(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "anotherUser", "DELETE", "ADMIN", true, false));
  }
  
  @Test
  public void authorizeGETRequestWithoutAuthentication() throws Exception{
    Assert.assertFalse(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "mnikolaus", "GET", "USER", false, false));
  }
  
  @Test
  public void authorizeGETRequestWithoutAuthenticationAdmin() throws Exception{
    Assert.assertFalse(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "anotherUser", "GET", "ADMIN", false, false));
  }
  
  @Test
  public void authorizePOSTRequestWhichRequiresAdminRightsAsAdmin() throws Exception{
    Assert.assertTrue(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "anotherUser", "POST", "ADMIN", true, true));
  }
  
  @Test
  public void authorizePOSTRequestWhichRequiresAdminRightsAsUser() throws Exception{
    Assert.assertFalse(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "mnikolaus", "POST", "USER", false, true));
  }
}
