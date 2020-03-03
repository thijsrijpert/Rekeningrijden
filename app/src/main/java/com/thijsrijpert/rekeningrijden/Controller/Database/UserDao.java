package com.thijsrijpert.rekeningrijden.Controller.Database;

import com.thijsrijpert.rekeningrijden.Model.User;

import java.util.ArrayList;

public abstract class UserDao {

	/**
	 *
	 * @param user
	 */
	public abstract User getUser(User user);

	public abstract ArrayList<User> getAllUsers();

	/**
	 *
	 * @param user
	 */
	public abstract int updateUser(User... user);

	/**
	 *
	 * @param user
	 */
	public abstract int insertUser(User... user);

}