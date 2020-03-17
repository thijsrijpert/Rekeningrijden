package com.thijsrijpert.rekeningrijden.Controller;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.thijsrijpert.rekeningrijden.Model.Ride;
import com.thijsrijpert.rekeningrijden.Model.User;

import static android.content.Context.MODE_PRIVATE;

public class PreferencesManager {

    private SharedPreferences sharedPreferences;
    private Gson gson;

    private static PreferencesManager preferenceManager;

    private PreferencesManager(Activity activity){
        this.sharedPreferences = activity.getSharedPreferences("Pref", MODE_PRIVATE);
        this.gson = new Gson();
    }

    public static PreferencesManager getInstance(Activity activity){
        if(preferenceManager == null){
            preferenceManager = new PreferencesManager(activity);
        }
        return preferenceManager;
    }

    public User getUserPref(){
        String json = sharedPreferences.getString("User", null);
        return gson.fromJson(json, User.class);
    }

    public Ride getRidePref(){
        String json = sharedPreferences.getString("Ride", null);
        return gson.fromJson(json, Ride.class);
    }

    public  void removePref(String key){
        sharedPreferences.edit().remove(key).apply();
    }

    public boolean userPrefExists(){
        String user = sharedPreferences.getString("User",null);
        return user != null;
    }

    public boolean ridePrefExists(){
        String ride = sharedPreferences.getString("Ride",null);
        return ride != null;
    }

    public void storeObjectInPref(String key, Object value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(value);
        editor.putString(key, json);
        editor.apply();
    }
}
