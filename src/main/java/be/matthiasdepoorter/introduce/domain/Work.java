package be.matthiasdepoorter.introduce.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

public class Work implements Comparable<Work> {

	private String title;

	private String company;

	private Date startDate;

	private Date endDate;

	private boolean relevant;

	private String information;

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
		return startDate.toLocalDate().format(DateTimeFormatter.ofPattern("M/yy"));
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate.toLocalDate().format(DateTimeFormatter.ofPattern("M/yy"));
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isRelevant() {
		return relevant;
	}

	public void setRelevant(boolean relevant) {
		this.relevant = relevant;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	@Override
	public String toString() {
		return this.getStartDate() + "-" + this.getEndDate() + this.title + this.company + this.information;
	}

	@Override
	public int compareTo(Work o) {
		return o.startDate.compareTo(this.startDate);
	}
}
