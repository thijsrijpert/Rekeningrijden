package com.thijsrijpert.rekeningrijden.Controller.ViewData;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.thijsrijpert.rekeningrijden.Controller.Database.AppDatabase;
import com.thijsrijpert.rekeningrijden.Controller.PreferencesManager;
import com.thijsrijpert.rekeningrijden.Controller.ViewControllers.ActivityController.CarActivity;
import com.thijsrijpert.rekeningrijden.Controller.ViewControllers.ActivityController.RideRegistrationActivity;
import com.thijsrijpert.rekeningrijden.Model.Car;
import com.thijsrijpert.rekeningrijden.Model.User;
import com.thijsrijpert.rekeningrijden.R;

import java.util.List;

public class CarViewData extends SuperViewData{

    public void addCar(CarActivity activity){
        this.activity = activity;

        Car car = getCarObject();

        AddCarTask addCarTask = new AddCarTask(activity, car);
        addCarTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void updateCar(CarActivity activity){
        this.activity = activity;

        Car car = getCarObject();

        UpdateCarTask updateCarTask = new UpdateCarTask(activity, car);
        updateCarTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public Car getCarObject(){
        String numberplate = ((EditText)activity.findViewById(R.id.etCarNumberplate)).getText().toString();
        String brand = ((Spinner)activity.findViewById(R.id.spCarBrand)).getSelectedItem().toString();
        //String color = ((EditText)activity.findViewById(R.id.etCarNumberplate)).getText().toString();
        String color = "#FFFFFF";
        String type = ((Spinner)activity.findViewById(R.id.spCarType)).getSelectedItem().toString();

        User user = PreferencesManager.getInstance(activity).getUserPref();

        return new Car(numberplate, brand, color, type, user);
    }


    public void loadAllUserCars(Activity activity, int resourceId){
        this.activity = activity;

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
            AppDatabase.getInstance(weakActivity.get().getApplicationContext()).carDao().updateCar(car);
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

    private static class AddCarTask extends DatabaseSyncTask<Void> {

        private Car car;

        AddCarTask(CarActivity activity, Car car) {
            super(activity);
            this.car = car;
        }

        @Override
        protected Void doInBackground(Void... params) {
            AppDatabase.getInstance(weakActivity.get().getApplicationContext()).carDao().insertCar(car);
            return null;
        }

        @Override
        protected void onPostExecute(Void empty) {
            if(activityIsActive()){
                Toast.makeText(weakActivity.get().getApplicationContext(), "Auto succesvol toegevoegd.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(weakActivity.get().getApplicationContext(), RideRegistrationActivity.class);
                weakActivity.get().startActivity(intent);
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
                    cars.add(0, new Car("Leeg"));
                }else if(weakActivity.get() instanceof CarActivity){
                    ((CarActivity) weakActivity.get()).getSpCarSearchNumberplate().setVisibility(View.INVISIBLE);
                }

                Spinner spinner = weakActivity.get().findViewById(resourceId);
                spinner.setAdapter(new ArrayAdapter<>(weakActivity.get().getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, cars));
            }
        }
    }
    }
