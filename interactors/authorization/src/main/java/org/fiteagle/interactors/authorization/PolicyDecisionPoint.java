package org.fiteagle.interactors.authorization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.xacml.PDP;
import com.sun.xacml.PDPConfig;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.ctx.Result;
import com.sun.xacml.finder.PolicyFinder;
import com.sun.xacml.finder.impl.FilePolicyModule;

public class PolicyDecisionPoint {
  
  private Logger log = LoggerFactory.getLogger(getClass());
  
  private static PolicyDecisionPoint instance = new PolicyDecisionPoint();
  
  private PDP pdp;
  
  public static PolicyDecisionPoint getInstance(){
    return instance;
  }
  
  private PolicyDecisionPoint(){
    URL url = this.getClass().getClassLoader().getResource("Policy.xml");
    String pathToPolicy = url.getPath();
    
    if(url.getPath().contains("jar")){
      //TODO: not the best solution
      log.info("Loading Policy.xml from jar");
      try {
        InputStream is = url.openStream();
        BufferedReader input = new BufferedReader(new InputStreamReader(is));
        File file = new File("Policy.xml");
        file.deleteOnExit();
        FileOutputStream outputStream = new FileOutputStream(file);
        
        int ch = 0;
        while((ch = input.read()) != -1){
          outputStream.write(ch);
        }
        
        outputStream.close();
        input.close();
        is.close();
        
        pathToPolicy = "Policy.xml";
      } catch (IOException e) {
       log.error(e.getMessage());
      }
    }
    
    
    FilePolicyModule filePolicyModule = new FilePolicyModule();
    filePolicyModule.addPolicy(pathToPolicy);
      
    PolicyFinder policyFinder = new PolicyFinder();
    Set<FilePolicyModule> policyModules = new HashSet<FilePolicyModule>();
    policyModules.add(filePolicyModule);
    policyFinder.setModules(policyModules);

    pdp = new PDP(new PDPConfig(null, policyFinder, null));
  };
  
  public boolean evaluateRequest(RequestCtx request) {
    ResponseCtx response = pdp.evaluate(request);
    Result result = (Result) response.getResults().toArray()[0];
    if(result.getDecision() == Result.DECISION_PERMIT){
      return true;
    }
    return false;
  }
  
}
