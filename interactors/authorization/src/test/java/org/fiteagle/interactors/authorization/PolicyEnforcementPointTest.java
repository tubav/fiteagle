package org.fiteagle.interactors.authorization;


import org.junit.Assert;
import org.junit.Test;

public class PolicyEnforcementPointTest {
  
  PolicyEnforcementPoint policyEnforcementPoint = PolicyEnforcementPoint.getInstance();
  
  @Test
  public void authorizeRequestWithoutAction() throws Exception{
    Assert.assertFalse(policyEnforcementPoint.isRequestAuthorized("anyUser", "mnikolaus", null, "user", true));
    Assert.assertFalse(policyEnforcementPoint.isRequestAuthorized("anyUser", "mnikolaus", "", "user", true));
  }
  
  @Test
  public void authorizePUTRequest() throws Exception{
    Assert.assertTrue(policyEnforcementPoint.isRequestAuthorized("anyUser", "mnikolaus", "PUT", "user", false));
  }
  
  @Test
  public void authorizeGETRequestSameIDs() throws Exception{
    Assert.assertTrue(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "mnikolaus", "GET", "user", true));
  }
  
  @Test
  public void authorizeGETRequestDifferntIDs() throws Exception{
    Assert.assertFalse(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "anotherUser", "GET", "user", true));
  }
  
  @Test
  public void authorizePOSTRequestSameIDsAdmin() throws Exception{
    Assert.assertTrue(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "mnikolaus", "POST", "admin", true));
  }
  
  @Test
  public void authorizeDELETERequestDifferntIDsAdmin() throws Exception{
    Assert.assertTrue(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "anotherUser", "DELETE", "admin", true));
  }
  
  @Test
  public void authorizeGETRequestWithoutAuthentication() throws Exception{
    Assert.assertFalse(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "mnikolaus", "GET", "user", false));
  }
  
  @Test
  public void authorizeGETRequestWithoutAuthenticationAdmin() throws Exception{
    Assert.assertFalse(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "anotherUser", "GET", "admin", false));
  }
}
