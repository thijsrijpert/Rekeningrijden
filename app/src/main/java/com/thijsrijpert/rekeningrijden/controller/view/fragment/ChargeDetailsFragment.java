package com.thijsrijpert.rekeningrijden.controller.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.thijsrijpert.rekeningrijden.model.DefaultCharge;
import com.thijsrijpert.rekeningrijden.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChargeDetailsFragment extends DetailsFragment {

    protected EditText etPrice;
    protected Button btnUpdate;
    protected DefaultCharge charge;

    public ChargeDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_default_charge_details, container, false);

        return view;
    }

    public void onStop(){
        super.onStop();
        if(getActivity() != null){
            getActivity().invalidateOptionsMenu();
        }
    }

    protected void findIds(View view){
        etPrice = view.findViewById(R.id.etChargePrice);
        btnUpdate = view.findViewById(R.id.btnChargeUpdate);
    }

    public EditText getEtPrice() {
        return etPrice;
    }
}
