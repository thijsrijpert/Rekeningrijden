package com.thijsrijpert.rekeningrijden.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Model Object that contains all user data
 */
@Entity(tableName = "Users")
public class User {
	@PrimaryKey @NonNull
	private final String username;
	private String zipcode;
	private String homenumber;
	private String phonenumber;
	private String driver;
	private String password;
	private Role role;

	/**
	 * Create a user object with just the primary key. Should only be used by converter functions for foreign keys.
	 * @param username the username of the user. Used to identify the user on login.
	 */
	@Ignore
	public User(@NonNull String username) {
		this.username = username;
	}

	/**
	 * Constructor used by database to create the model
	 * @param username the username of the user. Used to identify the user on login.
	 * @param zipcode the zipcode of the user. Only used for storage/display purposes. Not used by functional elements.
	 * @param homenumber the homenumber of the user. Only used for storage/display purposes. Not used by functional elements.
	 * @param phonenumber the phonenumber of the user. Only used for storage/display purposes. Not used by functional elements.
	 * @param driver the name of the user. Used to communicatie with the user
	 * @param password the passport of the user. Only used for the login functionality
	 * @param role the role of the user. Used to determine which interface should be shown.
	 */
	public User(@NonNull String username, @NonNull String zipcode, @NonNull String homenumber, @NonNull String phonenumber, @NonNull String driver, @NonNull String password, @NonNull Role role) {
		this.username = username;
		this.zipcode = zipcode;
		this.homenumber = homenumber;
		this.phonenumber = phonenumber;
		this.driver = driver;
		this.password = password;
		this.role = role;
	}

	/**
	 * Get the username of the user
	 * @return the username of the user. Used to identify the user on login.
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Get the zipcode of the user
	 * @return the zipcode of the user. Only used for storage/display purposes. Not used by functional elements.
	 */
	public String getZipcode() {
		return this.zipcode;
	}

	/**
	 * Set a new zipcode for the user
	 * @param zipcode the zipcode of the user. Only used for storage/display purposes. Not used by functional elements.
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * Get the homenumber of the user
	 * @return the homenumber of the user. Only used for storage/display purposes. Not used by functional elements.
	 */
	public String getHomenumber() {
		return this.homenumber;
	}

	/**
	 * Set a new homenumber for the user
	 * @param homenumber the homenumber of the user. Only used for storage/display purposes. Not used by functional elements.
	 */
	public void setHomenumber(String homenumber) {
		this.homenumber = homenumber;
	}

	/**
	 * Get the phonenumber of the user
	 * @return the phonenumber of the user. Only used for storage/display purposes. Not used by functional elements.
	 */
	public String getPhonenumber() {
		return this.phonenumber;
	}

	/**
	 * Set a new phonenumber for the user
	 * @param phonenumber the phonenumber of the user. Only used for storage/display purposes. Not used by functional elements.
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * Get the name of the user
	 * @return the name of the user. Used to communicatie with the user
	 */
	public String getDriver() {
		return this.driver;
	}

	/**
	 * Set the name of the user
	 * @param driver the name of the user. Used to communicatie with the user
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * Get the password of the user.
	 * @return the passport of the user. Only used for the login functionality
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Set a new password for the user
	 * @param password the passport of the user. Only used for the login functionality
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the role of the user
	 * @return the role of the user. Used to determine which interface should be shown.
	 */
	public Role getRole() {return this.role;}

	/**
	 * Set a new role for the user
	 * @param role the role of the user. Used to determine which interface should be shown.
	 */
	public void setRole(Role role) {this.role = role;}

}