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

	protected static void config(final URL url, final String username,
			final String password) {
		assert (url != null);
		TSSGClient.client = Client.create();
		TSSGClient.client
				.addFilter(new HTTPBasicAuthFilter(username, password));
		TSSGClient.webResource = TSSGClient.client.resource(url.toString());
	}

	protected static WebResource getWebResource() {
		return TSSGClient.webResource;
	}
}
