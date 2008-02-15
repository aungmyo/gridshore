package nl.gridshore.samples.bundles.exampleclient.impl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 15, 2008
 * Time: 11:00:23 AM
 * Servlet used to replace the training servlet if the training service is
 */
public class NonAvailableServlet extends HttpServlet {
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/html");
        ServletOutputStream out = httpServletResponse.getOutputStream();
        out.println("<html><body>");
        out.println("Service temporarily unavailable");
        out.println("</body></html>");
        out.close();
    }
}
