package org.fiteagle.interactors.certificates;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface RestCertificateManagement {
  
  @GET
  @Path("list")
  @Produces(MediaType.APPLICATION_JSON)
  public abstract List<String> getCertificates();
  
  @GET
  @Path("download/{commonName}")
  @Produces(MediaType.TEXT_PLAIN)
  public abstract String getCertificate(@PathParam("commonName") String commonName);  
  
} 