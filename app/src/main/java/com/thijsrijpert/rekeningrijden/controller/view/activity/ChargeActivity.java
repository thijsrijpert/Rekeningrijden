package com.thijsrijpert.rekeningrijden.controller.view.activity;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.thijsrijpert.rekeningrijden.controller.view.fragment.ChargePagerAdapter;
import com.thijsrijpert.rekeningrijden.controller.viewdata.LocationChargeViewData;
import com.thijsrijpert.rekeningrijden.controller.viewdata.TimeChargeViewData;
import com.thijsrijpert.rekeningrijden.model.LocationCharges;
import com.thijsrijpert.rekeningrijden.model.TimeCharges;
import com.thijsrijpert.rekeningrijden.R;

public class ChargeActivity extends OverviewActivity {

	private ChargePagerAdapter chargePagerAdapter;
	private ViewPager pager;
	private TimeCharges timeCharges;
	private LocationCharges locationCharges;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_charge_overview);

		super.activateToolbar();

		pager = findViewById(R.id.vpChargeOverview);
		chargePagerAdapter = new ChargePagerAdapter(getSupportFragmentManager(), this.getBaseContext());

		TabLayout tabLayout = findViewById(R.id.tabsCharge);

		super.activateViewPager(pager, tabLayout, chargePagerAdapter);
	}

	public void onStart() {
		super.onStart();

		timeCharges = new TimeCharges();
		locationCharges = new LocationCharges();

		LocationChargeViewData locationChargeViewData = new LocationChargeViewData(this);
		locationChargeViewData.loadAllLocationCharges();

		TimeChargeViewData timeChargeViewData = new TimeChargeViewData(this);
		timeChargeViewData.loadAllTimeCharges();
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

	public ChargePagerAdapter getChargePagerAdapter() {
		return chargePagerAdapter;
	}

	public ViewPager getPager() {
		return pager;
	}

	public TimeCharges getTimeCharges() {
		return timeCharges;
	}

	public LocationCharges getLocationCharges() {
		return locationCharges;
	}
}