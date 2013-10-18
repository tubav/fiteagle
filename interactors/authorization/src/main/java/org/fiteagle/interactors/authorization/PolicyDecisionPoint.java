package org.fiteagle.interactors.authorization;

import java.util.HashSet;
import java.util.Set;

import com.sun.xacml.Indenter;
import com.sun.xacml.PDP;
import com.sun.xacml.PDPConfig;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.finder.PolicyFinder;
import com.sun.xacml.finder.impl.FilePolicyModule;

public class PolicyDecisionPoint {
  
  private static PolicyDecisionPoint instance = new PolicyDecisionPoint();
  
  private FilePolicyModule filePolicyModule;
  
  public static PolicyDecisionPoint getInstance(){
    return instance;
  }
  
  private PolicyDecisionPoint(){
    String pathToPolicy = PolicyEnforcementPoint.class.getClassLoader().getResource("Policy.xml").getPath();
    filePolicyModule = new FilePolicyModule();
//  for (int i = 0; i < policyFiles.length; i++)
      filePolicyModule.addPolicy(pathToPolicy);
  };
  
  public boolean evaluateRequest(RequestCtx request) {
   
    

    PolicyFinder policyFinder = new PolicyFinder();
    Set<FilePolicyModule> policyModules = new HashSet<FilePolicyModule>();
    policyModules.add(filePolicyModule);
    policyFinder.setModules(policyModules);

    PDP pdp = new PDP(new PDPConfig(null, policyFinder, null));

    ResponseCtx response = pdp.evaluate(request);

//    return response.getResults().;
    response.encode(System.out, new Indenter());

    return true;
  }
  
}
