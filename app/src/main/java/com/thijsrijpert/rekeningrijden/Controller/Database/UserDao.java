package com.thijsrijpert.rekeningrijden.Controller.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thijsrijpert.rekeningrijden.Model.User;


/**
 * The Dao for the user model object contains all queries for the user CRUD system.
 */
@Dao
public interface UserDao {

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

	/**
	 *	Inserts a collection of user model objects into the database
	 * @param user the user model objects that should be inserted into the database
	 */
	@Update
	int updateUser(User... user);

	/**
	 *	Updates a collection of user model objects in the database based on the primary key. You cannot update the primary key.
	 * @param user the user model objects that should be updated
	 */
	@Insert
	long[] insertUser(User... user);

}