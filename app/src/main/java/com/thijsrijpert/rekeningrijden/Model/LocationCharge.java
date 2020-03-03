package com.thijsrijpert.rekeningrijden.Model;

import java.util.Date;

public class LocationCharge extends DefaultCharge {

	private double location;

	public LocationCharge() {

	}

	public LocationCharge(double price, Date startdate, Date enddate, double location) {
		super(price, startdate, enddate);
		this.location = location;
	}

	public double getLocation() {
		return this.location;
	}

	/**
	 *
	 * @param location
	 */
	public void setLocation(double location) {
		this.location = location;
	}

}