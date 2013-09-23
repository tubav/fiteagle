package org.fiteagle.interactors.sfa.rspec;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.fiteagle.adapter.common.Named;
import org.fiteagle.adapter.common.Publish;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.core.config.InterfaceConfiguration;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.rspec.ext.Method;
import org.fiteagle.interactors.sfa.rspec.ext.Parameter;
import org.fiteagle.interactors.sfa.rspec.ext.Property;
import org.fiteagle.interactors.sfa.rspec.ext.Resource;


public class SFAv3RspecTranslator {
	//
	protected static final String COMPONENT_ID_PREFIX = "urn:publicid:IDN+"
			+ InterfaceConfiguration.getInstance().getDomain();
	protected static final String COMPONENT_MANAGER_ID = InterfaceConfiguration
			.getInstance().getAM_URN();
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
			rspecParameter.setName(((Named)parameterAnnotations[i][0]).name());
		} else {

			rspecParameter.setName("arg0" + paramTypes[i].getName());

		}
		result.add(rspecParameter);
	}
	return result;
}
}