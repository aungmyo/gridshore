package nl.gridshore.monitoring.springannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Jettro Coenradie
 */
@Component("client")
public class JmxTestClient {
    private ContactServiceMonitor contactServiceMonitor;

    public int obtainAmountOfContact() {
        return contactServiceMonitor.getAmountOfContacts();
    }

    @Autowired
    public void setContactServiceMonitor(@Qualifier("proxyContactServiceMonitor")ContactServiceMonitor contactServiceMonitor) {
        this.contactServiceMonitor = contactServiceMonitor;
    }
}
