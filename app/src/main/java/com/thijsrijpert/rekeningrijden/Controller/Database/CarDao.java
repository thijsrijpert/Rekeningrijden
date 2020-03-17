package com.thijsrijpert.rekeningrijden.Controller.Database;

import androidx.room.Dao;
import androidx.room.Query;

import com.thijsrijpert.rekeningrijden.Model.Car;

import java.util.List;

/**
 * The Dao for the car model object contains all queries for the car CRUD system.
 */
@Dao
public interface CarDao extends BaseDao<Car>{

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

}