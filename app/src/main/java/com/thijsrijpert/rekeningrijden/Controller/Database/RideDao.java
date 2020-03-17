package com.thijsrijpert.rekeningrijden.Controller.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thijsrijpert.rekeningrijden.Model.Ride;

import java.util.List;

/**
 * The Dao for the ride model object contains all queries for the ride CRUD system.
 */
@Dao
public interface RideDao {

	/**
	 *	Query all rides made by a specific user
	 * @param username the username of a person. Select all rides this person made
	 * @return a list of all rides a car made
	 */
	@Query("SELECT numberplate, startlocation, stoplocation, starttime, stoptime, date FROM rides JOIN Cars USING (numberplate) WHERE username = :username")
	List<Ride> getAllRidesByUser(String username);

	/**
	 * Query a specific ride by the primary key
	 * @param numberplate the numberplate of the car. Attribuate of the car model object
	 * @param startTime the starttime of the ride. Attribute of the ride model object
	 * @param date the date that ride took place. Attribute of the ride model object
	 * @return all details of the ride
	 */
	@Query("SELECT * FROM rides WHERE numberplate = :numberplate AND starttime = :startTime AND date = :date ")
	Ride getRideByRide(String numberplate, String startTime, String date);
	/**
	 * Inserts a collection of rides into the database
	 * @param ride the ride model object that should be inserted into the database
	 */
	@Insert
	void insertRide(Ride... ride);

	/**
	 * Update a collection of ride in the database
	 * @param ride the ride model object that should be updated. Based on primary key
	 */
	@Update
	void updateRide(Ride ride);

}