package org.fiteagle.interactors.sfa.listresources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.fiteagle.adapter.common.ResourceAdapter;
//import org.fiteagle.adapter.common.ResourceProperties;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.rspec.ObjectFactory;
import org.fiteagle.interactors.sfa.rspec.RSpecContents;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class ListResourceRequestProcessor extends SFAv3RequestProcessor {

	private ResourceAdapterManager resourceManager;
	private ListResourceOptionsService optionsService;
	

	public ListResourceRequestProcessor() {
		resourceManager = ResourceAdapterManager.getInstance();
	}

	@Override
	public ListResourcesResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		
		
		ListResourceOptions options = (ListResourceOptions) specificArgs[0];
		// has to be modified to check credentials
		ListResourcesResult result = getResult(credentials, options);
		return result;

	}

	private ListResourcesResult getResult(ListCredentials listCredentials, ListResourceOptions options) {

		Object value = "";
		String output = "";
		AMCode returnCode = null;
		
		Authorization auth = new Authorization();
		
		auth.checkCredentialsList(listCredentials);
		
		if(!auth.areCredentialTypeAndVersionValid()){
			returnCode=auth.getReturnCode();
			output=auth.getAuthorizationFailMessage();
			ListResourcesResult result = new ListResourcesResult();
			result.setCode(returnCode);
			result.setOutput(output);
			return result;
		}
		
		checkOptions(options);
		
		
		ListResourcesResult result = new ListResourcesResult();
		if (optionsAreValid()) {
			value = getValue();
			
			if(this.optionsService.isCompressed()){
				result.setValue(this.compress((String)value));
			}else {
				result.setValue(value);
			}
			
			output = getOutput();
			returnCode = getRuntimeReturnCode();
		} else {
			returnCode = getOptionsValidationReturnCode();
			output = getOptionsValidationOutput();
		}
		result.setCode(returnCode);
		result.setOutput(output);
		return result;
	}

	private String getOptionsValidationOutput() {

		return optionsService.getErrorOutput();
	}

	private AMCode getRuntimeReturnCode() {
		if(runTimeReturnCode == null){
			runTimeReturnCode =  new AMCode();
			runTimeReturnCode.setGeni_code(GENI_CodeEnum.SUCCESS);
		}
		return runTimeReturnCode;
	}

	private void checkOptions(ListResourceOptions options) {
		if (optionsService == null)
			this.optionsService = new ListResourceOptionsService(options);

		optionsService.checkOptions();

	}

	private String getOutput() {
		return outPutString;
	}

	private Object getValue() {
	
		List<ResourceAdapter> resourceAdapters = resourceManager
				.getResourceAdapters();

		RSpecContents advertisedRspec = getAdvertisedRSpec(resourceAdapters);
		String advertisedRspecSTR = this.getRSpecString(advertisedRspec);

		return advertisedRspecSTR;
	}





	private boolean optionsAreValid() {

		return optionsService.areOptionsValid();
	}

	private AMCode getOptionsValidationReturnCode() {

		return optionsService.getErrorCode();
	}


}
