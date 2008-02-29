package nl.gridshore.samples.bundles.httpservlets.impl;

import nl.gridshore.samples.bundles.trainingservice.api.TrainingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import java.io.IOException;
import java.util.List;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 15, 2008
 * Time: 10:26:34 AM
 * Servlet used to show all trainings
 */
public class TrainingsServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(TrainingsServlet.class);
    private TrainingService trainingService;

    public TrainingsServlet(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        logger.info("doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)");
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