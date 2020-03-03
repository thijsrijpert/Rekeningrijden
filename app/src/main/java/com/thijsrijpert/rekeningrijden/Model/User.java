package com.thijsrijpert.rekeningrijden.Model;

public class User {

	private String username;
	private String zipcode;
	private String homenumber;
	private String phonenumber;
	private String driver;
	private String password;
	private Role role;

	public String getUsername() {
		return this.username;
	}

	/**
	 *
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	/**
	 *
	 * @param zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getHomenumber() {
		return this.homenumber;
	}

	/**
	 *
	 * @param homenumber
	 */
	public void setHomenumber(String homenumber) {
		this.homenumber = homenumber;
	}

	public String getPhonenumber() {
		return this.phonenumber;
	}

	/**
	 *
	 * @param phonenumber
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getDriver() {
		return this.driver;
	}

	/**
	 *
	 * @param driver
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 *
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {return this.role;}

	/**
	 *
	 * @param role
	 */
	public void setRole(Role role) {this.role = role;}

}