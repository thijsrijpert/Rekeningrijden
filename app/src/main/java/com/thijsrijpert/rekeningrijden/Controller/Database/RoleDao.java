package com.thijsrijpert.rekeningrijden.Controller.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.thijsrijpert.rekeningrijden.Model.Role;

import java.util.List;
/**
 * The Dao for the role model object contains all queries for the role CRUD system.
 */
@Dao
public interface RoleDao {

	/**
	 * Select all roles from the database
	 * @return all roles from the database
	 */
	@Query("SELECT role FROM roles")
	List<Role> getAllRoles();

	/**
	 * Insert a role into the database
	 * @param role the role model object that should be inserted into the database
	 */
	@Insert
	void insertRole(Role role);

}