package org.fiteagle.interactors.authorization;


import org.junit.Test;

import com.sun.xacml.Indenter;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;

public class PolicyEnforcementPointTest {
  
  PolicyEnforcementPoint policyEnforcementPoint = PolicyEnforcementPoint.getInstance();
  
  @Test
  public void createRequestTest() throws Exception{
    RequestCtx request = policyEnforcementPoint.createRequest("mnikolaus");
    
    request.encode(System.out, new Indenter());
  }
  
  @Test
  public void authorizeRequestTest() throws Exception{
    Boolean response = policyEnforcementPoint.isRequestAuthorized("mnikolaus");
  }
  
}
