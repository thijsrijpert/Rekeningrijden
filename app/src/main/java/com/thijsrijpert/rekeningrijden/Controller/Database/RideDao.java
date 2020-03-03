package com.thijsrijpert.rekeningrijden.Controller.Database;

import com.thijsrijpert.rekeningrijden.Model.Car;
import com.thijsrijpert.rekeningrijden.Model.Ride;

public abstract class RideDao {

	/**
	 *
	 * @param car
	 */
	public abstract Ride getAllRidesByCar(Car car);

	/**
	 *
	 * @param ride
	 */
	public abstract int insertRide(Ride ride);

	/**
	 *
	 * @param ride
	 */
	public abstract int updateRide(Ride ride);

}