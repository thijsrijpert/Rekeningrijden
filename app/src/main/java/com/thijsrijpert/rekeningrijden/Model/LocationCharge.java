package com.thijsrijpert.rekeningrijden.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;

import java.time.LocalDate;

/**
 * Model object that contains the info about a price paid for a specific location
 */
@Entity(primaryKeys = {"location", "startdate"}, tableName = "LocationCharges")
public class LocationCharge extends DefaultCharge {
	private final double location;


	/**
	 * Constructor for the time charge model object. Used by the database to create the database.
	 * @param price the price of the charge. Used to calculate the total price a user pays
	 * @param startdate the date this charge becomes valid. Used to calculate the total price a user has to pay.
	 * @param enddate the date this charge is no longer valid.  Used to calculate the total price a user has to pay.
	 * @param location the location this charge applies to. Used to calculate the total price a user has to pay.
	 */
	public LocationCharge(double price, @NonNull LocalDate startdate, @Nullable LocalDate enddate, double location) {
		super(price, startdate, enddate);
		this.location = location;
	}

	/**
	 * Get the charge location
	 * @return the location this charge applies to. Used to calculate the total price a user has to pay.
	 */
	public double getLocation() {
		return this.location;
	}

}