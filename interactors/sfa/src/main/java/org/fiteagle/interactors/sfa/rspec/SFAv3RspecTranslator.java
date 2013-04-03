package org.fiteagle.interactors.sfa.rspec;

import org.fiteagle.adapter.common.Resource;

public class SFAv3RspecTranslator {

	private final String type = "GENI";
	private final String version = "3";
	private final String adRspecNamespace = "http://www.geni.net/resources/rspec/3" ;
	private final String adRspecSchema = "http://www.geni.net/resources/rspec/3/ad.xsd";
	private final String[] adRspecExtensions = new String[]{""};
	
	private final String requestRspecNamespace ="http://www.geni.net/resources/rspec/3";
	private final String requestRspecSchema = "http://www.geni.net/resources/rspec/3/request.xsd";
	private final String[] requestRspecExtensions = new String[]{""};
	public String getAdRspecNamespace() {
		return adRspecNamespace;
	}
	public String getAdRspecSchema() {
		return adRspecSchema;
	}
	public String[] getAdRspecExtensions() {
		return adRspecExtensions;
	}
	public String getRequestRspecNamespace() {
		return requestRspecNamespace;
	}
	public String getRequestRspecSchema() {
		return requestRspecSchema;
	}
	public String[] getRequestRspecExtensions() {
		return requestRspecExtensions;
	}
	public String getType() {
		return type;
	}
	public String getVersion() {
		return version;
	}
	public Object translateToAdvertisementRspec(Resource resource) {
		//TODO identify node, link, interface etc??
		//DUMMY IMPL ONLY VALID FOR SINGLE STOPWATCHRESOURCE!!!
		NodeContents dummyNode = new NodeContents();
		dummyNode.setComponentName(resource.getName());
		dummyNode.setComponentManagerId("urn:publicid:IDN+fiteagle+authority+am"); // TODO generate URN based on dynamic information? 
		dummyNode.setComponentId("urn:publicid:IDN+fiteagle+node+"+resource.getName());
		return new ObjectFactory().createNode(dummyNode);
		
		
	}
	
	
	
	
}
