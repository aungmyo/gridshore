package nl.gridshore.monitoring;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Jettro Coenradie
 */
@Service("contactService")
public class InMemoryContactService implements ContactService {
    private ContactServiceStatistics statistics = new ContactServiceStatistics();
    private Set<Contact> contacts = new CopyOnWriteArraySet<Contact>();

    public void addContact(Contact contact) {
        contacts.add(contact);
        statistics.addContact();
    }

    public Set<Contact> listAllContacts() {
        return Collections.unmodifiableSet(contacts);
    }

    public Contact findContactByName(String name) {
        for (Contact contact : contacts) {
            if (name.equals(contact.getName())) {
                return contact;
            }
        }
        return null;
    }

    public ContactServiceStatistics getStatistics() {
        return statistics;
    }
}
