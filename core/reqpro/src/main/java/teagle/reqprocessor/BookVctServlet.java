package teagle.reqprocessor;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teagle.repository.Util;

/**
 * This is unused currently.
 */
public class BookVctServlet extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException { doPost(req, resp); }
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String op       = req.getParameter("op");
		String userName = req.getParameter("arg0");
		String name     = req.getParameter("arg1");

		Util.debug(String.format("operation=%s user=%s name=%s", op, userName, name));

		resp.getWriter().write("OK");
	}
}
