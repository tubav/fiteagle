package org.fiteagle.interactors.sfa.describe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniSlivers;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.rspec.RSpecContents;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class DescribeRequestProcessor extends SFAv3RequestProcessor {

	
	private ResourceAdapterManager resourceManager;
	private DescribeOptionsService optionsService;
	private String outPutString = "";
	
	
	public DescribeRequestProcessor() {
		resourceManager = ResourceAdapterManager.getInstance();
	}
	
	
	
	public DescribeResult processRequest(List<String> urns, ListCredentials credentials,
			Object... specificArgs) {
		DescribeOptions options = (DescribeOptions) specificArgs[0];
		DescribeResult result = getResult(urns, credentials, options);
		return result;
	}



	private DescribeResult getResult(List<String> urns,
			ListCredentials credentials, DescribeOptions options) {
		String value = "";
		String output = "";
		AMCode returnCode = null;
		
		Authorization auth = new Authorization();
		
		auth.checkCredentialsList(credentials);
		
		DescribeResult result = new DescribeResult();
		
		if(!auth.areCredentialTypeAndVersionValid()){
			returnCode=auth.getReturnCode();
			output=auth.getAuthorizationFailMessage();
			result.setCode(returnCode);
			result.setOutput(output);
			return result;
		}
		
		this.optionsService=new DescribeOptionsService(options);
		
		optionsService.checkOptions();
		if(!optionsService.areOptionsValid()){
			returnCode=optionsService.getErrorCode();
			output=optionsService.getErrorOutput();
			
			result.setCode(returnCode);
			result.setOutput(output);
			return result;
		}
		
		
		//TODO: process the correct request..
		returnCode = getReturnCode(GENI_CodeEnum.SUCCESS);
		
		result.setCode(returnCode);
		result.setValue(getResultValue(urns));
		return result;
		
	}



	private DescribeValue getResultValue(List<String> urns) {
	  
	  if(urns.size()>1);
	  if(urns.size()==1 && urns.get(0).contains("\\+sliver\\+"));
	//TODO: implement if there are one or multiple sliver urns not only one slice urn
	  
	  SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
    DescribeValue resultValue = new DescribeValue();
    ResourceAdapterManager resourceManager = ResourceAdapterManager.getInstance();
    Group group = GroupDBManager.getInstance().getGroup(new URN(urns.get(0)).getSubjectAtDomain());
    ArrayList<GeniSlivers> slivers = new ArrayList<GeniSlivers>();
    
    List<String> resourceIds = group.getResources();
    List<ResourceAdapter> resources = resourceManager.getResourceAdapterInstancesById(resourceIds);
    for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
      ResourceAdapter resourceAdapter = (ResourceAdapter) iterator.next();
      GeniSlivers tmpSliver = new GeniSlivers();
      tmpSliver.setGeni_sliver_urn(translator.translateResourceIdToSliverUrn(resourceAdapter.getId(),urns.get(0)));
      tmpSliver.setGeni_allocation_status((String)resourceAdapter.getProperties().get("allocation_status"));
      tmpSliver.setGeni_operational_status((String)resourceAdapter.getProperties().get("operational_status"));
      //TODO: expires????!!!
      //TODO: geni_error (optional)
      slivers.add(tmpSliver);
    }
    resultValue.setGeni_slivers(slivers);
    RSpecContents manifestRSpec = getManifestRSpec(resources);
    String geni_rspec = getRSpecString(manifestRSpec);
    if(this.optionsService.isCompressed()){
      resultValue.setGeni_rspec(this.compress(geni_rspec));
    } else resultValue.setGeni_rspec(geni_rspec);
    
    return resultValue;
		
	}






	@Override
	public AMResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		// TODO Auto-generated method stub
		return null;
	}

}
