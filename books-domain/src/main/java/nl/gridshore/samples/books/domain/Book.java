package nl.gridshore.samples.books.domain;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 18, 2008
 * Time: 9:58:15 AM
 * Domain class representing a book.
 */
@Entity
@Table(name = "books")
public class Book extends BaseDomain {
    private String title;
    private String isbn;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="Book_Author",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private List<Author> authors = new ArrayList<Author>();

    public Book() {
        super();
    }

    public Book(String title, String isbn) {
        super();
        this.title = title;
        this.isbn = isbn;
    }

    public Book(Long id, String title, String isbn) {
        super(id);
        this.title = title;
        this.isbn = isbn;
    }

    public Book(List<Author> authors, Long id, String title, String isbn) {
        super(id);
        this.authors = authors;
        this.title = title;
        this.isbn = isbn;
    }

    public void addAuthor(final Author author) {
        author.getBooks().add(this);
        this.authors.add(author);
    }

    public void removeAuthor(final Author author) {
        this.authors.remove(author);
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
