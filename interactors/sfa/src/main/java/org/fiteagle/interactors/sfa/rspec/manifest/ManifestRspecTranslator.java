package org.fiteagle.interactors.sfa.rspec.manifest;

import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.fiteagle.adapter.common.Named;
import org.fiteagle.adapter.common.NodeAdapterInterface;
import org.fiteagle.adapter.common.OpenstackResourceAdapter;
import org.fiteagle.adapter.common.Publish;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.SSHAccessable;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;
import org.fiteagle.interactors.sfa.rspec.ext.Method;
import org.fiteagle.interactors.sfa.rspec.ext.Parameter;
import org.fiteagle.interactors.sfa.rspec.ext.Property;
import org.fiteagle.interactors.sfa.rspec.ext.Resource;
import org.fiteagle.interactors.sfa.rspec.request.NodeContents;

public class ManifestRspecTranslator extends SFAv3RspecTranslator {

	public RSpecContents getManifestRSpec(List<ResourceAdapter> resourceAdapters) {
		RSpecContents manifestRspec = getRSpecFromAdapters(resourceAdapters);
		manifestRspec.setType(RspecTypeContents.MANIFEST);
		return manifestRspec;
	}

	public RSpecContents getRSpecFromAdapters(
			List<ResourceAdapter> resourceAdapters) {
		RSpecContents manifestRspec = new RSpecContents();

		List<Object> rspecContentElements = manifestRspec
				.getAnyOrNodeOrLink();

		for (ResourceAdapter resourceAdapter : resourceAdapters) {
			Object resource;
			
			if (resourceAdapter instanceof OpenstackResourceAdapter)
				resource = new SFAv3RspecTranslator().translateToOpenstackResource(resourceAdapter);
			else if (resourceAdapter instanceof SSHAccessable)
				resource = translateToNode(resourceAdapter);
			else if (resourceAdapter instanceof NodeAdapterInterface)
				resource = translateToOpenstackNode(resourceAdapter);
			else
				resource = translateToFITeagleResource(resourceAdapter);
			rspecContentElements.add(resource);
		}
		return manifestRspec;
	}
	

	private Object translateToOpenstackNode(ResourceAdapter resourceAdapter) {
		
		
		NodeAdapterInterface openstackNodeResourceAdapter = (NodeAdapterInterface) resourceAdapter;
		
		RSpecContents manifestRspec = new RSpecContents();

		List<Object> rspecContentElements = manifestRspec.getAnyOrNodeOrLink();
		
		
		org.fiteagle.interactors.sfa.rspec.manifest.NodeContents node = new org.fiteagle.interactors.sfa.rspec.manifest.NodeContents();
		List<Object> nodeContentsList = node.getAnyOrRelationOrLocation();
		
		List<OpenstackResourceAdapter> allocatedVms = openstackNodeResourceAdapter.getVms();
		
		for (Iterator iterator = allocatedVms.iterator(); iterator.hasNext();) {
			OpenstackResourceAdapter openstackResourceAdapter = (OpenstackResourceAdapter) iterator
					.next();
			
			org.fiteagle.interactors.sfa.rspec.manifest.NodeContents.SliverType tmpSliverType = new org.fiteagle.interactors.sfa.rspec.manifest.NodeContents.SliverType();
			org.fiteagle.interactors.sfa.rspec.manifest.DiskImageContents tmpDiskImage = new org.fiteagle.interactors.sfa.rspec.manifest.DiskImageContents();
			
			tmpDiskImage.setName(openstackResourceAdapter.getImageProperties().get(OpenstackResourceAdapter.IMAGE_NAME));
			tmpSliverType.setName(getFlavorNameOverFlavorId(openstackResourceAdapter.getFlavorId(), openstackResourceAdapter.getFlavorsProperties()));
			
			tmpSliverType.getAnyOrDiskImage().add(new org.fiteagle.interactors.sfa.rspec.manifest.ObjectFactory().createDiskImage(tmpDiskImage));
			nodeContentsList.add(new org.fiteagle.interactors.sfa.rspec.manifest.ObjectFactory().createNodeContentsSliverType(tmpSliverType));
			
			//TODO: add if the ip exists into the response!!!!!!!!!!11
			//TODO: if vmproperties are set the ip can be there!
			
		}

		return new org.fiteagle.interactors.sfa.rspec.manifest.ObjectFactory().createNode(node);
	}

