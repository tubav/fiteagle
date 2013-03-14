package org.fiteagle.delivery.xmlrpc.util;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fiteagle.interactors.sfa.ISFA;
import org.fiteagle.interactors.sfa.SFAInteractor_v2;
import org.fiteagle.interactors.sfa.types.AMCode;
import org.fiteagle.interactors.sfa.types.AMResult;
import org.fiteagle.interactors.sfa.types.AMValue;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import redstone.xmlrpc.XmlRpcInvocationHandler;

public class SFAHandler implements XmlRpcInvocationHandler {

	private Map<String, ISFA> interactorMap = new HashMap<>();
	ISFA iv2;

	public SFAHandler() {
		iv2 = new SFAInteractor_v2();
	}

	@Override
	public Object invoke(String methodName, List parameters) throws Throwable {
		List<Class> parameterClasses = new ArrayList<>();
		for (Object o : parameters) {
			parameterClasses.add(o.getClass());
		}
		Method m = null;
		Class[] parameterClassesArray = new Class[parameterClasses.size()];
		parameterClassesArray = parameterClasses.toArray(parameterClassesArray);
		if (!parameterClasses.isEmpty()) {
			m = iv2.getClass().getMethod(methodName, parameterClassesArray);
		} else {
			m = iv2.getClass().getMethod(methodName, null);
		}
		AMResult result = (AMResult) m.invoke(iv2, parameters.toArray());
		postProcess(result, methodName);

		Map<String, Object> response = new HashMap<>();
		try {
			response = introspect(result);
		} catch (IOException ioException) {
		}
		;

		return response;

	}

	private void postProcess(AMResult result, String methodName) {

		processCode(result.getCode());
		processValue(result.getValue(), methodName);
		
	}

	private void processValue(AMValue value, String methodName) {
		//TODO set urn, set hostname, set hrn, set geni_api_versions (# from interactor, url from server)
		
	}

	private AMCode processCode(AMCode code_result) {
		code_result.setAm_code(0);
		code_result.setAm_type("FITeagle");
		return code_result;
	}

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
