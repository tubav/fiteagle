package org.fiteagle.adapter.nodeadapter.client;

import java.io.IOException;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.fiteagle.adapter.nodeadapter.client.model.Images;
import org.fiteagle.adapter.nodeadapter.client.model.Server;

import com.woorea.openstack.nova.model.Flavors;
import com.woorea.openstack.nova.model.FloatingIp;

public class OpenstackParser {

	private JsonFactory factory = new JsonFactory();
	private ObjectMapper mapper = new ObjectMapper(factory);

	public Images parseToImages(String imagesString) throws JsonParseException,
			JsonMappingException, IOException {

		return mapper.readValue(imagesString, Images.class);
	}

	public FloatingIp parseToFloatingIp(String floatingIpString) throws JsonParseException,
			JsonMappingException, IOException {

		return mapper.readValue(floatingIpString, FloatingIp.class);
	}

	public Flavors parseToFlavors(String flavorsString)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(flavorsString, Flavors.class);
	}

	public Server parseToServer(String serverString) throws JsonParseException,
			JsonMappingException, IOException {
		Server server = mapper
				.readValue(
						serverString,
						org.fiteagle.adapter.nodeadapter.client.model.Server.class);
		return server;
	}

}
