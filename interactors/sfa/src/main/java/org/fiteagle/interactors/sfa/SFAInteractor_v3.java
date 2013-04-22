package org.fiteagle.interactors.sfa;

import java.io.IOException;

import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFARequestProcessorFactory;
import org.fiteagle.interactors.sfa.common.SFAv3MethodsEnum;
import org.fiteagle.interactors.sfa.getversion.GetVersionRequestProcessor;
import org.fiteagle.interactors.sfa.getversion.GetVersionResult;
import org.fiteagle.interactors.sfa.listresources.ListResourceOptions;
import org.fiteagle.interactors.sfa.listresources.ListResourceRequestProcessor;
import org.fiteagle.interactors.sfa.listresources.ListResourcesResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SFAInteractor_v3 implements ISFA {

	
	private final SFARequestProcessorFactory requestProcessorFactor = SFARequestProcessorFactory.getInstance();
	private final Logger log = LoggerFactory.getLogger(this.getClass());
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

	@Override
	public String resolve(Object o1, Object o2) {
		log.info("Method Resolve was called, you want to me to show you something");
		return "";
	}

	@Override
	public String getSelfCredential(Object o1, Object o2, Object o3) {
		log.info("Method getSelfCredential was called, you want to me to give you something");
		return "some cert";
	}

}
