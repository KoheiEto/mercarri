package com.example.domain;

import java.sql.Date;

public class User {
	private Integer id;
	private String mailAddress;
	private String password;
	/*
	 * private Integer authority; private String uuid; private Date registerDate;
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * public Integer getAuthority() { return authority; }
	 * 
	 * public void setAuthority(Integer authority) { this.authority = authority; }
	 * 
	 * public String getUuid() { return uuid; }
	 * 
	 * public void setUuid(String uuid) { this.uuid = uuid; }
	 * 
	 * public Date getRegisterDate() { return registerDate; }
	 * 
	 * public void setRegisterDate(Date registerDate) { this.registerDate =
	 * registerDate; }
	 */
}
