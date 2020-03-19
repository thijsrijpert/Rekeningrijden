package com.thijsrijpert.rekeningrijden.Controller.ViewData;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.thijsrijpert.rekeningrijden.Controller.Database.AppDatabase;
import com.thijsrijpert.rekeningrijden.Controller.PreferencesManager;
import com.thijsrijpert.rekeningrijden.Controller.ViewControllers.ActivityController.RideOverviewActivity;
import com.thijsrijpert.rekeningrijden.Controller.ViewControllers.ActivityController.RideRegistrationActivity;
import com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController.RideOverviewAdapter;
import com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController.RideYearTotalAdapter;
import com.thijsrijpert.rekeningrijden.Controller.exceptions.MissingPermissonException;
import com.thijsrijpert.rekeningrijden.Model.Car;
import com.thijsrijpert.rekeningrijden.Model.Ride;
import com.thijsrijpert.rekeningrijden.Model.User;
import com.thijsrijpert.rekeningrijden.R;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RideViewData extends SuperViewData{

    private Location location;

    public void registration(RideRegistrationActivity activity){
        super.activity = activity;
        Button button = activity.findViewById(R.id.btnRideRegistration);
        if(button.getText().equals("Starten")){
            startRegistration();
        }else{
            stopRegistration();
        }
    }

    private void startRegistration() {
        try {
            //check if the location is already found if not search for it
            //method will be called again when GPS coordinate is found
            if(location == null){
                findLocation();
                return;
            }

            //Get the starttime
            LocalTime starttime = getCurrentTime();

            //Get the car
            Spinner spinner = activity.findViewById(R.id.spRideCar);
            Car car = (Car) spinner.getSelectedItem();

            //Get the ride date
            LocalDate date = LocalDate.now();

            Ride ride = new Ride(car, getCoordinatesString(location), starttime, date);

            //Insert it to the database
            RideRegistrationTask rideRegistrationTask = new RideRegistrationTask((RideRegistrationActivity)activity, ride);
            rideRegistrationTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            //clear the session for the next insert
            location = null;
        } catch (MissingPermissonException e) {
           requeustGPSPermissions();
        } catch (Exception e) {
            Toast.makeText(activity.getApplicationContext(), "Er is een probleem opgetreden. Probeer het later nog een keer", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void stopRegistration(){
        try{
            if(location == null){
                findLocation();
                return;
            }

            LocalTime stoptime = getCurrentTime();

            Ride ride = PreferencesManager.getInstance(activity).getRidePref();

            ride.setStoplocation(getCoordinatesString(location));
            ride.setStoptime(stoptime);

            RideUpdateTask rideUpdateTask = new RideUpdateTask((RideRegistrationActivity)activity, ride);
            rideUpdateTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } catch(MissingPermissonException e){
            requeustGPSPermissions();
        } catch (Exception e) {
            Toast.makeText(activity.getApplicationContext(), "Er is een probleem opgetreden. Probeer het later nog een keer", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void loadAllRides(RideOverviewActivity activity){
        this.activity = activity;

        User user = PreferencesManager.getInstance(activity).getUserPref();

        LoadAllRidesTask loadAllRidesTask = new LoadAllRidesTask(activity, user);
        loadAllRidesTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void searchRides(View view){

    }

    private void requeustGPSPermissions(){
        activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1 );
    }

    private void displayGPSErrorMessage(){
        Toast.makeText(activity.getApplicationContext(), "Kon de GPS gegevens niet ophalen. Is de GPS ingeschakeld?", Toast.LENGTH_LONG).show();
    }

    private void findLocation() throws MissingPermissonException {
        if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            throw new MissingPermissonException();
        }
        FusedLocationProviderClient locationClient = LocationServices.getFusedLocationProviderClient(activity);
        locationClient.getLastLocation()
                .addOnSuccessListener((location) -> {
                    this.location = location;
                    this.registration((RideRegistrationActivity)activity);
                })
                .addOnFailureListener(e -> {
                    displayGPSErrorMessage();
                });
    }

    private LocalTime getCurrentTime(){
        Calendar timeNow = Calendar.getInstance();
        Calendar timeMidnight = Calendar.getInstance();

        System.out.println(timeMidnight.getTimeInMillis() / 1000);

        timeMidnight.set(Calendar.HOUR_OF_DAY, 0);
        timeMidnight.set(Calendar.MINUTE, 0);
        timeMidnight.set(Calendar.SECOND, 0);
        timeMidnight.set(Calendar.MILLISECOND, 0);

        System.out.println(timeMidnight.getTimeInMillis() / 1000);

        System.out.println(timeNow.getTimeInMillis() / 1000 - timeMidnight.getTimeInMillis() / 1000);

        return LocalTime.ofSecondOfDay(timeNow.getTimeInMillis() / 1000 - timeMidnight.getTimeInMillis() / 1000);
    }

    private String getCoordinatesString(Location location){
        return String.format(Locale.US, "%.2f;%.2f", location.getLatitude(), location.getLongitude());
    }

    private static class RideRegistrationTask extends DatabaseSyncTask<Void> {

        private Ride ride;

        RideRegistrationTask(RideRegistrationActivity activity, Ride ride) {
            super(activity);
            this.ride = ride;
        }

        @Override
        protected Void doInBackground(Void... params) {
            AppDatabase.getInstance(weakActivity.get()).rideDao().insert(ride);
            return null;
        }

        @Override
        protected void onPostExecute(Void empty) {
            if(weakActivity.get() == null) {
                return;
            }

            PreferencesManager.getInstance(weakActivity.get()).storeObjectInPref("Ride", ride);


            Toast.makeText(weakActivity.get().getApplicationContext(), "Rit is gestart.", Toast.LENGTH_SHORT).show();

            Button button = weakActivity.get().findViewById(R.id.btnRideRegistration);
            button.setText("Stoppen");
        }
    }

    private static class RideUpdateTask extends DatabaseSyncTask<Void> {

        private Ride ride;

        RideUpdateTask(RideRegistrationActivity activity, Ride ride) {
            super(activity);
            this.ride = ride;
        }

        @Override
        protected Void doInBackground(Void... params) {
            AppDatabase.getInstance(weakActivity.get()).rideDao().update(ride);
            return null;
        }

        @Override
        protected void onPostExecute(Void empty) {
            if(weakActivity.get() == null) {
                return;
            }

            PreferencesManager.getInstance(weakActivity.get()).removePref("Ride");


            Toast.makeText(weakActivity.get().getApplicationContext(), "Rit is gestopt.", Toast.LENGTH_SHORT).show();

            Button button = weakActivity.get().findViewById(R.id.btnRideRegistration);
            button.setText("Starten");
        }
    }

    private static class LoadAllRidesTask extends DatabaseSyncTask<List<Ride>> {

        private User user;

        LoadAllRidesTask(RideOverviewActivity activity, User user) {
            super(activity);
            this.user = user;
        }

        @Override
        protected List<Ride> doInBackground(Void... params) {
            return AppDatabase.getInstance(weakActivity.get()).rideDao().getAllRidesByUser(user.getUsername());
        }

        @Override
        protected void onPostExecute(List<Ride> rides) {
            if(weakActivity.get() == null) {
                return;
            }

            ((RideOverviewAdapter)((RideOverviewActivity)weakActivity.get()).getRidePagerAdapter()
                    .getListDetailsFragment().getListFragment().getAdapter()).setData(rides);
            ((RideOverviewActivity)weakActivity.get()).getRidePagerAdapter()
                    .getRideYearTotalFragment().getRecyclerView().setAdapter(new RideYearTotalAdapter(rides));
        }
    }
}
