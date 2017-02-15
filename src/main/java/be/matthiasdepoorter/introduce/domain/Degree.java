package be.matthiasdepoorter.introduce.domain;

public class Degree {

	private String title;
	private String location;
	private String dateReceived;
	private String dates;
	private String certificates;
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getDateReceived() {
		return dateReceived;
	}


	public void setDateReceived(String dateReceived) {
		this.dateReceived = dateReceived;
	}


	public String getDates() {
		return dates;
	}


	public void setDates(String dates) {
		this.dates = dates;
	}
	
	public String getCertificates() {
		return certificates;
	}


	public void setCertificates(String certificates) {
		this.certificates = certificates;
	}


	@Override
	public String toString() {
		return dates + title + dateReceived  + location + certificates;
	}
	
	

}
