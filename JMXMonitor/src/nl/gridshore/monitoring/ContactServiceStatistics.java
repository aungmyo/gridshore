package nl.gridshore.monitoring;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jettro Coenradie
 */
public class ContactServiceStatistics {
    private AtomicInteger amountOfContacts = new AtomicInteger(0);

    public int getAmountOfContacts() {
        return amountOfContacts.intValue();
    }

    public void addContact() {
        amountOfContacts.getAndIncrement();
    }

    public void removeContact() {
        amountOfContacts.getAndDecrement();
    }
}
