package com.thijsrijpert.rekeningrijden.Controller.Database;

import com.thijsrijpert.rekeningrijden.Model.DefaultCharge;

public abstract class DefaultChargeDao {

	/**
	 *
	 * @param charge
	 */
	public abstract void getDefaultChargeDefaultCharge(DefaultCharge charge);

	/**
	 *
	 * @param charge
	 */
	public abstract int insertDefaultCharge(DefaultCharge charge);

}