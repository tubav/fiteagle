package org.fiteagle.core.framework.deployment;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.teagle.clients.api.LegacyTeagleClient;

public class FITeagleServletTest {

	private String startRequest;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private final StringWriter result = new StringWriter();

	@Before
	public void setup() throws IOException {
		setupVctStartRequest();
		setupServletContext();
	}

	@Test
	public void test() throws ServletException, IOException {
		final FITeagleServlet servlet = new FITeagleServlet();
		servlet.doPost(request, response);
		Assert.assertTrue(this.result.getBuffer().toString()
				.contains("<status>0</status>"));
	}

	public void setupServletContext() throws IOException {
		this.request = EasyMock.createNiceMock(HttpServletRequest.class);
		EasyMock.expect(request.getInputStream()).andReturn(
				new MockServletInputStream(startRequest));
		EasyMock.replay(request);

		this.response = EasyMock.createMock(HttpServletResponse.class);
		EasyMock.expect(response.getWriter()).andReturn(
				new PrintWriter(this.result));
		EasyMock.replay(response);
	}

	private void setupVctStartRequest() {
		final String reqCommand = "startVct";
		final String vctName = "testvct";
		final String username = "testuser";
		this.startRequest = LegacyTeagleClient.genRequest(username, vctName,
				reqCommand);
	}

	private class MockServletInputStream extends ServletInputStream {
		private final String body;
		private int index = 0;

		public MockServletInputStream(final String body) {
			this.body = body;
		}

		@Override
		public int read() throws IOException {
			if (index == body.length()) {
				return -1;
			}

			return body.codePointAt(index++);
		}
	}
}
