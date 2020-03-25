package com.thijsrijpert.rekeningrijden.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The model object that contains the details of a default charge. Also extended by other charges
 */
@Entity(tableName = "DefaultCharges")
public class DefaultCharge implements Serializable {
	@NonNull
	protected Double price;
	@PrimaryKey @NonNull
	protected LocalDate startdate;
	@Nullable
	protected LocalDate enddate;

	/**
	 * Constructor for the time charge model object. Used by the database to create the database.
	 * @param price the price of the charge. Used to calculate the total price a user pays
	 * @param startdate the date this charge becomes valid. Used to calculate the total price a user has to pay.
	 * @param enddate the date this charge is no longer valid.  Used to calculate the total price a user has to pay.
	 */
	public DefaultCharge(@NonNull Double price, @NonNull LocalDate startdate, @Nullable LocalDate enddate) {
		this.price = price;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	/**
	 * Get the price that is paid for this charge
	 * @return the price of the charge. Used to calculate the total price a user pays
	 */
	public Double getPrice() {
		return this.price;
	}

	/**
	 * Set a new price for this charge
	 * @param price the price of the charge. Used to calculate the total price a user pays
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Get the startdate of this charge
	 * @return the date this charge becomes valid. Used to calculate the total price a user has to pay.
	 */
	public LocalDate getStartdate() {
		return this.startdate;
	}

	/**
	 * Set a new startdate for this charge
	 * @param startdate the date this charge becomes valid. Used to calculate the total price a user has to pay.
	 */
	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	/**
	 * Get the enddate of this charge
	 * @return the date this charge is no longer valid.  Used to calculate the total price a user has to pay.
	 */
	public LocalDate getEnddate() {
		return this.enddate;
	}

	/**
	 * Set a new enddate for this charge
	 * @param enddate the date this charge is no longer valid.  Used to calculate the total price a user has to pay.
	 */
	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

}