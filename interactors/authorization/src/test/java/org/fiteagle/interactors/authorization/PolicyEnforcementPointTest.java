package org.fiteagle.interactors.authorization;


import org.junit.Assert;
import org.junit.Test;

public class PolicyEnforcementPointTest {
  
  PolicyEnforcementPoint policyEnforcementPoint = PolicyEnforcementPoint.getInstance();
  
  @Test
  public void authorizeRequestWithoutAction() throws Exception{
    Assert.assertFalse(policyEnforcementPoint.isRequestAuthorized("anyUser", "mnikolaus", null, "user"));
    Assert.assertFalse(policyEnforcementPoint.isRequestAuthorized("anyUser", "mnikolaus", "", "user"));
  }
  
  @Test
  public void authorizePutRequest() throws Exception{
    Assert.assertTrue(policyEnforcementPoint.isRequestAuthorized("anyUser", "mnikolaus", "PUT", "user"));
  }
  
  @Test
  public void authorizeGETRequestSameIDs() throws Exception{
    Assert.assertTrue(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "mnikolaus", "GET", "user"));
  }
  
  @Test
  public void authorizeGETRequestDifferntIDs() throws Exception{
    Assert.assertFalse(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "anotherUser", "GET", "user"));
  }
  
  @Test
  public void authorizePOSTRequestSameIDsAdmin() throws Exception{
    Assert.assertTrue(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "mnikolaus", "POST", "admin"));
  }
  
  @Test
  public void authorizeDELETERequestDifferntIDsAdmin() throws Exception{
    Assert.assertTrue(policyEnforcementPoint.isRequestAuthorized("mnikolaus", "anotherUser", "DELETE", "admin"));
  }
  
}
