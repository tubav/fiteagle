package org.fiteagle.interactors.sfa.rspec;

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
import org.fiteagle.adapter.stopwatch.StopwatchAdapter;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.describe.DescribeValue;
import org.fiteagle.interactors.sfa.describe.GeniSlivers;

public class SFAv3RspecTranslator {

	private final Geni_RSpec_Version geni_rspec_version;
	private final String adRspecNamespace = "http://www.geni.net/resources/rspec/3";
	private final String adRspecSchema = "http://www.geni.net/resources/rspec/3/ad.xsd";
	private final ArrayList<String> adRspecExtensions = new ArrayList<String>();

	private final String requestRspecNamespace = "http://www.geni.net/resources/rspec/3";
	private final String requestRspecSchema = "http://www.geni.net/resources/rspec/3/request.xsd";
	private final ArrayList<String> requestRspecExtensions = new ArrayList<String>();

	private final String RSPEC_EXTENSION = "http://www.fiteagle.org/rspec/ext/1";
	public SFAv3RspecTranslator() {
		geni_rspec_version = new Geni_RSpec_Version();
		geni_rspec_version.setType("GENI");
		geni_rspec_version.setVersion("3");
		addAdRspecExtension(this.RSPEC_EXTENSION);
		addRequestRspecExtension(RSPEC_EXTENSION);
	}

	public String getAdRspecNamespace() {
		return adRspecNamespace;
	}

	public String getAdRspecSchema() {
		return adRspecSchema;
	}

	public String[] getAdRspecExtensions() {
		return adRspecExtensions.toArray(new String[adRspecExtensions.size()]);
	}

	public String getRequestRspecNamespace() {
		return requestRspecNamespace;
	}

	public String getRequestRspecSchema() {
		return requestRspecSchema;
	}

	public String[] getRequestRspecExtensions() {
		return requestRspecExtensions.toArray(new String[requestRspecExtensions.size()]);
	}

	public String getType() {
		return this.geni_rspec_version.getType();
	}

	public String getVersion() {
		return this.geni_rspec_version.getVersion();
	}

	private void addRequestRspecExtension(String extension){
		requestRspecExtensions.add(extension);
	}
	
	private void addAdRspecExtension(String extension){
		adRspecExtensions.add(extension);
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

			for (Iterator<String> iterator = propKeys.iterator(); iterator.hasNext();) {
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

	//TODO: only for static test. Implement slice(sliver) management.
	public DescribeValue getDescription(List<String> urns) {
		
		DescribeValue result = new DescribeValue();
		result.setGeni_rspec(createTestGeniManifestRspec());
		result.setGeni_slivers(createTestGeniSlivers());
		result.setGeni_urn("urn:publicid:IDN+fiteagletest+slice+testtest");
		
		return result;
	}

	// helper methods for static test. 
	
	private List<GeniSlivers> createTestGeniSlivers() {
		ArrayList<GeniSlivers> result= new ArrayList<GeniSlivers>();
		
		GeniSlivers testGeniSlivers1 = new GeniSlivers();
		testGeniSlivers1.setGeni_sliver_urn("urn:publicid:IDN+fiteagletest+sliver+123456");
		testGeniSlivers1.setGeni_expires("2019-09-22T22:00:00Z");
		testGeniSlivers1.setGeni_allocation_status("geni_allocated");
		testGeniSlivers1.setGeni_operational_status("geni_pending_allocation");

		GeniSlivers testGeniSlivers2 = new GeniSlivers();
		testGeniSlivers1.setGeni_sliver_urn("urn:publicid:IDN+fiteagletest+sliver+123457");
		testGeniSlivers1.setGeni_expires("2019-09-22T12:00:00Z");
		testGeniSlivers1.setGeni_allocation_status("geni_provisioned");
		testGeniSlivers1.setGeni_operational_status("geni_ready");
		
		return result;
	}

	
	private String createTestGeniManifestRspec() {
		RSpecContents manifestRspec = new RSpecContents();
		manifestRspec.setType("manifest");
		ResourceAdapter dummyResourceAdapter = new StopwatchAdapter();
		List<Object> rspecContentElements = manifestRspec.getAnyOrNodeOrLink();
		rspecContentElements.add(this.translateToFITeagleResource(dummyResourceAdapter));
		
		JAXBElement<RSpecContents> rspec = new ObjectFactory()
		.createRspec(manifestRspec);
		
		JAXBContext context;
		StringWriter stringWriter = new StringWriter();
		try {
			context = JAXBContext.newInstance("org.fiteagle.interactors.sfa.rspec");
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(rspec, stringWriter);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stringWriter.toString();
		
	}

}
