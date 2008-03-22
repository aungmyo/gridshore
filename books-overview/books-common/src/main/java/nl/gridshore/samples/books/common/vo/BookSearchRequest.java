package nl.gridshore.samples.books.common.vo;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 21, 2008
 * Time: 9:27:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class BookSearchRequest implements Serializable {
    private String bookTitle;
    private String bookIsbn;
    private String authorFullName;

    public String getAuthorFullName() {
        return authorFullName;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
