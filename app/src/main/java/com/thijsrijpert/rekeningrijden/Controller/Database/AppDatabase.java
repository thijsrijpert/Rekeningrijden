package com.thijsrijpert.rekeningrijden.Controller.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.thijsrijpert.rekeningrijden.Model.Car;
import com.thijsrijpert.rekeningrijden.Model.Converters;
import com.thijsrijpert.rekeningrijden.Model.DefaultCharge;
import com.thijsrijpert.rekeningrijden.Model.LocationCharge;
import com.thijsrijpert.rekeningrijden.Model.Ride;
import com.thijsrijpert.rekeningrijden.Model.Role;
import com.thijsrijpert.rekeningrijden.Model.User;

@Database(entities = {User.class, Car.class, DefaultCharge.class, LocationCharge.class, Ride.class, Role.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            instance =
                    Room.databaseBuilder(context,
                    AppDatabase.class, "Database").build();
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
    public abstract RideDao rideDao();
    public abstract RoleDao roleDao();
}
