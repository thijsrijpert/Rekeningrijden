package com.thijsrijpert.rekeningrijden.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * An model object that contains the the price of a specific time charge
 */
@Entity(primaryKeys = {"time", "startDate"}, tableName = "TimeCharges")
public class TimeCharge extends DefaultCharge {
	@NonNull
	private final LocalTime time;

	/**
	 * Constructor for the time charge model object. Used by the database to create the database.
	 * @param price the price of the charge. Used to calculate the total price a user pays
	 * @param startdate the date this charge becomes valid. Used to calculate the total price a user has to pay.
	 * @param enddate the date this charge is no longer valid.  Used to calculate the total price a user has to pay.
	 * @param time the time this charge applies to. Used to calculate the total price a user has to pay.
	 */
	public TimeCharge(@NonNull Double price, @NonNull LocalDate startdate, @Nullable LocalDate enddate, @NonNull LocalTime time) {
		super(price, startdate, enddate);
		this.time = time;
	}

	/**
	 * Get the charge time
	 * @return the time this charge applies to. Used to calculate the total price a user has to pay.
	 */
	public LocalTime getTime() {
		return this.time;
	}

}