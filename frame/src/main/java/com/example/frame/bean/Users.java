package com.example.frame.bean;

import java.io.Serializable;

public class Users implements Serializable {
	private int id;
	private String userName;
	private int number;
	private String password;
	private String user_icon;
	private int sex;
	private String jurisdiction;
	private String mobile_key;
	private String manifesto;

	public void setManifesto(String manifesto) {
		this.manifesto = manifesto;
	}

	public String getManifesto() {
		return manifesto;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", userName=" + userName + ", number="
				+ number + ", password=" + password + ", user_icon="
				+ user_icon + ", sex=" + sex + ", jurisdiction=" + jurisdiction
				+ ", mobile_key=" + mobile_key + "]";
	}

	public Users(int id, String userName, int number, String password,
                 String user_icon, int sex, String jurisdiction, String mobile_key) {
		super();
		this.id = id;
		this.userName = userName;
		this.number = number;
		this.password = password;
		this.user_icon = user_icon;
		this.sex = sex;
		this.jurisdiction = jurisdiction;
		this.mobile_key = mobile_key;
	}

	public String getMobile_key() {
		return mobile_key;
	}

	public void setMobile_key(String mobile_key) {
		this.mobile_key = mobile_key;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_icon() {
		return user_icon;
	}

	public void setUser_icon(String user_icon) {
		this.user_icon = user_icon;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
}
