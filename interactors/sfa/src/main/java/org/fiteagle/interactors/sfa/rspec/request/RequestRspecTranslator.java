package org.fiteagle.interactors.sfa.rspec.request;

import java.util.ArrayList;

import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;

public class RequestRspecTranslator extends SFAv3RspecTranslator {
	private final String requestRspecNamespace = "http://www.geni.net/resources/rspec/3";
	private final String requestRspecSchema = "http://www.geni.net/resources/rspec/3/request.xsd";
	private final ArrayList<String> requestRspecExtensions = new ArrayList<String>();

	public String getRequestRspecNamespace() {
		return requestRspecNamespace;
	}

	public String getRequestRspecSchema() {
		return requestRspecSchema;
	}

	public String[] getRequestRspecExtensions() {
		return requestRspecExtensions.toArray(new String[requestRspecExtensions
				.size()]);
	}

	private void addRequestRspecExtension(String extension) {
		requestRspecExtensions.add(extension);
	}

}
