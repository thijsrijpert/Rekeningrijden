package com.thijsrijpert.rekeningrijden.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Model object that contains the info about a price paid for a specific location
 */
@Entity(primaryKeys = {"location", "startdate"}, tableName = "LocationCharges")
public class LocationCharge extends DefaultCharge implements Serializable {
	@NonNull
	private String location;

	/**
	 * Constructor for the time charge model object. Used by the database to create the database.
	 * @param price the price of the charge. Used to calculate the total price a user pays
	 * @param startdate the date this charge becomes valid. Used to calculate the total price a user has to pay.
	 * @param enddate the date this charge is no longer valid.  Used to calculate the total price a user has to pay.
	 * @param location the location this charge applies to. Used to calculate the total price a user has to pay.
	 */
	public LocationCharge(@NonNull Double price, @NonNull LocalDate startdate, @Nullable LocalDate enddate, @NonNull String location) {
		super(price, startdate, enddate);
		this.location = location;
	}

	/**
	 * Get the charge location
	 * @return the location this charge applies to. Used to calculate the total price a user has to pay.
	 */
	public String getLocation() {
		return this.location;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LocationCharge that = (LocationCharge) o;
		return location.equals(that.location) && startdate.equals(that.startdate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(location, startdate);
	}
}