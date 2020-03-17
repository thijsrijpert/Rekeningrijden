package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.thijsrijpert.rekeningrijden.Controller.ViewData.RideViewData;
import com.thijsrijpert.rekeningrijden.R;

public class RideSearchFragment extends Fragment {


    public RideSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Button button = view.findViewById(R.id.btnSearch);

        button.setOnClickListener((event) ->{
            RideViewData rideViewData = new RideViewData();

        });
        return view;
    }

}
