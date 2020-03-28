package com.thijsrijpert.rekeningrijden.controller.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.thijsrijpert.rekeningrijden.controller.viewdata.RideViewData;
import com.thijsrijpert.rekeningrijden.model.Ride;
import com.thijsrijpert.rekeningrijden.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RideOverviewDetailsFragment extends DetailsFragment {

    private TextView tveStartLocation, tveStopLocation, tveStartTime, tveStopTime, tveDate, tveLocationCharge, tveTimeCharge, tveDistance, tveCosts;
    private RideViewData rideViewData;
    private Double defaultCharge;
    private Integer distance = -1;

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
        tveDistance = view.findViewById(R.id.tveRideDistance);
        tveCosts = view.findViewById(R.id.tveRideCost);
        if (getArguments() != null) {
            Ride ride = (Ride) getArguments().getSerializable("Item");
            if (ride != null) {
                tveStartLocation.setText(ride.getStartlocation());
                tveStopLocation.setText(ride.getStoplocation());
                tveStartTime.setText(ride.getStarttime().toString());
                tveDate.setText(ride.getDate().toString());
                rideViewData = new RideViewData(getActivity());
                rideViewData.loadDistance(ride);
                rideViewData.loadCharge(ride);
                if(ride.getStoptime() != null){
                    tveStopTime.setText(ride.getStoptime().toString());
                }
            }

        }

        return view;
    }

    public TextView getTveStartLocation() {
        return tveStartLocation;
    }

    public TextView getTveStopLocation() {
        return tveStopLocation;
    }

    public TextView getTveStartTime() {
        return tveStartTime;
    }

    public TextView getTveStopTime() {
        return tveStopTime;
    }

    public TextView getTveDate() {
        return tveDate;
    }

    public TextView getTveLocationCharge() {
        return tveLocationCharge;
    }

    public TextView getTveTimeCharge() {
        return tveTimeCharge;
    }

    public TextView getTveDistance() {
        return tveDistance;
    }

    public TextView getTveCosts() {
        return tveCosts;
    }

    public RideViewData getRideViewData() {
        return rideViewData;
    }

    public Double getDefaultCharge() {
        return defaultCharge;
    }

    public void setDefaultCharge(Double defaultCharge) {
        this.defaultCharge = defaultCharge;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
