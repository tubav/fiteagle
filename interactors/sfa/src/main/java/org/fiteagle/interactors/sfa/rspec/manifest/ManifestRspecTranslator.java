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
import org.fiteagle.adapter.common.OpenstackResourceAdapter;
import org.fiteagle.adapter.common.Publish;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.SSHAccessable;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;
import org.fiteagle.interactors.sfa.rspec.ext.Method;
import org.fiteagle.interactors.sfa.rspec.ext.Parameter;
import org.fiteagle.interactors.sfa.rspec.ext.Property;
import org.fiteagle.interactors.sfa.rspec.ext.Resource;

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
			else
				resource = translateToFITeagleResource(resourceAdapter);
			rspecContentElements.add(resource);
		}
		return manifestRspec;
	}

	public Object translateToNode(ResourceAdapter resourceAdapter) {

		NodeContents node = new NodeContents();
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
