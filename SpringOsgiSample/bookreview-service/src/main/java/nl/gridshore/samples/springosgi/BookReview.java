package nl.gridshore.samples.springosgi;

import java.io.Serializable;

public class BookReview extends Review implements Serializable {
	private static final long serialVersionUID = 3204246225317542493L;

	private Book book;

	public Book getBook() {
		return book;
	}

	public void setBook(final Book book) {
		this.book = book;
	}

}
