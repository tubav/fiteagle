package org.fiteagle.interactors.sfa.rspec;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.fiteagle.adapter.common.Named;
import org.fiteagle.adapter.common.OpenstackResourceAdapter;
import org.fiteagle.adapter.common.Publish;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.ResourceAdapterManager;
import org.fiteagle.core.config.InterfaceConfiguration;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.rspec.ext.openstack.Flavor;
import org.fiteagle.interactors.sfa.rspec.ext.openstack.Flavors;
import org.fiteagle.interactors.sfa.rspec.ext.openstack.Image;
import org.fiteagle.interactors.sfa.rspec.ext.openstack.OpenstackResource;
import org.fiteagle.interactors.sfa.rspec.ext.openstack.Vm;
import org.fiteagle.interactors.sfa.rspec.ext.Method;
import org.fiteagle.interactors.sfa.rspec.ext.Parameter;
import org.fiteagle.interactors.sfa.rspec.ext.Property;
import org.fiteagle.interactors.sfa.rspec.ext.Resource;


public class SFAv3RspecTranslator {
	//
	protected static final String COMPONENT_ID_PREFIX = "urn:publicid:IDN+"
			+ InterfaceConfiguration.getInstance().getDomain();
	protected static final String COMPONENT_MANAGER_ID = InterfaceConfiguration
			.getInstance().getCM_URN();
	private final Geni_RSpec_Version geni_rspec_version;


	//
	public SFAv3RspecTranslator() {
		geni_rspec_version = new Geni_RSpec_Version();
		geni_rspec_version.setType("GENI");
		geni_rspec_version.setVersion("3");

	}

	public String getVersion() {
		return this.geni_rspec_version.getVersion();
	}

	


	public String getType() {
		return this.geni_rspec_version.getType();
	}
	
	
	private List<Method> getResourceAdapterRspecMethods(
			ResourceAdapter resourceAdapter) {
		ArrayList<Method> result = new ArrayList<Method>();
		java.lang.reflect.Method[] resourceAdapterMethods = resourceAdapter
				.getClass().getDeclaredMethods();
		for (int i = 0; i < resourceAdapterMethods.length; i++) {
			java.lang.reflect.Method method = resourceAdapterMethods[i];
			if (method.isAnnotationPresent(Publish.class)) {
				Method tmpRspecMethod = new Method();

				tmpRspecMethod.setName(method.getName());
				tmpRspecMethod.setReturnType(method.getReturnType().getName());
				if (!getRspecParametersFromResourceAdapterMethod(method)
						.isEmpty()) {
					tmpRspecMethod
							.getParameter()
							.addAll(getRspecParametersFromResourceAdapterMethod(method));
				}
				result.add(tmpRspecMethod);
			}
		}
		return result;

	}
	
public Object translateToFITeagleResource(ResourceAdapter resourceAdapter) {
		
		Resource fiteagleSFAResource = new Resource();

		fiteagleSFAResource.getMethod().addAll(
				this.getResourceAdapterRspecMethods(resourceAdapter));

		fiteagleSFAResource.setName(resourceAdapter.getType());

		HashMap<String, Object> resourceAdapterProperties = resourceAdapter
				.getProperties();

		if (resourceAdapterProperties != null) {

			Set<String> propKeys = resourceAdapterProperties.keySet();

			for (Iterator<String> iterator = propKeys.iterator(); iterator
					.hasNext();) {
				Property tmpProperty = new Property();
				String key = iterator.next();
				tmpProperty.setName(key);
				// The resource adaptor properties must have a string
				// representation.
				tmpProperty.setValue(resourceAdapterProperties.get(key)
						.toString());
				fiteagleSFAResource.getProperty().add(tmpProperty);
			}

		}

		Property typeProperty = new Property();
		typeProperty.setName("type");
		// idProperty.setType("string");
		typeProperty.setValue(resourceAdapter.getType());
		fiteagleSFAResource.getProperty().add(typeProperty);

		Property idProperty = new Property();
		idProperty.setName("id");
		// idProperty.setType("string");
		idProperty.setValue(resourceAdapter.getId());
		fiteagleSFAResource.getProperty().add(idProperty);

		return new org.fiteagle.interactors.sfa.rspec.ext.ObjectFactory().createResource(fiteagleSFAResource);
	}



