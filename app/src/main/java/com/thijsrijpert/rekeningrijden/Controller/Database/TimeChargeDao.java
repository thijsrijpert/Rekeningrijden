package com.thijsrijpert.rekeningrijden.Controller.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thijsrijpert.rekeningrijden.Model.TimeCharge;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * The Dao for the time charge model object contains all queries for the time charge CRUD system.
 */
@Dao
public interface TimeChargeDao {

	/**
	 * Query all objects from the time charge table.
	 * @return All objects from the time charge table
	 */
	@Query("SELECT * FROM TimeCharges")
	List<TimeCharge> getAllTimeCharges();
	/**
	 *	Get all values for a specific time charge
	 * @param time the time the charge is affecting. Attribute of time charge
	 * @param startDate the date at which the time charge becomes valid. Attribute of time charge
	 * @return all data for a specific time charge
	 */
	@Query("SELECT * FROM TimeCharges WHERE time = :time AND startdate = :startDate")
	TimeCharge getTimeChargeByTimeCharge(LocalTime time, LocalDate startDate);

	/**
	 * Select the values for a charge on a time that is still valid
	 * @param time the time the charge is affecting. Attribute of time charge.
	 * @return all data for a specific time charge that is still valid
	 */
	@Query("SELECT * FROM TimeCharges WHERE time = :time AND enddate IS NOT NULL")
	TimeCharge getCurrentTimeCharge(LocalTime time);

	/**
	 * Insert a collection of time charge model objects
	 * @param charge the time charge model objects that contain the data.
	 */
	@Insert
	void insertTimeCharge(TimeCharge... charge);

	/**
	 * Update a collection of thime charge model objects.
	 * @param charge the time charge model objects that should be updated. Cannot change the primary key
	 */
	@Update
	void updateTimeCharge(TimeCharge... charge);

}