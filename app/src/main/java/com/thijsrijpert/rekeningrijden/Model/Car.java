package com.thijsrijpert.rekeningrijden.Model;

public class Car {

	private String numberplate;
	private String brand;
	private String color;
	private String type;
	private User username;

	public String getNumberplate() {
		return this.numberplate;
	}

	/**
	 *
	 * @param numberplate
	 */
	public void setNumberplate(String numberplate) {
		this.numberplate = numberplate;
	}

	public String getBrand() {
		return this.brand;
	}

	/**
	 *
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return this.color;
	}

	/**
	 *
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return this.type;
	}

	/**
	 *
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	public User getUsername() {return username; }

	/**
	 *
	 * @param username
	 */
	public void setUsername(User username) { this.username = username;}

}