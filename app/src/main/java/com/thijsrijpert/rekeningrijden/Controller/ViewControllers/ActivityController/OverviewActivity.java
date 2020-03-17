package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.ActivityController;

import android.os.Bundle;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class OverviewActivity extends SuperActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

	public void activateViewPager(ViewPager pager, TabLayout tabLayout, FragmentPagerAdapter adapter){
		pager.setAdapter(adapter);
		tabLayout.setupWithViewPager(pager);
		tabLayout.bringToFront();
	}

}