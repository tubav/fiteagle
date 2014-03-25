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

import org.fiteagle.adapter.common.NodeAdapterInterface;
import org.fiteagle.adapter.common.OpenstackResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.SSHAccessable;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;
import org.fiteagle.interactors.sfa.rspec.advertisement.NodeContents.SliverType;
import org.fiteagle.interactors.sfa.rspec.ext.Property;
import org.fiteagle.interactors.sfa.rspec.ext.Resource;
import org.fiteagle.interactors.sfa.rspec.ext.openstack.Flavor;
import org.fiteagle.interactors.sfa.rspec.ext.openstack.Flavors;
import org.fiteagle.interactors.sfa.rspec.ext.openstack.Image;
import org.fiteagle.interactors.sfa.rspec.ext.openstack.OpenstackResource;

public class AdvertisementRspecTranslator extends SFAv3RspecTranslator {

	private final String adRspecNamespace = "http://www.geni.net/resources/rspec/3";
	private final String adRspecSchema = "http://www.geni.net/resources/rspec/3/ad.xsd";
	private final ArrayList<String> adRspecExtensions = new ArrayList<String>();
	private final String RSPEC_EXTENSION = "http://fiteagle.org/rspec/ext/1";

	public AdvertisementRspecTranslator() {
		super();
		addAdRspecExtension(this.RSPEC_EXTENSION);
	}

	// TODO: mapping between type of resource and sfa type, waiting for ontology
	// based solution
	public RSpecContents getAdvertisedRSpec(
			List<ResourceAdapter> resourceAdapters) {
		SFAv3RspecTranslator translator = new SFAv3RspecTranslator();
		RSpecContents adRSpecContents = new RSpecContents();
		adRSpecContents.setType("advertisement");
		List<Object> rspecElements = adRSpecContents.getAnyOrNodeOrLink();
		for (ResourceAdapter ra : resourceAdapters) {
			if (ra instanceof OpenstackResourceAdapter) {
				Object resource = translator
						.translateOpenstackResourceAdapterToAdvertisementOpenstackResource((OpenstackResourceAdapter) ra);
				rspecElements.add(resource);
			} else if (ra instanceof NodeAdapterInterface) {
				Object node = translateNodeAdapterInterfaceToAdvertisementNode(ra);
				rspecElements.add(node);
			} else if (ra instanceof SSHAccessable) {
				Object node = translateSSHAccesableToAdvertisementNode(ra);
				rspecElements.add(node);
			} else {
				Object resource = translateToFITeagleResource(ra);
				rspecElements.add(resource);
			}
		}
		return adRSpecContents;
	}

