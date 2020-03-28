package com.thijsrijpert.rekeningrijden.controller.view.fragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.thijsrijpert.rekeningrijden.R;

public class ChargePagerAdapter extends FragmentPagerAdapter {

    private final Context context;
    private ListDetailsFragment locationListDetailsFragment, timeListDetailsFragment;
    private ChargeDetailsFragment defaultChargeFragment;

    public ChargePagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        locationListDetailsFragment = new ListDetailsFragment(new LocationChargeFragment(), new LocationChargeDetailsFragment());
        timeListDetailsFragment  = new ListDetailsFragment(new TimeChargeFragment(), new TimeChargeDetailsFragment());
        defaultChargeFragment = new ChargeDetailsFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return locationListDetailsFragment;
            case 1:
                return timeListDetailsFragment;
            default:
                return defaultChargeFragment;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return context.getString(R.string.hdChargeLocation);
            case 1:
                return context.getString(R.string.hdChargeTime);
            case 2:
                return context.getString(R.string.hdChargeDefault);
            default:
                return null;
        }
    }

    public ListDetailsFragment getLocationListDetailsFragment() {
        return locationListDetailsFragment;
    }

    public ListDetailsFragment getTimeListDetailsFragment() {
        return timeListDetailsFragment;
    }

    public ChargeDetailsFragment getDefaultChargeFragment() {
        return defaultChargeFragment;
    }
}
