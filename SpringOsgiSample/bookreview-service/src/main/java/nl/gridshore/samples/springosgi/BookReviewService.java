package nl.gridshore.samples.springosgi;

import java.util.List;

public interface BookReviewService {
	public List findBookReviewByKeyword(String keyword);
	public BookReview findBookReviewByIsbn(String isbn);
}
