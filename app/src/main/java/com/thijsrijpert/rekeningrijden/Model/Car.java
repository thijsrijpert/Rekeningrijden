package com.thijsrijpert.rekeningrijden.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Model object of car.
 */
@Entity(tableName = "Cars")
public class Car {
	@PrimaryKey @NonNull
	private final String numberplate;
	private String brand;
	private String color;
	private String type;
	private User username;

	/**
	 * Constructor of car with just the primary key
	 * @param numberplate the numberplate of the car. Used as a primary key for the car object model
	 */
	@Ignore
	public Car(@NonNull String numberplate) {
		this.numberplate = numberplate;
	}

	/**
	 * Constructor for the car object. Used by the database to create the table
	 * @param numberplate the numberplate of the car. Used as a primary key for the car object model
	 * @param brand the brand of the car. Only used for storage/display purposes. Not used by functional elements.
	 * @param color the color of the car. Only used for storage/display purposes. Not used by functional elements.
	 * @param type teh type of the car. Only used for storage/display purposes. Not used by functional elements.
	 * @param username the user that owns the car. Used to identify who can ride with this car.
	 */
	public Car(@NonNull String numberplate, @NonNull String brand, @NonNull String color, @NonNull String type, @NonNull User username) {
		this.numberplate = numberplate;
		this.brand = brand;
		this.color = color;
		this.type = type;
		this.username = username;
	}

	/**
	 * Get the numberplate of this car
	 * @return the numberplate of the car. Used as a primary key for the car object model
	 */
	public String getNumberplate() {
		return this.numberplate;
	}

	/**
	 * Get the brand of this car
	 * @return the brand of the car. Only used for storage/display purposes. Not used by functional elements.
	 */
	public String getBrand() {
		return this.brand;
	}

	/**
	 * Set the brand of the car
	 * @param brand the brand of the car. Only used for storage/display purposes. Not used by functional elements.
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Get the color of this car
	 * @return the color of the car. Only used for storage/display purposes. Not used by functional elements.
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * Set the color of the car
	 * @param color the brand of the car. Only used for storage/display purposes. Not used by functional elements.
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * Get the type of this car
	 * @return the type of the car. Only used for storage/display purposes. Not used by functional elements.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Set the type of the car
	 * @param type of the car. Only used for storage/display purposes. Not used by functional elements.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Get the owner of the car
	 * @return the user that owns the car. Used to identify who can ride with this car.
	 */
	public User getUsername() {return username; }


	/**
	 * Set the owner of the car
	 * @param username that owns the car. Used to identify who can ride with this car.
	 */
	public void setUsername(User username) { this.username = username;}

	@Override @NonNull
	public String toString() {
		return numberplate;
	}
}