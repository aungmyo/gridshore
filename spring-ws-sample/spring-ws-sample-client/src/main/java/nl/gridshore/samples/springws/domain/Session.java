package nl.gridshore.samples.springws.domain;

public class Session {
	private int id;
	private String name;
	private Congress congress;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Congress getCongress() {
		return congress;
	}
	public void setCongress(Congress congress) {
		this.congress = congress;
	}
}