	private ArrayList<Parameter> getRspecParametersFromResourceAdapterMethod(
			java.lang.reflect.Method method) {

		ArrayList<Parameter> result = new ArrayList<Parameter>();
		Class<?>[] paramTypes = method.getParameterTypes();
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		for (int i = 0; i < paramTypes.length; i++) {
			Parameter rspecParameter = new Parameter();
			rspecParameter.setType(paramTypes[i].getName());
			if (parameterAnnotations[i][0] != null
					&& parameterAnnotations[i][0].annotationType().equals(
							Named.class)) {
				rspecParameter.setName(((Named) parameterAnnotations[i][0])
						.name());
			} else {

				rspecParameter.setName("arg0" + paramTypes[i].getName());

			}
			result.add(rspecParameter);
		}
		return result;
	}

	public ResourceAdapter translateResourceToResourceAdapter(Resource object) {
		List<Property> properties = object.getProperty();

		ResourceAdapter resource;
		HashMap<String, Object> resourceProperties = new HashMap<String, Object>();
		String id = "";
		for (Iterator iterator = properties.iterator(); iterator.hasNext();) {
			Property property = (Property) iterator.next();
			if (property.getName().compareToIgnoreCase("id") == 0) {
				id = property.getValue();

				break;
			}
		}
		resource = ResourceAdapterManager.getInstance()
				.getResourceAdapterInstance(id);
		return resource;
	}

	public String translateResourceIdToSliverUrn(String id, String sliceUrn) {
		String[] str = sliceUrn.split("\\+slice\\+");
		String response = str[0] + "+sliver+" + id;
		return response;
	}

	public String getIdFromSliverUrn(String urn) {
		String[] str = urn.split("\\+sliver\\+");
		return str[1];
	}


	public Object translateOpenstackResourceAdapterToAdvertisementOpenstackResource(
			OpenstackResourceAdapter resourceAdapter) {

		// TODO: check here the constraints!!
		OpenstackResource openstackResource = new OpenstackResource();

		openstackResource.setResourceId(resourceAdapter.getId());

		Image image = getImageFromOpenstackAdapter(resourceAdapter);
		openstackResource.setImage(image);

		Flavors flavors = getFlavorsFromOpenstackAdapter(resourceAdapter);

		openstackResource.setFlavors(flavors);

		return new org.fiteagle.interactors.sfa.rspec.ext.openstack.ObjectFactory()
				.createOpenstackResource(openstackResource);
	}

	public Flavors getFlavorsFromOpenstackAdapter(
			OpenstackResourceAdapter resourceAdapter) {

		Flavors flavors = new Flavors();
		List<Flavor> flavorList = flavors.getFlavor();

		List<HashMap<String, String>> flavorsProperties = resourceAdapter
				.getFlavorsProperties();

		for (Iterator iterator = flavorsProperties.iterator(); iterator
				.hasNext();) {
			HashMap<String, String> hashMap = (HashMap<String, String>) iterator
					.next();

			Flavor flavor = new Flavor();

			flavor.setDisk(hashMap.get(OpenstackResourceAdapter.FLAVOR_DISK));
			flavor.setId(hashMap.get(OpenstackResourceAdapter.FLAVOR_ID));
			flavor.setName(hashMap.get(OpenstackResourceAdapter.FLAVOR_NAME));
			flavor.setOsFlavorAccessIsPublic(new Boolean(
					hashMap.get(OpenstackResourceAdapter.FLAVOR_OSFLAVORACCESSISPUBLIC)));
			flavor.setOSFLVDISABLED(new Boolean(hashMap
					.get(OpenstackResourceAdapter.FLAVOR_OSFLVDISABLED)));
			flavor.setOSFLVEXTDATAEphemeral(Integer.valueOf(hashMap
					.get(OpenstackResourceAdapter.FLAVOR_OSFLVEXTDATAEPHEMERAL)));
			flavor.setRam(hashMap.get(OpenstackResourceAdapter.FLAVOR_RAM));
			flavor.setRxtxFactor(Float.valueOf(hashMap
					.get(OpenstackResourceAdapter.FLAVOR_RXTXFACTOR)));
			flavor.setSwap(hashMap.get(OpenstackResourceAdapter.FLAVOR_SWAP));
			flavor.setVcpus(hashMap.get(OpenstackResourceAdapter.FLAVOR_VCPUS));

			flavorList.add(flavor);
		}

		return flavors;
	}

