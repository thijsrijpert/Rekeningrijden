package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.thijsrijpert.rekeningrijden.R;

public class RidePagerAdapter extends FragmentPagerAdapter {

    private final Context context;
    private final RideOverviewFragment rideOverviewFragment;
    private final RideYearTotalFragment rideYearTotalFragment;
    private final RideSearchFragment rideSearchFragment;

    public RidePagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        rideOverviewFragment = new RideOverviewFragment();
        rideYearTotalFragment = new RideYearTotalFragment();
        rideSearchFragment = new RideSearchFragment();
        this.context = context;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return rideOverviewFragment;
            case 1:
                return rideYearTotalFragment;
            case 2:
                return rideSearchFragment;
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

    public RideOverviewFragment getRideOverviewFragment() {
        return rideOverviewFragment;
    }

    public RideYearTotalFragment getRideYearTotalFragment() {
        return rideYearTotalFragment;
    }

    public RideSearchFragment getRideSearchFragment() {
        return rideSearchFragment;
    }
}
