package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.ActivityController;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.thijsrijpert.rekeningrijden.R;

public class SuperActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestory() {
        super.onDestroy();
    }

    protected void activateToolbar(){
        Toolbar toolbar = findViewById(R.id.tbRideRegistration);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menuAccount:
                intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                break;
            case R.id.menuOverview:
                intent = new Intent(getApplicationContext(), RideOverviewActivity.class);
                break;
            case R.id.menuLogout:
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                break;
            case R.id.menuCar:
                intent = new Intent(getApplicationContext(), CarActivity.class);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        startActivity(intent);
        return true;
    }
}
