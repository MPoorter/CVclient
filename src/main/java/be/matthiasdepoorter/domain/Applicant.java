package be.matthiasdepoorter.domain;

import java.io.Serializable;
import java.util.Collection;


public class Applicant implements Serializable{

	private String name;
	

	private String address;
	

	private String birthday;
	

	private String email;
	

	private String phone;
	
	
	private String website;
	

	private String profile;
	

	private Collection <Study> studies;
	

	private Collection <Work> workExperiences;
	
	
	private byte[] skills;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Collection<Study> getStudies() {
		return studies;
	}

	public void setStudies(Collection<Study> studies) {
		this.studies = studies;
	}

	public Collection<Work> getWorkExperiences() {
		return workExperiences;
	}

	public void setWorkExperiences(Collection<Work> workExperiences) {
		this.workExperiences = workExperiences;
	}

	public byte[] getSkills() {
		return skills;
	}

	public void setSkills(byte[] skills) {
		this.skills = skills;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return name +
				address + 
				 birthday + 
				 email + 
				phone +
				website + 
				profile;
	}
	

	
	
	

}
