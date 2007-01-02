package nl.gridshore.samples.springosgi.impl;

import java.util.List;

import nl.gridshore.samples.springosgi.BookReviewService;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class BookReviewServiceIntegrationTest extends
		AbstractDependencyInjectionSpringContextTests {
	private BookReviewService myBookReviewService;

	public void setBookReviewService(BookReviewService bookReviewService) {
		this.myBookReviewService = bookReviewService;
	}

	protected String[] getConfigLocations() {
		return new String[] { "META-INF/spring/bundle-context.xml" };
	}

	public void testGetBookReviewByKeyWord() {
		List bookreviews = myBookReviewService.findBookReviewByKeyword("habits");
		assertNotNull(bookreviews);
		assertEquals(1, bookreviews.size());
	}
}
