package com.thijsrijpert.rekeningrijden.controller.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.thijsrijpert.rekeningrijden.model.Car;
import com.thijsrijpert.rekeningrijden.model.Ride;

import java.time.LocalDate;
import java.time.LocalTime;
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

	@Query("SELECT (c.price + l.price)/2 " +
			" FROM rides r " +
			" JOIN locationcharges c ON (r.startlocation = c.location) " +
			" JOIN locationcharges l ON (r.stoplocation = l.location) " +
			" WHERE r.numberplate = :numberplate " +
			" AND r.date = :date " +
			" AND r.starttime = :starttime " +
			" AND (c.startdate <= r.date " +
			" AND (c.enddate >= r.date OR c.enddate IS NULL)) " +
			" AND (l.startdate <= r.date " +
			" AND (l.enddate >= r.date OR l.enddate IS NULL))")
	double getActiveLocationCharge(LocalDate date, LocalTime starttime, Car numberplate);

	@Query("SELECT (t.price + c.price)/2 " +
			" FROM rides r " +
			" JOIN TimeCharges c ON (r.starttime = c.time) " +
			" JOIN TimeCharges t ON (r.stoptime = t.time) " +
			" WHERE r.numberplate = :numberplate " +
			" AND r.date = :date " +
			" AND r.starttime = :starttime " +
			" AND (c.startdate <= r.date " +
			" AND (c.enddate >= r.date OR c.enddate IS NULL)) " +
			" AND (t.startdate <= r.date " +
			" AND (t.enddate >= r.date OR t.enddate IS NULL))")
	double getActiveTimeCharge(LocalDate date, LocalTime starttime, Car numberplate);

	@Query("SELECT c.price " +
			"FROM rides r " +
			"JOIN DefaultCharges c ON (r.date >= c.startdate) " +
			"WHERE r.numberplate = :numberplate " +
			"AND r.date = :date " +
			"AND r.starttime = :starttime " +
			"AND (c.enddate >= r.date OR c.enddate IS NULL)")
	double getActiveDefaultCharge(LocalDate date, LocalTime starttime, Car numberplate);
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