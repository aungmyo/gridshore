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
 * To change this template use File | Settings | File Templates.
 */
public class HelloServlet extends HttpServlet {
    private TrainingService trainingService;

    public HelloServlet(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        List<String> trainings = trainingService.obtainTrainings();
        httpServletResponse.setContentType("text/html");
        ServletOutputStream out = httpServletResponse.getOutputStream();
        out.println("<html><body>");
        for (String training : trainings) {
            out.println(training);
            out.println("<br/>");
        }
        out.println("</body></html>");
        out.close();
    }
}
