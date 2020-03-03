package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.ActivityController;

import android.os.Bundle;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController.ChargePagerAdapter;
import com.thijsrijpert.rekeningrijden.R;

public class ChargeActivity extends OverviewActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_charge_overview);

		super.activateToolbar();

		ViewPager pager = findViewById(R.id.vpChargeOverview);
		TabLayout tabLayout = findViewById(R.id.tabsCharge);
		FragmentPagerAdapter adapter = new ChargePagerAdapter(getSupportFragmentManager(), this.getBaseContext());

		super.activateViewPager(pager, tabLayout, adapter);

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