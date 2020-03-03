package com.thijsrijpert.rekeningrijden.Controller.Database;

import com.thijsrijpert.rekeningrijden.Model.LocationCharge;

public abstract class LocationChargeDao {

	/**
	 *
	 * @param charge
	 */
	public abstract LocationCharge getLocationChargeByLocationCharge(LocationCharge charge);

	/**
	 *
	 * @param charge
	 */
	public abstract int insertLocationCharge(LocationCharge charge);

}