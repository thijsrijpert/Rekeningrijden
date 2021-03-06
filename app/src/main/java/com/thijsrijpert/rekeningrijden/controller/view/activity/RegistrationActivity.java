package com.thijsrijpert.rekeningrijden.controller.view.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.thijsrijpert.rekeningrijden.controller.PreferencesManager;
import com.thijsrijpert.rekeningrijden.controller.viewdata.UserViewData;
import com.thijsrijpert.rekeningrijden.model.User;
import com.thijsrijpert.rekeningrijden.R;

public class RegistrationActivity extends SuperActivity {

	private EditText etName, etUsername, etPassword, etZipcode, etHomenumber, etPhonenumber;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_registration);

		etName = findViewById(R.id.etRegistrationName);
		etUsername = findViewById(R.id.etRegistrationUsername);
		etPassword = findViewById(R.id.etRegistrationPassword);
		etZipcode = findViewById(R.id.etRegistrationZipcode);
		etHomenumber = findViewById(R.id.etRegistrationHomeNumber);
		etPhonenumber = findViewById(R.id.etRegistrationPhonenumber);

		super.activateToolbar();

		Button btnRegistration = findViewById(R.id.btnRegistrationRegister);

		UserViewData userViewData = new UserViewData(this);

		btnRegistration.setOnClickListener(view -> userViewData.register());
	}

	public void onStart() {
		super.onStart();

		User user = PreferencesManager.getInstance(this).getUserPref();

		if(user != null){
			etName.setText(user.getDriver());
			etPassword.setText(user.getPassword());
			etZipcode.setText(user.getZipcode());
			etHomenumber.setText(user.getHomenumber());
			etPhonenumber.setText(user.getPhonenumber());

			etUsername.setText(user.getUsername());
			etUsername.setEnabled(false);
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

		etName.setText("");
		etPassword.setText("");
		etZipcode.setText("");
		etHomenumber.setText("");
		etPhonenumber.setText("");

		etUsername.setText("");
		etUsername.setEnabled(true);
	}

	public void onDestory() {
		super.onDestroy();
	}

	public EditText getEtName() {
		return etName;
	}

	public EditText getEtUsername() {
		return etUsername;
	}

	public EditText getEtPassword() {
		return etPassword;
	}

	public EditText getEtZipcode() {
		return etZipcode;
	}

	public EditText getEtHomenumber() {
		return etHomenumber;
	}

	public EditText getEtPhonenumber() {
		return etPhonenumber;
	}
}