package org.fiteagle.core.framework.deployment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.fiteagle.core.framework.requestprocessor.RequestProcessor;

import teagle.vct.util.Util;

public class FITeagleServlet extends HttpServlet {

	private static final long serialVersionUID = 6498501880798821411L;

	@Override
	public void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		final String in = convertStreamToString(request.getInputStream());
		final PrintWriter out = response.getWriter();
		final RequestProcessor reqProcessor = new RequestProcessor();

		Util.debug("Getting: " + in);
		try {
			reqProcessor.handle(in);
		} catch (final JAXBException e) {
			throw new ServletException(e);
		}

		final String result = reqProcessor.getResultAsString();
		Util.debug("Returning: " + result);
		out.write(result);
	}

	private String convertStreamToString(final ServletInputStream inputStream) {
		return new java.util.Scanner(inputStream).useDelimiter("\\A").next();
	}
}