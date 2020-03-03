package com.thijsrijpert.rekeningrijden.Model;

import android.text.format.Time;

import java.util.Date;

public class Ride {

	private Car numberplate;
	private double startlocation;
	private double stoplocation;
	private TimeCharge starttime;
	private TimeCharge stoptime;
	private Date date;

	public Ride() {

	}

	public Ride(Car numberplate, double startlocation, double stoplocation, TimeCharge starttime, TimeCharge stoptime, Date date) {
		this.numberplate = numberplate;
		this.startlocation = startlocation;
		this.stoplocation = stoplocation;
		this.starttime = starttime;
		this.stoptime = stoptime;
		this.date = date;
	}

	public Car getNumberplate() { return this.numberplate;}

	/**
	 *
	 * @param numberplate
	 */
	public void setNumberplate(Car numberplate) {

	}

	public double getStartlocation() {
		return this.startlocation;
	}

	/**
	 *
	 * @param startlocation
	 */
	public void setStartlocation(double startlocation) {
		this.startlocation = startlocation;
	}

	public double getStoplocation() {
		return this.stoplocation;
	}

	/**
	 *
	 * @param stoplocation
	 */
	public void setStoplocation(double stoplocation) {
		this.stoplocation = stoplocation;
	}

	public Time getStarttime() {
		// TODO - implement Ride.getStarttime
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param starttime
	 */
	public void setStarttime(Time starttime) {
		// TODO - implement Ride.setStarttime
		throw new UnsupportedOperationException();
	}

	public Time getStoptime() {
		// TODO - implement Ride.getStoptime
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param stoptime
	 */
	public void setStoptime(Time stoptime) {
		// TODO - implement Ride.setStoptime
		throw new UnsupportedOperationException();
	}

	public Date getDate() {
		return this.date;
	}

	/**
	 *
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}