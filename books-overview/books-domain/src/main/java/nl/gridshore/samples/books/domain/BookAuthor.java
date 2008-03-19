package nl.gridshore.samples.books.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 19, 2008
 * Time: 10:54:41 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "bookauthors")
public class BookAuthor extends BaseDomain {
    private Long book_Id;
    private Long Author_id;

    public BookAuthor() {
    }

    public Long getAuthor_id() {
        return Author_id;
    }

    public void setAuthor_id(Long author_id) {
        Author_id = author_id;
    }

    public Long getBook_Id() {
        return book_Id;
    }

    public void setBook_Id(Long book_Id) {
        this.book_Id = book_Id;
    }
}
