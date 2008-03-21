package nl.gridshore.samples.books.domain;

import javax.persistence.*;
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
    @Column(unique = true)
    private String fullName;
    private String email;
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<Book>();

    public Author() {
    }

    public Author(String fullName) {
        super();
        this.fullName = fullName;
    }

    public Author(String email, String fullName) {
        super();
        this.email = email;
        this.fullName = fullName;
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
