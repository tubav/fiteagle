package org.fiteagle.interactors.sfa.register;

import java.util.HashMap;

import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;

public class RegisterRequestProcessor extends SFAv3RequestProcessor{

  @Override
  public AMResult processRequest(ListCredentials credentials, Object... specificArgs) {
    
    throw new NotImplemented();
  }
  
  public HashMap<String, Object> register(HashMap<String, Object> parameters){
    return null;
    
  }
  
  public class NotImplemented extends RuntimeException{
    private static final long serialVersionUID = 1L;
  }
}
