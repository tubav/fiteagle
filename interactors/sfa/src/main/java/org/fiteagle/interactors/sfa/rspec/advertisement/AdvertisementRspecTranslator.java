package org.fiteagle.interactors.sfa.rspec.advertisement;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.SSHAccessable;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;
import org.fiteagle.interactors.sfa.rspec.ext.Property;
import org.fiteagle.interactors.sfa.rspec.ext.Resource;

public class AdvertisementRspecTranslator extends SFAv3RspecTranslator {

	private final String adRspecNamespace = "http://www.geni.net/resources/rspec/3";
	private final String adRspecSchema = "http://www.geni.net/resources/rspec/3/ad.xsd";
	private final ArrayList<String> adRspecExtensions = new ArrayList<String>();
	private final String RSPEC_EXTENSION = "http://fiteagle.org/rspec/ext/1";

	public AdvertisementRspecTranslator() {
		super();
		addAdRspecExtension(this.RSPEC_EXTENSION);
	}

	//TODO: mapping between type of resource and sfa type, waiting for ontology based solution
	public RSpecContents getAdvertisedRSpec(
			List<ResourceAdapter> resourceAdapters) {
		RSpecContents adRSpecContents = new RSpecContents();
		adRSpecContents.setType("advertisement");
		List<Object> rspecElements = adRSpecContents.getAnyOrNodeOrLink();
		for (ResourceAdapter ra : resourceAdapters) {
			if (ra instanceof SSHAccessable) {
				Object node = translateSSHAccesableToAdvertisementNode(ra);
				rspecElements.add(node);
			}else{
				Object resource = translateToFITeagleResource(ra);
				rspecElements.add(resource);
			}
		}
		return adRSpecContents;
	}
	
	public Object translateSSHAccesableToAdvertisementNode(
			ResourceAdapter resourceAdapter) {
		NodeContents node = new NodeContents();

		HashMap<String, Object> resourceAdapterProperties = resourceAdapter
				.getProperties();

		ObjectFactory factory = new ObjectFactory();

		HashMap<String, Object> resourceProperties = resourceAdapter
				.getProperties();
		node.setComponentId(COMPONENT_ID_PREFIX + "+node+"
				+ resourceAdapter.getId());
		node.setComponentManagerId(COMPONENT_MANAGER_ID);
		node.setExclusive(resourceAdapter.isExclusive());

		List<Object> nodeContent = node.getAnyOrRelationOrLocation();

		AvailableContents available = new AvailableContents();
		available.setNow(resourceAdapter.isAvailable());
		nodeContent.add(factory.createAvailable(available));

		LocationContents location = new LocationContents();
		location.setCountry((String) resourceAdapterProperties.get("country"));
		location.setLatitude((String) resourceAdapterProperties.get("latitude"));
		location.setLongitude((String) resourceAdapterProperties
				.get("longitude"));
		nodeContent.add(factory.createLocation(location));

		SSHAccessable sshAccesableResource = (SSHAccessable) resourceAdapter;

		HardwareTypeContents hardwareType = new HardwareTypeContents();
		hardwareType.setName(sshAccesableResource.getHardwareType());
		nodeContent.add(factory.createHardwareType(hardwareType));

		return new ObjectFactory().createNode(node);
	}

	public String getAdvertisedRSpecString(RSpecContents rspec) {
		String advertisedRspecSTR = "";

		JAXBElement<RSpecContents> rspecElem = new ObjectFactory()
				.createRspec(rspec);

		try {
			advertisedRspecSTR = getAdvertisedString(rspecElem);
		} catch (JAXBException e) {

		}
		return advertisedRspecSTR;
	}

	private String getAdvertisedString(Object jaxbObject) throws JAXBException {
		JAXBContext context = JAXBContext
				.newInstance("org.fiteagle.interactors.sfa.rspec.advertisement:org.fiteagle.interactors.sfa.rspec.ext");
		Marshaller marshaller = context.createMarshaller();
		marshaller
				.setProperty(
						Marshaller.JAXB_SCHEMA_LOCATION,
						"http://www.geni.net/resources/rspec/3 http://www.geni.net/resources/rspec/3/ad.xsd http://fiteagle.org/rspec/ext/1 http://fiteagle.org/rspec/ext/1");
		StringWriter stringWriter = new StringWriter();
		marshaller.marshal(jaxbObject, stringWriter);

		return stringWriter.toString();

	}

	public String getAdRspecSchema() {
		return adRspecSchema;
	}

	public String getAdRspecNamespace() {
		return adRspecNamespace;
	}

	public String[] getAdRspecExtensions() {
		return adRspecExtensions.toArray(new String[adRspecExtensions.size()]);
	}

	private void addAdRspecExtension(String extension) {
		adRspecExtensions.add(extension);
	}
}
