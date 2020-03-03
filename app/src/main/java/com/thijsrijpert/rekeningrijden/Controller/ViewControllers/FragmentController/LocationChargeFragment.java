package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thijsrijpert.rekeningrijden.Model.LocationCharge;
import com.thijsrijpert.rekeningrijden.R;

import java.util.Date;

public class LocationChargeFragment extends Fragment {



    public LocationChargeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_charge_list, container, false);

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new LocationChargeAdapter(new LocationCharge[]{new LocationCharge(12.23, new Date(), new Date(), 1.000)}));
        return view;
    }


}
