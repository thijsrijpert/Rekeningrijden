package com.thijsrijpert.rekeningrijden.Controller.ViewData;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.thijsrijpert.rekeningrijden.Controller.Database.AppDatabase;
import com.thijsrijpert.rekeningrijden.Controller.Database.UserDao;
import com.thijsrijpert.rekeningrijden.Controller.PreferencesManager;
import com.thijsrijpert.rekeningrijden.Controller.ViewControllers.ActivityController.CarActivity;
import com.thijsrijpert.rekeningrijden.Controller.ViewControllers.ActivityController.LoginActivity;
import com.thijsrijpert.rekeningrijden.Controller.ViewControllers.ActivityController.RegistrationActivity;
import com.thijsrijpert.rekeningrijden.Controller.ViewControllers.ActivityController.RideRegistrationActivity;
import com.thijsrijpert.rekeningrijden.Model.Role;
import com.thijsrijpert.rekeningrijden.Model.User;
import com.thijsrijpert.rekeningrijden.R;

public class UserViewData extends SuperViewData {

    public void login(LoginActivity activity){
        String name = ((EditText)activity.findViewById(R.id.etName)).getText().toString();
        String password = ((EditText)activity.findViewById(R.id.etPassword)).getText().toString();
        User user = new User(name);
        user.setPassword(password);
        LoginTask loginTask = new LoginTask(activity, user);
        loginTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void register(RegistrationActivity activity){

        String name = activity.getEtName().getText().toString();
        String username = activity.getEtUsername().getText().toString();
        String password = activity.getEtPassword().getText().toString();
        String zipcode = activity.getEtZipcode().getText().toString();
        String homenumber = activity.getEtHomenumber().getText().toString();
        String phonenumber = activity.getEtPhonenumber().getText().toString();

        User user = new User(username, zipcode, homenumber, phonenumber, name, password, new Role("Advanced"));

        RegistrationTask registrationTask = new RegistrationTask(activity, user);
        registrationTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
            if(weakActivity.get() == null) {
                return;
            }
            if(user == null){
                Toast.makeText(weakActivity.get().getApplicationContext(), "Inloggegevens onjuist.", Toast.LENGTH_LONG).show();
            }else{
                PreferencesManager.getInstance(weakActivity.get()).storeObjectInPref("User", user);

                Intent intent = new Intent(weakActivity.get().getApplicationContext(), RideRegistrationActivity.class);
                weakActivity.get().startActivity(intent);
            }
        }
    }

    private static class RegistrationTask extends DatabaseSyncTask<Void> {
        private User user;

        RegistrationTask(RegistrationActivity activity, User user) {
            super(activity);
            this.user = user;
        }

        @Override
        protected Void doInBackground(Void... params) {
            UserDao userDao = AppDatabase.getInstance(weakActivity.get().getApplicationContext()).userDao();
            if(PreferencesManager.getInstance(weakActivity.get()).userPrefExists()){
                userDao.updateUser(user);
            }else{
                userDao.insertUser(user);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void empty) {
            if(weakActivity.get() == null) {
                return;
            }

            PreferencesManager.getInstance(weakActivity.get()).storeObjectInPref("User", user);

            Intent intent = new Intent(weakActivity.get().getApplicationContext(), CarActivity.class);
            weakActivity.get().startActivity(intent);
        }
    }
}