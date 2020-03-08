package com.thijsrijpert.rekeningrijden.Controller.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thijsrijpert.rekeningrijden.Model.DefaultCharge;

import java.time.LocalDate;


/**
 * The Dao for the default charge model object contains all queries for the default charge CRUD system.
 */
@Dao
public interface DefaultChargeDao {

	/**
	 *	Query to get the  default charge that start at a specific date.
	 * @param startdate the date at which this charge should start. Attribute of Default Charge model object
	 * @return the default charge that was valid at this date
	 */
	@Query("SELECT * FROM defaultcharges WHERE startdate = :startdate")
	DefaultCharge getDefaultChargeByStartDate(LocalDate startdate);

	/**
	 * Inserts specific charge model objects into the database
	 * @param charge the specific default charge model objects that should be inserted
	 */
	@Insert
	void insertDefaultCharge(DefaultCharge... charge);

	/**
	 * Update the values of the default charge model object in the database. Primary key cannot be updated
	 * @param charge the default charge model objects that should be updated
	 */
	@Update
	void updateDefaultCharge(DefaultCharge... charge);

}