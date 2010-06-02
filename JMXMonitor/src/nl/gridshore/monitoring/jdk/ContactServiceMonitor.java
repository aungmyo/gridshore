package nl.gridshore.monitoring.jdk;

import nl.gridshore.monitoring.ContactService;
import nl.gridshore.monitoring.ContactServiceStatistics;

/**
 * @author Jettro Coenradie
 */
public class ContactServiceMonitor implements ContactServiceMonitorMXBean {
    private ContactServiceStatistics statistics;

    public ContactServiceMonitor(ContactService service) {
        this.statistics = service.getStatistics();
    }

    public int getAmountOfContacts() {
        return statistics.getAmountOfContacts();
    }
}
