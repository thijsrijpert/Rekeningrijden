package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.thijsrijpert.rekeningrijden.Model.Ride;

import java.util.List;

public abstract class ListAdapter<T extends ViewHolder, E> extends Adapter<T> {

    protected OnItemClickListener listener;
    protected List<E> data;

    public interface OnItemClickListener{
        void onItemClick(Ride ride);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<E> data){
        this.data = data;
        this.notifyDataSetChanged();
    }

}
