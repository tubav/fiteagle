package org.fiteagle.interactors.sfa.rspec;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.ResourceProperties;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;

public class SFAv3RspecTranslator {

	private final Geni_RSpec_Version geni_rspec_version;
	private final String adRspecNamespace = "http://www.geni.net/resources/rspec/3" ;
	private final String adRspecSchema = "http://www.geni.net/resources/rspec/3/ad.xsd";
	private final String[] adRspecExtensions = new String[]{""};
	
	private final String requestRspecNamespace ="http://www.geni.net/resources/rspec/3";
	private final String requestRspecSchema = "http://www.geni.net/resources/rspec/3/request.xsd";
	private final String[] requestRspecExtensions = new String[]{""};
	public SFAv3RspecTranslator() {
		geni_rspec_version = new Geni_RSpec_Version();
		geni_rspec_version.setType("GENI");
		geni_rspec_version.setVersion("3");
	}
	
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
		return this.geni_rspec_version.getType();
	}
	public String getVersion() {
		return this.geni_rspec_version.getVersion();
	}
	public Object translateToAdvertisementRspec(ResourceProperties resource) {
		//TODO identify node, link, interface etc??
		//DUMMY IMPL ONLY VALID FOR SINGLE STOPWATCHRESOURCE!!!
		NodeContents dummyNode = new NodeContents();
		dummyNode.setComponentName(resource.getName());
		dummyNode.setComponentManagerId("urn:publicid:IDN+fiteagle+authority+am"); // TODO generate URN based on dynamic information? 
		dummyNode.setComponentId("urn:publicid:IDN+fiteagle+node+"+resource.getName());
		return new ObjectFactory().createNode(dummyNode);
	}
	
	public Object translateToFITeagleResource(ResourceAdapter resource) {
		//TODO identify node, link, interface etc??
		//DUMMY IMPL ONLY VALID FOR SINGLE STOPWATCHRESOURCE!!!
		Resource fiteagleSFSResource= new Resource();
		fiteagleSFSResource.setName(resource.getType());
		 Property componentManagerIdProperty = new Property();
		 componentManagerIdProperty.setName("componentManagerId");
		 componentManagerIdProperty.setType("urn");
		 componentManagerIdProperty.setValue("urn:publicid:IDN+fiteagle+authority+am");
		fiteagleSFSResource.setProperty(componentManagerIdProperty);
		
		Property componentIdProperty = new Property();
		 componentIdProperty.setName("componentId");
		 componentIdProperty.setType("urn");
		 componentIdProperty.setValue("urn:publicid:IDN+fiteagle+node+"+resource.getType());
		 
		 fiteagleSFSResource.setProperty(componentIdProperty);
		return new ObjectFactory().createResource(fiteagleSFSResource);
	}
	
	
	
	
}
