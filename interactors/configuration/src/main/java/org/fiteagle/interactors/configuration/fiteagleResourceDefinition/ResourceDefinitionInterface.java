package org.fiteagle.interactors.configuration.fiteagleResourceDefinition;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ResourceDefinitionInterface {
	
	@GET
	@Path("/ext/1")
	@Produces(MediaType.APPLICATION_XML)
	public String getResourceDefinition();

}