	private Image getImageFromOpenstackAdapter(
			OpenstackResourceAdapter resourceAdapter) {
		Image image = new Image();
		HashMap<String, String> imageProperties = resourceAdapter
				.getImageProperties();
		image.setId(imageProperties.get(resourceAdapter.IMAGE_ID));
		image.setName(imageProperties.get(resourceAdapter.IMAGE_NAME));
		if(imageProperties.get(resourceAdapter.IMAGE_MINDISK)!=null)
		image.setMinDisk(Integer.valueOf(imageProperties.get(resourceAdapter.IMAGE_MINDISK)));
		
		if(imageProperties.get(resourceAdapter.IMAGE_CREATED)!=null && imageProperties.get(resourceAdapter.IMAGE_CREATED)!="")
			image.setCreated(convertLongToXMLGregCal(Long.parseLong(imageProperties.get(resourceAdapter.IMAGE_CREATED))));
		
		if(imageProperties.get(resourceAdapter.IMAGE_MINRAM)!=null)
		image.setMinRam(Integer.valueOf(imageProperties
				.get(resourceAdapter.IMAGE_MINRAM)));
		if(imageProperties.get(resourceAdapter.IMAGE_OSEXTIMG_SIZE)!=null)
		image.setOSEXTIMGSIZE(Double.valueOf(imageProperties
				.get(resourceAdapter.IMAGE_OSEXTIMG_SIZE)));
		if(imageProperties.get(resourceAdapter.IMAGE_PROGRESS)!=null)
		image.setProgress(Integer.valueOf(imageProperties
				.get(resourceAdapter.IMAGE_PROGRESS)));
		image.setStatus(imageProperties.get(resourceAdapter.IMAGE_STATUS));
		if(imageProperties.get(resourceAdapter.IMAGE_UPDATED)!=null && imageProperties.get(resourceAdapter.IMAGE_UPDATED)!="")
			image.setUpdated(convertLongToXMLGregCal(Long.parseLong(imageProperties.get(resourceAdapter.IMAGE_UPDATED))));
		
		return image;
	}

	private XMLGregorianCalendar convertLongToXMLGregCal(long timeInMilis) {
		
		GregorianCalendar gregCal = new GregorianCalendar();
		gregCal.setTime(new Date(timeInMilis));
		XMLGregorianCalendar xmlGregCal = null;
		try {
			xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregCal);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return xmlGregCal;
	}


	public Object translateToOpenstackResource(ResourceAdapter resourceAdapter) {
		OpenstackResourceAdapter openstackresourceAdapter = (OpenstackResourceAdapter) resourceAdapter;
		OpenstackResource resource = new OpenstackResource();

		resource.setResourceId(resourceAdapter.getId());

		Image image = getImageFromOpenstackAdapter(openstackresourceAdapter);
		Flavors flavors = getFlavorsFromOpenstackAdapter(openstackresourceAdapter);
		Vm vm = getVMFromOpenstackAdapter(openstackresourceAdapter);

		resource.setVm(vm);
		return new org.fiteagle.interactors.sfa.rspec.ext.openstack.ObjectFactory().createOpenstackResource(resource);
	}

