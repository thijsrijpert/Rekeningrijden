package com.thijsrijpert.rekeningrijden.controller.viewdata;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.thijsrijpert.rekeningrijden.R;
import com.thijsrijpert.rekeningrijden.controller.DistanceCalculator;
import com.thijsrijpert.rekeningrijden.controller.PreferencesManager;
import com.thijsrijpert.rekeningrijden.controller.database.AppDatabase;
import com.thijsrijpert.rekeningrijden.controller.exceptions.MissingPermissonException;
import com.thijsrijpert.rekeningrijden.controller.view.activity.RideOverviewActivity;
import com.thijsrijpert.rekeningrijden.controller.view.activity.RideRegistrationActivity;
import com.thijsrijpert.rekeningrijden.controller.view.fragment.RideOverviewAdapter;
import com.thijsrijpert.rekeningrijden.controller.view.fragment.RideOverviewDetailsFragment;
import com.thijsrijpert.rekeningrijden.controller.view.fragment.RideYearTotalAdapter;
import com.thijsrijpert.rekeningrijden.model.Car;
import com.thijsrijpert.rekeningrijden.model.Ride;
import com.thijsrijpert.rekeningrijden.model.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

public class RideViewData extends SuperViewData{

    private Location location;
    private DistanceCalculator distanceCalculator;

    public RideViewData(Activity activity){
        this.activity = activity;
        this.distanceCalculator = new DistanceCalculator(activity);
    }

    public void registration(){
        Button button = ((RideRegistrationActivity)activity).getBtnRideRegistration();
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
            LocalTime starttime = LocalTime.now();

            //Get the car
            Spinner spinner = ((RideRegistrationActivity)activity).getSpRideCar();
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

            LocalTime stoptime = LocalTime.now();

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

    public void loadAllRides(){
        User user = PreferencesManager.getInstance(activity).getUserPref();

        LoadAllRidesTask loadAllRidesTask = new LoadAllRidesTask((RideOverviewActivity)activity, user);
        loadAllRidesTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
                    this.registration();
                })
                .addOnFailureListener(e -> displayGPSErrorMessage());

    }

    public void loadDistance(Ride ride){
        RideOverviewDetailsFragment fragment = (RideOverviewDetailsFragment) ((RideOverviewActivity) activity)
                .getRidePagerAdapter().getListDetailsFragment().getDetailsFragment();

        if(fragment.getDistance() == -1){
            distanceCalculator.calculate(ride, this);
        }else if(fragment.getDistance() == -2){
            ((RideOverviewDetailsFragment)((RideOverviewActivity)activity)
                    .getRidePagerAdapter().getListDetailsFragment().getDetailsFragment())
                    .getTveDistance().setText("n.v.t");
        }else{
            ((RideOverviewDetailsFragment)((RideOverviewActivity)activity)
                    .getRidePagerAdapter().getListDetailsFragment().getDetailsFragment())
                    .getTveDistance().setText(String.format(Locale.US, "%d", fragment.getDistance() / 1000));
        }

        calculateCosts();
    }

    public void loadCharge(Ride ride){
        LoadChargesTask loadChargesTask = new LoadChargesTask((RideOverviewActivity)activity, ride);
        loadChargesTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void calculateCosts(){
        RideOverviewDetailsFragment fragment = (RideOverviewDetailsFragment) ((RideOverviewActivity) activity)
                .getRidePagerAdapter().getListDetailsFragment().getDetailsFragment();
        TextView costs = fragment.getTveCosts();
        if(fragment.getDistance() > 0) {
            TextView locationCharge = fragment.getTveLocationCharge();
            TextView timeCharge = fragment.getTveLocationCharge();
            Double defaultCharge = fragment.getDefaultCharge();
            Integer distance = fragment.getDistance();

            if (locationCharge.length() != 0 && timeCharge.length() != 0 && costs.length() == 0) {
                double location = Double.parseDouble(locationCharge.getText().toString());
                double time = Double.parseDouble(timeCharge.getText().toString());
                Double charge = (distance / 1000 * defaultCharge) + (distance / 1000 * time) + (distance / 1000 * location);
                System.out.println(charge.toString());
                costs.setText(String.format(Locale.US, "%.2f", charge));
            }
        }else if (fragment.getDistance() == -2){
            costs.setText("n.v.t");
        }
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

            Button button = ((RideRegistrationActivity)weakActivity.get()).getBtnRideRegistration();
            button.setText(R.string.btnDriverStop);
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
            if(activityIsActive()) {
                PreferencesManager.getInstance(weakActivity.get()).removePref("Ride");

                Toast.makeText(weakActivity.get().getApplicationContext(), "Rit is gestopt.", Toast.LENGTH_SHORT).show();

                Button button = ((RideRegistrationActivity)weakActivity.get()).getBtnRideRegistration();
                button.setText(R.string.btnDriverStart);
            }
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

    private static class LoadChargesTask extends DatabaseSyncTask<Double[]> {

        private Ride ride;

        LoadChargesTask(RideOverviewActivity activity, Ride ride) {
            super(activity);
            this.ride = ride;
        }

        @Override
        protected Double[] doInBackground(Void... params) {
            Double[] charges = new Double[3];
            charges[0] = AppDatabase.getInstance(weakActivity.get()).rideDao().getActiveTimeCharge(ride.getDate(), ride.getStarttime(), ride.getNumberplate());
            charges[1] = AppDatabase.getInstance(weakActivity.get()).rideDao().getActiveLocationCharge(ride.getDate(), ride.getStarttime(), ride.getNumberplate());
            charges[2] = AppDatabase.getInstance(weakActivity.get()).rideDao().getActiveDefaultCharge(ride.getDate(), ride.getStarttime(), ride.getNumberplate());
            return charges;
        }

        @Override
        protected void onPostExecute(Double[] charges) {
            if(activityIsActive()) {
                ((RideOverviewDetailsFragment)((RideOverviewActivity)weakActivity.get())
                        .getRidePagerAdapter().getListDetailsFragment().getDetailsFragment())
                        .getTveTimeCharge().setText(String.format(Locale.US, "%.2f", charges[0]));
                ((RideOverviewDetailsFragment)((RideOverviewActivity)weakActivity.get())
                        .getRidePagerAdapter().getListDetailsFragment().getDetailsFragment())
                        .getTveLocationCharge().setText(String.format(Locale.US, "%.2f", charges[1]));
                ((RideOverviewDetailsFragment) ((RideOverviewActivity) weakActivity.get()).getRidePagerAdapter()
                        .getListDetailsFragment().getDetailsFragment()).setDefaultCharge(charges[2]);
                ((RideOverviewDetailsFragment) ((RideOverviewActivity) weakActivity.get()).getRidePagerAdapter()
                        .getListDetailsFragment().getDetailsFragment()).getRideViewData().calculateCosts();

            }


        }
    }
}
