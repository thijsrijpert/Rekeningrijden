package com.thijsrijpert.rekeningrijden.Controller.ViewData;

import android.app.Activity;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

abstract class DatabaseSyncTask<T> extends AsyncTask<Void, Void, T> {

    //Prevent memory leak
    protected WeakReference<Activity> weakActivity;

    DatabaseSyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    protected boolean activityIsActive(){
        return weakActivity.get() != null;
    }


}