	private Vm getVMFromOpenstackAdapter(
			OpenstackResourceAdapter openstackresourceAdapter) {
		Vm vm = new Vm();

		HashMap<String, String> vmProperties = openstackresourceAdapter
				.getVMProperties();

		if (vmProperties.get(OpenstackResourceAdapter.VM_AccessIPv4) != null)
			vm.setAccessIPv4(vmProperties
					.get(OpenstackResourceAdapter.VM_AccessIPv4));

		if (vmProperties.get(OpenstackResourceAdapter.VM_AccessIPv6) != null)
			vm.setAccessIPv6(vmProperties
					.get(OpenstackResourceAdapter.VM_AccessIPv6));

		if (vmProperties.get(OpenstackResourceAdapter.VM_ConfigDrive) != null)
			vm.setConfigDrive(vmProperties
					.get(OpenstackResourceAdapter.VM_ConfigDrive));

		if (vmProperties.get(OpenstackResourceAdapter.VM_FlavorId) != null)
			vm.setFlavorId(vmProperties
					.get(OpenstackResourceAdapter.VM_FlavorId));

		if (vmProperties.get(OpenstackResourceAdapter.VM_HostId) != null)
			vm.setHostId(vmProperties.get(OpenstackResourceAdapter.VM_HostId));

		if (vmProperties.get(OpenstackResourceAdapter.VM_Id) != null)
			vm.setId(vmProperties.get(OpenstackResourceAdapter.VM_Id));

		if (vmProperties.get(OpenstackResourceAdapter.VM_ImageId) != null)
			vm.setImageId(vmProperties.get(OpenstackResourceAdapter.VM_ImageId));

		if (vmProperties.get(OpenstackResourceAdapter.VM_KeyName) != null)
			vm.setKeyName(vmProperties.get(OpenstackResourceAdapter.VM_KeyName));

		if (vmProperties.get(OpenstackResourceAdapter.VM_Name) != null)
			vm.setName(vmProperties.get(OpenstackResourceAdapter.VM_Name));

		if (vmProperties.get(OpenstackResourceAdapter.VM_OSDCFDiskConfig) != null)
			vm.setOSDCFDiskConfig(vmProperties
					.get(OpenstackResourceAdapter.VM_OSDCFDiskConfig));

		if (vmProperties
				.get(OpenstackResourceAdapter.VM_OSEXTAZAvailabilityZone) != null)
			vm.setOSEXTAZAvailabilityZone(vmProperties
					.get(OpenstackResourceAdapter.VM_OSEXTAZAvailabilityZone));

		if (vmProperties.get(OpenstackResourceAdapter.VM_OSEXTSTSPowerState) != null)
			vm.setOSEXTSTSPowerState(vmProperties
					.get(OpenstackResourceAdapter.VM_OSEXTSTSPowerState));

		if (vmProperties.get(OpenstackResourceAdapter.VM_OSEXTSTSTaskState) != null)
			vm.setOSEXTSTSTaskState(vmProperties
					.get(OpenstackResourceAdapter.VM_OSEXTSTSTaskState));

		if (vmProperties.get(OpenstackResourceAdapter.VM_OSEXTSTSVmState) != null)
			vm.setOSEXTSTSVmState(vmProperties
					.get(OpenstackResourceAdapter.VM_OSEXTSTSVmState));

		if (vmProperties.get(OpenstackResourceAdapter.VM_Progress) != null)
			vm.setProgress(Integer.valueOf(vmProperties
					.get(OpenstackResourceAdapter.VM_Progress)));

		if (vmProperties.get(OpenstackResourceAdapter.VM_Status) != null)
			vm.setStatus(vmProperties.get(OpenstackResourceAdapter.VM_Status));

		if (vmProperties.get(OpenstackResourceAdapter.VM_TenantId) != null)
			vm.setTenantId(vmProperties
					.get(OpenstackResourceAdapter.VM_TenantId));


		if (vmProperties.get(OpenstackResourceAdapter.VM_UserId) != null)
			vm.setUserId(vmProperties.get(OpenstackResourceAdapter.VM_UserId));

		if (vmProperties.get(OpenstackResourceAdapter.VM_FloatingIP) != null)
			vm.setAccessIPv4(vmProperties
					.get(OpenstackResourceAdapter.VM_FloatingIP));

		return vm;
	}
}
