package com.thijsrijpert.rekeningrijden.Controller.Database;

import com.thijsrijpert.rekeningrijden.Model.Car;
import com.thijsrijpert.rekeningrijden.Model.User;

public abstract class CarDao {

	/**
	 *
	 * @param user
	 */
	public abstract Car getAllCarsByUser(User user);

	/**
	 *
	 * @param car
	 */
	public abstract int insertCar(Car car);

	/**
	 *
	 * @param car
	 */
	public abstract int updateCar(Car car);

}