package com.thijsrijpert.rekeningrijden.Controller.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thijsrijpert.rekeningrijden.Model.Car;

import java.util.List;

/**
 * The Dao for the car model object contains all queries for the car CRUD system.
 */
@Dao
public interface CarDao {

	/**
	 * Selects all cars owned by the logged in user from the database
	 * @param username the username of the logged in user. Attribute of the User model object
	 * @return the cars that are owned by this user
	 */
	@Query("SELECT * FROM Cars WHERE username = :username")
	List<Car> getAllCarsByUser(String username);

	/**
	 * Get all data of a specific car. Numberplate is an attribute of the Car model object
	 * @param numberplate the numberplate of the car that is being looked up
	 * @return the car that is selected by the query
	 */
	@Query("SELECT * FROM Cars WHERE numberplate = :numberplate")
	Car getAllCarsByNumberplate(String numberplate);
	/**
	 * Insert a collection of car model objects into the database.
	 * @param car the car model objects that should be inserted into the databse
	 */
	@Insert
	void insertCar(Car... car);

	/**
	 * Update a collection of car model objects based on the primary key. The primary key cannot be updated
	 * @param car the car model objects that should be updated.
	 */
	@Update
	void updateCar(Car... car);

}