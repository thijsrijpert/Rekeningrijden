package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thijsrijpert.rekeningrijden.Model.Car;
import com.thijsrijpert.rekeningrijden.Model.Ride;
import com.thijsrijpert.rekeningrijden.Model.TimeCharge;
import com.thijsrijpert.rekeningrijden.R;

import java.util.Date;

public class RideOverviewFragment extends Fragment {


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RideOverviewFragment() {
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
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        Ride ride1 = new Ride(new Car(), 2.00, 2.00, new TimeCharge(), new TimeCharge(), new Date(System.currentTimeMillis()));
        Ride ride2 = new Ride(new Car(), 3.00, 3.00, new TimeCharge(), new TimeCharge(), new Date(System.currentTimeMillis()));
        Ride ride3 = new Ride(new Car(), 4.00, 4.00, new TimeCharge(), new TimeCharge(), new Date(System.currentTimeMillis()));

        Ride[] rides = new Ride[]{ride1, ride2, ride3};
        recyclerView.setAdapter(new RideOverviewAdapter(rides));

        return view;
    }
}
