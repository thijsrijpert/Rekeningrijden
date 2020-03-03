package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thijsrijpert.rekeningrijden.Model.LocationCharge;
import com.thijsrijpert.rekeningrijden.R;


public class LocationChargeAdapter extends RecyclerView.Adapter<LocationChargeAdapter.LocationChargeViewHolder> {

    private LocationCharge[] charges;

    public LocationChargeAdapter(LocationCharge[] charges) {
        this.charges = charges;
    }

    @NonNull
    @Override
    public LocationChargeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_time_charge, parent, false);
        return new LocationChargeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationChargeAdapter.LocationChargeViewHolder holder, int position) {
        holder.bind(charges[position]);
    }

    @Override
    public int getItemCount() {
        return charges.length;
    }

    public class LocationChargeViewHolder extends RecyclerView.ViewHolder {
        private final TextView chargeLocation;
        private final TextView chargeLocationPrice;

        public LocationChargeViewHolder(View view) {
            super(view);
            chargeLocation = view.findViewById(R.id.tvChargeTime);
            chargeLocationPrice =  view.findViewById(R.id.tvChargeTimePrice);
        }

        public void bind(LocationCharge charge) {
            chargeLocation.setText(String.format("%f",charge.getLocation()));
            chargeLocationPrice.setText(String.format("%f",charge.getPrice()));
        }
    }
}
