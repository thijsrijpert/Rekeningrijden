package com.thijsrijpert.rekeningrijden.controller.view.activity;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.thijsrijpert.rekeningrijden.controller.view.fragment.RidePagerAdapter;
import com.thijsrijpert.rekeningrijden.controller.viewdata.RideViewData;
import com.thijsrijpert.rekeningrijden.R;

public class RideOverviewActivity extends OverviewActivity{

	private RidePagerAdapter ridePagerAdapter;
	RideViewData rideViewData;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_ride_overview);

		super.activateToolbar();

		ViewPager pager = findViewById(R.id.vpRideOverview);
		TabLayout tabLayout = findViewById(R.id.tabsRide);
		ridePagerAdapter = new RidePagerAdapter(getSupportFragmentManager(), this.getBaseContext());

		rideViewData = new RideViewData(this);

		super.activateViewPager(pager, tabLayout, ridePagerAdapter);

	}

	public void onStart() {
		super.onStart();

		rideViewData.loadAllRides();
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

	public RidePagerAdapter getRidePagerAdapter() {
		return ridePagerAdapter;
	}
}