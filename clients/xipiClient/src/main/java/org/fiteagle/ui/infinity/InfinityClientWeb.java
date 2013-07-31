package org.fiteagle.ui.infinity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.fiteagle.ui.infinity.model.InfinityInfrastructure;
import org.fiteagle.ui.infinity.model.InfinityValueID;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class InfinityClientWeb extends InfinityClient {

	private static final String PATH_PORTLET = "/InfinityServices-portlet/json";
	private final WebResource service;
	private final WebResource path;

	public InfinityClientWeb(URI uri) {
		this.service = Client.create(new DefaultClientConfig()).resource(uri);
		this.path = service.path(PATH_PORTLET);
	}

	public InfinityInfrastructure getInfrastructuresById(Number id) {
		String methodName = InfinityClient.Methods.GET_INFRA_BY_ID.getValue();
		MultivaluedMapImpl queryParams = getDefaultQueryParams(methodName);
		queryParams.add("serviceParameters", "[id]");
		queryParams.add("id", id.toString());

		String jsonString = getJsonString(queryParams);

		try {
			return this.parser.parseGetInfrastructuresById(jsonString);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String getJsonString(MultivaluedMapImpl queryParams) {
		WebResource resource = path.queryParams(queryParams);
		Builder builder = resource.accept(MediaType.APPLICATION_JSON_TYPE);
		String jsonString = builder.get(String.class);
		
		InputStream fixedInputStream;
//		try {
//			fixedInputStream = fixEncoding(new ByteArrayInputStream(jsonString.getBytes("ISO-8859-1")));
			fixedInputStream = fixEncoding(new ByteArrayInputStream(jsonString.getBytes()));
//		} catch (UnsupportedEncodingException e) {
//			throw new RuntimeException(e.getMessage());
//		}
		return convertStreamToString(fixedInputStream);
	}

	private MultivaluedMapImpl getDefaultQueryParams(String methodName) {
		MultivaluedMapImpl queryParams = new MultivaluedMapImpl();
		queryParams.add("serviceClassName",
				"com.liferay.infinity.service.InfrastructureServiceUtil");
		queryParams.add("serviceMethodName", methodName);
		return queryParams;
	}

	@Override
	public ArrayList<InfinityValueID> searchInfrastructures() {
		String methodName = InfinityClient.Methods.SEARCH_INFRASTRUCTURES.getValue();
		MultivaluedMapImpl queryParams = getDefaultQueryParams(methodName);
		queryParams.add("serviceParameters", "%5btext,country,component%5d");

		String jsonString = getJsonString(queryParams);

		try {
			return this.parser.parseSearchInfrastructuresResponse(jsonString);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
