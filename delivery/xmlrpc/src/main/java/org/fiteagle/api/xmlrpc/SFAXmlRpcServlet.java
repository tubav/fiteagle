package org.fiteagle.api.xmlrpc;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fiteagle.interactors.sfa.SFAInteractor;

import redstone.xmlrpc.XmlRpcInvocationHandler;
import redstone.xmlrpc.XmlRpcServlet;
import redstone.xmlrpc.handlers.ReflectiveInvocationHandler;

public class SFAXmlRpcServlet extends XmlRpcServlet {

	private static final long serialVersionUID = -4349365825732565723L;

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		super.init(arg0);
		ReflectiveInvocationHandler recHandler = new ReflectiveInvocationHandler(
				new SFAInteractor());

		getXmlRpcServer().addInvocationHandler("sfa", recHandler);

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		XmlRpcInvocationHandler sfaWrap = getXmlRpcServer()
				.getInvocationHandler("sfa");

		try {
			Object ret = sfaWrap.invoke("getVersion", new LinkedList<String>());
			Writer writer = new OutputStreamWriter(resp.getOutputStream());
			this.getXmlRpcServer().getSerializer().serialize(ret, writer);
			writer.flush();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
