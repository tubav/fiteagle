package org.fiteagle.api.xmlrpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fiteagle.api.xmlrpc.util.MethodCall;
import org.fiteagle.interactors.sfa.SFAInteractor;

import redstone.xmlrpc.XmlRpcInvocationHandler;
import redstone.xmlrpc.XmlRpcServlet;
import redstone.xmlrpc.handlers.ReflectiveInvocationHandler;

import com.thoughtworks.xstream.XStream;

public class SFAXmlRpcServlet extends XmlRpcServlet {

	private static final long serialVersionUID = -4349365825732565723L;
	private XmlRpcInvocationHandler sfaWrap;

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		super.init(arg0);
		ReflectiveInvocationHandler recHandler = new ReflectiveInvocationHandler(
				new SFAInteractor());

		getXmlRpcServer().addInvocationHandler("sfa", recHandler);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.sfaWrap = getXmlRpcServer()
				.getInvocationHandler("sfa");
		
		try {
			//String requestXML = convertStreamToString(req.getInputStream());
			//String responseXML = handleRequest(requestXML);
			handleRequest(req.getInputStream(), resp.getOutputStream());
			//writer.write(responseXML);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		
	}

	public void handleRequest(InputStream inputStream,
			OutputStream outputStream) throws IOException {
		Writer writer = new OutputStreamWriter(outputStream);

		
		
		MethodCall request = getMethodCall(inputStream);
		String methodName = getNameOfMethodCall(request);		

		if ("getVersion".equals(methodName)) {
			writer.write(this.getVersionStatic());
		} else if ("listResources".equals(methodName)) {
			writer.write(this.listResourcesStatic());
		} else {

			try {
				Object ret = sfaWrap.invoke(methodName, request.getParams());
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
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		writer.flush();
	}

	private MethodCall getMethodCall(InputStream inputStream) {
		XStream xstream = new XStream();
		xstream.alias("methodCall", MethodCall.class);
		MethodCall request = (MethodCall) xstream.fromXML(inputStream);
		return request;
	}

	private String getNameOfMethodCall(MethodCall request) {
		String methodName = request.getMethodName();
		methodName = Character.toLowerCase(methodName.charAt(0))
				+ (methodName.length() > 1 ? methodName.substring(1) : "");
		return methodName;
	}

	public String getVersionStatic() throws IOException {
		return getFileAsString("/org/fiteagle/delivery/xmlrpc/sfa/getversion_response.xml");
	}

	public String listResourcesStatic() throws IOException {
		return getFileAsString("/org/fiteagle/delivery/xmlrpc/sfa/listresources_response.xml");
	}

	public static String getFileAsString(String filePath) throws java.io.IOException {
		final InputStream resourceAsStream = getFileAsInputStream(filePath);
		final StringBuffer fileData = new StringBuffer(1024);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(
				resourceAsStream));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}

	public static InputStream getFileAsInputStream(String filePath)
			throws IOException {
		final InputStream resourceAsStream = Thread.class.getClass().getResourceAsStream(filePath);

		if (null == resourceAsStream)
			throw new IOException("File not found in classpath: " + filePath);
		
		return resourceAsStream;
	}

}
