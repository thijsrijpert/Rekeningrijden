package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.ActivityController;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.thijsrijpert.rekeningrijden.R;

public class LoginActivity extends AppCompatActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_login);

		Button btnLogin = findViewById(R.id.btnLogin);
		Button btnRegistration = findViewById(R.id.btnLoginRegistration);

		btnLogin.setOnClickListener((view) -> {
			Intent intent = new Intent(getApplicationContext(), ChargeActivity.class);

			startActivity(intent);
		});

		btnRegistration.setOnClickListener((view) -> {
			Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);

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