package nl.gridshore.samples.springosgi.impl;

import java.util.List;

import nl.gridshore.samples.springosgi.BookReview;
import nl.gridshore.samples.springosgi.BookReviewService;
import junit.framework.TestCase;

public class BookReviewServiceImplTest extends TestCase {
	private BookReviewService bookReviewService;
	
	public void setUp() {
		this.bookReviewService = new BookReviewServiceImpl();
	}
	
	public void testFindBookReviewByIsbn() {
		BookReview review = bookReviewService.findBookReviewByIsbn("0743269519");
		assertNotNull(review);
		assertEquals("The 7 habits of highly effective people", review.getBook().getTitle());
		assertEquals(4, review.getAppriciation());
	}

	public void testFindBookReviewByIsbnNonExsiting() {
		BookReview review = bookReviewService.findBookReviewByIsbn("1234567890");
		assertNull(review);
	}

	public void testFindBookReviewByKeyword() {
		List reviews = bookReviewService.findBookReviewByKeyword("habits");
		assertNotNull(reviews);
		assertEquals("amount of books returned not as expected",1, reviews.size());
		BookReview review = (BookReview)reviews.get(0);
		assertNotNull(review);
		assertEquals("The 7 habits of highly effective people", review.getBook().getTitle());
		assertEquals(4, review.getAppriciation());
	}

	public void testFindBookReviewByKeywordNonExisting() {
		List reviews = bookReviewService.findBookReviewByKeyword("jettro");
		assertNotNull(reviews);
		assertEquals("amount of books returned not as expected",0, reviews.size());
	}

}
