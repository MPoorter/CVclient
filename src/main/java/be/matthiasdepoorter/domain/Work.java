package be.matthiasdepoorter.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Work implements Comparable<Work> {

	private String title;

	private String company;

	private String startDate;

	private String endDate;

	private boolean relevant;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isRelevant() {
		return relevant;
	}

	public void setRelevant(boolean relevant) {
		this.relevant = relevant;
	}

	@Override
	public String toString() {
//		return this.getStartDate().format(DateTimeFormatter.ofPattern("yyyy"))
//				+ ((this.getStartDate().getYear() != this.getEndDate().getYear())
//						? " - "+ this.getEndDate().format(DateTimeFormatter.ofPattern("yyyy")) : "\t")
//				+ title + company;
		return this.startDate+this.endDate + this.title + this.company;
	}

	@Override
	public int compareTo(Work o) {
		return o.endDate.compareTo(this.endDate)+o.startDate.compareTo(this.startDate);
	}

}
