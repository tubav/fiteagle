package org.fiteagle.interactors.sfa.provision;

import java.util.ArrayList;

import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.describe.DescribeValue;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class ProvisionRequestProcessor extends SFAv3RequestProcessor{

  
  public ProvisionResult processRequest(ArrayList<String> urns, ListCredentials credentials, ProvisionOptions provisionOptions) {
    ProvisionResult result = getResult(urns, credentials, provisionOptions);
    return result;
  }
  
  private ProvisionResult getResult(ArrayList<String> urns, ListCredentials credentials, ProvisionOptions provisionOptions) {
    String value = "";
    String output = "";
    AMCode returnCode = null;
    
    Authorization auth = new Authorization();
    
    auth.checkCredentialsList(credentials);
    
   ProvisionResult result = new ProvisionResult();
    
    if(!auth.areCredentialTypeAndVersionValid()){
      returnCode=auth.getReturnCode();
      output=auth.getAuthorizationFailMessage();
      result.setCode(returnCode);
      result.setOutput(output);
      return result;
    }
    //TODO: check options!!!
    
  //TODO: process the correct request..
    returnCode = getSuccessReturnCode();
    
    result.setCode(returnCode);
    result.setValue(getTestProvisionResultValue());
    return result;
    
  }

  private ProvisionValue getTestProvisionResultValue() {
    
    SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
    DescribeValue describeValue = translator.getDescription(new ArrayList<String>());
    ProvisionValue provisionValue = new ProvisionValue();
    provisionValue.setGeni_rspec(describeValue.getGeni_rspec());
    provisionValue.setGeni_slivers(describeValue.getGeni_slivers());
    
    return provisionValue;
  }

  @Override
  public AMResult processRequest(ListCredentials credentials, Object... specificArgs) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
