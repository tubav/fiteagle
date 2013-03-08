package org.fiteagle.interactors.sfa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Map;

import org.fiteagle.interactors.sfa.binding.GetVersionResult;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SFAInteractor implements ISFA {

	@Override
	public Map<String, Object> getVersion() throws IOException {
		final GetVersionResult resultObject = new GetVersionResult();
		final Map<String, Object> resultMap = this.convertToMap(resultObject);
		return resultMap;
	}

	@Override
	public String getVersionTemp() throws IOException {
		return readFileAsString("/org/fiteagle/interactor/sfa/getversion_response.xml");
	}

	@Override
	public Map<String, Object> listResources() throws IOException {
		return null;
	}

	@Override
	public String listResourcesTemp() throws IOException {
		return readFileAsString("/org/fiteagle/interactor/sfa/listresources_response.xml");
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> convertToMap(final Object getVersionResult)
			throws IOException {
		final ObjectMapper mapper = new ObjectMapper();
		final StringWriter writer = new StringWriter();

		mapper.writeValue(writer, getVersionResult);
		final Map<String, Object> result = mapper.readValue(writer.toString(),
				Map.class);

		return result;
	}

	private String readFileAsString(String filePath) throws java.io.IOException {
		StringBuffer fileData = new StringBuffer(1024);
		BufferedReader reader = new BufferedReader(new InputStreamReader(this
				.getClass().getResourceAsStream(filePath)));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}
}
