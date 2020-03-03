package com.thijsrijpert.rekeningrijden.Model;

import java.util.Date;

public class DefaultCharge {

	private double price;
	private Date startdate;
	private Date enddate;

	public DefaultCharge() {

	}

	public DefaultCharge(double price, Date startdate, Date enddate) {
		this.price = price;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	public double getPrice() {
		return this.price;
	}

	/**
	 *
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	/**
	 *
	 * @param startdate
	 */
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	/**
	 *
	 * @param enddate
	 */
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

}