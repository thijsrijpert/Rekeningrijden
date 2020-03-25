package com.thijsrijpert.rekeningrijden.controller.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.thijsrijpert.rekeningrijden.model.DefaultCharge;

import java.time.LocalDate;


/**
 * The Dao for the default charge model object contains all queries for the default charge CRUD system.
 */
@Dao
public interface DefaultChargeDao extends BaseDao<DefaultCharge>{

	/**
	 *	Query to get the  default charge that start at a specific date.
	 * @param startdate the date at which this charge should start. Attribute of Default Charge model object
	 * @return the default charge that was valid at this date
	 */
	@Query("SELECT * FROM defaultcharges WHERE startdate = :startdate")
	DefaultCharge getDefaultChargeByStartDate(LocalDate startdate);
}