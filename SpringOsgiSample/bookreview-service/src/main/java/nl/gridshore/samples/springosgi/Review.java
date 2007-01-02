package nl.gridshore.samples.springosgi;

import java.io.Serializable;

public class Review implements Serializable {
	private static final long serialVersionUID = 3914088681045662999L;

	private String summary;
	private int appriciation; // ranging from 1 to 5 stars
	private String detailedDescription;

	public int getAppriciation() {
		return appriciation;
	}
	public void setAppriciation(int appriciation) {
		this.appriciation = appriciation;
	}
	public String getDetailedDescription() {
		return detailedDescription;
	}
	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
