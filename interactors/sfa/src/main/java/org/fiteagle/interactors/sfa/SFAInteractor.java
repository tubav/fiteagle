package org.fiteagle.interactors.sfa;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.fiteagle.interactors.sfa.binding.GetVersionResult;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SFAInteractor implements ISFA {

	public Map<String, Object> getVersion() throws IOException {
		GetVersionResult resultObject = new GetVersionResult();
		Map<String, Object> resultMap = convertToMap(resultObject);
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> convertToMap(Object getVersionResult)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter writer = new StringWriter();
		
		mapper.writeValue(writer, getVersionResult);
		Map<String, Object> result = mapper.readValue(writer.toString(), Map.class);
		
		return result;
	}
}
