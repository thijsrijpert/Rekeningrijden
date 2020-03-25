package com.thijsrijpert.rekeningrijden.controller.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thijsrijpert.rekeningrijden.model.Ride;
import com.thijsrijpert.rekeningrijden.R;

import java.util.ArrayList;
import java.util.List;

public class RideYearTotalFragment extends Fragment {

    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RideYearTotalFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ride_year_total_list, container, false);
        recyclerView = view.findViewById(R.id.listRideYearTotal);

        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        List<Ride> rides = new ArrayList<>();
        recyclerView.setAdapter(new RideYearTotalAdapter(rides));
        return view;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
