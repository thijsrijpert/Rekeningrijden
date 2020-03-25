package com.thijsrijpert.rekeningrijden.controller.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.thijsrijpert.rekeningrijden.model.TimeCharge;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * The Dao for the time charge model object contains all queries for the time charge CRUD system.
 */
@Dao
public interface TimeChargeDao extends BaseDao<TimeCharge>{

	/**
	 * Query all objects from the time charge table.
	 * @return All objects from the time charge table
	 */
	@Query("SELECT * FROM TimeCharges ORDER BY time, startdate")
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

}