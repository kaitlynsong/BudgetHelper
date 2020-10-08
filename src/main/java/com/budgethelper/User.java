package com.budgethelper;

import java.sql.Date;
 
public class User {
    private String name;
    private String email;
    private String password;
    private String gender;
    private String note;
    private boolean married;
    @Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", gender=" + gender + ", note="
				+ note + ", married=" + married + ", birthday=" + birthday + ", profession=" + profession + "]";
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getGender() {
		return gender;
	}
	public String getNote() {
		return note;
	}
	public boolean isMarried() {
		return married;
	}
	public Date getBirthday() {
		return birthday;
	}
	public String getProfession() {
		return profession;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setMarried(boolean married) {
		this.married = married;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	private Date birthday;
    private String profession;
 
    // getters
    // setters
     
    // override toString()
 
}
