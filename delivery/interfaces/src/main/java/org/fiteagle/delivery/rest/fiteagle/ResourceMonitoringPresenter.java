package org.fiteagle.delivery.rest.fiteagle;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.fiteagle.interactors.api.ConfigurationManagerBoundary;
import org.fiteagle.interactors.api.ResourceMonitoringBoundary;
import orgt.fiteagle.core.monitoring.StatusTable;

import com.google.inject.Inject;

@Path("/v1/monitoring")
public class ResourceMonitoringPresenter {
	
	String resourceInformation = "\n" + 
			"<!DOCTYPE html>\n" + 
			"<html>\n" + 
			"\n" + 
			"  <head>\n" + 
			"    <meta charset='utf-8' />\n" + 
			"    <meta name=\"description\" content=\"FITeagle is an extensible and distributed open source experimentation and management framework for federated Future Internet testbeds.\" />\n" + 
			"    \n" + 
			"    <title>FITeagle | Future Internet Testbed Experimentation and Management Framework</title>\n" + 
			"  </head>\n" + 
			"\n" + 
			"  <body>\n" + 
			"\n" + 
			"    <!-- HEADER -->\n" + 
			"    <div id=\"header_wrap\" class=\"outer\">\n" + 
			"        <header class=\"inner\">\n" + 
			"          <a id=\"forkme_banner\" href=\"http://av.tu-berlin.de\">av/ngn tool</a>\n" + 
			"        </header>\n" + 
			"    </div>\n" + 
			"        <header class=\"inner\">\n" + 
			"          <ul class=\"horizontal\">\n" + 
			"    <li><a href=\"#about\">about</a></li>\n" + 
			"    <li><a href=\"#contact\">contact</a></li>\n" + 
			"    <li><a href=\"#users\">users</a></li>\n" + 
			"    <li><a href=\"#developers\">developers</a></li>\n" + 
			"    <li><a href=\"#demos\">demos</a></li>\n" + 
			"    <li><a href=\"#publications\">publications</a></li>\n" + 
			"  </ul>\n" + 
			"        </header>\n" + 
			"    </div>" + 
			"    example site!!\n" + 
			"  </body>\n" + 
			"</html>\n";
	private ResourceMonitoringBoundary monitor;
	
	@Inject
	  public ResourceMonitoringPresenter(final ResourceMonitoringBoundary monitor) {
	    this.monitor = monitor;
	  }

	@GET
	@Path("/data")
	@Produces("application/xml")
	public String getResourceDefinition() {
		Collection<StatusTable> monitoringData = monitor.getMonitoringData();
		return resourceInformation;
	}

}
