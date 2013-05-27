package org.fiteagle.interactors.sfa.provision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.Group;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.GENISliverAllocationState;
import org.fiteagle.interactors.sfa.common.GENISliverOperationalState;
import org.fiteagle.interactors.sfa.common.GeniSlivers;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.describe.DescribeValue;
import org.fiteagle.interactors.sfa.rspec.RSpecContents;
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
    
    if(urns.size()>1);
    if(urns.size()==1 && urns.get(0).contains("\\+sliver\\+"));
  //TODO: implement if there are one or multiple sliver urns not only one slice urn
    
    SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
    ResourceAdapterManager resourceManager = new ResourceAdapterManager();
    Group group = resourceManager.getGroup(urns.get(0));
    
    ArrayList<ResourceAdapter> resources = group.getResources();
    
    for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
      ResourceAdapter resourceAdapter = (ResourceAdapter) iterator.next();
      if(resourceAdapter.getStatus().compareToIgnoreCase("geni_allocated")==0){
        HashMap<String, Object> props = resourceAdapter.getProperties();
        props.put("operational_status", GENISliverOperationalState.geni_configuring);
        resourceAdapter.setProperties(props);
        resourceAdapter.configure();
        props.put("operational_status", GENISliverOperationalState.geni_ready);
        props.put("allocation_status", GENISliverAllocationState.geni_provisioned);
        
//        TODO:!!!!!!!!!!!!!!!!! where to store status???? PROPS!!!!!!!?????!!!!!!
      }
    }
    
    
    returnCode = getSuccessReturnCode();
    
    result.setCode(returnCode);
    result.setValue(getTestProvisionResultValue());
    return result;
    
  }
  
  private ProvisionValue getProvisionResultValue(String urn) {
    
    SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
    ProvisionValue resultValue = new ProvisionValue();
    ResourceAdapterManager resourceManager = new ResourceAdapterManager();
    Group group = resourceManager.getGroup(urn);
    ArrayList<GeniSlivers> slivers = new ArrayList<GeniSlivers>();
    
    ArrayList<ResourceAdapter> resources = group.getResources();
    for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
      ResourceAdapter resourceAdapter = (ResourceAdapter) iterator.next();
      GeniSlivers tmpSliver = new GeniSlivers();
      tmpSliver.setGeni_sliver_urn(translator.translateResourceIdToSliverUrn(resourceAdapter.getId(),urn));
      tmpSliver.setGeni_allocation_status(resourceAdapter.getStatus());//TODO: set right status information over translator(implement this in translator)
      //TODO: expires????!!!
      //TODO s......
      slivers.add(tmpSliver);
    }
    resultValue.setGeni_slivers(slivers);
    
    RSpecContents manifestRSpec = getManifestRSpec(resources);
    String geni_rspec = getRSpecString(manifestRSpec);
    resultValue.setGeni_rspec(geni_rspec);
    
    return resultValue;
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
