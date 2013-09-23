package org.fiteagle.interactors.sfa.getversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniCredentialType;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.common.SFAv3RequestProcessor;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;
import org.fiteagle.interactors.sfa.rspec.advertisement.AdvertisementRspecTranslator;
import org.fiteagle.interactors.sfa.rspec.request.RequestRspecTranslator;

public class GetVersionRequestProcessor extends SFAv3RequestProcessor {

	final static int GENI_API_VERSION = 3;
	
	public GetVersionResult processRequest() {
		GetVersionResult getVersionResult = new GetVersionResult();
		GetVersionValue value = getVersionValue();
		value.addGenericAttribute("hrn", interfaceConfig.getAM_HRN());
		value.addGenericAttribute("urn", interfaceConfig.getAM_URN());
		value.addGenericAttribute("hostname", interfaceConfig.getHostname());
		value.addGenericAttribute("fiteagle version", interfaceConfig.getCommitVersion());
		Map<String, String> peers = new HashMap<>();
		value.addGenericAttribute("peers", peers);

		int geniAllocate = interfaceConfig.getGeni_allocate();
		switch (geniAllocate) {
		case 0:
			value.addGenericAttribute("geni_allocate", GeniAllocateEnum.geni_single);
			break;

		case 1:
			value.addGenericAttribute("geni_allocate", GeniAllocateEnum.geni_disjoint);
			break;
		
		default:
			value.addGenericAttribute("geni_allocate", GeniAllocateEnum.geni_many);
			break;
		}
		
		//Set F4F extensions
		value.setF4f_describe_testbed(interfaceConfig.getTestbed_description());
		value.setF4f_testbed_homepage(interfaceConfig.getTestbed_homepage());
		value.setF4f_testbed_picture(interfaceConfig.getTestbed_picture());
		
		if(interfaceConfig.getEndorsed_tool_names()!=null){
			ArrayList<F4FEndorsedTools> f4fEndorsedTools = new ArrayList<F4FEndorsedTools>();
			
			String[] toolNames = interfaceConfig.getEndorsed_tool_names().split(",");
			String[] toolLogos = interfaceConfig.getEndorsed_tool_logos().split(",");
			String[] toolHomepages = interfaceConfig.getEndorsed_tool_homepages().split(",");
			String[] toolVersions = interfaceConfig.getEndorsed_tool_versions().split(",");
			
			for (int i = 0; i < toolNames.length; i++) {
				
				F4FEndorsedTools f4fEndorsedTool = new F4FEndorsedTools();
				f4fEndorsedTool.setTool_name(toolNames[i].trim());
				if(i<toolLogos.length)
				f4fEndorsedTool.setTool_logo(toolLogos[i].trim());
				if(i<toolHomepages.length)
				f4fEndorsedTool.setTool_homepage(toolHomepages[i].trim());
				if(i<toolVersions.length)
				f4fEndorsedTool.setTool_version(toolVersions[i].trim());
				
				f4fEndorsedTools.add(f4fEndorsedTool);
			}
			value.setF4f_endorsed_tools(f4fEndorsedTools);
			
		}
		
		
		
		getVersionResult.setValue(value);
		AMCode code = new AMCode();
		code.setGeni_code(GENI_CodeEnum.SUCCESS);
		getVersionResult.setCode(code);
		getVersionResult.setOutput("");
		return getVersionResult;

		
	}

	private GetVersionValue getVersionValue() {
		GetVersionValue value = new GetVersionValue();
		//Set GENI API
		value.setGeni_api(GetVersionRequestProcessor.GENI_API_VERSION);
		//Set GENI request Rspec versions
		AdvertisementRspecTranslator adTranslator = new AdvertisementRspecTranslator();
		RequestRspecTranslator requestTranslator = new RequestRspecTranslator();
		List<GeniRequestRSpecVersions> geni_request_rspec_versions_list = getGeniRequestRspecVersionsList(requestTranslator);
		value.setGeni_request_rspec_versions(geni_request_rspec_versions_list);
		
		Map<Integer, String> geniApiVersions= new HashMap<Integer, String>();
		Integer version=new Integer(GetVersionRequestProcessor.GENI_API_VERSION);
		String url=this.interfaceConfig.getAM_URL();
    
		geniApiVersions.put(version, url);
    
		value.setGeni_api_versions(geniApiVersions);
		
		//Set GENI ad rspec versions;
		
		value.setGeni_single_allocation(0);
		List<GeniAdRSpecVersions> geni_ad_rspec_versions_list = getGeniAdRspecVersionsList(adTranslator);
		value.setGeni_ad_rspec_versions(geni_ad_rspec_versions_list);
		
		//Set GENI credential types 
		//TODO to be filled dynamically by credential core service e.g.
		List<GeniCredentialType> geniCredentialTypes = getGeniCredentialTypes();
		value.setGeni_credential_types(geniCredentialTypes);
		return value;
	}

	private List<GeniCredentialType> getGeniCredentialTypes() {
		List<GeniCredentialType> geniCredentialTypes = new ArrayList<>();
		GeniCredentialType credentialType = new GeniCredentialType();
		credentialType.setGeni_type("geni_sfa");
		credentialType.setGeni_version("3");
		geniCredentialTypes.add(credentialType);
		return geniCredentialTypes;
	}

	private List<GeniAdRSpecVersions> getGeniAdRspecVersionsList(
			AdvertisementRspecTranslator translator) {
		List<GeniAdRSpecVersions> geni_ad_rspec_versions_list = new ArrayList<>();
		GeniAdRSpecVersions geniAdRSpecVersions = new GeniAdRSpecVersions();
		geniAdRSpecVersions.setType(translator.getType());
		geniAdRSpecVersions.setVersion(translator.getVersion());
		geniAdRSpecVersions.setSchema(translator.getAdRspecSchema());
		geniAdRSpecVersions.setNamespace(translator.getAdRspecNamespace());
		geniAdRSpecVersions.setExtensions(translator.getAdRspecExtensions());
		geni_ad_rspec_versions_list.add(geniAdRSpecVersions);
		return geni_ad_rspec_versions_list;
	}

	private List<GeniRequestRSpecVersions> getGeniRequestRspecVersionsList(
			RequestRspecTranslator translator) {
		List<GeniRequestRSpecVersions> geni_request_rspec_versions_list = new ArrayList<>();
		GeniRequestRSpecVersions geniRequestRSpecVersions = new GeniRequestRSpecVersions();
		geniRequestRSpecVersions.setType(translator.getType());
		geniRequestRSpecVersions.setVersion(translator.getVersion());
		geniRequestRSpecVersions.setSchema(translator.getRequestRspecSchema());
		geniRequestRSpecVersions.setNamespace(translator.getRequestRspecNamespace());
		geniRequestRSpecVersions.setExtensions(translator.getRequestRspecExtensions());
		geni_request_rspec_versions_list.add(geniRequestRSpecVersions);
		return geni_request_rspec_versions_list;
	}

	@Override
	public AMResult processRequest(ListCredentials credentials,
			Object... specificArgs) {
		return processRequest();
	}

}
