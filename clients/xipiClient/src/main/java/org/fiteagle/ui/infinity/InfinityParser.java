package org.fiteagle.ui.infinity;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.fiteagle.ui.infinity.model.InfinityArrayList;
import org.fiteagle.ui.infinity.model.InfinityInfrastructure;
import org.fiteagle.ui.infinity.model.InfinityValueID;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InfinityParser {

	private JsonFactory factory = new JsonFactory();
	private ObjectMapper mapper = new ObjectMapper(factory);

	public ArrayList<InfinityValueID> parseGetTechnicalComponentsResponse(
			String string) throws JsonParseException, JsonMappingException,
			IOException {
		ArrayList<InfinityValueID> result = mapper.readValue(string,
				new TypeReference<java.util.List<InfinityValueID>>() {
				});
		return result;
	}

	public InfinityInfrastructure parseGetInfrastructuresById(String input)
			throws JsonParseException, JsonMappingException, IOException {
		InfinityInfrastructure result = mapper.readValue(input,
				InfinityInfrastructure.class);
		return result;
	}

	public ArrayList<InfinityArrayList> parseGetComponentDetailResponse(
			String input) throws JsonParseException, JsonMappingException,
			IOException {
		ArrayList<InfinityArrayList> result = mapper.readValue(input,
				new TypeReference<java.util.List<InfinityArrayList>>() {
				});
		return result;
	}

	public ArrayList<InfinityValueID> parseSearchInfrastructuresResponse(
			String input) throws JsonParseException, JsonMappingException,
			IOException {
		return this.parseGetTechnicalComponentsResponse(input);
	}

	public void write(OutputStream stream, InfinityInfrastructure input)
			throws JsonGenerationException, JsonMappingException, IOException {
		this.mapper.writeValue(stream, input);
	}
}