	private Object translateNodeAdapterInterfaceToAdvertisementNode(
			ResourceAdapter resourceAdapter) {
		// TODO: check here the constraints!!

		NodeContents node = new NodeContents();// this is the respons
												// representing openstack as
												// resource
		
		node.setComponentId(COMPONENT_ID_PREFIX + "+node+"
				+ resourceAdapter.getId());
		node.setComponentManagerId(COMPONENT_MANAGER_ID);
		node.setComponentName(resourceAdapter.getId());
		
		
		
		
		List<Object> sliverTypes = node.getAnyOrRelationOrLocation();// these
																		// are
																		// the
																		// images
																		// defined
																		// in a
																		// sliver
																		// type

		NodeAdapterInterface nodeAdapter = (NodeAdapterInterface) resourceAdapter;

		List<OpenstackResourceAdapter> openstackAdaptersContainingImages = nodeAdapter
				.getImages();

		OpenstackResourceAdapter openstackAdapter = openstackAdaptersContainingImages
				.get(0);

		// openstackAdapter.getFlavorsProperties();

		// get the openstack resources as openstack resource def..
		JAXBElement<OpenstackResource> openStackResourceAsJaxb = (JAXBElement<OpenstackResource>) new SFAv3RspecTranslator()
				.translateOpenstackResourceAdapterToAdvertisementOpenstackResource((OpenstackResourceAdapter) openstackAdapter);
		OpenstackResource openstackResource = openStackResourceAsJaxb
				.getValue();

		// flavors as sliver type and immages every one of these
		Flavors existingFlavors = openstackResource.getFlavors();
		// TODO: set existing flavors as sliver types in node

		List<Flavor> flavorsList = existingFlavors.getFlavor();

		// add in all flavors as sliver types. all images(of openstack
		// resources) as disk images.
		for (Iterator iterator = flavorsList.iterator(); iterator.hasNext();) {
			Flavor flavor = (Flavor) iterator.next();
			// TODO set this in node as sliver type!!
			SliverType sliverType = new NodeContents.SliverType();
			sliverType.setName(flavor.getName());
			sliverTypes
					.add(new org.fiteagle.interactors.sfa.rspec.advertisement.ObjectFactory()
							.createNodeContentsSliverType(sliverType)); // add
																		// to
																		// the
																		// node
																		// the
																		// through
																		// flavors
			// defined sliver type
		}

		// set all disk images in a for loop into every sliver type

		for (Iterator iterator = openstackAdaptersContainingImages.iterator(); iterator
				.hasNext();) {
			OpenstackResourceAdapter openstackResourceAdapterContainingDiskImage = (OpenstackResourceAdapter) iterator
					.next();

//			HashMap<String, String> imageProperties = openstackResourceAdapterContainingDiskImage
//					.getImageProperties();

			// add for every sliverType the current image
			for (Iterator iterator2 = sliverTypes.iterator(); iterator2
					.hasNext();) {
				JAXBElement<NodeContents.SliverType> sliverTypeJaxb = (JAXBElement<NodeContents.SliverType>) iterator2
						.next();
				SliverType sliverType = sliverTypeJaxb.getValue();

				JAXBElement<OpenstackResource> openStackResourceContainingImageAsJaxb = (JAXBElement<OpenstackResource>) new SFAv3RspecTranslator()
						.translateOpenstackResourceAdapterToAdvertisementOpenstackResource((OpenstackResourceAdapter) openstackResourceAdapterContainingDiskImage);
				OpenstackResource openStackResourceContainingImage = openStackResourceContainingImageAsJaxb
						.getValue();
				Image openstackResourceImage = openStackResourceContainingImage
						.getImage();

				NodeContents.SliverType.DiskImage diskImage = new NodeContents.SliverType.DiskImage();
				diskImage.setName(openstackResourceImage.getName());
				sliverType.getAnyOrDiskImage().add(new org.fiteagle.interactors.sfa.rspec.advertisement.ObjectFactory().createNodeContentsSliverTypeDiskImage(diskImage));
			}

			// diskImages = sliverType.getAnyOrDiskImage();
			//
			//
			// // diskImage.set
			//
			// // openstackResource.setResourceId(resourceAdapter.getId());
			// Image image =
			// openstackResource.getImageFromOpenstackAdapter(resourceAdapter);
			// openstackResource.setImage(image);
			//
			// Flavors flavors =
			// getFlavorsFromOpenstackAdapter(resourceAdapter);
			//
			//
			// openstackResource.setFlavors(flavors);
			//
			// return new
			// org.fiteagle.interactors.sfa.rspec.ext.openstack.ObjectFactory()
			// .createOpenstackResource(openstackResource);

		}
		AvailableContents available = new AvailableContents();
		available.setNow(resourceAdapter.isAvailable());
		//add the availability into the node
		sliverTypes.add(new org.fiteagle.interactors.sfa.rspec.advertisement.ObjectFactory().createAvailable(available));
		
		return new org.fiteagle.interactors.sfa.rspec.advertisement.ObjectFactory()
				.createNode(node);
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
		
		
		//TODO: this is just to test
				//if dummy...
				//this is just to test
				
				if(resourceAdapter.getType()!=null && resourceAdapter.getType().compareTo("org.fiteagle.adapter.dummyNode.DummyNodeAdapter")==0){
					
					if(resourceAdapterProperties != null && resourceAdapterProperties.get("sliverTypeName")!=null){
						SliverType sliverType = new SliverType();
						sliverType.setName((String)resourceAdapter.getProperties().get("sliverTypeName"));
						nodeContent.add(factory.createNodeContentsSliverType(sliverType));
					}
				}
				//this is just to test
		

		return new ObjectFactory().createNode(node);
	}

	public String getAdvertisedRSpecString(RSpecContents rspec) {
		String advertisedRspecSTR = "";

		JAXBElement<RSpecContents> rspecElem = new ObjectFactory()
				.createRspec(rspec);

		try {
			advertisedRspecSTR = getAdvertisedString(rspecElem);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return advertisedRspecSTR;
	}

	// TODO: this doesn't work!!!
	private String getAdvertisedString(Object jaxbObject) throws JAXBException {
		JAXBContext context = JAXBContext
				.newInstance("org.fiteagle.interactors.sfa.rspec.advertisement:org.fiteagle.interactors.sfa.rspec.ext:org.fiteagle.interactors.sfa.rspec.ext.openstack");
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
