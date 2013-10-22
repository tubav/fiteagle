package org.fiteagle.interactors.authorization;

import java.util.HashSet;
import java.util.Set;

import com.sun.xacml.Indenter;
import com.sun.xacml.PDP;
import com.sun.xacml.PDPConfig;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.ctx.Result;
import com.sun.xacml.finder.PolicyFinder;
import com.sun.xacml.finder.impl.FilePolicyModule;

public class PolicyDecisionPoint {
  
  private static PolicyDecisionPoint instance = new PolicyDecisionPoint();
  
  private PDP pdp;
  
  public static PolicyDecisionPoint getInstance(){
    return instance;
  }
  
  private PolicyDecisionPoint(){
    String userDir = System.getProperty("user.dir");
    String[] splitted = userDir.split("fiteagle");
    String pathToPolicy = splitted[0]+"fiteagle/interactors/authorization/src/main/resources/Policy.xml";
    
    FilePolicyModule filePolicyModule = new FilePolicyModule();
//  for (int i = 0; i < policyFiles.length; i++)
      filePolicyModule.addPolicy(pathToPolicy);
      
    PolicyFinder policyFinder = new PolicyFinder();
    Set<FilePolicyModule> policyModules = new HashSet<FilePolicyModule>();
    policyModules.add(filePolicyModule);
    policyFinder.setModules(policyModules);

    pdp = new PDP(new PDPConfig(null, policyFinder, null));
  };
  
  public boolean evaluateRequest(RequestCtx request) {
    ResponseCtx response = pdp.evaluate(request);
    
    response.encode(System.out, new Indenter());
    
    Result result = (Result) response.getResults().toArray()[0];
    if(result.getDecision() == Result.DECISION_PERMIT){
      return true;
    }
    return false;
  }
  
}
