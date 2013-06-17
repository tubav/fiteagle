package org.fiteagle.interactors.configuration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fiteagle.core.config.Configurator;

@Path("/")
public class ConfigurationManager implements ConfigurationManagement{
  @Override
  @GET
  @Path("version")
  @Produces(MediaType.APPLICATION_JSON)
  public String getVersion() {
    return new Configurator().getCommitVersion();
  }

}
