package com.thijsrijpert.rekeningrijden.controller.view.fragment;

import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thijsrijpert.rekeningrijden.R;
import com.thijsrijpert.rekeningrijden.controller.view.activity.ChargeActivity;
import com.thijsrijpert.rekeningrijden.model.LocationCharge;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class LocationChargeAdapter extends ListAdapter<LocationChargeAdapter.LocationChargeViewHolder, LocationCharge> {

    LocationChargeAdapter(List<LocationCharge> data, ContextProvider contextProvider) {
        super(data, contextProvider);
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
        if(getItemCount() != 0){
            holder.bind(((ChargeActivity)contextProvider.getContext()).getLocationCharges().getCharges().get(position), listener);
        }
    }

    @Override
    public int getItemCount() {
        return ((ChargeActivity)contextProvider.getContext()).getLocationCharges().getCharges().size();
    }

    class LocationChargeViewHolder extends RecyclerView.ViewHolder {

        private final TextView chargeLocation;
        private final TextView chargeLocationPrice;

        private LocationChargeViewHolder(View view) {
            super(view);
            chargeLocation = view.findViewById(R.id.tvChargeTime);
            chargeLocationPrice =  view.findViewById(R.id.tvChargeTimePrice);
        }

        private void bind(LocationCharge charge, OnItemClickListener<LocationCharge> listener) {
            if(charge.getEnddate() == null){
                chargeLocation.setText(String.format(Locale.getDefault(), "%s ( %s )",getLocationName(charge.getLocation()), charge.getLocation()));
                chargeLocationPrice.setText(String.format(Locale.US,"%.2f",charge.getPrice()));

                itemView.setOnClickListener((view) ->{
                    listener.onItemClick(charge);
                });
            }else{
                LinearLayout.LayoutParams layoutParamsPrice = (LinearLayout.LayoutParams)chargeLocationPrice.getLayoutParams();
                layoutParamsPrice.setMargins(0,0,0,0);
                layoutParamsPrice.width = 0;
                layoutParamsPrice.height = 0;
                LinearLayout.LayoutParams layoutParamsLocation = (LinearLayout.LayoutParams)chargeLocation.getLayoutParams();
                layoutParamsLocation.setMargins(0,0,0,0);
                layoutParamsLocation.width = 0;
                layoutParamsLocation.height = 0;
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
