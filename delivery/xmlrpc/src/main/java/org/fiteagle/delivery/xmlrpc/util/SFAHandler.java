package org.fiteagle.delivery.xmlrpc.util;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fiteagle.interactors.sfa.ISFA;
import org.fiteagle.interactors.sfa.SFAInteractor_v3;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.AMValue;
import org.fiteagle.interactors.sfa.common.Credentials;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.listresources.ListResourceOptions;

import redstone.xmlrpc.XmlRpcArray;
import redstone.xmlrpc.XmlRpcInvocationHandler;
import redstone.xmlrpc.XmlRpcStruct;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SFAHandler implements XmlRpcInvocationHandler {

	ISFA interactor;

	public SFAHandler(SFAInteractor_v3 sfaInteractor_v3) {
		this.interactor = sfaInteractor_v3;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object invoke(String methodName, List parameters) throws Throwable {
		Map<String, Object> response = new HashMap<>();
		Method[] methodsFromHandler = interactor.getClass().getMethods();
		Method knownMethod = null;
		AMResult result = null;
		for (int i = 0; i < methodsFromHandler.length; i++) {
			if (methodsFromHandler[i].getName().equals(methodName)) {
				// Critical assumption !!! Only one method which equals the
				// methodname exists!
				// failure prone
				knownMethod = methodsFromHandler[i];
			}
		}
		if (knownMethod != null) {

			Class<?>[] parameterClasses = knownMethod.getParameterTypes();
			if (parameterClasses.length == 0) {
				// Method takes no parameters => nothing to do
				result = (AMResult) knownMethod.invoke(interactor,(Object[]) null);
			} else {
				// identify classes & instantiate objects

				List<Object> methodParameters = createEmptyMethodParameters(parameterClasses);
				if (methodParameters.size() == parameterClasses.length) {
					// should be, otherwise something is wrong
					for (int i = 0; i < parameterClasses.length; i++) {
						xmlStructToObject(parameters.get(i),
								methodParameters.get(i));
					}
				}
				result = (AMResult) knownMethod.invoke(interactor, methodParameters.toArray());
				postProcess(result, methodName);
			}

		}

		try {
			response = introspect(result);
		} catch (IOException ioException) {

			// TODO add logging
			System.err.println(ioException.getStackTrace());
		}

		return response;

	}

	private void xmlStructToObject(Object from, Object to) {
		if (to.getClass().isAssignableFrom(ListResourceOptions.class)) {
			XmlRpcStruct listResourceOptionsStruct = (XmlRpcStruct) from;
			ListResourceOptions listResourceOptions = (ListResourceOptions) to;

			// both booleans default to false if not set => no check for
			// existence necessary
			listResourceOptions.setGeni_available(listResourceOptionsStruct
					.getBoolean("geni_available"));
			listResourceOptions.setGeni_compressed(listResourceOptionsStruct
					.getBoolean("geni_compressed"));

			XmlRpcStruct geni_rspec_version_struct = listResourceOptionsStruct
					.getStruct("geni_rspec_version");
			if (geni_rspec_version_struct != null) {
				String type = geni_rspec_version_struct.getString("type");
				String version = geni_rspec_version_struct.getString("version");
				Geni_RSpec_Version geni_RSpec_Version = new Geni_RSpec_Version();
				geni_RSpec_Version.setType(type);
				geni_RSpec_Version.setVersion(version);

				listResourceOptions.setGeni_respec_version(geni_RSpec_Version);

			} else {
				// TODO error handling throw exception => set corresponding
				// error code of response
			}

		}
		if (to.getClass().isAssignableFrom(ListCredentials.class)) {
			XmlRpcArray listCredentialsArray = (XmlRpcArray) from;
			ListCredentials listCredentials = (ListCredentials) to;
			if (listCredentialsArray.size() > 0) {
				for (int i = 0; i < listCredentialsArray.size(); i++) {
					XmlRpcStruct credentialsStruct = (XmlRpcStruct) listCredentialsArray
							.get(i);
					Credentials credentials = new Credentials();
					if (credentialsStruct.getString("geni_type") != null) {
						credentials.setGeni_type(credentialsStruct
								.getString("geni_type"));
					} else {
						// TODO error handling
					}
					if (credentialsStruct.getString("geni_version") != null) {
						credentials.setGeni_version(credentialsStruct
								.getString("geni_version"));
					} else {
						// TODO error handling
					}
					if (credentialsStruct.getString("geni_value") != null) {
						credentials.setGeni_value(credentialsStruct
								.getString("geni_value"));
					} else {
						// TODO error handling
					}

					listCredentials.addCredentials(credentials);

				}
			}
		}

	}

	private List<Object> createEmptyMethodParameters(Class<?>[] parameterClasses)
			throws InstantiationException, IllegalAccessException {

		List<Object> returnList = new ArrayList<>();
		for (int i = 0; i < parameterClasses.length; i++) {
			Object o = parameterClasses[i].newInstance();
			returnList.add(o);
		}

		return returnList;
	}

	private void postProcess(AMResult result, String methodName) {

		processCode(result.getCode());
		processValue(result.getValue(), methodName);

	}

	private void processValue(AMValue value, String methodName) {
		// TODO set urn, set hostname, set hrn, set geni_api_versions (# from
		// interactor, url from server)

	}

	private AMCode processCode(AMCode code_result) {
		if(code_result == null)
			code_result = new AMCode();
		
		code_result.setAm_code(0);
		code_result.setAm_type("FITeagle");
		
		return code_result;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> introspect(Object result) throws IOException {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		final StringWriter writer = new StringWriter();

		mapper.writeValue(writer, result);
		final Map<String, Object> response = mapper.readValue(
				writer.toString(), Map.class);

		return response;
	}

}
