package com.thijsrijpert.rekeningrijden.controller.view.fragment;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

public abstract class ListAdapter<T extends ViewHolder, E> extends Adapter<T> {

    protected OnItemClickListener listener;
    protected List<E> data;
    protected ContextProvider contextProvider;

    protected ListAdapter(List<E> data, ContextProvider contextProvider){
        this.data = data;
        this.contextProvider = contextProvider;
    }


    public interface OnItemClickListener<E>{
        void onItemClick(E item);
    }
    public interface ContextProvider{
        Activity getContext();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<E> data){
        this.data = data;
        this.notifyDataSetChanged();
    }

}
