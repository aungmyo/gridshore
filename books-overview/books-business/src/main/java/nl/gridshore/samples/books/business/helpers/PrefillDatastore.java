package nl.gridshore.samples.books.business.helpers;

import nl.gridshore.samples.books.business.BookManager;
import nl.gridshore.samples.books.domain.Book;
import nl.gridshore.samples.books.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 21, 2008
 * Time: 8:50:45 AM
 * Helper class to create an initial set of objects in the datastore. We use the beans from this
 * application to fill the datastore
 */
public class PrefillDatastore {
    private BookManager bookManager;

    @Autowired
    public PrefillDatastore(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public void initializeDatastore() {
        createBookAndAuthors("Professional Java Development with the Spring Framework","0764574833",
                "Rod Johnson", "Alef Arendsen");
        createBookAndAuthors("The machine that changed the world","0743299795",
                "James P. Womack","Daniel T. Jones","Daniel Roos");
        createBookAndAuthors("The 7 Habits of highly effective people", "0743269519",
                "Stephen R. Covey");
        createBookAndAuthors("Pour your heart into it","0786883561",
                "Howard Schultz", "Dori Jones Yang");
        createBookAndAuthors("Ik ging weg bij microsoft om de wereld te verbeteren","9047000099",
                "John Wood");
        createBookAndAuthors("The Cathedral and the Bazaar","0596001088",
                "Eric S. Raymond");
        createBookAndAuthors("Aspect Oriented Analysis and Design", "0321246748",
                "Siobhan Clarke", "Elisa Baniassad");
        createBookAndAuthors("Thinking in Java 4th ed.","0131872486",
                "Bruce Eckel");
        createBookAndAuthors("Aspect oriented software development with use cases","0321268881",
                "Ivar Jacobson", "Pan-wei NG");
    }

    private void createBookAndAuthors(String title, String isbn, String... authors) {
        Book book = new Book(title,isbn);
        for (String authorName : authors) {
            book.addAuthor(new Author(authorName));
        }
        bookManager.storeBook(book);
    }
}
