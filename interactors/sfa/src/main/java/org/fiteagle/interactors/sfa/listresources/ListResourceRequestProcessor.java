package org.fiteagle.interactors.sfa.listresources;

import java.util.List;

import org.fiteagle.adapter.common.Resource;
import org.fiteagle.core.ResourceManager;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFACredentialsService;
import org.fiteagle.interactors.sfa.common.SFAv3MethodsEnum;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.rspec.RSpecContents;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class ListResourceRequestProcessor extends SFAv3RequestProcessor {


	private ResourceManager resourceManager;
	
	public ListResourceRequestProcessor(){
		resourceManager = new ResourceManager();
	}

	@Override
	public ListResourcesResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		ListResourceOptions options = (ListResourceOptions) specificArgs[0];
		
		ListResourcesResult result = new ListResourcesResult();
		ListResourceOptionsService optionsService = new ListResourceOptionsService(options);
		SFACredentialsService credentialService = new SFACredentialsService(credentials);
		
		//not very elegant ....
		boolean goOn = true;
		AMCode returnCode = new AMCode();
		
		if(!optionsService.checkRSpecVersion()){
			returnCode.setGeni_code(GENI_CodeEnum.BADVERSION);
			goOn = false;
		}
		
		if(goOn && !(optionsService.optionsAreValid() && optionsService.optionsComplete())){
			returnCode.setGeni_code(GENI_CodeEnum.BADARGS);
			goOn = false;
		}
		
		if(goOn && !credentialService.isPermitted(SFAv3MethodsEnum.LIST_RESOURCES) ){
			returnCode.setGeni_code(GENI_CodeEnum.FORBIDDEN);
			goOn = false;
		}
		
		if(goOn){
			//TODO evaluate available resources option & implement availability concept
			List<Resource> resources = resourceManager.getResources();
			
			RSpecContents advertisedRspec = new RSpecContents();
			advertisedRspec.setType("advertisement");
			
			List<Object> rspecContentElements = advertisedRspec.getAnyOrNodeOrLink();
			SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
			for(Resource resource: resources){
				rspecContentElements.add(translator.translateToAdvertisementRspec(resource));
			}
			result.setValue(advertisedRspec);
		}
		result.setCode(returnCode);
		return result;
	}

}
