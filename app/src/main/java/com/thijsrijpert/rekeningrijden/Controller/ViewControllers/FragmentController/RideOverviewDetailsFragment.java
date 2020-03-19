package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.thijsrijpert.rekeningrijden.Model.Ride;
import com.thijsrijpert.rekeningrijden.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RideOverviewDetailsFragment extends DetailsFragment {

    private TextView tveStartLocation, tveStopLocation, tveStartTime, tveStopTime, tveDate, tveLocationCharge, tveTimeCharge;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_ride_overview_details, container, false);
        tveStartLocation = view.findViewById(R.id.tveRideStartLocation);
        tveStopLocation = view.findViewById(R.id.tveRideStopLocation);
        tveStartTime = view.findViewById(R.id.tveRideStartTime);
        tveStopTime = view.findViewById(R.id.tveRideStopTime);
        tveDate = view.findViewById(R.id.tveRideDate);
        tveLocationCharge = view.findViewById(R.id.tveRideLocationCharge);
        tveTimeCharge = view.findViewById(R.id.tveRideTimeCharge);
        if (getArguments() != null) {
            Ride ride = (Ride) getArguments().getSerializable("Ride");
            if (ride != null) {
                tveStartLocation.setText(ride.getStartlocation());
                tveStopLocation.setText(ride.getStoplocation());
                tveStartTime.setText(ride.getStarttime().toString());
                if(ride.getStoptime() != null){
                    tveStopTime.setText(ride.getStoptime().toString());
                }
            }

        }

        return view;
    }

}
