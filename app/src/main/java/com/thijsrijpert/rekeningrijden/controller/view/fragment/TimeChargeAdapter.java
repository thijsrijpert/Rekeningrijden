package com.thijsrijpert.rekeningrijden.controller.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thijsrijpert.rekeningrijden.controller.view.activity.ChargeActivity;
import com.thijsrijpert.rekeningrijden.model.TimeCharge;
import com.thijsrijpert.rekeningrijden.R;

import java.util.List;
import java.util.Locale;


public class TimeChargeAdapter extends ListAdapter<TimeChargeAdapter.TimeChargeViewHolder, TimeCharge> {

    public TimeChargeAdapter(List<TimeCharge> data, ContextProvider contextProvider) {
        super(data, contextProvider);
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
        if(getItemCount() != 0){
            holder.bind(((ChargeActivity)contextProvider.getContext()).getTimeCharges().getCharges().get(position), listener);
        }

    }

    @Override
    public int getItemCount() {
        return ((ChargeActivity)contextProvider.getContext()).getTimeCharges().getCharges().size();
    }

    class TimeChargeViewHolder extends RecyclerView.ViewHolder {
        private final TextView chargeTime;
        private final TextView chargeTimePrice;
        private final View view;

        private TimeChargeViewHolder(View view) {
            super(view);
            this.view = view;
            chargeTime = view.findViewById(R.id.tvChargeTime);
            chargeTimePrice =  view.findViewById(R.id.tvChargeTimePrice);
        }

        private void bind(TimeCharge charge, OnItemClickListener<TimeCharge> listener) {
            if(charge.getEnddate() == null){
                chargeTime.setText(charge.getTime().toString());
                chargeTimePrice.setText(String.format(Locale.getDefault(), "%.2f",charge.getPrice()));

                view.setOnClickListener((view) -> listener.onItemClick(charge));

                LinearLayout.LayoutParams layoutParamsPrice = (LinearLayout.LayoutParams)chargeTimePrice.getLayoutParams();
                layoutParamsPrice.setMargins(16,16,16,16);
                layoutParamsPrice.width = 2000;
                layoutParamsPrice.height = 100;
                LinearLayout.LayoutParams layoutParamsLocation = (LinearLayout.LayoutParams)chargeTime.getLayoutParams();
                layoutParamsLocation.setMargins(0,0,0,0);
                layoutParamsLocation.width = 600;
                layoutParamsLocation.height = 100;
            }else{
                LinearLayout.LayoutParams layoutParamsPrice = (LinearLayout.LayoutParams)chargeTimePrice.getLayoutParams();
                layoutParamsPrice.setMargins(0,0,0,0);
                layoutParamsPrice.width = 0;
                layoutParamsPrice.height = 0;
                LinearLayout.LayoutParams layoutParamsLocation = (LinearLayout.LayoutParams)chargeTime.getLayoutParams();
                layoutParamsLocation.setMargins(0,0,0,0);
                layoutParamsLocation.width = 0;
                layoutParamsLocation.height = 0;
            }
        }
    }
}
