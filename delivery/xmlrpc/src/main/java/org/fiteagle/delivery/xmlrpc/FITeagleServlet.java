package org.fiteagle.delivery.xmlrpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fiteagle.delivery.xmlrpc.util.FITeagleUtils;
import org.fiteagle.delivery.xmlrpc.util.MethodCall;
import org.fiteagle.interactors.sfa.SFAInteractor;

import redstone.xmlrpc.XmlRpcInvocationHandler;
import redstone.xmlrpc.XmlRpcServlet;
import redstone.xmlrpc.handlers.ReflectiveInvocationHandler;

import com.thoughtworks.xstream.XStream;

public class FITeagleServlet extends XmlRpcServlet {

	private static final String HANDLER = "handler";
	private static final long serialVersionUID = -4349365825732565723L;
	private XmlRpcInvocationHandler handler;
	private final XStream xstream;
	private Object interactor;

	public FITeagleServlet() {
		this.xstream = new XStream();
		//TODO: choose dependency injection here (i.e. add a parameter to
		// define the interactor here, use reflection to find one or use
		// a config file)
		this.interactor = new SFAInteractor();
	}

	@Override
	public void init(final ServletConfig arg0) throws ServletException {
		super.init(arg0);
		final ReflectiveInvocationHandler sfaInteractor = new ReflectiveInvocationHandler(
				this.interactor);

		this.getXmlRpcServer().addInvocationHandler(FITeagleServlet.HANDLER,
				sfaInteractor);
	}

	@Override
	public void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		this.handler = this.getXmlRpcServer().getInvocationHandler(
				FITeagleServlet.HANDLER);

		this.handleRequest(req.getInputStream(), resp.getOutputStream());
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
			final OutputStream outputStream) throws IOException {
		final Writer writer = new OutputStreamWriter(outputStream);

		final MethodCall request = this.parseInputXML(inputStream);

		if ("getVersion".equals(request.getMethodName())) {
			writer.write(this.handleRequestGetVersionStatic());
		} else if ("listResources".equals(request.getMethodName())) {
			writer.write(this.handleRequestListResourcesStatic());
		} else {
			writer.write(this.handleRequest(request));
		}

		writer.flush();
	}

	private String handleRequest(final MethodCall request) throws IOException {
		final StringWriter writer = new StringWriter();
		final String methodName = request.getMethodName();

		try {
			// fixme: why do we need an XmlRpcServer context here? Not nice for
			// testing...
			final Object ret = this.handler.invoke(methodName,
					request.getParams());
			this.getXmlRpcServer().getSerializer()
					.writeEnvelopeHeader(ret, writer);
			this.getXmlRpcServer().getSerializer().serialize(ret, writer);
			this.getXmlRpcServer().getSerializer()
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
		this.xstream.alias("methodCall", MethodCall.class);
		final MethodCall request = (MethodCall) this.xstream
				.fromXML(inputStream);
		return request;
	}
}
