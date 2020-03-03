package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.thijsrijpert.rekeningrijden.R;

public class RidePagerAdapter extends FragmentPagerAdapter {

    private final Context context;

    public RidePagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RideOverviewFragment();
            case 1:
                return new RideYearTotalFragment();
            case 2:
                return new RideYearTotalFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return context.getString(R.string.hdRideOverview);
            case 1:
                return context.getString(R.string.hdRideYearTotal);
            case 2:
                return context.getString(R.string.hdRideSearch);
            default:
                return null;
        }
    }
}
