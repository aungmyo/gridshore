package nl.gridshore.samples.bundles.exampleclient.impl;

import nl.gridshore.samples.bundles.trainingservice.api.TrainingService;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 11, 2008
 * Time: 12:10:20 AM
 * Servlet that uses a TrainingService to obtain info about trainings
 */
public class HelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/html");
        ServletOutputStream out = httpServletResponse.getOutputStream();
        out.println("<html><body>");
        out.println("<a href=\"trainings\">trainings</a>");
        out.println("</body></html>");
        out.close();
    }
}
