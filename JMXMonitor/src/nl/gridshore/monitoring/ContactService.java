package nl.gridshore.monitoring;

import nl.gridshore.monitoring.Contact;

import java.util.List;
import java.util.Set;

/**
 * @author Jettro Coenradie
 */
public interface ContactService {
    void addContact(Contact contact);

    Set<Contact> listAllContacts();

    Contact findContactByName(String name);

    ContactServiceStatistics getStatistics();
}
