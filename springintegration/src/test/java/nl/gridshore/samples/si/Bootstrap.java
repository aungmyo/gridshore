package nl.gridshore.samples.si;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: jettrocoenradie
 * Date: Mar 28, 2009
 * Time: 9:53:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"integration-config.xml"});
        new Scanner(System.in).nextLine();
    }
    
}
