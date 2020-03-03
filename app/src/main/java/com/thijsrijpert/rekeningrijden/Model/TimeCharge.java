package com.thijsrijpert.rekeningrijden.Model;

import android.text.format.Time;

import java.util.Date;

public class TimeCharge extends DefaultCharge {

	private Time time;

	public TimeCharge() {

	}

	public TimeCharge(double price, Date startdate, Date enddate, Time time) {
		super(price, startdate, enddate);
		this.time = time;
	}

	public Time getTime() {
		return this.time;
	}

	/**
	 *
	 * @param time
	 */
	public void setTime(Time time) {
		this.time = time;
	}

}