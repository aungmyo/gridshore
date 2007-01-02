package nl.gridshore.samples.springosgi;

import java.io.Serializable;

public class Book implements Serializable {
	private static final long serialVersionUID = 7268972492306328602L;

	private int id;
	private String isbn;
	private String title;
	private String summary;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
