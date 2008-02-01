package nl.gridshore.samples.training.business;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 11:57:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class TempRunnerApp {
    public static void main(String[] args) {
        String filename = "to-integration/src/test/resources/example.xls";

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
                "classpath:business-config.xml",
                "classpath:dataaccess-config.xml"});

        ProjectService service = (ProjectService) context.getBean("trainingService");
        service.importAndHandleEmployeeDataFile(filename);
    }
}
