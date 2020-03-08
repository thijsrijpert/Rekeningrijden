package com.thijsrijpert.rekeningrijden.Controller.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thijsrijpert.rekeningrijden.Model.LocationCharge;

import java.time.LocalDate;
import java.util.List;

/**
 * The Dao for the location charge model object contains all queries for the location charge CRUD system.
 */
@Dao
public interface LocationChargeDao {

	/**
	 * Query all location charge present in the database
	 * @return All location charges that have been added to the database
	 */
	@Query("SELECT * FROM locationcharges")
	 List<LocationCharge> getAllLocationCharges();

	/**
	 * Query all location charges for a specific location
	 * @param location the location that contains these location charges
	 * @return all location charges for a specific location
	 */
	@Query("SELECT * FROM LocationCharges WHERE location = :location")
	List<LocationCharge> getLocationChargeByLocation(double location);

	/**
	 * Get the all data of a specific location charge
	 * @param location the location that contains these location charges. Attribute of location charge
	 * @param startdate startDate the date at which the location charge becomes valid. Attribute of location charge
	 * @return all data of the location charge
	 */
	@Query("SELECT * FROM LocationCharges WHERE location = :location AND startdate = :startdate")
	LocationCharge getLocationChargeByLocationAndStartDate(double location, LocalDate startdate);

	/**
	 * Get the current location charge for a specific location
	 * @param location the location that contains these location charges. Attribute of location charge
	 * @return the current location charge for a specific location
	 */
	@Query("SELECT * FROM LocationCharges WHERe location = :location AND enddate IS NOT NULL")
	LocationCharge getCurrentLocationCharge(double location);

	/**
	 * Insert a collection of location charge model objects into the database
	 * @param charge the location charge model object that should be inserted.
	 */
	@Insert
	void insertLocationCharge(LocationCharge... charge);

	/**
	 * Update a collection of location charge model objects
	 * @param charge the location charge that should be updated. Primary Key cannot be updated.
	 */
	@Update
	void updateLocationCharge(LocationCharge... charge);

}