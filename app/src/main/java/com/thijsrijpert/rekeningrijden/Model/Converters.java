package com.thijsrijpert.rekeningrijden.Model;

import androidx.room.TypeConverter;

import java.time.LocalTime;
import java.time.LocalDate;

public class Converters {

    @TypeConverter
    public static Role stringToRole(String role) {
        return new Role(role);
    }

    @TypeConverter
    public static String roleToString(Role role) {
        return role.getRole();
    }

    @TypeConverter
    public static LocalTime longToTime(long time) {
        return LocalTime.ofSecondOfDay(time);
    }

    @TypeConverter
    public static int timeToLong(LocalTime time) {
        return time.toSecondOfDay();
    }

    @TypeConverter
    public static LocalDate longToDate(long date){ return LocalDate.ofEpochDay(date);}

    @TypeConverter
    public static long dateToLong(LocalDate date){ return date.toEpochDay();}

    @TypeConverter
    public static User usernameToUser(String username){ return new User(username);}

    @TypeConverter
    public static String userToUsername(User user){ return user.getUsername();}

    @TypeConverter
    public static Car numberplateToCar(String numberplate){ return new Car(numberplate);}

    @TypeConverter
    public static String carToNumberplate(Car car){ return car.getNumberplate();}
}
