package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.ActivityController;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.thijsrijpert.rekeningrijden.R;

public class CarActivity extends SuperActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_car);

		super.activateToolbar();

		Button btnCarUpdate = findViewById(R.id.btnCarUpdate);

		btnCarUpdate.setOnClickListener((view) -> {
			Intent intent = new Intent(getApplicationContext(), RideRegistrationActivity.class);

			startActivity(intent);
		});
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
}