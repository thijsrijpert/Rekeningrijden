package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController;

import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thijsrijpert.rekeningrijden.Model.Ride;
import com.thijsrijpert.rekeningrijden.R;

import java.io.IOException;
import java.util.List;

public class RideOverviewAdapter extends RecyclerView.Adapter<RideOverviewAdapter.RideViewHolder> {
    private List<Ride> rides;

    public RideOverviewAdapter(List<Ride> rides){
        this.rides = rides;
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
        holder.bind(rides.get(position));
    }

    @Override
    public int getItemCount() { return rides.size(); }

    class RideViewHolder extends RecyclerView.ViewHolder{

        TextView startLocation;
        TextView stopLocation;
        TextView date;
        TextView price;

        RideViewHolder(@NonNull View itemView) {
            super(itemView);
            startLocation = itemView.findViewById(R.id.tvStartLocation);
            stopLocation = itemView.findViewById(R.id.tvStopLocation);
            date = itemView.findViewById(R.id.tvDate);
            price = itemView.findViewById(R.id.tvPrice);
        }

        void bind(Ride ride){
            if(ride.getStoplocation() != null){
                startLocation.setText(getLocationName(ride.getStartlocation()));
                stopLocation.setText(getLocationName(ride.getStoplocation()));
                date.setText(ride.getDate().toString());
                price.setText("2");
            }
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
