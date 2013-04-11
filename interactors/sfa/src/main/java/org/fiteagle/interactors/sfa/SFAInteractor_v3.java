package org.fiteagle.interactors.sfa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniCredentialType;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFACredentialsService;
import org.fiteagle.interactors.sfa.common.SFAOptionsService;
import org.fiteagle.interactors.sfa.common.SFARequestProcessorFactory;
import org.fiteagle.interactors.sfa.common.SFAv3MethodsEnum;
import org.fiteagle.interactors.sfa.getversion.GeniAdRSpecVersions;
import org.fiteagle.interactors.sfa.getversion.GeniRequestRSpecVersions;
import org.fiteagle.interactors.sfa.getversion.GetVersionRequestProcessor;
import org.fiteagle.interactors.sfa.getversion.GetVersionResult;
import org.fiteagle.interactors.sfa.getversion.GetVersionValue;
import org.fiteagle.interactors.sfa.listresources.ListResourceOptions;
import org.fiteagle.interactors.sfa.listresources.ListResourceRequestProcessor;
import org.fiteagle.interactors.sfa.listresources.ListResourcesResult;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class SFAInteractor_v3 implements ISFA {

	
	private final SFARequestProcessorFactory requestProcessorFactor = SFARequestProcessorFactory.getInstance();
	
	@Override
	public GetVersionResult getVersion() throws IOException {
		
		GetVersionRequestProcessor getVersionRequestProcessor = requestProcessorFactor.createRequestProcessor(SFAv3MethodsEnum.GET_VERSION);
		
		final GetVersionResult getVersionResult = getVersionRequestProcessor.processRequest();		
		return getVersionResult;
	}

	@Override
	public ListResourcesResult listResources(ListCredentials credentials,
			ListResourceOptions listResourceOptions) throws IOException {
		
	
		ListResourceRequestProcessor listResourceRequestProcessor = requestProcessorFactor.createRequestProcessor(SFAv3MethodsEnum.LIST_RESOURCES);
		ListResourcesResult result = listResourceRequestProcessor.processRequest(credentials, listResourceOptions);
		
		AMCode returnCode = result.getCode();
		//Do something
		
		return result;
		
	}

}
	
