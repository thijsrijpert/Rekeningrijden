package com.thijsrijpert.rekeningrijden.controller.view.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.thijsrijpert.rekeningrijden.R;
import com.thijsrijpert.rekeningrijden.controller.PreferencesManager;
import com.thijsrijpert.rekeningrijden.controller.view.fragment.ListDetailsFragment;
import com.thijsrijpert.rekeningrijden.controller.viewdata.RideViewData;
import com.thijsrijpert.rekeningrijden.model.DefaultCharge;
import com.thijsrijpert.rekeningrijden.model.User;

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

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }else{
            System.out.println("Actionbar problem");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(PreferencesManager.getInstance(this).userPrefExists()) {
            User user = PreferencesManager.getInstance(this).getUserPref();

            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);

            switch (user.getRole().getRole()) {
                case "Admin":
                    menu.findItem(R.id.menuCar).setVisible(false);
                    menu.findItem(R.id.menuOverview).setVisible(false);
                    if(((ChargeActivity)this).getPager().getCurrentItem() == 2 ||
                            ((ChargeActivity)this).getChargePagerAdapter().getLocationListDetailsFragment().getDetailsFragment().isVisible() ||
                            ((ChargeActivity)this).getChargePagerAdapter().getTimeListDetailsFragment().getDetailsFragment().isVisible()
                    ){
                        menu.findItem(R.id.menuChargeNew).setVisible(false);
                    }
                    break;
                case "Advanced":
                    menu.findItem(R.id.menuChargeNew).setVisible(false);
                    break;
                case "Basic":
                    menu.removeItem(R.id.menuChargeNew);
                    menu.removeItem(R.id.menuOverview);
                    break;
                default:
                    return false;
            }
        }

        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.menuAccount:
                intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                break;
            case R.id.menuOverview:
                intent = new Intent(getApplicationContext(), RideOverviewActivity.class);
                break;
            case R.id.menuLogout:
                PreferencesManager.getInstance(this).removePref("User");
                PreferencesManager.getInstance(this).removePref("Ride");
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                break;
            case R.id.menuCar:
                intent = new Intent(getApplicationContext(), CarActivity.class);
                break;
            case R.id.menuChargeNew:
                int currentItem = ((ChargeActivity)this).getPager().getCurrentItem();
                ListDetailsFragment<DefaultCharge> fragment;
                if(currentItem == 0){
                    fragment = ((ChargeActivity)this).getChargePagerAdapter().getLocationListDetailsFragment();
                }else if(currentItem == 1){
                    fragment = ((ChargeActivity)this).getChargePagerAdapter().getTimeListDetailsFragment();
                }else{
                    break;
                }
                if(fragment.isResumed()){
                    fragment.onPause();
                    fragment.onStop();
                }
                fragment.onItemClick(null);
                break;
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        if(intent != null){
            startActivity(intent);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //check if there where any results
        if (requestCode == 1 && grantResults.length > 0) {
            //check if any requests have been denied
            for(int grantResult : grantResults){
                if(grantResult == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "Toegang tot GPS data geweigerd.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            RideViewData rideViewData = new RideViewData(this);
            rideViewData.registration();
        } else if(requestCode == 2 && grantResults.length == 1) {
            if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                Toast.makeText(this, "Toegang tot Internet data geweigerd.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Toegang tot GPS data geweigerd.", Toast.LENGTH_SHORT).show();
        }
    }
}
