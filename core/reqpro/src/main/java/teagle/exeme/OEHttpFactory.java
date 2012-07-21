package teagle.exeme;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;

/**
 * This contains just a common function that is called from several 
 * places: to start a booking and to get the booking execution log. 
 * It just returns an HttpClient that has authentication credentials
 * attached that are read from reqprocessor.properties. 
 */
public class OEHttpFactory {
	/**
	 * Returns an HttpClient that can be used to make requests to the Orchestration Engine
	 * @param properties a Properties instance that contains authentication data
	 */
	public static HttpClient makeHttpClient(Properties properties) throws IOException {
		String oe_user   = properties.getProperty("oe.user");
		String oe_pass   = properties.getProperty("oe.pass");
		String oe_domain = properties.getProperty("oe.domain");
		String oe_realm  = properties.getProperty("oe.realm");

		if (oe_user==null || oe_pass==null || oe_domain==null  || oe_realm==null)			
			throw new IOException("missing configuration item from reqprocessor.properties");
			
		HttpClient httpClient = new HttpClient();
		
		Credentials creds = new UsernamePasswordCredentials(oe_user, oe_pass);		
		AuthScope oeScope = new AuthScope(oe_domain, 80, oe_realm);
		httpClient.getState().setCredentials(oeScope, creds);
		return httpClient;
		
	}
}
