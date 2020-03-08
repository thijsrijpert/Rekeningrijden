package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thijsrijpert.rekeningrijden.Model.Ride;
import com.thijsrijpert.rekeningrijden.R;

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


        Ride[] rides = new Ride[]{};
        recyclerView.setAdapter(new RideOverviewAdapter(rides));

        return view;
    }
}
