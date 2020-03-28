package com.thijsrijpert.rekeningrijden.controller.view.fragment;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.thijsrijpert.rekeningrijden.R;
import com.thijsrijpert.rekeningrijden.controller.viewdata.RideViewData;
import com.thijsrijpert.rekeningrijden.model.Ride;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RideOverviewDetailsFragment extends DetailsFragment {

    private TextView tveStartLocation, tveStopLocation, tveStartTime, tveStopTime, tveDate, tveLocationCharge, tveTimeCharge, tveDistance, tveCosts;
    private RideViewData rideViewData;
    private Double defaultCharge;
    private Integer distance;

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

        distance = -1;

        rideViewData = new RideViewData(getActivity());

        return view;
    }

    public void onStart(){
        super.onStart();

        if (getArguments() != null) {
            Ride ride = (Ride) getArguments().getSerializable("Item");
            if (ride != null) {
                tveStartLocation.setText(getLocationName(ride.getStartlocation()));
                tveStartTime.setText(ride.getStarttime().toString());
                tveDate.setText(ride.getDate().toString());
                rideViewData = new RideViewData(getActivity());
                rideViewData.loadDistance(ride);
                rideViewData.loadCharge(ride);
                if(ride.getStoptime() != null && ride.getStoplocation() != null){
                    tveStopTime.setText(ride.getStoptime().toString());
                    tveStopLocation.setText(getLocationName(ride.getStoplocation()));
                }
            }
        }
    }

    public void onStop(){
        super.onStop();
        tveStartLocation.setText("");
        tveStopLocation.setText("");
        tveStartTime.setText("");
        tveStopTime.setText("");
        tveDate.setText("");
        tveLocationCharge.setText("");
        tveTimeCharge.setText("");
        tveCosts.setText("");
        tveDistance.setText("");
        defaultCharge = 0.0;
        distance = -1;
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

    private String getLocationName(@NonNull String coordinate){
        Geocoder geocoder = new Geocoder(getActivity());
        try {
            String[] coordinates = coordinate.split(getActivity().getString(R.string.coordinateSplitCharacter), 2);
            List<Address> list = geocoder.getFromLocation(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]), 1);
            return list.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
