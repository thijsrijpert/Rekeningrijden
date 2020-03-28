package com.thijsrijpert.rekeningrijden.controller.view.fragment;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.thijsrijpert.rekeningrijden.R;
import com.thijsrijpert.rekeningrijden.controller.viewdata.LocationChargeViewData;
import com.thijsrijpert.rekeningrijden.model.DefaultCharge;
import com.thijsrijpert.rekeningrijden.model.LocationCharge;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class LocationChargeDetailsFragment extends TimeChargeDetailsFragment {

    private EditText etName;

    public LocationChargeDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_location_charge_details, container, false);
        findIds(view);

        etName.setEnabled(false);

        LocationChargeViewData locationChargeViewData = new LocationChargeViewData(getActivity());

        btnUpdate.setOnClickListener(viewInner ->{
            locationChargeViewData.newLocationCharge((LocationCharge)charge);
        });
        btnDelete.setOnClickListener(viewInner -> locationChargeViewData.endLocationCharge((LocationCharge)charge));
        return view;
    }

    public void onStart(){
        super.onStart();
        if(getArguments() != null) {
            charge = (DefaultCharge) getArguments().getSerializable("Item");
            if (charge != null) {
                etPrimary.setText(((LocationCharge) charge).getLocation());
                etPrimary.setEnabled(false);
                etName.setText(getLocationName(((LocationCharge) charge).getLocation()));
                etPrice.setText(String.format(Locale.US, "%f", charge.getPrice()));
            } else {
                btnDelete.setVisibility(View.INVISIBLE);
            }
        }

    }

    public void onStop(){
        super.onStop();

        etName.setText("");
    }


    public void findIds(View view){
        super.findIds(view);
        etName = view.findViewById(R.id.etChargeName);
    }


    public EditText getEtPrimary() {
        return etPrimary;
    }

    public EditText getEtName() {
        return etName;
    }

    public EditText getEtPrice() {
        return etPrice;
    }

    private String getLocationName(@NonNull String coordinate){
        Geocoder geocoder = new Geocoder(this.getContext());
        String[] coordinates = coordinate.split(Objects.requireNonNull(this.getContext()).getString(R.string.coordinateSplitCharacter), 2);
        try {
            List<Address> list = geocoder.getFromLocation(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]), 1);
            return list.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
