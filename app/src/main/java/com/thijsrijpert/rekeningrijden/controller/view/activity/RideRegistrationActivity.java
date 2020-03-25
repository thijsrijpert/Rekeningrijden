package com.thijsrijpert.rekeningrijden.controller.view.activity;

import android.os.Bundle;
import android.widget.Button;

import com.thijsrijpert.rekeningrijden.R;
import com.thijsrijpert.rekeningrijden.controller.PreferencesManager;
import com.thijsrijpert.rekeningrijden.controller.viewdata.CarViewData;
import com.thijsrijpert.rekeningrijden.controller.viewdata.RideViewData;

public class RideRegistrationActivity extends SuperActivity {

	private Button btnRideRegistration;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ride_registration);

		super.activateToolbar();

		btnRideRegistration = findViewById(R.id.btnRideRegistration);

		btnRideRegistration.setOnClickListener((event) -> {
			RideViewData rideViewData = new RideViewData();
			rideViewData.registration(this);
		});

	}

	public void onStart() {
		super.onStart();
		CarViewData carViewData = new CarViewData();
		carViewData.loadAllUserCars(this, R.id.spRideCar);

		if(!PreferencesManager.getInstance(this).ridePrefExists()){
			btnRideRegistration.setText("Starten");
		}else{
			btnRideRegistration.setText("Stoppen");
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


}