/**
 * 
 */
package teagle.vct.tssg.impl;

import java.net.URL;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

/**
 * @author sim
 *
 */
public class TSSGClient {

	private static Client client;
	private static WebResource webResource;

	protected static void config(URL url, String username, String password) 
	{
		assert(url != null);
		client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter(username, password));
		webResource = client.resource(url.toString());
	}
	
	protected static WebResource getWebResource() {
		return webResource;
	}
}
