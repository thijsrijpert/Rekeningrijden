package com.thijsrijpert.rekeningrijden.controller.viewdata;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.thijsrijpert.rekeningrijden.controller.database.AppDatabase;
import com.thijsrijpert.rekeningrijden.controller.PreferencesManager;
import com.thijsrijpert.rekeningrijden.controller.view.activity.CarActivity;
import com.thijsrijpert.rekeningrijden.controller.view.activity.RideRegistrationActivity;
import com.thijsrijpert.rekeningrijden.model.Car;
import com.thijsrijpert.rekeningrijden.model.User;
import com.thijsrijpert.rekeningrijden.R;

import java.util.List;

public class CarViewData extends SuperViewData{

    public CarViewData(Activity activity){
        this.activity = activity;
    }

    public void addCar(){
        try{
            Car car = getCarObject();
            AddCarTask addCarTask = new AddCarTask((CarActivity)activity, car);
            addCarTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }catch(NullPointerException e){
            Toast.makeText(activity.getApplicationContext(), "Kenteken niet ingevoerd", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateCar(){
        try{
            Car car = getCarObject();
            UpdateCarTask updateCarTask = new UpdateCarTask((CarActivity)activity, car);
            updateCarTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }catch(NullPointerException e){
            Toast.makeText(activity.getApplicationContext(), "Kenteken niet ingevoerd", Toast.LENGTH_SHORT).show();
        }
    }

    private Car getCarObject() throws NullPointerException{
        String numberplate = ((EditText)activity.findViewById(R.id.etCarNumberplate)).getText().toString().trim().replaceAll("\\s", "");
        String brand = ((Spinner)activity.findViewById(R.id.spCarBrand)).getSelectedItem().toString();
        //String color = ((EditText)activity.findViewById(R.id.etCarNumberplate)).getText().toString();
        String color = "#FFFFFF";
        String type = ((Spinner)activity.findViewById(R.id.spCarType)).getSelectedItem().toString();

        if(numberplate.equals("")){
            throw new NullPointerException();
        }else{
            User user = PreferencesManager.getInstance(activity).getUserPref();
            return new Car(numberplate, brand, color, type, user);
        }


    }

    public void loadAllUserCars(int resourceId){
        User user = PreferencesManager.getInstance(activity).getUserPref();

        LoadCarsTask loadCarsTask = new LoadCarsTask(activity, user, resourceId );
        loadCarsTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private static class UpdateCarTask extends DatabaseSyncTask<Void> {

        private Car car;

        UpdateCarTask(CarActivity activity, Car car) {
            super(activity);
            this.car = car;
        }

        @Override
        protected Void doInBackground(Void... params) {
            AppDatabase.getInstance(weakActivity.get().getApplicationContext()).carDao().update(car);
            return null;
        }

        @Override
        protected void onPostExecute(Void empty) {
            if(activityIsActive()){
                Toast.makeText(weakActivity.get().getApplicationContext(), "Auto succesvol aangepast.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(weakActivity.get().getApplicationContext(), RideRegistrationActivity.class);
                weakActivity.get().startActivity(intent);
            }
        }
    }

    private static class AddCarTask extends DatabaseSyncTask<Boolean> {

        private Car car;

        AddCarTask(CarActivity activity, Car car) {
            super(activity);
            this.car = car;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try{
                AppDatabase.getInstance(weakActivity.get().getApplicationContext()).carDao().insert(car);
            }catch(SQLiteConstraintException e){
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if(activityIsActive() && success){
                Toast.makeText(weakActivity.get().getApplicationContext(), "Auto succesvol toegevoegd.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(weakActivity.get().getApplicationContext(), RideRegistrationActivity.class);
                weakActivity.get().startActivity(intent);
            }else if(activityIsActive() && !success){
                Toast.makeText(weakActivity.get().getApplicationContext(), "De Auto is al eens ingevoerd", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private static class LoadCarsTask extends DatabaseSyncTask<List<Car>> {

        private User user;
        private int resourceId;

        LoadCarsTask(Activity activity, User user, int resourceId) {
            super(activity);
            this.resourceId = resourceId;
            this.user = user;
        }

        @Override
        protected List<Car> doInBackground(Void... params) {
            return AppDatabase.getInstance(weakActivity.get().getApplicationContext()).carDao().getAllCarsByUser(user.getUsername());
        }

        @Override
        protected void onPostExecute(List<Car> cars) {
            if(activityIsActive()) {
                if(cars.size() > 0 && weakActivity.get() instanceof CarActivity){
                    ((CarActivity)weakActivity.get()).getEtNumberplate().setEnabled(false);
                }else if(weakActivity.get() instanceof CarActivity){
                    ((CarActivity) weakActivity.get()).getSpCarSearchNumberplate().setVisibility(View.INVISIBLE);
                }

                Spinner spinner = weakActivity.get().findViewById(resourceId);
                spinner.setAdapter(new ArrayAdapter<>(weakActivity.get().getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, cars));
            }
        }
    }
    }
