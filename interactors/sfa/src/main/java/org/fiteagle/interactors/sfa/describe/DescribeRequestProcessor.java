package org.fiteagle.interactors.sfa.describe;

import java.util.List;

import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.listresources.ListResourceOptions;
import org.fiteagle.interactors.sfa.listresources.ListResourcesResult;
import org.fiteagle.interactors.sfa.rspec.RSpecContents;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class DescribeRequestProcessor extends SFAv3RequestProcessor {

	
	private ResourceAdapterManager resourceManager;
	private DescribeOptionsService optionsService;
	private String outPutString = "";
	
	
	public DescribeRequestProcessor() {
		resourceManager = new ResourceAdapterManager();
	}
	
	
	
	public DescribeResult processRequest(List<String> urns, ListCredentials credentials,
			Object... specificArgs) {
//		List<String> urns = (List<String>) specificArgs[0];
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
		returnCode = getSuccessReturnCode();
		
		result.setCode(returnCode);
		result.setValue(getResultValue(urns));
		return result;
		
	}



	private DescribeValue getResultValue(List<String> urns) {
		//TODO: static test values only. Slice management is missing
		SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
		DescribeValue describeValue = translator.getDescription(urns);
		String rspecStr = (String) describeValue.getGeni_rspec();
		if(this.optionsService.isCompressed()){
			describeValue.setGeni_rspec(this.compress(rspecStr));
		}
		
		return describeValue;
		
	}






	@Override
	public AMResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		// TODO Auto-generated method stub
		return null;
	}

}