	private String getFlavorNameOverFlavorId(String flavorId,
			List<HashMap<String, String>> flavorsProperties) {
		
		for (Iterator iterator = flavorsProperties.iterator(); iterator
				.hasNext();) {
			HashMap<String, String> hashMap = (HashMap<String, String>) iterator
					.next();
			
			if(hashMap.get(OpenstackResourceAdapter.FLAVOR_ID).compareTo(flavorId)==0) return hashMap.get(OpenstackResourceAdapter.FLAVOR_NAME); 
			
		}
		
		return null;
	}

	public Object translateToNode(ResourceAdapter resourceAdapter) {

		org.fiteagle.interactors.sfa.rspec.manifest.NodeContents node = new org.fiteagle.interactors.sfa.rspec.manifest.NodeContents();
		HashMap<String, Object> resourceAdapterProperties = resourceAdapter
				.getProperties();

		if (resourceAdapterProperties != null) {
			ObjectFactory factory = new ObjectFactory();
			Set<String> propKeys = resourceAdapterProperties.keySet();
			node.setComponentId(COMPONENT_ID_PREFIX + "+node+"
					+ resourceAdapter.getId());
			node.setComponentManagerId(COMPONENT_MANAGER_ID);
			node.setExclusive(resourceAdapter.isExclusive());
			List<Object> nodeContent = node.getAnyOrRelationOrLocation();

			LocationContents location = new LocationContents();
			location.setCountry((String) resourceAdapterProperties
					.get("country"));
			location.setLatitude((String) resourceAdapterProperties
					.get("latitude"));
			location.setLongitude((String) resourceAdapterProperties
					.get("longitude"));
			nodeContent.add(factory.createLocation(location));

			if (resourceAdapter instanceof SSHAccessable) {
				SSHAccessable sshAccessableResource = (SSHAccessable) resourceAdapter;

				HardwareTypeContents hardwareType = new HardwareTypeContents();
				hardwareType.setName(sshAccessableResource.getHardwareType());
				nodeContent.add(factory.createHardwareType(hardwareType));

				List<Object> services = node.getAnyOrRelationOrLocation();
				ServiceContents service = new ServiceContents();
				List<Object> logins = service.getAnyOrLoginOrInstall();
				LoginServiceContents login = new LoginServiceContents();

				login.setAuthentication("ssh-keys");
				login.setHostname(sshAccessableResource.getIp());
				login.setPort(sshAccessableResource.getPort());

				logins.add(new ObjectFactory().createLogin(login));

				services.add(new ObjectFactory().createServices(service));
				// TODO: add node properties
			}
		}

		return new ObjectFactory().createNode(node);
	}

	public String getRSpecString(RSpecContents rspec) {
		String manifestRspecString = "";

		JAXBElement<RSpecContents> rspecElem = new ObjectFactory()
				.createRspec(rspec);

		try {
			manifestRspecString = getString(rspecElem);
		} catch (JAXBException e) {

		}

		// result.setValue(advertisedRspecSTR);
		return manifestRspecString;
	}

	private String getString(Object jaxbObject) throws JAXBException {
		JAXBContext context = JAXBContext
				.newInstance("org.fiteagle.interactors.sfa.rspec.manifest:org.fiteagle.interactors.sfa.rspec.ext:org.fiteagle.interactors.sfa.rspec.ext.openstack");
		Marshaller marshaller = context.createMarshaller();
		StringWriter stringWriter = new StringWriter();
		marshaller.marshal(jaxbObject, stringWriter);

		return stringWriter.toString();

	}

}
