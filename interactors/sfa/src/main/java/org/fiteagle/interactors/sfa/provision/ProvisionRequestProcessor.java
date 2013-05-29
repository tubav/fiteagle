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
    //TODO: check options
    
  //process the correct request..
    
    if(urns.size()>1);
    if(urns.size()==1 && urns.get(0).contains("\\+sliver\\+"));
  //TODO: implement if there are one or multiple sliver urns not only one slice urn
    
    SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
    ResourceAdapterManager resourceManager = ResourceAdapterManager.getInstance();
    Group group = resourceManager.getGroup(urns.get(0));
    
    ArrayList<GeniSlivers> slivers = new ArrayList<GeniSlivers>();
    ProvisionValue resultValue = new ProvisionValue();
    
    ArrayList<ResourceAdapter> resources = group.getResources();
    
    for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
      ResourceAdapter resourceAdapter = (ResourceAdapter) iterator.next();
      if(resourceAdapter.getStatus().compareToIgnoreCase(GENISliverAllocationState.geni_allocated.toString())==0){
        HashMap<String, Object> props = resourceAdapter.getProperties();
        props.put("operational_status", GENISliverOperationalState.geni_configuring.toString());
        resourceAdapter.setProperties(props);
        resourceAdapter.configure();
        props.put("operational_status", GENISliverOperationalState.geni_ready.toString());
        props.put("allocation_status", GENISliverAllocationState.geni_provisioned.toString());
        resourceAdapter.setProperties(props);
        
        GeniSlivers tmpSliver = new GeniSlivers();
        tmpSliver.setGeni_sliver_urn(translator.translateResourceIdToSliverUrn(resourceAdapter.getId(),urns.get(0)));
        tmpSliver.setGeni_allocation_status((String)resourceAdapter.getProperties().get("allocation_status"));
        tmpSliver.setGeni_operational_status((String)resourceAdapter.getProperties().get("operational_status"));
        //TODO: expires????!!!
        //TODO error(optional)??
        slivers.add(tmpSliver);
        
      }
    }
    
    resultValue.setGeni_slivers(slivers);
    
    RSpecContents manifestRSpec = getManifestRSpec(resources);
    String geni_rspec = getRSpecString(manifestRSpec);
    resultValue.setGeni_rspec(geni_rspec);
    
    
    
    returnCode = getSuccessReturnCode();
    
    result.setCode(returnCode);
    result.setValue(resultValue);
    return result;
    
  }
  
  @Override
  public AMResult processRequest(ListCredentials credentials, Object... specificArgs) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
