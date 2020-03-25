package com.thijsrijpert.rekeningrijden.controller.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.thijsrijpert.rekeningrijden.controller.viewdata.CarViewData;
import com.thijsrijpert.rekeningrijden.model.Brands;
import com.thijsrijpert.rekeningrijden.model.Car;
import com.thijsrijpert.rekeningrijden.model.Types;
import com.thijsrijpert.rekeningrijden.R;

public class CarActivity extends SuperActivity {

	private EditText etNumberplate;
	private Spinner spBrands, spTypes, spCarSearchNumberplate;
	private Button btnNew, btnAdd;
	private CarViewData carViewData;
	private ArrayAdapter<String> adapterBrands, adapterTypes;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_car);

		super.activateToolbar();

		etNumberplate = findViewById(R.id.etCarNumberplate);
		spBrands = findViewById(R.id.spCarBrand);
		spTypes = findViewById(R.id.spCarType);
		btnNew = findViewById(R.id.btnCarNew);
		btnAdd = findViewById(R.id.btnCarUpdate);
		spCarSearchNumberplate = findViewById(R.id.spCarSearchNumberplate);

		carViewData = new CarViewData();

		btnAdd.setOnClickListener((view) -> {
			if(etNumberplate.isEnabled()){
				carViewData.addCar(this);
			}else{
				carViewData.updateCar(this);
			}
		});

		btnNew.setOnClickListener((event) ->{
			etNumberplate.setText("");
			spBrands.setSelection(0);
			spTypes.setSelection(0);
			etNumberplate.setEnabled(true);
			spCarSearchNumberplate.setSelection(0);
		});

		spCarSearchNumberplate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Car car = (Car) spCarSearchNumberplate.getSelectedItem();
				etNumberplate.setText(car.getNumberplate());
				etNumberplate.setEnabled(false);

				spBrands.setSelection(adapterBrands.getPosition(car.getBrand()));
				spTypes.setSelection(adapterTypes.getPosition(car.getType()));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	public void onStart() {
		super.onStart();
		Brands brands = new Brands();
		Types types = new Types();

		adapterBrands = new ArrayAdapter<>(
			this, R.layout.support_simple_spinner_dropdown_item, brands.getBrands()
		);
		adapterTypes = new ArrayAdapter<>(
				this, R.layout.support_simple_spinner_dropdown_item, types.getTypes()
		);

		carViewData.loadAllUserCars(this, R.id.spCarSearchNumberplate);

		spBrands.setAdapter(adapterBrands);
		spTypes.setAdapter(adapterTypes);
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

	public EditText getEtNumberplate() {
		return etNumberplate;
	}

	public Spinner getSpBrands() {
		return spBrands;
	}

	public Spinner getSpTypes() {
		return spTypes;
	}

	public Button getBtnNew() {
		return btnNew;
	}

	public Button getBtnAdd() {
		return btnAdd;
	}

	public Spinner getSpCarSearchNumberplate() {
		return spCarSearchNumberplate;
	}
}