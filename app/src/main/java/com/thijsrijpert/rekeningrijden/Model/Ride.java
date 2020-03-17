package com.thijsrijpert.rekeningrijden.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Contains the model object for a ride.
 */
@Entity(primaryKeys = {"numberplate", "starttime", "date"}, tableName = "Rides")
public class Ride {
	@NonNull
	private final Car numberplate;
	@NonNull
	private final String startlocation;
	@Nullable
	private String stoplocation;
	@NonNull
	private final LocalTime starttime;
	@Nullable
	private LocalTime stoptime;
	@NonNull
	private final LocalDate date;

	/**
	 * Constructor for the ride model object. Used by the database to create the tables
	 * @param numberplate instance of car. Contains all info about the car that made the ride
	 * @param startlocation startlocation of the ride. Contains the coordinates at which the ride started
	 * @param starttime starttime of the ride. Contains the time at which the ride started
	 * @param date date of the ride. Contains the date the ride took place
	 */
	@Ignore
	public Ride(@NonNull Car numberplate, @NonNull String startlocation, @NonNull LocalTime starttime, @NonNull LocalDate date) {
		this.numberplate = numberplate;
		this.startlocation = startlocation;
		this.starttime = starttime;
		this.date = date;
	}

	/**
	 * Constructor for the ride model object. Used by the database to create the tables
	 * @param numberplate instance of car. Contains all info about the car that made the ride
	 * @param startlocation startlocation of the ride. Contains the coordinates at which the ride started
	 * @param stoplocation stoplocation of the ride. Contains the coordinates at which the ride stopped
	 * @param starttime starttime of the ride. Contains the time at which the ride started
	 * @param stoptime stoptime of the ride. Contains the time the ride stopped
	 * @param date date of the ride. Contains the date the ride took place
	 */
	public Ride(@NonNull Car numberplate, @NonNull String startlocation, @Nullable  String stoplocation, @NonNull LocalTime starttime, @Nullable LocalTime stoptime, @NonNull LocalDate date) {
		this.numberplate = numberplate;
		this.startlocation = startlocation;
		this.stoplocation = stoplocation;
		this.starttime = starttime;
		this.stoptime = stoptime;
		this.date = date;
	}

	/**
	 * Get the car of the ride
	 * @return instance of car. Contains all info about the car that made the ride
	 */
	public Car getNumberplate() { return this.numberplate;}

	/**
	 * Get the locations of the start of the ride
	 * @return startlocation of the ride. Contains the coordinates at which the ride started
	 */
	public String getStartlocation() {
		return this.startlocation;
	}

	/**
	 * Get the stoplocation of the ride
	 * @return stoplocation of the ride. Contains the coordinates at which the ride stopped
	 */
	public String getStoplocation() {
		return this.stoplocation;
	}

	/**
	 * Set a new stoplocation for the ride
	 * @param stoplocation stoplocation of the ride. Contains the coordinates at which the ride stopped
	 */
	public void setStoplocation(String stoplocation) {
		this.stoplocation = stoplocation;
	}

	/**
	 * Get the starttime of the ride
	 * @return starttime of the ride. Contains the time at which the ride started
	 */
	public LocalTime getStarttime() {
		return starttime;
	}

	/**
	 * Get the stoptime of the ride
	 * @return stoptime of the ride. Contains the time the ride stopped
	 */
	public LocalTime getStoptime() {
		return stoptime;
	}

	/**
	 * Set a new stoptime for this ride
	 * @param stoptime stoptime of the ride. Contains the time the ride stopped
	 */
	public void setStoptime(LocalTime stoptime) {
		this.stoptime = stoptime;
	}

	/**
	 * Get the date of this ride
	 * @return date of the ride. Contains the date the ride took place
	 */
	public LocalDate getDate() {
		return this.date;
	}

}