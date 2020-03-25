package com.thijsrijpert.rekeningrijden.controller.view.fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ListFragment extends Fragment {

    protected RecyclerView recyclerView;
    protected ListAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListFragment() {
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public ListAdapter getAdapter(){
        return adapter;
    }



}
