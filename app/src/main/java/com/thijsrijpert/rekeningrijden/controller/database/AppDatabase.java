package com.thijsrijpert.rekeningrijden.controller.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.thijsrijpert.rekeningrijden.model.Car;
import com.thijsrijpert.rekeningrijden.model.Converters;
import com.thijsrijpert.rekeningrijden.model.DefaultCharge;
import com.thijsrijpert.rekeningrijden.model.LocationCharge;
import com.thijsrijpert.rekeningrijden.model.Ride;
import com.thijsrijpert.rekeningrijden.model.Role;
import com.thijsrijpert.rekeningrijden.model.TimeCharge;
import com.thijsrijpert.rekeningrijden.model.User;

@Database(entities = {User.class, Car.class, DefaultCharge.class, LocationCharge.class, Ride.class, Role.class, TimeCharge.class}, version = 4, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            instance =
                    Room.databaseBuilder(context,
                    AppDatabase.class, "Database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public static void destroyInstance(){
        instance = null;
    }


    public abstract UserDao userDao();
    public abstract CarDao carDao();
    public abstract DefaultChargeDao defaultChargeDao();
    public abstract LocationChargeDao locationChargeDao();
    public abstract TimeChargeDao timeChargeDao();
    public abstract RideDao rideDao();
    public abstract RoleDao roleDao();
}
