package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.ActivityController;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.thijsrijpert.rekeningrijden.Controller.PreferencesManager;
import com.thijsrijpert.rekeningrijden.Controller.ViewData.CarViewData;
import com.thijsrijpert.rekeningrijden.Controller.ViewData.RideViewData;
import com.thijsrijpert.rekeningrijden.R;

public class RideRegistrationActivity extends SuperActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ride_registration);

		super.activateToolbar();

		Button button = findViewById(R.id.btnRideRegistration);

		button.setOnClickListener((event) -> {
			RideViewData rideViewData = new RideViewData();
			rideViewData.registration(this);
		});

	}

	public void onStart() {
		super.onStart();
		CarViewData carViewData = new CarViewData();
		carViewData.loadAllUserCars(this, R.id.spRideCar);

		if(!PreferencesManager.getInstance(this).ridePrefExists()){
			((Button)findViewById(R.id.btnRideRegistration)).setText("Starten");
		}else{
			((Button)findViewById(R.id.btnRideRegistration)).setText("Stoppen");
		}
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
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		//check if there where any results
		if (grantResults.length > 0) {
			//check if any requests have been denied
			for(int grantResult : grantResults){
				if(grantResult == PackageManager.PERMISSION_DENIED) {
					Toast.makeText(this, "Toegang tot GPS data geweigerd.", Toast.LENGTH_SHORT).show();
					return;
				}
			}
			RideViewData rideViewData = new RideViewData();
			rideViewData.registration(this);
		} else {
			Toast.makeText(this, "Toegang tot GPS data geweigerd.", Toast.LENGTH_SHORT).show();
		}
	}

}