package com.thijsrijpert.rekeningrijden.Controller.Database;

import androidx.room.Dao;
import androidx.room.Query;

import com.thijsrijpert.rekeningrijden.Model.Ride;

import java.util.List;

/**
 * The Dao for the ride model object contains all queries for the ride CRUD system.
 */
@Dao
public interface RideDao extends BaseDao<Ride>{

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

}