package org.fiteagle.interactors.sfa.delete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.ResourceAdapterManager.ResourceNotFound;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.groupmanagement.GroupDBManager.GroupNotFound;
import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.GENISliverAllocationState;
import org.fiteagle.interactors.sfa.common.GENISliverOperationalState;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniSlivers;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.describe.DescribeValue;
import org.fiteagle.interactors.sfa.provision.ProvisionOptions;
import org.fiteagle.interactors.sfa.provision.ProvisionResult;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class DeleteRequestProcessor extends SFAv3RequestProcessor {
  
  private ResourceAdapterManager resourceManager;
  private GENI_CodeEnum code = GENI_CodeEnum.SUCCESS;
private GroupDBManager groupManager;
  
public DeleteResult processRequest(List<String> urns, ListCredentials credentials, DeleteOptions deleteOptions) {
    DeleteResult result = getResult(urns, credentials, deleteOptions);
    return result;
  }
  
  private DeleteResult getResult(List<String> urns, ListCredentials credentials, DeleteOptions deleteOptions) {
    
    String output = "";
    AMCode returnCode = null;
    
    Authorization auth = new Authorization();
    
    auth.checkCredentialsList(credentials);
    
    DeleteResult result = new DeleteResult();
    
    if (!auth.areCredentialTypeAndVersionValid()) {
      returnCode = auth.getReturnCode();
      output = auth.getAuthorizationFailMessage();
      result.setCode(returnCode);
      result.setOutput(output);
      return result;
    }
    result.setValue(getDeleteResultValue(urns));
    returnCode = getReturnCode(code);
    
    result.setCode(returnCode);
   
    return result;
  }
  
  private ArrayList<GeniSlivers> getDeleteResultValue(List<String> urns) {
    SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
    //TODO: the urn is a slice urn..
    String test = urns.get(0);
    ArrayList<GeniSlivers> slivers = new ArrayList<>();
	if(urns.get(0).contains("+slice+")){
	  Group group = null;
	  try{
		   group=groupManager.getGroup(new URN(urns.get(0)).getSubjectAtDomain());
	  }catch(GroupNotFound e){
		  code = GENI_CodeEnum.SEARCHFAILED;
		  return slivers;
	  }
      List<String> resourceAdapterInstanceIds = group.getResources();
      List<ResourceAdapter> resourceAdapterInstances = resourceManager.getResourceAdapterInstancesById(resourceAdapterInstanceIds);
      while(resourceAdapterInstances.size()>0){
        ResourceAdapter resourceAdapter = (ResourceAdapter) resourceAdapterInstances.get(0);
        String id=resourceAdapter.getId();
        String urn = translator.translateResourceIdToSliverUrn(id, urns.get(0));
        resourceManager.deleteResource(id);
        GeniSlivers tmpSliver = new GeniSlivers();
        tmpSliver.setGeni_sliver_urn(urn);
        tmpSliver.setGeni_allocation_status(GENISliverAllocationState.geni_unallocated.toString());
        //TODO: expires????!!!
        //TODO error(optional)??
        slivers .add(tmpSliver);
      }
      
    } else{
    	
    	//TODO völlig falsch!!!
    
      
      for (String urn: urns) {
     
        String id=translator.getIdFromSliverUrn(urn);
       
        try{
        	resourceManager.deleteResource(id);
        }catch(ResourceNotFound e){
        	code = GENI_CodeEnum.SEARCHFAILED;
        	return new ArrayList<GeniSlivers>();
        }
        GeniSlivers tmpSliver = new GeniSlivers();
//      tmpSliver.setGeni_sliver_urn(translator.translateResourceIdToSliverUrn(id,urn));
        
        tmpSliver.setGeni_sliver_urn(urn);
        tmpSliver.setGeni_allocation_status(GENISliverAllocationState.geni_unallocated.toString());
        //TODO: expires????!!!
        //TODO error(optional)??
        slivers.add(tmpSliver);
        
      }
      
    }
    
    return slivers;
  }

//  private ArrayList<GeniSlivers> getTestDeleteResultValue() {
//    
//    SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
//    DescribeValue describeValue = translator.getDescription(new ArrayList<String>());
//    ArrayList<GeniSlivers> slivers = new ArrayList<GeniSlivers>();
//    
//    List<GeniSlivers> testList = describeValue.getGeni_slivers();
//    for (Iterator iterator = testList.iterator(); iterator.hasNext();) {
//      GeniSlivers geniSlivers = (GeniSlivers) iterator.next();
//      // optional attribute "geni_error" can also be set but for delete other geni sliver attributes are not allowed!!!!!!
//      GeniSlivers tmpSlivers = new GeniSlivers();
//      tmpSlivers.setGeni_sliver_urn(geniSlivers.getGeni_sliver_urn());
//      tmpSlivers.setGeni_allocation_status(geniSlivers.getGeni_allocation_status());
//      tmpSlivers.setGeni_expires(geniSlivers.getGeni_expires());
//      //TODO: error(optional)
//      
//      slivers.add(tmpSlivers);
//    }
//    
//    return slivers;
//  }
  
  @Override
  public AMResult processRequest(ListCredentials credentials, Object... specificArgs) {
    // TODO Auto-generated method stub
    return null;
  }

public void setResourceManager(ResourceAdapterManager instance) {
	this.resourceManager = instance;
}

public void setGroupDBManager(GroupDBManager groupManager) {
	this.groupManager = groupManager;
	
}
  

}
