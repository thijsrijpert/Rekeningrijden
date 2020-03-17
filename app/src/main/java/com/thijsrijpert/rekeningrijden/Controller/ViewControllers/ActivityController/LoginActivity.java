package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.ActivityController;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.thijsrijpert.rekeningrijden.Controller.PreferencesManager;
import com.thijsrijpert.rekeningrijden.Controller.ViewData.UserViewData;
import com.thijsrijpert.rekeningrijden.R;

public class LoginActivity extends SuperActivity {

	private EditText etUsername, etPassword;
	private Button btnLogin,  btnRegistration;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_login);

		btnLogin = findViewById(R.id.btnLogin);
		btnRegistration = findViewById(R.id.btnLoginRegistration);
		etUsername = findViewById(R.id.etName);
		etPassword = findViewById(R.id.etPassword);

		btnLogin.setOnClickListener((view) -> {
			UserViewData controller = new UserViewData();
			controller.login(this);
		});

		btnRegistration.setOnClickListener((view) -> {
			Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
			startActivity(intent);
		});

	}

	public void onStart() {
		super.onStart();

		if(PreferencesManager.getInstance(this).userPrefExists()){
			Intent intent = new Intent(getApplicationContext(), RideRegistrationActivity.class);
			startActivity(intent);
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

	public EditText getEtUsername() {
		return etUsername;
	}

	public EditText getEtPassword() {
		return etPassword;
	}

	public Button getBtnLogin() {
		return btnLogin;
	}

	public Button getBtnRegistration() {
		return btnRegistration;
	}
}