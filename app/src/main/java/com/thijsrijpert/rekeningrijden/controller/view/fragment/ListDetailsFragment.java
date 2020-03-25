package com.thijsrijpert.rekeningrijden.controller.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.thijsrijpert.rekeningrijden.R;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListDetailsFragment<T extends Serializable> extends Fragment implements ListAdapter.OnItemClickListener<T> {

    private DetailsFragment detailsFragment;
    private ListFragment listFragment;
    private int id;


    public ListDetailsFragment() {
        // Required empty public constructor
    }

    public ListDetailsFragment(ListFragment listFragment, DetailsFragment detailsFragment) {
        this.listFragment = listFragment;
        this.detailsFragment = detailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_list_details, container, false);
        FrameLayout fragmentHolder = view.findViewById(R.id.listDetailsFragmentHolder);
        if(fragmentHolder != null){
            id = View.generateViewId();
            fragmentHolder.setId(id);
        }

        listFragment.getAdapter().setListener(this);

        return view;
    }

    public void onStart(){
        super.onStart();
        if (getFragmentManager() != null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(id, listFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            if(getActivity() != null){
                getActivity().invalidateOptionsMenu();
            }
        }else{
            System.out.println("Error FragmentManager not found");
        }
    }


    public void onPause(){
        super.onPause();
        if(getFragmentManager() != null && !listFragment.isAdded()){
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(id, listFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            if(getActivity() != null){
                getActivity().invalidateOptionsMenu();
            }
        }
    }


    @Override
    public void onItemClick(T modelObject) {
        if(getFragmentManager() != null){
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(id, detailsFragment, "listToDetails");
            transaction.addToBackStack("listToDetails");
            Bundle bundle = new Bundle();
            bundle.putSerializable("Item", modelObject);
            detailsFragment.setArguments(bundle);
            transaction.commit();
            if(getActivity() != null){
                getActivity().invalidateOptionsMenu();
            }
        }
    }

    public void showListView(){
        if(getFragmentManager() != null){
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(id, listFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            if(getActivity() != null){
                getActivity().invalidateOptionsMenu();
            }
        }
    }

    public Fragment getDetailsFragment() {
        return detailsFragment;
    }

    public ListFragment getListFragment() {
        return listFragment;
    }
}
