package nl.gridshore.samples.bundles.jettyhttpservice.impl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 17, 2008
 * Time: 8:54:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class DummyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/html");
        ServletOutputStream out = httpServletResponse.getOutputStream();
        out.println("<html><body>");
        out.println("Seeing these lines of text means two things:<br/>");
        out.println("<ol>");
        out.println("<li>The jetty server is running with an osgi container.</helpli>");
        out.println("<li>There is no default servlet configured.</li>");
        out.println("</0l>");
        out.println("</body></html>");
        out.close();
    }
}
