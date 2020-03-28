package com.thijsrijpert.rekeningrijden.controller.viewdata;

import android.app.Activity;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.widget.Toast;

import com.thijsrijpert.rekeningrijden.controller.database.AppDatabase;
import com.thijsrijpert.rekeningrijden.controller.view.activity.ChargeActivity;
import com.thijsrijpert.rekeningrijden.controller.view.fragment.LocationChargeDetailsFragment;
import com.thijsrijpert.rekeningrijden.model.LocationCharge;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

/**
 * Database UI interaction
 */
public class LocationChargeViewData extends SuperViewData {

    /**
     * Create a new ViewData object for locationCharge to interact with the db
     * @param activity the current displayed activity
     */
    public LocationChargeViewData(Activity activity){
        this.activity = activity;
    }

    /**
     * Add a new location charge to the database and data model
     */
    public void newLocationCharge(LocationCharge oldCharge){
        ChargeActivity activity = (ChargeActivity)this.activity;
        //Get the displayed fragment from the UI
        LocationChargeDetailsFragment fragment = (LocationChargeDetailsFragment)activity.getChargePagerAdapter().getLocationListDetailsFragment().getDetailsFragment();
        LocationCharge charge;
        try {
            //Get the data from the UI
            String primary = fragment.getEtPrimary().getText().toString();
            String price = fragment.getEtPrice().getText().toString();

            String[] locations = primary.split(";");
            if(locations.length != 2 || locations[0].length() != 5 || locations[1].length() != 4 || Double.parseDouble(locations[0]) > 54 || Double.parseDouble(locations[0]) < 49 || Double.parseDouble(locations[1]) < 2 || Double.parseDouble(locations[1]) > 8  ){
                throw new NumberFormatException();
            }

            //Get the charge date
            LocalDate date = LocalDate.now();

            //Create a charge data object
            charge = new LocationCharge(Double.parseDouble(String.format(Locale.US, "%s", price)), date, null, primary);
        }catch(NullPointerException e){
            if(fragment.getEtPrimary().getText().toString().equals("")){
                Toast.makeText(activity, "Er is geen locatie ingevoerd", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(activity, "Er is geen prijs ingevoerd", Toast.LENGTH_SHORT).show();
            }
            return;
        }catch (NumberFormatException e){
            Toast.makeText(activity, "De ingevulde locatie of prijs is incorrect", Toast.LENGTH_SHORT).show();
            return;
        }

        //Add the new record to the database
        if(activity.getLocationCharges().getBinaryIndex(charge, 1) == null){
            NewLocationChargeTask newLocationChargeTask = new NewLocationChargeTask(activity, charge, oldCharge);
            newLocationChargeTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
        }

    }

    /**
     * Load all records from the database into the datamodel
     */
    public void loadAllLocationCharges(){
        //Load all LocationCharges from the database
        LoadLocationChargesTask loadLocationChargesTask = new LoadLocationChargesTask((ChargeActivity)activity);
        loadLocationChargesTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /**
     * Update the locationCharge in the database and datamodel to include an enddate
     * @param locationCharge the locationcharge that needs to be changed
     */
    public void endLocationCharge(LocationCharge locationCharge){
        //look up the current enddate and add it to the charge
        locationCharge.setEnddate(LocalDate.now());

        //Change the record in the database
        UpdateLocationChargeTask updateLocationChargeTask = new UpdateLocationChargeTask((ChargeActivity)activity, locationCharge);
        updateLocationChargeTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }

    /**
     * ASync task that creates a new record in the database
     */
    private static class NewLocationChargeTask extends DatabaseSyncTask<Boolean> {

        private LocationCharge locationCharge;
        private LocationCharge oldCharge;
        /**
         * Sets the variables for the async task to be executed
         * @param activity the activity that is currently displayed
         * @param locationCharge the locationCharge that should be inserted
         */
        NewLocationChargeTask(ChargeActivity activity, LocationCharge locationCharge, LocationCharge oldCharge) {
            super(activity);
            this.locationCharge = locationCharge;
            this.oldCharge = oldCharge;
        }

        /**
         * Execute the query
         * @param params not used
         * @return null, the query has no feedback
         */
        @Override
        protected Boolean doInBackground(Void... params) {
            try{
                AppDatabase.getInstance(weakActivity.get()).locationChargeDao().insert(locationCharge);
            }catch(SQLiteConstraintException e){
                return false;
            }

            return true;
        }

        /**
         * Update the model so the listview is up to date
         * @param success check if a constraint exception took place
         */
        @Override
        protected void onPostExecute(Boolean success) {
            //check if the activity is still displayed
            if(activityIsActive() && success) {
                if(oldCharge != null){
                    UpdateLocationChargeTask updateLocationChargeTask = new UpdateLocationChargeTask((ChargeActivity)weakActivity.get(), oldCharge);
                    updateLocationChargeTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
                }else{
                    //Update the datamodel to reflect he changes
                    ((ChargeActivity)weakActivity.get()).getLocationCharges().create(locationCharge);

                    //close the details view and open the listview
                    ((ChargeActivity)weakActivity.get()).getChargePagerAdapter().getLocationListDetailsFragment().showListView();

                    //notify the adapter that the data has been changed, and should be updated in the UI
                    ((ChargeActivity)weakActivity.get()).getChargePagerAdapter().getLocationListDetailsFragment().getListFragment().getAdapter().notifyDataSetChanged();

                    //send feedback to the user to let them no their request was succesfull
                    Toast.makeText(weakActivity.get().getApplicationContext(), "Toeslag opgeslagen.", Toast.LENGTH_SHORT).show();
                }

            }else if(activityIsActive() && !success){
                Toast.makeText(weakActivity.get().getApplicationContext(), "Deze toeslag is vandaag al aangepast", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Load all data from the database into the datamodel
     */
    private static class LoadLocationChargesTask extends DatabaseSyncTask<List<LocationCharge>> {


        /**
         * Sets the variables for the async task to be executed
         * @param activity the activity that is currently displayed
         */
        LoadLocationChargesTask(ChargeActivity activity) {
            super(activity);
        }

        /**
         * Execute the query
         * @param params Not used
         * @return the data that is returned from the query
         */
        @Override
        protected List<LocationCharge> doInBackground(Void... params) {
            return AppDatabase.getInstance(weakActivity.get()).locationChargeDao().getAllLocationCharges();
        }

        /**
         * Clear the old model data and add the new ones
         * @param charges the data that is return from the query
         */
        @Override
        protected void onPostExecute(List<LocationCharge> charges) {
            if(activityIsActive()) {
                //delete the old model data
                ((ChargeActivity)weakActivity.get()).getLocationCharges().getCharges().clear();
                //add the new model data
                ((ChargeActivity)weakActivity.get()).getLocationCharges().getCharges().addAll(charges);
                //notify the adapter that the data has been updated and so should the UI
                ((ChargeActivity)weakActivity.get()).getChargePagerAdapter().getLocationListDetailsFragment().getListFragment().getAdapter().notifyDataSetChanged();
            }
        }
    }

    /**
     * Update the loctioncharge so the old charge has an enddate
     */
    private static class UpdateLocationChargeTask extends DatabaseSyncTask<Void> {

        private LocationCharge locationCharge;

        /**
         * Sets the variables for the async task to be executed
         * @param activity the activity that is currently displayed
         * @param locationCharge the locationCharge that should be inserted
         */
        UpdateLocationChargeTask(ChargeActivity activity, LocationCharge locationCharge) {
            super(activity);
            this.locationCharge = locationCharge;
        }

        /**
         * Execute the query
         * @param params Not used
         * @return null, the query has no feedback
         */
        @Override
        protected Void doInBackground(Void... params) {
            AppDatabase.getInstance(weakActivity.get()).locationChargeDao().update(locationCharge);
            return null;
        }

        /**
         * Update the datamodel and return to the listview
         * @param Void null, the query has no feedback
         */
        @Override
        protected void onPostExecute(Void Void) {
            //Check if the activity is still displayed
            if(activityIsActive()) {
                try{
                    //Update the datamodel to reflect he changes
                    ((ChargeActivity)weakActivity.get()).getLocationCharges().update(locationCharge);
                    //Notify the adapter that the dataset has been updated
                    ((ChargeActivity)weakActivity.get()).getChargePagerAdapter().getLocationListDetailsFragment().getListFragment().getAdapter().notifyDataSetChanged();
                }catch(NullPointerException e){
                    //the datamodel is corrupt, so it should be reloaded
                    LoadLocationChargesTask loadLocationChargesTask = new LoadLocationChargesTask((ChargeActivity)weakActivity.get());
                    loadLocationChargesTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
                }catch(Exception e){
                    Toast.makeText(weakActivity.get(), "Er is een probleem opgetreden, probeer het later nog eens", Toast.LENGTH_LONG).show();
                    return;
                }

                //Close the details view and return to the listview
                ((ChargeActivity)weakActivity.get()).getChargePagerAdapter().getLocationListDetailsFragment().showListView();

                //Display feedback to the user
                Toast.makeText(weakActivity.get().getApplicationContext(), "Toeslag Verwijderd.", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
