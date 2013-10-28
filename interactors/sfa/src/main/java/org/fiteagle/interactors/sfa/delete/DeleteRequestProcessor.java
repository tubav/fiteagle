package org.fiteagle.interactors.sfa.delete;

import java.util.ArrayList;
import java.util.List;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.ResourceAdapterManager.ResourceNotFound;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.groupmanagement.JPAGroupDB.CouldNotFindGroup;
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
import org.fiteagle.interactors.sfa.common.Sliver;
import org.fiteagle.interactors.sfa.common.SliverManagement;
import org.fiteagle.interactors.sfa.common.SliverManagement.SliverNotFound;
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
	  }catch(CouldNotFindGroup e){
		  code = GENI_CodeEnum.SEARCHFAILED;
		  return slivers;
	  }
      List<String> resourceAdapterInstanceIds = group.getResources();
      SliverManagement sliverManagement = SliverManagement.getInstance();
      List<Sliver> bookedSlivers = sliverManagement.getSlivers(resourceAdapterInstanceIds);
      while(bookedSlivers.size()>0){
       
        Sliver sliver =  bookedSlivers.get(0);
        GeniSlivers tmpSliver = releaseSliver(group, sliver);
        //TODO: implementation of timer functionality
        
        slivers.add(tmpSliver);
        
        bookedSlivers.remove(0);
      }
      
    } else{
    
      for (String urn: urns) {
     
    	URN u = new URN(urn);
    
    
        SliverManagement sliverManagement = SliverManagement.getInstance();
        Sliver sliver = null;
        try{
        	 sliver = sliverManagement.getSliver(u);
        }catch(SliverNotFound e){
        	  code = GENI_CodeEnum.SEARCHFAILED;
    		  return slivers;
        }
  
        GeniSlivers tmpSliver = releaseSliver(sliver.getGroup(), sliver);

        tmpSliver.setGeni_sliver_urn(urn);
        tmpSliver.setGeni_allocation_status(GENISliverAllocationState.geni_unallocated.toString());
//        tmpSliver.setGeni_expires(geni_expires);
        slivers.add(tmpSliver);
        
      }
      
    }
    
    return slivers;
  }

private GeniSlivers releaseSliver(Group group, Sliver sliver) {
	ResourceAdapter resourceAdapter = sliver.getResource();
	//TODO: test this line
	resourceAdapter.release();
	
	String id=sliver.getId();
//        String urn = translator.translateResourceIdToSliverUrn(id, urns.get(0));
	resourceManager.releaseResource(resourceAdapter);
      
	group.deleteResource(id);
	GroupDBManager.getInstance().updateGroup(group);
	
	
	GeniSlivers tmpSliver = new GeniSlivers();
	tmpSliver.setGeni_sliver_urn(id);
	sliver.setAllocationState(GENISliverAllocationState.geni_unallocated);
	sliver.setOperationalState(GENISliverOperationalState.geni_stopping);
	tmpSliver.setGeni_allocation_status(GENISliverAllocationState.geni_unallocated.toString());
	return tmpSliver;
}

 
  
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
