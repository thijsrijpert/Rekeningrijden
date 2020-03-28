package com.thijsrijpert.rekeningrijden.controller.view.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import com.thijsrijpert.rekeningrijden.R;
import com.thijsrijpert.rekeningrijden.controller.PreferencesManager;
import com.thijsrijpert.rekeningrijden.controller.viewdata.CarViewData;
import com.thijsrijpert.rekeningrijden.controller.viewdata.RideViewData;

public class RideRegistrationActivity extends SuperActivity {

	private Button btnRideRegistration;
	private Spinner spRideCar;
	private RideViewData rideViewData;
	private CarViewData carViewData;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ride_registration);

		super.activateToolbar();

		btnRideRegistration = findViewById(R.id.btnRideRegistration);

		rideViewData = new RideViewData(this);

		spRideCar = findViewById(R.id.spRideCar);

		carViewData = new CarViewData(this);

		btnRideRegistration.setOnClickListener(view -> rideViewData.registration());

	}

	public void onStart() {
		super.onStart();

		carViewData.loadAllUserCars(R.id.spRideCar);

		if(!PreferencesManager.getInstance(this).ridePrefExists()){
			btnRideRegistration.setText(R.string.btnDriverStart);
		}else{
			btnRideRegistration.setText(R.string.btnDriverStop);
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

		btnRideRegistration.setText(R.string.btnDriverStart);
	}

	public void onDestory() {
		super.onDestroy();
	}

	public Button getBtnRideRegistration() {
		return btnRideRegistration;
	}

	public Spinner getSpRideCar() {
		return spRideCar;
	}
}