package org.fiteagle.interactors.sfa.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniSlivers;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
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
    
    
    //process the correct request..
    returnCode = getReturnCode(GENI_CodeEnum.SUCCESS);
    
    result.setCode(returnCode);
    result.setValue(getResultStatusValue(urns));
    return result;
  }
  private StatusValue getResultStatusValue(List<String> urns) {

    SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
    ResourceAdapterManager resourceManager = ResourceAdapterManager.getInstance();
    ArrayList<GeniSlivers> slivers = new ArrayList<GeniSlivers>();
    
    String instaNceId = translator.getIdFromSliverUrn(urns.get(0)); 
    //Group group = GroupDBManager.getInstance().getGroup(instaNceId);

    for (Iterator iterator = urns.iterator(); iterator.hasNext();) {
      String urn = (String) iterator.next();
      String id=translator.getIdFromSliverUrn(urn);
     
      ResourceAdapter resourceAdapter = resourceManager.getResourceAdapterInstance(instaNceId);
      GeniSlivers tmpSliver = new GeniSlivers();
      tmpSliver.setGeni_sliver_urn(translator.translateResourceIdToSliverUrn(resourceAdapter.getId(),urn));
      tmpSliver.setGeni_allocation_status((String)resourceAdapter.getProperties().get("allocation_status"));//TODO: set right status information over translator(implement this in translator)
      tmpSliver.setGeni_operational_status((String)resourceAdapter.getProperties().get("operational_status"));
      //TODO: expires????!!!
      //TODO error(optional)??
      slivers.add(tmpSliver);
      
    }
    
      StatusValue statusResultValue = new StatusValue();
   
      statusResultValue.setGeni_slivers(slivers );
      
      return statusResultValue;
    }



  @Override
  public AMResult processRequest(ListCredentials credentials, Object... specificArgs) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
