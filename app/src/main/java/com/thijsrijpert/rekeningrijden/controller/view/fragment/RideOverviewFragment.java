package com.thijsrijpert.rekeningrijden.controller.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thijsrijpert.rekeningrijden.model.Ride;
import com.thijsrijpert.rekeningrijden.R;

import java.util.ArrayList;

public class RideOverviewFragment extends ListFragment {

    public RideOverviewFragment(){
        ArrayList<Ride> rides = new ArrayList<>();
        adapter = new RideOverviewAdapter(rides, this::getActivity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ride_list, parent, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        recyclerView.setAdapter(adapter);

        return view;
    }



}
