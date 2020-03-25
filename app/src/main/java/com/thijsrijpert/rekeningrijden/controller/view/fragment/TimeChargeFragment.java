package com.thijsrijpert.rekeningrijden.controller.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thijsrijpert.rekeningrijden.model.TimeCharge;
import com.thijsrijpert.rekeningrijden.R;

import java.util.ArrayList;

public class TimeChargeFragment extends ListFragment {

    public TimeChargeFragment() {
        ArrayList<TimeCharge> charges = new ArrayList<>();
        adapter = new TimeChargeAdapter(charges, this::getActivity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_charge_list, container, false);

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        return view;
    }


}
