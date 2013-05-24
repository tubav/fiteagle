package org.fiteagle.delivery.xmlrpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.security.cert.X509Certificate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fiteagle.core.aaa.AuthenticationHandler;
import org.fiteagle.delivery.xmlrpc.util.FITeagleUtils;
import org.fiteagle.delivery.xmlrpc.util.FixedSerializer;
import org.fiteagle.delivery.xmlrpc.util.GeniAMHandler;
import org.fiteagle.delivery.xmlrpc.util.SFARegistryHandler;
import org.fiteagle.interactors.sfa.SFAInteractor_v3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redstone.xmlrpc.XmlRpcInvocationHandler;
import redstone.xmlrpc.XmlRpcServlet;


public class FITeagleServlet extends XmlRpcServlet {

	private static final long serialVersionUID = -4349365825732565723L;

	private FixedXmlRpcServer server;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	//TODO make this configurable
	private final String AM_PATH = "/am/v3";
	private final String REGISTRY_PATH = "/registry/v1";
	
	private int counter = 0;

	public FITeagleServlet()  {

		// TODO: choose dependency injection here (i.e. add a parameter to
		// define the interactor here, use reflection to find one or use
		// a config file)
	  
		this.server = new FixedXmlRpcServer();
		this.server.setSerializer(new FixedSerializer());
		SFAInteractor_v3 interactor = new SFAInteractor_v3();
		final XmlRpcInvocationHandler sfaHandler = new GeniAMHandler(
		    interactor);
		
		this.server.addInvocationHandler(AM_PATH, sfaHandler);
		
		final XmlRpcInvocationHandler registryHandler = new SFARegistryHandler(interactor);
		this.server.addInvocationHandler(REGISTRY_PATH, registryHandler);

		XmlRpcController controller = new XmlRpcController();
		this.server.addInvocationInterceptor(controller);	
	
	
	}

	@Override
	public void init(final ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);

	}

	@Override
	public void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
	  
	  counter++;
	  log.info(""+counter);
	  this.handleRequest(req.getInputStream(), resp.getWriter(), req.getPathInfo());
	
	 
		
	}

  public String handleRequestGetVersionStatic() throws IOException {
		return FITeagleUtils
				.getFileAsString("/org/fiteagle/delivery/xmlrpc/sfa/getversion_response.xml");
	}

	public String handleRequestListResourcesStatic() throws IOException {
		return FITeagleUtils
				.getFileAsString("/org/fiteagle/delivery/xmlrpc/sfa/listresources_response.xml");
	}

	public void handleRequest(final InputStream inputStream, final Writer writer, String path)
			throws IOException {
		this.server.execute(inputStream, writer, path);
	}

	
	
}
