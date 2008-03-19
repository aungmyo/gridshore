package nl.gridshore.samples.books.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 18, 2008
 * Time: 9:59:05 AM
 * Domain class representing an Author
 */
@Entity
@Table(name = "authors")
public class Author extends BaseDomain {
    private String fullName;
    private String email;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<Book>();

    public Author() {
    }

    public Author(String email, String fullName, Long id) {
        super(id);
        this.email = email;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void addBook(final Book book) {
        book.getAuthors().add(this);
        this.books.add(book);
    }

    public void removeBook(final Book book) {
        this.books.remove(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
