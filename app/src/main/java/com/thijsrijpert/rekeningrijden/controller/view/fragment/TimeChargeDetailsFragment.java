package com.thijsrijpert.rekeningrijden.controller.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.thijsrijpert.rekeningrijden.controller.view.activity.ChargeActivity;
import com.thijsrijpert.rekeningrijden.controller.viewdata.TimeChargeViewData;
import com.thijsrijpert.rekeningrijden.model.DefaultCharge;
import com.thijsrijpert.rekeningrijden.model.TimeCharge;
import com.thijsrijpert.rekeningrijden.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeChargeDetailsFragment extends ChargeDetailsFragment {

    protected EditText etPrimary;
    protected Button btnDelete;

    public TimeChargeDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_charge_details, container, false);
        findIds(view);

        TimeChargeViewData timeChargeViewData = new TimeChargeViewData();
        btnUpdate.setOnClickListener((viewInner)->{
            if(getActivity() != null){
                if(charge != null){
                    timeChargeViewData.endTimeCharge((ChargeActivity)getActivity(), (TimeCharge)charge);
                }
                timeChargeViewData.newTimeCharge((ChargeActivity)getActivity());
            }
        });
        btnDelete.setOnClickListener((viewInner)->{
            if(getActivity() != null){
                timeChargeViewData.endTimeCharge((ChargeActivity)getActivity(), (TimeCharge)charge);
            }
        });

        return view;
    }

    public void onStart(){
        super.onStart();

        if(charge != null){
            etPrimary.setEnabled(false);
        }

        if(getArguments() != null && !(this instanceof LocationChargeDetailsFragment)){
            charge = (DefaultCharge) getArguments().getSerializable("Item");
            if(charge != null){
                etPrimary.setText(((TimeCharge)charge).getTime().toString());
                etPrice.setText(String.format(Locale.US, "%f", charge.getPrice()));
            }else{
                btnDelete.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void onStop(){
        super.onStop();

        etPrimary.setText("");
        etPrice.setText("");
        etPrimary.setEnabled(true);
        btnDelete.setVisibility(View.VISIBLE);
        charge = null;
    }


    public void findIds(View view){
        super.findIds(view);
        etPrimary = view.findViewById(R.id.etChargePrimary);
        btnDelete = view.findViewById(R.id.btnChargeDelete);
    }


    public EditText getEtPrimary() {
        return etPrimary;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }
}
