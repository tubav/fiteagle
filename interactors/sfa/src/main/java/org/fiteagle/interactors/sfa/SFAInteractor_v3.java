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
import org.fiteagle.interactors.sfa.getversion.GetVersionResult;
import org.fiteagle.interactors.sfa.getversion.GetVersionValue;
import org.fiteagle.interactors.sfa.listresources.ListResourceOptions;
import org.fiteagle.interactors.sfa.listresources.ListResourceRequestProcessor;
import org.fiteagle.interactors.sfa.listresources.ListResourcesResult;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class SFAInteractor_v3 implements ISFA {

	final static int GENI_API_VERSION = 3;
	
	
	@Override
	public GetVersionResult getVersion() throws IOException {
		final GetVersionResult getVersionResult = new GetVersionResult();

		GetVersionValue value = new GetVersionValue();
		//Set GENI API
		value.setGeni_api(SFAInteractor_v3.GENI_API_VERSION);
		//Set GENI request Rspec versions
		SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
		List<GeniRequestRSpecVersions> geni_request_rspec_versions_list = new ArrayList<>();
		GeniRequestRSpecVersions geniRequestRSpecVersions = new GeniRequestRSpecVersions();
		geniRequestRSpecVersions.setType(translator.getType());
		geniRequestRSpecVersions.setVersion(translator.getVersion());
		geniRequestRSpecVersions.setSchema(translator.getRequestRspecSchema());
		geniRequestRSpecVersions.setNamespace(translator.getRequestRspecNamespace());
		geniRequestRSpecVersions.setExtensions(translator.getRequestRspecExtensions());
		geni_request_rspec_versions_list.add(geniRequestRSpecVersions);
		value.setGeni_request_rspec_versions(geni_request_rspec_versions_list);
		
		//Set GENI ad rspec versions;
		
		value.setGeni_single_allocation(false);
		List<GeniAdRSpecVersions> geni_ad_rspec_versions_list = new ArrayList<>();
		GeniAdRSpecVersions geniAdRSpecVersions = new GeniAdRSpecVersions();
		geniAdRSpecVersions.setType(translator.getType());
		geniAdRSpecVersions.setVersion(translator.getVersion());
		geniAdRSpecVersions.setSchema(translator.getAdRspecSchema());
		geniAdRSpecVersions.setNamespace(translator.getAdRspecNamespace());
		geniAdRSpecVersions.setExtensions(translator.getAdRspecExtensions());
		geni_ad_rspec_versions_list.add(geniAdRSpecVersions);
		value.setGeni_ad_rspec_versions(geni_ad_rspec_versions_list);
		
		//Set GENI credential types 
		//TODO to be filled dynamically by credential core service e.g.
		List<GeniCredentialType> geniCredentialTypes = new ArrayList<>();
		GeniCredentialType credentialType = new GeniCredentialType();
		credentialType.setGeni_type("geni_sfa");
		credentialType.setGeni_version("3");
		geniCredentialTypes.add(credentialType);
		value.setGeni_credential_types(geniCredentialTypes);
		
		
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
	
