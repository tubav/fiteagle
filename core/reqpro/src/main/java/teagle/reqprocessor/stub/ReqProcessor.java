package teagle.reqprocessor.stub;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import teagle.exeme.OEHttpFactory;
import teagle.repository.Configlet;
import teagle.repository.Loader;
import teagle.repository.OrchestrateReturn;
import teagle.repository.OrchestrateReturn.Result.Mapping;
import teagle.repository.PTM;
import teagle.repository.RepositoryException;
import teagle.repository.ResourceInstance;
import teagle.repository.Testbed;
import teagle.repository.Util;
import teagle.repository.VCT;
import teagle.repository.tssg.client.CachingTSSGClient;
import teagle.repository.tssg.client.TSSGResourceInstanceManager;
import teagle.repository.tssg.client.TSSGVctManager;
import teagle.reqprocessor.vct.validate.ValidateActions;
import teagle.vct.model.ModelError;

import com.thoughtworks.xstream.XStream;

/**
 * Request Processor implementation. The main use for this is to handle bookings
 * by sending them to the orchestration engine, listening for completion notifications
 * for these bookings, etc.
 */
public class ReqProcessor extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ClassLoader loader=null;
	private XStream xs=null;
	
	private TSSGVctManager vctManager=null;
	private TSSGResourceInstanceManager resourceInstanceManager=null;
	
	//private BookingManager bookingManager=null;

	DocumentBuilder docBuilder;
	Properties properties;

	private ValidateActions validateActions;

	@Override
	public void init() throws ServletException {
		try {
			Util.info("reqprocessor init");
			
			// load configuration during startup
			properties = new Properties();
			InputStream stream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("reqprocessor.properties");
			if (stream==null)
				throw new ServletException("Could not open resource reqprocessor.properties");
			properties.load(stream);
			
			String library = properties.getProperty("reqprocessor.library");
			if (library==null)
				throw new ServletException("could not read property reqprocessor.library");	
			
			String repoUrlString = properties.getProperty("repo.url");
			if (repoUrlString == null)
			{
				repoUrlString = "http://root:r00t@localhost:8080/repository/rest";
				Util.warn("Could not read repo.url from requestprocessor.properties. Defaulting to " + repoUrlString);
			}
				//throw new ServletException("Could not read repo.url from requestprocessor.properties");
			
			System.out.println("Using " + repoUrlString + " as repository URL");
			
			try 
			{
				CachingTSSGClient.config(new URL(repoUrlString));
			}
			catch (MalformedURLException e)
			{
				throw new ServletException("repo.url (" + repoUrlString + ") is not a valid url", e);
			}
			
			// create a custom loader as well as repository clients
			loader = new Loader(this.getClass().getClassLoader(), library); 
			xs = Util.newXstream();
			xs.alias("testbed", Testbed.class);
			xs.alias("idmapping", OrchestrateReturn.Result.Mapping[].class);
			xs.alias("logentry", String.class);
			xs.processAnnotations(OrchestrateReturn.class);
			
			vctManager = new TSSGVctManager(loader);
			resourceInstanceManager = new TSSGResourceInstanceManager(loader);
			//bookingManager = new BookingManager();
			
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			validateActions = new ValidateActions(properties.getProperty("pe.endpoint"));
			
		} catch (IOException e) { throw new ServletException(e);
		} catch (RepositoryException e) { throw new ServletException(e);
		} catch (ParserConfigurationException e) { throw new ServletException(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException { 
		resp.getWriter().write("Request Processor Prototype");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// parse the incoming request (a list of strings) using xstream
			String [] args = (String[])xs.fromXML(req.getInputStream());
			if (args.length < 2) 
				throw new ServletException("operation missing");

			String registry  = args[0];
			String operation = args[1];

			Util.info("registry=" + registry + " operation=" + operation);

			String [] opArgs = new String[args.length - 2];
			for (int i=2; i<args.length; i++) opArgs[i-2] = args[i];
			
			// process the operations we're interested in 
			//if (registry.equals(VctRegistry.class.getSimpleName())) {
				if (operation.equals("setVct") && opArgs.length == 2){
					CachingTSSGClient.getInstance().clearCache();
					Util.info("Start setVCT");
					String response = setVct(opArgs[0], opArgs[1]);
					Util.info("Done. Writing response: " + response);
					resp.getOutputStream().write(response.getBytes());
					Util.info("Wrote response. Bytes: " + response.length());
				}
				else if (operation.equals("pollBooking") && opArgs.length == 2) 
					pollBooking(opArgs[0], opArgs[1]);
				else
					throw new ServletException("Illegal operation");
			//}
		} 
		catch (RepositoryException e) 
		{ 
			Util.error("RepositoryException: " + e.toString());
			e.printStackTrace(); 
			Util.error("throwing: " + e.toString());
			throw new ServletException(e);
		} 
		catch (ServletException e) 
		{ 
			Util.error("ServletException: " + e.toString());
			e.printStackTrace(); 
			Util.error("throwing: " + e.toString());
			throw e;
		}
		catch (Exception e)
		{
			Util.error("RepositoryException: " + e.toString());
			e.printStackTrace();
			Util.error("throwing: " + e.toString());
			throw new ServletException(e);
		}
		
		Util.info("Operation finished");
	}

	private static Map<String, Integer> stateIds;
	static {
		stateIds = new HashMap<String, Integer>();
		stateIds.put("new", 1);
		stateIds.put("unprovisioned", 2);
		stateIds.put("provisioned", 3);
	}
	 
	// This just advances a resource's state, so once at "provisioned", it
	// won't go back to "unprovisioned" (see stateIds below).
	// Steps may be skipped.
	
	private void advanceResourceStates(VCT vct, String newState) throws ServletException, RepositoryException {
		for (ResourceInstance instance: vct.testbed.components) {
			//ResourceInstance instance;
			
			// Checking each instance (3 repo requests) may be a bottleneck.
			// It may be worth to do this in one step (request).
	//		if (resourceInstanceManager.instanceExists(instance.getId())) {
		//		instance = resourceInstanceManager.getInstance(instance.getId());
		//	} else {
				//instance = new ResourceInstance(ResourceInstance.getId(), ResourceInstance.getTypename(), "new");//in order to the new constructor
//				instance = new ResourceInstance();
				instance.setUser(vct.user);
//				instance.id = ResourceInstance.id;
//				instance.type = ResourceInstance.getClass().getName();
				//instance.permissions = vct.permissions;
//				instance.state = "new";
	//		}
			
			Integer oldStateId = stateIds.get(instance.getState());
			Integer newStateId = stateIds.get(newState);
			
			if (oldStateId==null || newStateId==null)
				throw new ServletException(String.format("unknown states: %s -> %s", 
						instance.getState(), newState));
			
			if (oldStateId < newStateId) {
				instance.setState(newState);
				resourceInstanceManager.setInstance(instance); 
			}
		}
	}
	
	private String setVct(String userName, String name) throws ServletException, IOException, RepositoryException {
		Util.info("userName=" + userName + " name=" + name);
		VCT vct = vctManager.getVct(userName, name);
		
		Util.debug("state=" + vct.getState());
		
		// Resources should be at least unprovisioned. If (when ... think async) the 
		// booking succeeds, we can move them to provisioned.
		//
		// Note that a resource that is already provisioned will not be reset back to
		// unprovisioned by this call.
		advanceResourceStates(vct, "unprovisioned");
		
		// inprogress_* states are set by the vcttool when booking a vct. This triggers
		// us to send the booking request to the OE. 
		if (true || vct.getState().equals("inprogress_sync") ||
			vct.getState().equals("inprogress_async") ||
			vct.getState().equals("inprogress_direct")) {
			try {
				String booking = exportVctRequest(userName, name, vct.testbed);
				String response;
				OrchestrateReturn ret;

				boolean asyncBooking = vct.getState().equals("inprogress_async");
			//	boolean directBooking = vct.getState().equals("inprogress_direct");

				try {
//					response = directBooking ?
	//						bookDirect(vct.testbed) :
					
					//EvaluationHandler handler = new EvaluationHandler();
				//if (validateActions.validateVCT(userName, vct, handler, resourceInstanceManager, resourceInstanceManager)) {
						ret =	bookViaOE(asyncBooking, vct, booking);
						response = xs.toXML(ret);
						//Util.debug("Orchestrate result: " + response);
						//ret = (OrchestrateReturn)xs.fromXML(response);		
					/*} else {
						System.out.println("Validation of the VCT booking has failed");
						ret = new OrchestrateReturn(1, handler.getMessage());
						response = xs.toXML(ret);						
					}*/
				}
				catch (ConnectException e)
				{
					e.printStackTrace();
					ret = new OrchestrateReturn(1, "RP was unable to contact the OE: " + e.getMessage());
					response = xs.toXML(ret);
				} 

				
				if (!asyncBooking) {
					if (ret.status==0) {
						advanceResourceStates(vct, "provisioned");
						vct.state = "booked";
					} else {
						vct.state = "unbooked";
					}
				} else {
					if (ret.status==0) {
						vct.state = "inprogress_wait";
					} else {
						vct.state = "unbooked";
					}
				}

//				vct.orchestrateReturn = ret;
				vctManager.setVctState(vct);
				
				// keep in mind that others might be listening on setVct; so if 
				// you renameIds() afterwards they might get old (design) ids
				
				if (ret.status==0 && !asyncBooking) { 
					renameIds(userName, name, ret.result.idmapping);
				}
				
				return response;
			} catch (SAXException e) { throw new ServletException(e);
			} catch (ModelError e) { throw new ServletException(e);
			} catch (TransformerException e) { throw new ServletException(e);
			}
			catch (Exception e) {
				e.printStackTrace();
				return xs.toXML(new OrchestrateReturn(1, e.getMessage()));
			}
		}
		else throw new ServletException("Vct is in the wrong state: " + vct.state);
	}
	
	/**
	 * Replace both vct and resource instance ids (temp ones => permanent ones).
	 * The idmapping parameter is usually just taken from an Orchestration Engine result.
	 */
	private void renameIds(String userName, String name, Mapping[] idmapping) throws RepositoryException {
		for (Mapping m : idmapping)
		{
			int i = m.designid.indexOf(".resources.");
			if (i > 0)
				m.designid = m.designid.substring(i + 11);
		}
		
		vctManager.renameIds(userName, name, idmapping);
//		resourceInstanceManager.renameIds(idmapping);
	}

	private static String resultPattern = 
		".*<textarea name=\"result\"[^>]*>([^<]*)</textarea>.*";
	
	private static String logPattern = ".*Errors in execution</h2><pre>See <a href=\"(.+)\">logs</a>.*";
	
	private String regexFind(String regex, String text) {
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches() ? matcher.group(1) : null;
	}

	// First OE operation ... no "uninteractive" version exists so we have to scrape the html
	// for the result afterwards. 
	private String call_putVCTSpec(HttpClient client, String oe_url, String vctid, String booking) 
			throws HttpException, IOException, ServletException {
		Util.info("call_putVCTSpec vct=" + vctid + " oe_url: " + oe_url);
		Util.info(booking);
		PostMethod post = new PostMethod(oe_url);
		
		HttpMethodRetryHandler rh = new HttpMethodRetryHandler() {
			
			@Override
			public boolean retryMethod(HttpMethod arg0, IOException arg1, int arg2) {
				Util.error("Exception during method call: " + arg1);
				return false;
			}
		};
		
		post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, rh);
		
		try {
			Part[] parts = {
				new StringPart("step",		"xdocmd"),
				new StringPart("op",		"putVCTSpec"),
				new StringPart("siteid",	"teagle"),
				new StringPart("appid",		"teagle.repository"),
				new StringPart("serviceid",	"VCTRegistry"),
				new StringPart("clientid",	""),
				new StringPart("v_vctid",	vctid),
				new FilePart("v_vctfile",	new ByteArrayPartSource("booking.xml", booking.getBytes())),	
				new StringPart("options",	"d0"),
				new StringPart("userId",	""),
			};

			post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
			
			client.executeMethod(post);
			if (post.getStatusCode() != 200)
				throw new IOException("Unexpected response from OE: " + post.getStatusLine());

			String response = Util.readStream(post.getResponseBodyAsStream());
			String result = regexFind(resultPattern, response);
			if (result==null)
				throw new ServletException("Could not find result tag, response=" + response);
			
			Util.info("vct=" + vctid + " result=" + result);
			return result;
		} finally {
			post.releaseConnection();
		}
	}

	// second OE operation ... again no uninteractive version exists
	private String call_deployVCT(HttpClient client, String oe_url, String vctid) 
			throws HttpException, IOException, ServletException {
		Util.info("call_deployVCT vct=" + vctid);
	
		String url = oe_url + 
			"?step=xdocmd" +
			"&op=deployVCT" +
			"&siteid=teagle" +
			"&appid=teagle.orchestrationengine" +
			"&serviceid=Launcher" +
			"&clientid=" +
			"&v_vctid=" + URLEncoder.encode(vctid, "UTF-8") +
			"&options=d0&userId=";

		GetMethod get = new GetMethod(url);
		
		try {
			client.executeMethod(get);			
			if (get.getStatusCode() != 200)
				throw new IOException("Unexpected response: " + get.getStatusLine());
			
			String response = Util.readStream(get.getResponseBodyAsStream());
			String result = regexFind(resultPattern, response);
			if (result==null)
				//throw new ServletException("Could not find result tag, response=" + response);
				return response;
			
			Util.info("vct=" + vctid + " result=" + result);
			return result;
		} finally {
			get.releaseConnection();
		}
	}

	// Third OE operation (sync version) ... this actually sends requests to the PTMs. We use 
	// an uninteractive version of the one available on the web site, that just returns the 
	// OrchestrateReturn structure, which we parse using XStream.
	private String call_orchestrateSync(HttpClient client, String oe_xml_url, String vctid) 
			throws HttpException, IOException, ServletException {
		Util.info("call_orchestrateSync vct=" + vctid);

		String url = oe_xml_url +
			"?step=docmd" +
			"&op=orchestrate" +
			"&siteid=teagle" +
			"&appid=teagle.testbeds.usersebastian" +
			"&serviceid=" + URLEncoder.encode(vctid, "UTF-8") +
			"&clientid=" +
			"&options=d0" +
			"&userId=";

		GetMethod get = new GetMethod(url);
		
		try {
			client.executeMethod(get);
			if (get.getStatusCode() != 200)
				throw new IOException("Unexpected response: " + get.getStatusLine());
			
			String response = Util.readStream(get.getResponseBodyAsStream());
			Util.p(response);
			
			//return (OrchestrateReturn)xs.fromXML(response);
			return response;
		} finally {
			get.releaseConnection();
		}
	}
	
	// Third OE operation (async). Again, uninteractive version, and this unly returns a provisional
	// success result, which contains an url that will contain the final result, once the booking
	// completes.
	private String call_orchestrateAsync(HttpClient client, String oe_xml_url, String vctid) 
			throws HttpException, IOException, ServletException {
		Util.info("call_orchestrateAsync vct=" + vctid);

		String url = oe_xml_url +
			"?step=docmd" +
			"&op=executeVCT" +
			"&siteid=teagle" +
			"&appid=teagle.orchestrationengine" +
			"&serviceid=Launcher" +
			"&clientid=" +
			"&v_vctid=" + URLEncoder.encode(vctid, "UTF-8") +
			"&options=d0" +
			"&userId=sebastian";

		GetMethod get = new GetMethod(url);

		try {
			client.executeMethod(get);
			if (get.getStatusCode() != 200)
				throw new IOException("Unexpected response: " + get.getStatusLine());

			String response = Util.readStream(get.getResponseBodyAsStream());
			Util.p(response);

			//return (OrchestrateReturn)xs.fromXML(response);
			return response;
		} finally {
			get.releaseConnection();
		}
	}
	
	private OrchestrateReturn bookViaOE(boolean async, VCT vct, String booking) 
			throws IOException, ServletException {
		String vctid = vct.user + "_" + vct.name;
		return doBookViaOE(async, vctid, booking);
	}
	
	/**
	 * Call the three OE operations that trigger a booking, and return an error if any of
	 * them fails.  
	 * @param async whether to book asynchronously or not 
	 * @param vctid the vct id, currently it's generated by concatenating the user and vct name
	 * @param booking a string that contains an xml request which will get sent to the OE
	 * @return an OrchestrateReturn instance that is parsed from the xml produced by the OE
	 */
	private OrchestrateReturn doBookViaOE(boolean async, String vctid, String booking) 
			throws IOException, ServletException {
		Util.info("doBookViaOE vctid=" + vctid);
		Util.info(booking);
		
		String oe_url    = properties.getProperty("oe.url");
		String oe_xml_url= properties.getProperty("oe.xml_url");
		
		if (oe_url==null || oe_xml_url==null)
			throw new ServletException("missing configuration item from reqprocessor.properties");
		
		HttpClient httpClient = OEHttpFactory.makeHttpClient(properties);
		
		String putVCTSpec_result = call_putVCTSpec(httpClient, oe_url, vctid, booking);
		if (! putVCTSpec_result.equalsIgnoreCase("OK"))
			throw new ServletException("Unexpected putVCTspec result: " + putVCTSpec_result);

		//try { Thread.sleep(500); } catch (Exception e) { }
		
		String deployVCT_result = call_deployVCT(httpClient, oe_url, vctid);
		if (! deployVCT_result.equalsIgnoreCase("OK"))
		{
			String logUrl = regexFind(logPattern, deployVCT_result);
			if (logUrl != null)
			{
				OrchestrateReturn r = new OrchestrateReturn(1, "Error in orchestration");
				r.log = logUrl;
				return r;
			}
			throw new ServletException("Unexpected deployVCT result: " + deployVCT_result);
		}

		//try { Thread.sleep(500); } catch (Exception e) { }
		
		String oeRet = async ?
			call_orchestrateAsync(httpClient, oe_xml_url, vctid) :
			call_orchestrateSync(httpClient, oe_xml_url, vctid);
		
		Util.debug("answer from OE: " + oeRet);
		try
		{
			return (OrchestrateReturn)xs.fromXML(oeRet);
		}
		catch (Error e)
		{
			e.printStackTrace();
			throw e;
		}
	//	Util.info("oeRet.status=" + oeRet.status);
	}

	/**
	 * For asynchronous bookings, this checks whether a previously started booking
	 * has finished. It does this by reading the xml url initially returned by the 
	 * OE, that will contain a list of id mappings when the booking has finished.
	 * 
	 * Note that we can only tell when an async booking has completed successfully. 
	 * We cannot tell when a booking has failed. This will be fixed when we implement
	 * receiving notifications from the OE.
	 */
	private void pollBooking(String userName, String name) throws ServletException, IOException, RepositoryException {
		VCT vct = vctManager.getVct(userName, name);
		
		if (! vct.state.equals("inprogress_wait")) {
			Util.warn(String.format("user=%s vct=%s: state=%s, returning", userName, name, vct.state));
			return;
		}
		
		//TODO: make this async stuff work again
		OrchestrateReturn oeRet = null;//vct.orchestrateReturn;
		if (oeRet==null || oeRet.result==null || oeRet.result.return_==null ||
				oeRet.result.return_.report==null) {
			Util.warn(String.format("user=%s vct=%s: orchestrateReturn incomplete", userName, name));
			return;
		}
		
		HttpClient httpClient = OEHttpFactory.makeHttpClient(properties);
		GetMethod get = new GetMethod(oeRet.result.return_.report);

		try {
			httpClient.executeMethod(get);
			if (get.getStatusCode() != 200)
				throw new IOException("Unexpected response: " + get.getStatusLine());

			String mappingXml = Util.readStream(get.getResponseBodyAsStream());
			Util.p(mappingXml);
			
			oeRet.result.idmapping = (OrchestrateReturn.Result.Mapping[])xs.fromXML(mappingXml);
			Util.info(String.format("got %s mappings", oeRet.result.idmapping.length));
			
			// TODO: this is a hack until I can find out whether
			// the orchestration has finished, and was successful.
			// if idmapping is empty, it's either because:
			//  - there was an error, or
			//  - there were no resources
			// 
			// The last case is a problem, because empty vcts will never transition
			// to "booked" this way. So a test is required in the vcttool to
			// disallow asynchronously booking empty vcts.
			
			if (oeRet.result.idmapping.length > 0) {
				advanceResourceStates(vct, "provisioned");
			
				vct.state = "booked";
				vctManager.setVct(userName, name, vct);
				
				renameIds(userName, name, oeRet.result.idmapping);
			}
		} catch (IOException e) { e.printStackTrace();
		} finally {
			get.releaseConnection();
		}
	}

	/**
	 * This is just for testing purposes.
	 */
	public static void main(String[] args) throws Exception {
		/*String res = 
			"<pre><textarea name=\"result\" style=\"color:#000000; background: #FFFFFF\" rows=\"1\" " +
			"cols=\"40\" readonly=\"true\">OK</textarea></pre>";
		Pattern pattern2 = Pattern.compile(
				".*<textarea name=\"result\"[^>]*>([^<]*)</textarea>.*", Pattern.MULTILINE);
		Matcher matcher2 = pattern2.matcher(res);

		if (! matcher2.matches()) 
			throw new ServletException("Could not find result tag");

		System.out.println(matcher2.group(1));
		if (true) return;*/
		new ReqProcessor().doMain();
	}
	
	private void doMain() throws Exception {
		init();
		
		/*String test_booking =
			"<testbed>"+
			"  <components>"+
			"    <fokus.resources.systemuser>"+
			"      <id>fokus.resources.systemuser-747856455</id>"+
			"      <geometry>"+
			"        <x>20</x>"+
			"        <y>20</y>"+
			"        <w>0</w>"+
			"        <h>0</h>"+
			"      </geometry>"+
			"      <rules/>"+
			"      <configuration>"+
			"        <name>user10</name>"+
			"        <password/>"+
			"      </configuration>"+
			"    <state>unprovisioned</state>"+
			"    </fokus.resources.systemuser>"+
			"  </components>"+
			"  <connections/>"+
			"</testbed>";

		OrchestrateReturn ret = doBookViaOE(true, "bodi_bookuser1", test_booking);
		System.out.println(ret.status);
		System.out.println(ret.message);
		System.out.println(ret.log);
		if (ret.result.idmapping!=null) {
			System.out.println(ret.result.idmapping.length + " mappings:");
			for (OrchestrateReturn.Result.Mapping m: ret.result.idmapping)
				System.out.println(m.designid + " => " + m.runtimeid);
		}
		
		pollBooking("testuser", "testbooksync");*/
		
		vctManager.getVct("testuser", "testbooksync");
		//bookDirect(vct.testbed);
	}

	/**
	 * Store a copy of the booking request in the repo.
	 */
	
	private Element addAttr(Document doc, Node parent, String name, String value)
	{
		Element child = (Element)parent.appendChild(doc.createElement(name));
		child.appendChild(doc.createTextNode(value));
		return child;
	}
	
	private String exportVctRequest(String userName, String name, Testbed testbed) throws IOException, RepositoryException, SAXException, ModelError, TransformerException {
		Document doc = docBuilder.newDocument();
		
		Node testbedElement = doc.appendChild(doc.createElement("testbed"));
		Node componentsElement = testbedElement.appendChild(doc.createElement("components"));
		Util.info("exportVCTRequest");
		//HashMap<Resource, String> typeMap = new HashMap<Resource, String>();
		HashMap<ResourceInstance, String> typeMap = new HashMap<ResourceInstance, String>();
		HashMap<String, String> idMap = new HashMap<String, String>();
		
		for (ResourceInstance i : testbed.components)
		{
			int index = i.getId().indexOf('.');
			String type = "";
			
			if (index < 0)
			{
				PTM ptm = resourceInstanceManager.getPTMByResource(i.getType());
				type = ptm.getId() + "____resources____";
			}
			
			Util.info("Adding to typemap: " + i.getId() + " --> " + type +  i.getTypename());
			typeMap.put(i, type +  i.getTypename());
			
			if (/*i.isProvisioned() || */index > 0)
			{
				idMap.put(i.getId(), i.getId());
				Util.info("Adding to idMap (1): " + i.getId() + " --> " + i.getId());
			}
			else
			{
				Util.info("Adding to idMap (2): " + i.getId() + " --> " + type + i.getId());
				idMap.put(i.getId(), type + i.getId());
			}
		}
				
		Node connectionsElement = testbedElement.appendChild(doc.createElement("connections"));
		for (ResourceInstance i : testbed.components)
		{
			Node componentElement = componentsElement.appendChild(doc.createElement(typeMap.get(i)));
			addAttr(doc, componentElement, "id", idMap.get(i.getId()));
			addAttr(doc, componentElement, "state", i.getState());
			Node configElement = componentElement.appendChild(doc.createElement("configuration"));
			for (Configlet c : i.getConfiguration())
				if (!c.isArray())
				{
					String val = c.getValueString();
					if (c.isReference())
					{
						String ref = idMap.get(val);
						if (ref != null)
						{
							val = dynId(ref);
							
							Node connectionElement = connectionsElement.appendChild(doc.createElement("connection"));
							Node srcElement = connectionElement.appendChild(doc.createElement("src"));
							String srcId = i.getId(), tmp;
							tmp = idMap.get(srcId);
							if (tmp != null)
								srcId = tmp;
							addAttr(doc, srcElement, "id", srcId);
					
							Node dstElement = connectionElement.appendChild(doc.createElement("dst"));
							addAttr(doc, dstElement, "id", ref);
							addAttr(doc, connectionElement, "type", "references");
						}
					}
					addAttr(doc, configElement, c.getName(), val).setAttribute("type", c.getType());
				}
				else
				{
					String[] array = c.getArrayValueStrings();
					Node attrElement = configElement.appendChild(doc.createElement(c.getName()));
					Node arrayElement = attrElement.appendChild(doc.createElement(c.getType()));
					String elementName = c.getType().substring(c.getType().length() - 5);
					for (String e : array)
						addAttr(doc, arrayElement, elementName, c.isReference() ? dynId(idMap.containsKey(e) ? idMap.get(e) : e) : e);
				}
			
			 if (i.getParentInstance() != null)
			 {
					Node connectionElement = connectionsElement.appendChild(doc.createElement("connection"));
					Node srcElement = connectionElement.appendChild(doc.createElement("src"));
					
					String srcId = i.getParentInstance().getId(), dstId = i.getId(), tmp;
					tmp = idMap.get(srcId);
					if (tmp != null)
						srcId = tmp;
					tmp = idMap.get(dstId);
					if (tmp != null)
						dstId = tmp;
					
					addAttr(doc, srcElement, "id", srcId);
					Node dstElement = connectionElement.appendChild(doc.createElement("dst"));
					addAttr(doc, dstElement, "id", dstId);
					addAttr(doc, connectionElement, "type", "contains");
			 }
		}
		
		//addResourceStates(testbed, doc);

		StringWriter writer = new StringWriter();
		Util.writeXml(doc, writer);
		
		String booking = writer.toString();
		booking = booking.replace("____", ".");
		//bookingManager.setBooking(userName, name, booking);

		return booking;
	}
	
	private String dynId(String id)
	{
		if (id.length() == 0)
			return "";
		return "$dynid(" + id + ")"; 
	}
}
