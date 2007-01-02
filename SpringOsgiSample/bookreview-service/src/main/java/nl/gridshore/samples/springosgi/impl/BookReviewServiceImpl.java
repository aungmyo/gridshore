package nl.gridshore.samples.springosgi.impl;

import java.util.ArrayList;
import java.util.List;

import nl.gridshore.samples.springosgi.Book;
import nl.gridshore.samples.springosgi.BookReview;
import nl.gridshore.samples.springosgi.BookReviewService;

public class BookReviewServiceImpl implements BookReviewService {
	private BookReview[] bookreviews;
	{
		Book objectsFirstBook = new Book();
		objectsFirstBook.setId(1);
		objectsFirstBook.setIsbn("013197629X");
		objectsFirstBook.setTitle("Objects First With Java");
		objectsFirstBook.setSummary("Book explaining object oriented programming using the BlueJ editor");
		
		Book aspectOSDBook = new Book();
		aspectOSDBook.setId(2);
		aspectOSDBook.setIsbn("0321268881");
		aspectOSDBook.setTitle("Aspect-Oriented Software Development with Use Cases");
		aspectOSDBook.setSummary("Book describing the method to start using aspects during analysis and design.");
		
		Book habitsBook = new Book();
		habitsBook.setId(3);
		habitsBook.setIsbn("0743269519");
		habitsBook.setTitle("The 7 habits of highly effective people");
		habitsBook.setSummary("Everything you need to know to be an effective software engineer, husband, wife, mother, father,etc.");
		
		bookreviews = new BookReview[3];
		bookreviews[0] = new BookReview();
		bookreviews[0].setBook(objectsFirstBook);
		bookreviews[0].setSummary("Very basic book, nice to start doing it the OOP way.");
		bookreviews[0].setDetailedDescription("Very basic book, nice to start doing it the OOP way. I like the usage of BlueJ during the learning process");
		bookreviews[0].setAppriciation(3);
		
		bookreviews[1] = new BookReview();
		bookreviews[1].setBook(aspectOSDBook);
		bookreviews[1].setSummary("The book to start with when learning about aspects.");
		bookreviews[1].setDetailedDescription("This book is so complete, everyone doing aspects should read this. Very nice");
		bookreviews[1].setAppriciation(5);

		bookreviews[2] = new BookReview();
		bookreviews[2].setBook(habitsBook);
		bookreviews[2].setSummary("Good to start being effective in your daily routine");
		bookreviews[2].setDetailedDescription("Sometime examples go a little bid to far by my taste. Still it is very educative and it did stimulate me to change my day to day business.");
		bookreviews[2].setAppriciation(4);

		
	}
	public BookReview findBookReviewByIsbn(String isbn) {
		BookReview bookReview = null;
		boolean found = false;
		for (int i = 0; i < bookreviews.length && found == false; i++) {
			if (bookreviews[i].getBook().getIsbn().equals(isbn)) {
				bookReview = bookreviews[i];
			}
		}
		return bookReview;
	}

	public List findBookReviewByKeyword(String keyword) {
		List foundBookReviews = new ArrayList();
		for (int i = 0; i < bookreviews.length ; i++) {
			if (bookreviews[i].getBook().getTitle().contains(keyword)) {
				foundBookReviews.add(bookreviews[i]);
			}
		}
		return foundBookReviews;
	}

}
