package com.thijsrijpert.rekeningrijden.Controller.Database;

import androidx.room.Dao;
import androidx.room.Query;

import com.thijsrijpert.rekeningrijden.Model.User;


/**
 * The Dao for the user model object contains all queries for the user CRUD system.
 */
@Dao
public interface UserDao extends BaseDao<User>{

	/**
	 * Query to check if the user before logging in and to get all data of this user.
	 * @param username the username of the user that wants to log in. Attribute of the User model object
	 * @param password the password of the user that wants to log in. Attribute of the User model object
	 */
	@Query("SELECT * FROM users WHERE username = :username AND password = :password")
	User getUserLogin(String username, String password);

	/**
	 * Query to get all data from a specific user.
	 * @param username the username of the user that is logged in. Attribute of the User model object
	 */
	@Query("SELECT * FROM users WHERE username = :username")
	User getUser(String username);

}