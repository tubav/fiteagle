package org.fiteagle.interactors.sfa;

import java.io.IOException;

import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFACredentialsService;
import org.fiteagle.interactors.sfa.common.SFAOptionsService;
import org.fiteagle.interactors.sfa.common.SFARequestProcessorFactory;
import org.fiteagle.interactors.sfa.common.SFAv3MethodsEnum;
import org.fiteagle.interactors.sfa.getversion.GetVersionResult;
import org.fiteagle.interactors.sfa.getversion.GetVersionValue;
import org.fiteagle.interactors.sfa.listresources.ListResourceOptions;
import org.fiteagle.interactors.sfa.listresources.ListResourceRequestProcessor;
import org.fiteagle.interactors.sfa.listresources.ListResourcesResult;

public class SFAInteractor_v3 implements ISFA {

	final static int GENI_API_VERSION = 3;
	
	@Override
	public GetVersionResult getVersion() throws IOException {
		final GetVersionResult getVersionResult = new GetVersionResult();

		// TODO set Value according to version, etc...

		GetVersionValue value = new GetVersionValue();
		value.setGeni_api(SFAInteractor_v3.GENI_API_VERSION);
		getVersionResult.setValue(value);


		// TODO set Code according to occuring exceptions etc

		AMCode code = new AMCode();
		code.setGeni_code(GENI_CodeEnum.SUCCESS);
		getVersionResult.setCode(code);

		// TODO set Output

		getVersionResult.setOutput("");

		return getVersionResult;
	}

	@Override
	public ListResourcesResult listResources(ListCredentials credentials,
			ListResourceOptions listResourceOptions) throws IOException {
		
		SFARequestProcessorFactory sfaRequestProcFactory = new SFARequestProcessorFactory();
		ListResourceRequestProcessor listResourceRequestProcessor = sfaRequestProcFactory.createRequestProcessor(SFAv3MethodsEnum.LIST_RESOURCES);
		ListResourcesResult result = listResourceRequestProcessor.processRequest(credentials, listResourceOptions);
		
		AMCode returnCode = result.getCode();
		//Do something
		
		return result;
		
	}

}
	
