package org.fiteagle.interactors.sfa.allocate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.GeniSlivers;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.describe.DescribeResult;
import org.fiteagle.interactors.sfa.describe.DescribeValue;
import org.fiteagle.interactors.sfa.rspec.RSpecContents;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class AllocateRequestProcessor extends SFAv3RequestProcessor{

  
  public AllocateResult processRequest(String urn, ListCredentials credentials,RSpecContents requestRspec, AllocateOptions allocateOptions) {
    AllocateResult result = getResult(urn, credentials, requestRspec);
    return result;
  }
  
  
  private AllocateResult getResult(String urn, ListCredentials credentials, RSpecContents requestRspec) {
    String value = "";
    String output = "";
    AMCode returnCode = null;
    
    Authorization auth = new Authorization();
    
    auth.checkCredentialsList(credentials);
    
    AllocateResult result = new AllocateResult();
    
    if(!auth.areCredentialTypeAndVersionValid()){
      returnCode=auth.getReturnCode();
      output=auth.getAuthorizationFailMessage();
      result.setCode(returnCode);
      result.setOutput(output);
      return result;
    }
    //check options is optional!
    
  //TODO: process the correct request..
    returnCode = getSuccessReturnCode();
    
    result.setCode(returnCode);
    result.setValue(getTestResultValue(urn, requestRspec));
    return result;
  }


  private AllocateValue getTestResultValue(String urn, RSpecContents requestRspec) {
  //TODO: static test values only.
    SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
    DescribeValue describeValue = translator.getDescription(new ArrayList<String>());
    
    AllocateValue resultValue = new AllocateValue();
    
    ArrayList<GeniSlivers> slivers = new ArrayList<GeniSlivers>();
    
    List<GeniSlivers> testList = describeValue.getGeni_slivers();
    for (Iterator iterator = testList.iterator(); iterator.hasNext();) {
      GeniSlivers geniSlivers = (GeniSlivers) iterator.next();
      //for allocate other geni sliver attributes are not allowed!!!!!!
      GeniSlivers tmpSlivers = new GeniSlivers();
      tmpSlivers.setGeni_sliver_urn(geniSlivers.getGeni_sliver_urn());
      tmpSlivers.setGeni_expires(geniSlivers.getGeni_expires());
      tmpSlivers.setGeni_allocation_status(geniSlivers.getGeni_allocation_status());
      slivers.add(tmpSlivers);
    }
    
    resultValue.setGeni_slivers(slivers );
    resultValue.setGeni_rspec(describeValue.getGeni_rspec());
    
    return resultValue;
    
  }


  @Override
  public AMResult processRequest(ListCredentials credentials, Object... specificArgs) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
