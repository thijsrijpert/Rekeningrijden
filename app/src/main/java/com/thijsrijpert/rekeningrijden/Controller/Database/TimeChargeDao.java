package com.thijsrijpert.rekeningrijden.Controller.Database;

import com.thijsrijpert.rekeningrijden.Model.TimeCharge;

public abstract class TimeChargeDao {

	/**
	 *
	 * @param charge
	 */
	public abstract TimeCharge getTimeChargeByTimeCharge(TimeCharge charge);

	/**
	 *
	 * @param charge
	 */
	public abstract int insertTimeCharge(TimeCharge charge);

}