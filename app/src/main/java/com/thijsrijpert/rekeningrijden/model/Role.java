package com.thijsrijpert.rekeningrijden.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "Roles")
public class Role {
	@PrimaryKey @NonNull
	private final String role;

	/**
	 * Constructor for the role model object
	 * @param role the name of the role
	 */
	public Role(@NonNull String role) {
		this.role = role;
	}

	/**
	 * Get the name of the role
	 * @return the name of the role
	 */
	public String getRole() {
		return this.role;
	}

}