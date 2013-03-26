package org.fiteagle.delivery.xmlrpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fiteagle.delivery.xmlrpc.util.FITeagleUtils;
import org.fiteagle.delivery.xmlrpc.util.FixedSerializer;
import org.fiteagle.delivery.xmlrpc.util.SFAHandler;
import org.fiteagle.interactors.sfa.SFAInteractor_v3;

import redstone.xmlrpc.XmlRpcInvocationHandler;
import redstone.xmlrpc.XmlRpcServer;
import redstone.xmlrpc.XmlRpcServlet;

public class FITeagleServlet extends XmlRpcServlet {

//	private static final String HANDLER = "handler";
	private static final long serialVersionUID = -4349365825732565723L;
//	private XmlRpcInvocationHandler handler;
	

	private XmlRpcServer server;
	
	public FITeagleServlet() {
		
		
		//TODO: choose dependency injection here (i.e. add a parameter to
		// define the interactor here, use reflection to find one or use
		// a config file)
		
		this.server = new FixedXmlRpcServer();
		this.server.setSerializer(new FixedSerializer());
		final XmlRpcInvocationHandler sfaHandler = new SFAHandler(new SFAInteractor_v3());

		this.server.addInvocationHandler("__default__",
				sfaHandler);
		 
		
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
//
//		this.handler = this.getXmlRpcServer().getInvocationHandler(
//				FITeagleServlet.HANDLER);
//
		
		
		this.handleRequest(req.getInputStream(), resp.getWriter());
		
		
		
	}

	
	public String handleRequestGetVersionStatic() throws IOException {
		return FITeagleUtils
				.getFileAsString("/org/fiteagle/delivery/xmlrpc/sfa/getversion_response.xml");
	}

	public String handleRequestListResourcesStatic() throws IOException {
		return FITeagleUtils
				.getFileAsString("/org/fiteagle/delivery/xmlrpc/sfa/listresources_response.xml");
	}

	public void handleRequest(final InputStream inputStream,
			final Writer writer) throws IOException {
		this.server.execute(inputStream, writer);
	}

	/**
	private String handleRequest(final MethodCall request) throws IOException {
		final StringWriter writer = new StringWriter();
		final String methodName = request.getMethodName();

		try {
			// fixme: why do we need an XmlRpcServer context here? Not nice for
			// testing...
			final Object ret = this.handler.invoke(methodName,
					request.getParams());
			
			this.server.getSerializer()
					.writeEnvelopeHeader(ret, writer);
			this.server.getSerializer().serialize(ret, writer);
			this.server.getSerializer()
					.writeEnvelopeFooter(ret, writer);
			//
			// String ret = (String)sfaWrap.invoke("getVersion2", new
			// LinkedList<String>());
			// Writer writer = new
			// OutputStreamWriter(resp.getOutputStream());
			// writer.write(ret);
			// writer.flush();
		} catch (final Throwable e) {
			writer.write(e.getMessage());
		}

		return writer.toString();
	}

	private MethodCall parseInputXML(final InputStream inputStream) {
		
		final MethodCall request = (MethodCall) this.xstream
				.fromXML(inputStream);
		return request;
	}
	
	*/
}
