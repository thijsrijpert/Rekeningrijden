package com.thijsrijpert.rekeningrijden.Controller.Database;

import com.thijsrijpert.rekeningrijden.Model.Role;

import java.util.ArrayList;

public abstract class RoleDao {

	public abstract ArrayList<Role> getAllRoles();

	/**
	 *
	 * @param role
	 */
	public abstract void insertRole(Role role);

}