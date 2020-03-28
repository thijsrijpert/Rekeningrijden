package com.thijsrijpert.rekeningrijden.controller.view.fragment;

import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thijsrijpert.rekeningrijden.model.Ride;
import com.thijsrijpert.rekeningrijden.R;

import java.io.IOException;
import java.util.List;

public class RideOverviewAdapter extends ListAdapter<RideOverviewAdapter.RideViewHolder, Ride> {

    public RideOverviewAdapter(List<Ride> rides, ContextProvider contextProvider){
        super(rides, contextProvider);
    }

    @NonNull
    @Override
    public RideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_ride, parent, false);
        return new RideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RideViewHolder holder, int position) {
        holder.bind(data.get(position),listener);
    }

    @Override
    public int getItemCount() { return data.size(); }

    class RideViewHolder extends RecyclerView.ViewHolder{

        TextView startLocation;
        TextView stopLocation;
        TextView date;
        TextView price;
        View itemView;

        RideViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            startLocation = itemView.findViewById(R.id.tvStartLocation);
            stopLocation = itemView.findViewById(R.id.tvStopLocation);
            date = itemView.findViewById(R.id.tvDate);
            price = itemView.findViewById(R.id.tvPrice);
        }

        void bind(Ride ride, OnItemClickListener listener){
            if(ride.getStoplocation() != null){
                startLocation.setText(getLocationName(ride.getStartlocation()));
                stopLocation.setText(getLocationName(ride.getStoplocation()));
                date.setText(ride.getDate().toString());
                price.setText("2");
            }
            itemView.setOnClickListener((view) -> listener.onItemClick(ride));

        }

        private String getLocationName(@NonNull String coordinate){
            Geocoder geocoder = new Geocoder(itemView.getContext());
            try {
                String[] coordinates = coordinate.split(itemView.getContext().getString(R.string.coordinateSplitCharacter), 2);
                List<Address> list = geocoder.getFromLocation(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]), 1);
                return list.get(0).getLocality();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
