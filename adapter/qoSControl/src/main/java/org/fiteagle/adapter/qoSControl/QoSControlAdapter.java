package org.fiteagle.adapter.qoSControl;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import org.fiteagle.adapter.common.AdapterConfiguration;
import org.fiteagle.adapter.common.Named;
import org.fiteagle.adapter.common.Publish;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
@Named(name="QoSControl")
public class QoSControlAdapter extends ResourceAdapter {
Logger log = LoggerFactory.getLogger(getClass());
	public QoSControlAdapter() {
		super();
//		try {
//			//HttpServer httpServer = startServer();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}

	public static final URI BASE_URI = getBaseURI();

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/").port(40301).build();
	}

	protected static HttpServer startServer() throws IOException {
		System.out.println("Starting grizzly...");
		ResourceConfig rc = new PackagesResourceConfig(
				"org.fiteagle.adapter.qoSControl.tmp");
		return GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void configure(AdapterConfiguration configuration) {
		// TODO Auto-generated method stub

	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

	}

	
	public static List<ResourceAdapter> getJavaInstances() {
		LinkedList<ResourceAdapter> adapers = new LinkedList<>();

		adapers.add(new QoSControlAdapter());
		return adapers;
	}

	@Override
	public boolean isLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setLoaded(boolean loaded) {
		// TODO Auto-generated method stub

	}
	
	@Publish
	public void setQoS(@Named(name="ruleId")String ruleId){
		log.info("Adapter received action setQoS: "+ruleId);
	}

}
