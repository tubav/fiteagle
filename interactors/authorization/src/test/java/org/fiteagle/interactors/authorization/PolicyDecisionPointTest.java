package org.fiteagle.interactors.authorization;

import org.fiteagle.interactors.api.PolicyEnforcementPointBoundary;
import org.junit.Test;

import com.sun.xacml.Indenter;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;

public class PolicyDecisionPointTest {
  
  PolicyEnforcementPointBoundary policyEnforcementPoint = PolicyEnforcementPoint.getInstance();
  PolicyDecisionPoint policyDecisionPoint = PolicyDecisionPoint.getInstance();
  
//  @Test  
//  public void evaluate() throws Exception {  
//    ResponseCtx response = policyEnforcementPoint.createRequest("mitjanikolaus@localhost");
//    
//    String currentPath = System.getProperty("user.dir");
//    String pathToPolicy = currentPath+"/src/main/resources/Policy.xml";   
//    
//    ResponseCtx response = policyDecisionPoint.evaluateRequest(request, pathToPolicy);
//    response.encode(System.out, new Indenter());
//  }

}
