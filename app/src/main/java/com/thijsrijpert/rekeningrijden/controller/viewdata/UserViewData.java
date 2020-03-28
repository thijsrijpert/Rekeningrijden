package com.thijsrijpert.rekeningrijden.controller.viewdata;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.thijsrijpert.rekeningrijden.controller.database.AppDatabase;
import com.thijsrijpert.rekeningrijden.controller.database.UserDao;
import com.thijsrijpert.rekeningrijden.controller.PreferencesManager;
import com.thijsrijpert.rekeningrijden.controller.view.activity.CarActivity;
import com.thijsrijpert.rekeningrijden.controller.view.activity.ChargeActivity;
import com.thijsrijpert.rekeningrijden.controller.view.activity.LoginActivity;
import com.thijsrijpert.rekeningrijden.controller.view.activity.RegistrationActivity;
import com.thijsrijpert.rekeningrijden.controller.view.activity.RideRegistrationActivity;
import com.thijsrijpert.rekeningrijden.model.Role;
import com.thijsrijpert.rekeningrijden.model.User;
import com.thijsrijpert.rekeningrijden.R;

public class UserViewData extends SuperViewData {

    public UserViewData(Activity activity){
        this.activity = activity;
    }

    public void login(){
        String name = ((EditText)activity.findViewById(R.id.etName)).getText().toString();
        String password = ((EditText)activity.findViewById(R.id.etPassword)).getText().toString();
        User user = new User(name);
        user.setPassword(password);
        LoginTask loginTask = new LoginTask((LoginActivity)activity, user);
        loginTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void register(){
        RegistrationActivity activity = (RegistrationActivity)this.activity;

        String name = activity.getEtName().getText().toString();
        String username = activity.getEtUsername().getText().toString();
        String password = activity.getEtPassword().getText().toString();
        String zipcode = activity.getEtZipcode().getText().toString().trim().replaceAll("\\s", "").toUpperCase();
        String homenumber = activity.getEtHomenumber().getText().toString();
        String phonenumber = activity.getEtPhonenumber().getText().toString().trim().replaceAll("\\s", "");

        if(username.equals("") || zipcode.equals("") || homenumber.equals("") || phonenumber.equals("") || name.equals("") || password.equals("")){
            Toast.makeText(activity.getApplicationContext(), "Er zijn velden niet ingevuld", Toast.LENGTH_SHORT).show();
        }else if(zipcode.length() != 6){
            if(zipcode.length() < 6){
                Toast.makeText(activity.getApplicationContext(), "De postcode is te kort", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(activity.getApplicationContext(), "De postcode is te lang", Toast.LENGTH_SHORT).show();
            }
        }else if(phonenumber.length() != 10){
            if(phonenumber.length() < 10){
                Toast.makeText(activity.getApplicationContext(), "Het telefoonnummer is te kort", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(activity.getApplicationContext(), "Het telefoonnummer is te lang", Toast.LENGTH_SHORT).show();
            }
        }else{
            User oldUser = PreferencesManager.getInstance(getActivity()).getUserPref();
            Role role;
            if(oldUser != null){
                role = oldUser.getRole();
            }else{
                role = new Role("Advanced");
            }
            User user = new User(username, zipcode, homenumber, phonenumber, name, password, role);

            RegistrationTask registrationTask = new RegistrationTask(activity, user);
            registrationTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }


    private static class LoginTask extends DatabaseSyncTask<User> {

        private User user;

        LoginTask(LoginActivity activity, User user) {
            super(activity);
            this.user = user;
        }

        @Override
        protected User doInBackground(Void... params) {
            UserDao userDao = AppDatabase.getInstance(weakActivity.get().getApplicationContext()).userDao();
            return userDao.getUserLogin(user.getUsername(), user.getPassword());
        }

        @Override
        protected void onPostExecute(User user) {
            if(activityIsActive()) {
                if(user == null){
                    Toast.makeText(weakActivity.get().getApplicationContext(), "Inloggegevens onjuist.", Toast.LENGTH_LONG).show();
                }else{
                    PreferencesManager.getInstance(weakActivity.get()).storeObjectInPref("User", user);

                    Intent intent;
                    if(user.getRole().getRole().equals("Admin")){
                        intent = new Intent(weakActivity.get().getApplicationContext(), ChargeActivity.class);
                    }else{
                        intent = new Intent(weakActivity.get().getApplicationContext(), RideRegistrationActivity.class);
                    }

                    weakActivity.get().startActivity(intent);
                }
            }
        }
    }

    private static class RegistrationTask extends DatabaseSyncTask<Boolean> {
        private User user;

        RegistrationTask(RegistrationActivity activity, User user) {
            super(activity);
            this.user = user;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            UserDao userDao = AppDatabase.getInstance(weakActivity.get().getApplicationContext()).userDao();
            if(PreferencesManager.getInstance(weakActivity.get()).userPrefExists()){
                userDao.update(user);
            }else{
                try{
                    userDao.insert(user);
                }catch(SQLiteConstraintException e){
                    return false;
                }

            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if(activityIsActive() && success) {
                PreferencesManager.getInstance(weakActivity.get()).storeObjectInPref("User", user);

                Intent intent;
                if(((RegistrationActivity)weakActivity.get()).getEtUsername().isEnabled()){
                    intent = new Intent(weakActivity.get().getApplicationContext(), CarActivity.class);
                }else if(user.getRole().getRole().equals("Admin")) {
                    intent = new Intent(weakActivity.get().getApplicationContext(), ChargeActivity.class);
                }else{
                    intent = new Intent(weakActivity.get().getApplicationContext(), RideRegistrationActivity.class);
                }

                weakActivity.get().startActivity(intent);
            }else if(activityIsActive() && !success){
                Toast.makeText(weakActivity.get().getApplicationContext(), "Deze gebruikersnaam bestaat al", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
