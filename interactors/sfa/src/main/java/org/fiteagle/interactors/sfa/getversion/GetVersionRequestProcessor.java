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
//		peers.put("fiteagle", interfaceConfig.getAM_URL());
		value.addGenericAttribute("peers", peers);
		
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
//				String toolName = toolNames[i].trim();
//				String toolLogo = toolLogos[i].trim();
//				String toolHomepage = toolHomepages[i].trim();
//				String toolVersion = toolVersions[i].trim();
//				F4FEndorsedTools f4fEndorsedTool = new F4FEndorsedTools();
//				f4fEndorsedTool.setTool_name(toolName);
//				f4fEndorsedTool.setTool_logo(toolLogo);
//				f4fEndorsedTool.setTool_homepage(toolHomepage);
//				f4fEndorsedTool.setTool_version(toolVersion);
				
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
		SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
		
		List<GeniRequestRSpecVersions> geni_request_rspec_versions_list = getGeniRequestRspecVersionsList(translator);
		value.setGeni_request_rspec_versions(geni_request_rspec_versions_list);
		
		Map<String, String> geniApiVersions= new HashMap<String, String>();
//		String version="3";
//    String url="https://fiteagle.org:9112/api/sfa/v3/am";
		String version=new Integer(GetVersionRequestProcessor.GENI_API_VERSION).toString();
		String url=this.interfaceConfig.getAM_URL();
    
		geniApiVersions.put(version, url);
    
		value.setGeni_api_versions(geniApiVersions);
		
		//Set GENI ad rspec versions;
		
		value.setGeni_single_allocation(false);
		List<GeniAdRSpecVersions> geni_ad_rspec_versions_list = getGeniAdRspecVersionsList(translator);
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
			SFAv3RspecTranslator translator) {
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
			SFAv3RspecTranslator translator) {
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
