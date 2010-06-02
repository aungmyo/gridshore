package nl.gridshore.monitoring.springannotation;

import nl.gridshore.monitoring.ContactService;
import nl.gridshore.monitoring.ContactServiceStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * @author Jettro Coenradie
 */
@Component
@ManagedResource
public class ContactServiceMonitorSpring implements ContactServiceMonitor {
    private ContactServiceStatistics statistics;

    @Autowired
    public ContactServiceMonitorSpring(@Qualifier("contactService") ContactService service) {
        statistics = service.getStatistics();
    }

    @ManagedAttribute
    public int getAmountOfContacts() {
        return statistics.getAmountOfContacts();
    }

}
