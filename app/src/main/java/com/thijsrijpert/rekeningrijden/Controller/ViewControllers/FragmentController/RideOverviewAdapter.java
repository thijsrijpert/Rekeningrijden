package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thijsrijpert.rekeningrijden.Model.Ride;
import com.thijsrijpert.rekeningrijden.R;

import java.util.Locale;

public class RideOverviewAdapter extends RecyclerView.Adapter<RideOverviewAdapter.RideViewHolder> {
    private Ride[] rides;

    public RideOverviewAdapter(Ride[] rides){ this.rides = rides; }

    @NonNull
    @Override
    public RideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_ride, parent, false);
        return new RideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RideViewHolder holder, int position) {
        holder.bind(rides[position]);
    }

    @Override
    public int getItemCount() { return rides.length; }

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
            startLocation.setText(String.format(Locale.getDefault(),"%f",ride.getStartlocation()));
            stopLocation.setText(String.format(Locale.getDefault(), "%f", ride.getStoplocation()));
            date.setText(ride.getDate().toString());
            price.setText("2");

        }

    }
}
