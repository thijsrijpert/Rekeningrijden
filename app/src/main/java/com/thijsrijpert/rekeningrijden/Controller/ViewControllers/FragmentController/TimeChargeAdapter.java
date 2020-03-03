package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thijsrijpert.rekeningrijden.Model.TimeCharge;
import com.thijsrijpert.rekeningrijden.R;


public class TimeChargeAdapter extends RecyclerView.Adapter<TimeChargeAdapter.TimeChargeViewHolder> {

    private TimeCharge[] charges;

    public TimeChargeAdapter(TimeCharge[] charges) {
        this.charges = charges;
    }

    @NonNull
    @Override
    public TimeChargeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_time_charge, parent, false);
        return new TimeChargeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeChargeAdapter.TimeChargeViewHolder holder, int position) {
        holder.bind(charges[position]);
    }

    @Override
    public int getItemCount() {
        return charges.length;
    }

    public class TimeChargeViewHolder extends RecyclerView.ViewHolder {
        private final TextView chargeTime;
        private final TextView chargeTimePrice;

        public TimeChargeViewHolder(View view) {
            super(view);
            chargeTime = view.findViewById(R.id.tvChargeTime);
            chargeTimePrice =  view.findViewById(R.id.tvChargeTimePrice);
        }

        public void bind(TimeCharge charge) {
            chargeTime.setText(charge.getTime().toString());
            chargeTimePrice.setText(String.format("%f",charge.getPrice()));
        }
    }
}
