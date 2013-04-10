package org.fiteagle.interactors.sfa.rspec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;

public class SFAv3RspecTranslator {

	private final Geni_RSpec_Version geni_rspec_version;
	private final String adRspecNamespace = "http://www.geni.net/resources/rspec/3";
	private final String adRspecSchema = "http://www.geni.net/resources/rspec/3/ad.xsd";
	private final String[] adRspecExtensions = new String[] { "" };

	private final String requestRspecNamespace = "http://www.geni.net/resources/rspec/3";
	private final String requestRspecSchema = "http://www.geni.net/resources/rspec/3/request.xsd";
	private final String[] requestRspecExtensions = new String[] { "" };

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

	public Object translateToFITeagleResource(ResourceAdapter resourceAdapter) {

		Resource fiteagleSFAResource = new Resource();

		fiteagleSFAResource.getMethod().addAll(
				this.getResourceAdapterRspecMethods(resourceAdapter));

		fiteagleSFAResource.setName(resourceAdapter.getType());

		HashMap<String, Object> resourceAdapterProperties = resourceAdapter
				.getProperties();

		if (resourceAdapterProperties != null) {

			Set<String> propKeys = resourceAdapterProperties.keySet();

			for (Iterator iterator = propKeys.iterator(); iterator.hasNext();) {
				Property tmpProperty = new Property();
				String key = (String) iterator.next();
				tmpProperty.setName(key);
				// The resource adaptor properties must have a string
				// representation.
				tmpProperty.setValue(resourceAdapterProperties.get(key)
						.toString());
				fiteagleSFAResource.getProperty().add(tmpProperty);
				System.out.println("adding the property: "
						+ tmpProperty.getName());
			}

		}

		Property idProperty = new Property();
		idProperty.setName("id");
		idProperty.setType("string");
		idProperty.setValue(resourceAdapter.getId());
		fiteagleSFAResource.getProperty().add(idProperty);

		Property statusProperty = new Property();
		statusProperty.setName("status");
		statusProperty.setType("string");
		statusProperty.setValue(resourceAdapter.getStatus());
		fiteagleSFAResource.getProperty().add(statusProperty);

		// Property componentManagerIdProperty = new Property();
		// componentManagerIdProperty.setName("componentManagerId");
		// componentManagerIdProperty.setType("urn");
		// componentManagerIdProperty.setValue("urn:publicid:IDN+fiteagle+authority+am");
		// fiteagleSFSResource.setProperty(componentManagerIdProperty);
		//
		// Property componentIdProperty = new Property();
		// componentIdProperty.setName("componentId");
		// componentIdProperty.setType("urn");
		// componentIdProperty.setValue("urn:publicid:IDN+fiteagle+node+"+resourceAdapter.getName());
		// fiteagleSFSResource.setProperty(componentIdProperty);

		return new ObjectFactory().createResource(fiteagleSFAResource);
	}

	private List<Method> getResourceAdapterRspecMethods(
			ResourceAdapter resourceAdapter) {
		ArrayList<Method> result = new ArrayList<Method>();
		// TODO: fix this to generic!!
		java.lang.reflect.Method[] resourceAdapterMethods = resourceAdapter.getClass().getDeclaredMethods();
		for (int i = 0; i < resourceAdapterMethods.length; i++) {
			Method tmpRspecMethod = new Method();
			tmpRspecMethod.setName(resourceAdapterMethods[i].getName());
			tmpRspecMethod.setReturnType(resourceAdapterMethods[i]
					.getReturnType().getName());
			if (!getRspecParametersFromResourceAdapterMethod(
					resourceAdapterMethods[i]).isEmpty()) {
				tmpRspecMethod
						.getParameter()
						.addAll(getRspecParametersFromResourceAdapterMethod(resourceAdapterMethods[i]));
			}
			result.add(tmpRspecMethod);
		}
		return result;

	}

	private ArrayList<Parameter> getRspecParametersFromResourceAdapterMethod(
			java.lang.reflect.Method method) {

		ArrayList<Parameter> result = new ArrayList<Parameter>();
		Class<?>[] paramTypes = method.getParameterTypes();
		for (int i = 0; i < paramTypes.length; i++) {
			Parameter rspecParameter = new Parameter();
			rspecParameter.setType(paramTypes[0].getName());
			// TODO: consider starting in debug modus or using "Paranamer" to
			// get the method names.
			rspecParameter.setName("arg0" + paramTypes[0].getName());
			result.add(rspecParameter);
		}
		return result;
	}

}
