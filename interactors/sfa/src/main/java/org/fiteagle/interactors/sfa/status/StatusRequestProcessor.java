package org.fiteagle.interactors.sfa.status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.GeniSlivers;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.describe.DescribeValue;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class StatusRequestProcessor extends SFAv3RequestProcessor {

  private StatusOptionsService optionsService;


  public StatusResult processRequest(List<String> urns, ListCredentials credentials, Object... specificArgs) {
    StatusOptions options = (StatusOptions) specificArgs[0];
    StatusResult result = getResult(urns, credentials, options);
    return result;
  }


  private StatusResult getResult(List<String> urns, ListCredentials credentials, StatusOptions options) {
    String value = "";
    String output = "";
    AMCode returnCode = null;
    
    Authorization auth = new Authorization();
    
    auth.checkCredentialsList(credentials);
    
    StatusResult result = new StatusResult();
    
    if(!auth.areCredentialTypeAndVersionValid()){
      returnCode=auth.getReturnCode();
      output=auth.getAuthorizationFailMessage();
      result.setCode(returnCode);
      result.setOutput(output);
      return result;
    }
    
    this.optionsService=new StatusOptionsService(options);
    
    optionsService.checkOptions();
    if(!optionsService.areOptionsValid()){
      //TODO: check options
    }
    
    
    //TODO: process the correct request..
    returnCode = getSuccessReturnCode();
    
    result.setCode(returnCode);
    result.setValue(getTestResultStatusValue(urns));
    return result;
  }


  private StatusValue getTestResultStatusValue(List<String> urns) {
  //TODO: static test values only.
    
    StatusValue testStatusResultValue = new StatusValue();
    
    testStatusResultValue.setGeni_urn("urn:publicid:IDN+fiteagle:av+slice+myslice");
    
    SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
    DescribeValue describeValue = translator.getDescription(urns);
    
//    ArrayList<GeniSlivers> slivers = new ArrayList<GeniSlivers>();
//    
//    List<GeniSlivers> testList = describeValue.getGeni_slivers();
//    for (Iterator iterator = testList.iterator(); iterator.hasNext();) {
//      GeniSlivers geniSlivers = (GeniSlivers) iterator.next();
//      //for status other geni sliver attributes are not allowed!!!!!!
//      slivers.add(geniSlivers);
//    }
    
    
    List<GeniSlivers> slivers = describeValue.getGeni_slivers();
    
    testStatusResultValue.setGeni_slivers(slivers );
    
    return testStatusResultValue;
  }


  @Override
  public AMResult processRequest(ListCredentials credentials, Object... specificArgs) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
