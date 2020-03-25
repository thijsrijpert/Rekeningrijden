package com.thijsrijpert.rekeningrijden.model;

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
    public static LocalTime intToTime(Integer time) {
        if(time != null){
            return LocalTime.ofSecondOfDay(time);
        }
        return null;
    }

    @TypeConverter
    public static Integer timeToInt(LocalTime time) {
        if(time != null){
            return time.toSecondOfDay();
        }
        return null;
    }

    @TypeConverter
    public static LocalDate longToDate(Long date){
        if(date != null){
            return LocalDate.ofEpochDay(date);
        }
        return null;
    }

    @TypeConverter
    public static Long dateToLong(LocalDate date){
        if(date != null){
            return date.toEpochDay();
        }
        return null;
    }

    @TypeConverter
    public static User usernameToUser(String username){ return new User(username);}

    @TypeConverter
    public static String userToUsername(User user){ return user.getUsername();}

    @TypeConverter
    public static Car numberplateToCar(String numberplate){ return new Car(numberplate);}

    @TypeConverter
    public static String carToNumberplate(Car car){ return car.getNumberplate();}
}
