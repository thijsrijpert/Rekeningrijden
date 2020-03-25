package com.thijsrijpert.rekeningrijden.controller.viewdata;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.thijsrijpert.rekeningrijden.controller.database.AppDatabase;
import com.thijsrijpert.rekeningrijden.controller.view.activity.ChargeActivity;
import com.thijsrijpert.rekeningrijden.controller.view.fragment.TimeChargeDetailsFragment;
import com.thijsrijpert.rekeningrijden.model.TimeCharge;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

/**
 * Database UI interaction
 */
public class TimeChargeViewData {

    /**
     * Create a new time charge database and model object
     * @param activity the activity that is currently displayed
     */
    public void newTimeCharge(ChargeActivity activity){
        //get the current fragment
        TimeChargeDetailsFragment fragment = (TimeChargeDetailsFragment)activity.getChargePagerAdapter().getTimeListDetailsFragment().getDetailsFragment();
        TimeCharge charge;
        try{
            String primary = fragment.getEtPrimary().getText().toString();
            String price = fragment.getEtPrimary().getText().toString();
            //Get the charge date
            LocalDate date = LocalDate.now();
            charge = new TimeCharge(Double.parseDouble(String.format(Locale.US, "%s", price)), date, null, LocalTime.ofSecondOfDay(Long.parseLong(primary)));
        }catch(NullPointerException | NumberFormatException e){
            if(fragment.getEtPrimary().getText().toString().equals("")){
                Toast.makeText(activity, "Er is geen tijd ingevoerd", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(activity, "Er is geen prijs ingevoerd", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        //If the data is new and it is not a update check if the key is already present in the datamodel to prevent a UNIQUE constraint
        if(fragment.getBtnDelete().getVisibility() == View.INVISIBLE && activity.getTimeCharges().hasPrimary(charge) != null ){

            //Get the index of the charge that needs to be updated
            int index = activity.getTimeCharges().getBinaryIndex(charge, 0);

            //Get the charge from the datamodel
            TimeCharge oldCharge = activity.getTimeCharges().getCharges().get(index);

            //Query the database to update the charge
            endTimeCharge(activity, oldCharge);
        }

        //Insert the data into the database
        if(activity.getTimeCharges().getBinaryIndex(charge, 1) == null){
            NewTimeChargeTask timeChargeTask = new NewTimeChargeTask(activity, charge);
            timeChargeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    /**
     * Load all data from the database into the object model
     * @param activity the activity that is currently displayed
     */
    public void loadAllTimeCharges(ChargeActivity activity){
        LoadTimeChargesTask loadTimeChargesTask = new LoadTimeChargesTask(activity);
        loadTimeChargesTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /**
     * Add the end date to the current active charge, and load the changes into the model object
     * @param activity the activity that is currently displayed
     * @param timeCharge the old time charge that should be updated
     */
    public void endTimeCharge(ChargeActivity activity, TimeCharge timeCharge){
        timeCharge.setEnddate(LocalDate.now());
        UpdateTimeChargeTask updateTimeChargeTask = new UpdateTimeChargeTask(activity, timeCharge);
        updateTimeChargeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /**
     * Query the database to insert a new record and update the model object
     */
    private static class NewTimeChargeTask extends DatabaseSyncTask<Void> {

        private TimeCharge timeCharge;

        /**
         * Set up the nessacary data to compelete the query
         * @param activity the activity that is currently displayed
         * @param timeCharge the timecharge that should be updated
         */
        NewTimeChargeTask(ChargeActivity activity, TimeCharge timeCharge) {
            super(activity);
            this.timeCharge = timeCharge;
        }

        /**
         * Execute the query
         * @param params Not used
         * @return null, the query doesn't provide feedback
         */
        @Override
        protected Void doInBackground(Void... params) {
            AppDatabase.getInstance(weakActivity.get()).timeChargeDao().insert(timeCharge);
            return null;
        }

        @Override
        protected void onPostExecute(Void empty) {
            //Check if the activity is still displayed
            if(activityIsActive()) {
                //display the listview instead of the detail view
                ((ChargeActivity)weakActivity.get()).getChargePagerAdapter().getTimeListDetailsFragment().showListView();

                //add the added timecharge to the datamodel
                ((ChargeActivity)weakActivity.get()).getTimeCharges().create(timeCharge);

                //notify the adapter of the update
                ((ChargeActivity)weakActivity.get()).getChargePagerAdapter().getTimeListDetailsFragment().getListFragment().getAdapter().notifyDataSetChanged();

                //Display feedback to the user
                Toast.makeText(weakActivity.get().getApplicationContext(), "Toeslag opgeslagen.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Query the database to load the data into the datamodel
     */
    private static class LoadTimeChargesTask extends DatabaseSyncTask<List<TimeCharge>> {

        /**
         * Set up the nessacary data to compelete the query
         * @param activity the activity that is currently displayed
         */
        LoadTimeChargesTask(ChargeActivity activity) {
            super(activity);
        }

        /**
         * Execute the query
         * @param params Not used
         * @return the data that is selected from the database
         */
        @Override
        protected List<TimeCharge> doInBackground(Void... params) {
            return AppDatabase.getInstance(weakActivity.get()).timeChargeDao().getAllTimeCharges();
        }

        /**
         * Delete all entries of the model and insert the updated one's
         * @param charges the data that is selected from the database
         */
        @Override
        protected void onPostExecute(List<TimeCharge> charges) {
            //Check if the activity is still displayed
            if(activityIsActive()) {
                //delete all old data
                ((ChargeActivity)weakActivity.get()).getTimeCharges().getCharges().clear();
                //add all new data
                ((ChargeActivity)weakActivity.get()).getTimeCharges().getCharges().addAll(charges);
                //send a notification to the adapter to update the UI
                ((ChargeActivity)weakActivity.get()).getChargePagerAdapter().getTimeListDetailsFragment().getListFragment().getAdapter().notifyDataSetChanged();
            }
        }

    }

    /**
     * Update the database
     */
    private static class UpdateTimeChargeTask extends DatabaseSyncTask<Void> {

        private TimeCharge timeCharge;

        /**
         * Set up the nessacary data to compelete the query
         * @param activity the activity that is currently displayed
         */
        UpdateTimeChargeTask(ChargeActivity activity, TimeCharge timeCharge) {
            super(activity);
            this.timeCharge = timeCharge;
        }


        /**
         * Execute the query
         * @param params Not used
         * @return null, the query doesn't provide feedback
         */
        @Override
        protected Void doInBackground(Void... params) {
            AppDatabase.getInstance(weakActivity.get()).timeChargeDao().update(timeCharge);
            return null;
        }

        /**
         *
         * @param Void
         */
        @Override
        protected void onPostExecute(Void Void) {
            if(activityIsActive()) {
                try{
                    //Update the datamodel to reflect he changes
                    ((ChargeActivity)weakActivity.get()).getTimeCharges().update(timeCharge);
                }catch(NullPointerException e){
                    //the datamodel is corrupt, so it should be reloaded
                    TimeChargeViewData.LoadTimeChargesTask loadTimeChargesTask = new  TimeChargeViewData.LoadTimeChargesTask((ChargeActivity)weakActivity.get());
                    loadTimeChargesTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
                    return;
                }catch(Exception e){
                    Toast.makeText(weakActivity.get(), "Er is een probleem opgetreden, probeer het later nog eens", Toast.LENGTH_LONG).show();
                    return;
                }

                //Close the details view and return to the listview
                ((ChargeActivity)weakActivity.get()).getChargePagerAdapter().getTimeListDetailsFragment().showListView();

                //Notify the adapter that the dataset has been updated
                ((ChargeActivity)weakActivity.get()).getChargePagerAdapter().getTimeListDetailsFragment().getListFragment().getAdapter().notifyDataSetChanged();

                //Display feedback to the user
                Toast.makeText(weakActivity.get().getApplicationContext(), "Toeslag Verwijderd.", Toast.LENGTH_SHORT).show();

            }
        }

    }
}
