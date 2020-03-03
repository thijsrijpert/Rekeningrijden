package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.thijsrijpert.rekeningrijden.Model.Ride;
import com.thijsrijpert.rekeningrijden.R;

import java.util.List;

public class RideYearTotalAdapter extends RecyclerView.Adapter<RideYearTotalAdapter.ViewHolder> {

    private final List<Ride> rides;

    public RideYearTotalAdapter(List<Ride> items) {
        rides = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_ride_year_total, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
       holder.bind(rides.get(position));
    }

    @Override
    public int getItemCount() {
        return rides.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView date;
        private final TextView price;

        private ViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.tvRideYearTotalDate);
            price = (TextView) view.findViewById(R.id.tvRideYearTotalPrice);
        }

        private void bind(Ride ride){
            date.setText("1-2-2020");
            price.setText("2.00");
        }


    }
}
