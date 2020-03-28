package com.thijsrijpert.rekeningrijden.controller.viewdata;

import android.app.Activity;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.widget.Toast;

import com.thijsrijpert.rekeningrijden.controller.database.AppDatabase;
import com.thijsrijpert.rekeningrijden.controller.view.activity.ChargeActivity;
import com.thijsrijpert.rekeningrijden.controller.view.fragment.ChargeDetailsFragment;
import com.thijsrijpert.rekeningrijden.model.DefaultCharge;

import java.time.LocalDate;
import java.util.Locale;

public class DefaultChargeViewData extends SuperViewData {

    public DefaultChargeViewData(Activity activity){
        this.activity = activity;
    }

    public void newDefaultCharge(){
        //get the current fragment
        ChargeDetailsFragment fragment = ((ChargeActivity)activity).getChargePagerAdapter().getDefaultChargeFragment();
        DefaultCharge charge;
        try{
            String price = fragment.getEtPrice().getText().toString();
            //Get the charge date
            LocalDate date = LocalDate.now();
            charge = new DefaultCharge(Double.parseDouble(String.format(Locale.US, "%s", price)), date, null);
        }catch(NullPointerException | NumberFormatException e){
            if(fragment.getEtPrice().getText().toString().equals("")){
                Toast.makeText(activity, "Er is geen tijd ingevoerd", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        NewDefaultChargeTask defaultChargeTask = new NewDefaultChargeTask((ChargeActivity)activity, charge);
        defaultChargeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private static class NewDefaultChargeTask extends DatabaseSyncTask<Boolean> {

        private DefaultCharge charge;

        NewDefaultChargeTask(ChargeActivity activity, DefaultCharge charge) {
            super(activity);
            this.charge = charge;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try{
                AppDatabase.getInstance(weakActivity.get()).defaultChargeDao().insert(charge);
            }catch(SQLiteConstraintException e){
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            //Check if the activity is still displayed
            if(activityIsActive() && success) {
                //Display feedback to the user
                Toast.makeText(weakActivity.get().getApplicationContext(), "Toeslag opgeslagen.", Toast.LENGTH_SHORT).show();
            }else if(!success){
                Toast.makeText(weakActivity.get().getApplicationContext(), "Er is vandaag al een toeslag ingevoerd.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
