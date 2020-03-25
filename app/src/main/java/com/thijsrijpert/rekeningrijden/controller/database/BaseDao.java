package com.thijsrijpert.rekeningrijden.controller.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface BaseDao<T> {

    /**
     * Insert a collection of model objects into the database.
     * @param object the model objects that should be inserted into the database
     */
    @Insert
    void insert(T object);

    /**
     * Update a collection of model objects based on the primary key. The primary key cannot be updated
     * @param object the model objects that should be updated.
     */
    @Update
    void update(T object);
}
