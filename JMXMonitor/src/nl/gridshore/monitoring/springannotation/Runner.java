package nl.gridshore.monitoring.springannotation;

import nl.gridshore.monitoring.Contact;
import nl.gridshore.monitoring.ContactService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Jettro Coenradie
 */
public class Runner {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("nl/gridshore/monitoring/springannotation/spring-config.xml");
        ContactService service = context.getBean(ContactService.class);

        String name = "";

        do {
            int amountOfContacts = context.getBean(JmxTestClient.class).obtainAmountOfContact();

            System.out.println("Amount of contacts now is : " + amountOfContacts);

            System.out.println("Type stop if you want to, well euh, stop !");

            name = (new BufferedReader(new InputStreamReader(System.in))).readLine();
            service.addContact(new Contact(0,name));
        } while (!"stop".equals(name));
    }

}
