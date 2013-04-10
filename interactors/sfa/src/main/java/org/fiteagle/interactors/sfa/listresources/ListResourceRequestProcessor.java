package org.fiteagle.interactors.sfa.listresources;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.ResourceProperties;
import org.fiteagle.core.ResourceManager;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.rspec.ObjectFactory;
import org.fiteagle.interactors.sfa.rspec.RSpecContents;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class ListResourceRequestProcessor extends SFAv3RequestProcessor {

	private ResourceManager resourceManager;
	private ListResourceOptionsService optionsService;
	private AMCode runTimeReturnCode;
	private String outPutString = "";

	public ListResourceRequestProcessor() {
		resourceManager = new ResourceManager();
	}

	@Override
	public ListResourcesResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		ListResourceOptions options = (ListResourceOptions) specificArgs[0];
		// has to be modified to check credentials
		ListResourcesResult result = getResult(options);
		return result;

		// SFACredentialsService credentialService = new
		// SFACredentialsService(credentials);

		// if(goOn &&
		// !credentialService.isPermitted(SFAv3MethodsEnum.LIST_RESOURCES) ){
		// returnCode.setGeni_code(GENI_CodeEnum.FORBIDDEN);
		// goOn = false;
		// }
	}

	private ListResourcesResult getResult(ListResourceOptions options) {

		checkOptions(options);
		String value = "";
		String output = "";
		AMCode returnCode = null;
		if (optionsAreValid()) {
			value = getValue();
			output = getOutput();
			returnCode = getRuntimeReturnCode();
		} else {
			returnCode = getOptionsValidationReturnCode();
			output = getOptionsValidationOutput();
		}
		ListResourcesResult result = new ListResourcesResult();
		result.setCode(returnCode);
		result.setOutput(output);
		result.setValue(value);
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

	private String getValue() {
	
		List<ResourceAdapter> resourceAdapters = resourceManager
				.getResourceAdapters();

		RSpecContents advertisedRspec = getAdvertisedRSpec(resourceAdapters);
		String advertisedRspecSTR = getRSpecString(advertisedRspec);

		return advertisedRspecSTR;
	}

	private String getRSpecString(RSpecContents advertisedRspec) {
		String advertisedRspecSTR = "";
		

		JAXBElement<RSpecContents> rspec = new ObjectFactory()
				.createRspec(advertisedRspec);

		try {
			advertisedRspecSTR = getString(rspec);
		} catch (JAXBException e) {
			 setRuntimeReturnCode(GENI_CodeEnum.ERROR);
			 setOutput("Internal Server Error!");
		}

		// result.setValue(advertisedRspecSTR);
		return advertisedRspecSTR;
	}

	private void setOutput(String string) {
		this.outPutString = string;
		
	}

	private void setRuntimeReturnCode(GENI_CodeEnum error) {
		runTimeReturnCode = new AMCode();
		runTimeReturnCode.setGeni_code(error);
		
	}

	private RSpecContents getAdvertisedRSpec(List<ResourceAdapter> resourceAdapters) {
		RSpecContents advertisedRspec = new RSpecContents();
		advertisedRspec.setType("advertisement");

		List<Object> rspecContentElements = advertisedRspec
				.getAnyOrNodeOrLink();
		SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
//		
//		//TODO:!!!!TEST. just to test stop watch resource with static content. 
//		ResourceProperties props = new StopWatchInstanceProperties();
//		props.setIdentifier("myStopWatchInstance");
//		props.setName("StopWatch");
//		Object fiteagleResource1 = translator.translateToFITeagleResource(props);
//		rspecContentElements.add(fiteagleResource1);
//		//TEST!!!!!
		
		for(ResourceAdapter resourceAdapter: resourceAdapters){
			Object fiteagleResource = translator.translateToFITeagleResource(resourceAdapter);
			rspecContentElements.add(fiteagleResource);
		}
		return advertisedRspec;
	}

	private List<ResourceProperties> getResourceProperties() {
		List<ResourceProperties> resources = new ArrayList<ResourceProperties>();
		List<ResourceAdapter> resourceAdapters = resourceManager
				.getResourceAdapters();
		for (ResourceAdapter adapter : resourceAdapters) {
			resources.addAll(adapter.getAllResources());
		}
		return resources;
	}

	private boolean optionsAreValid() {

		return optionsService.areOptionsValid();
	}

	private AMCode getOptionsValidationReturnCode() {

		return optionsService.getErrorCode();
	}

	private String getString(Object jaxbObject) throws JAXBException {
		JAXBContext context = JAXBContext
				.newInstance("org.fiteagle.interactors.sfa.rspec");
		Marshaller marshaller = context.createMarshaller();
		StringWriter stringWriter = new StringWriter();
		marshaller.marshal(jaxbObject, stringWriter);

		return stringWriter.toString();

	}

}
