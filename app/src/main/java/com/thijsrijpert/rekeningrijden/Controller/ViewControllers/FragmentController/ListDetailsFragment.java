package com.thijsrijpert.rekeningrijden.Controller.ViewControllers.FragmentController;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.thijsrijpert.rekeningrijden.Model.Ride;
import com.thijsrijpert.rekeningrijden.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListDetailsFragment extends Fragment implements ListAdapter.OnItemClickListener {

    private Fragment detailsFragment;
    private ListFragment listFragment;


    public ListDetailsFragment() {
        // Required empty public constructor
    }

    public ListDetailsFragment(ListFragment listFragment, Fragment detailsFragment) {
        this.listFragment = listFragment;
        this.detailsFragment = detailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_list_details, container, false);

        return view;
    }

    public void onResume(){
        super.onResume();

        if (getFragmentManager() != null) {
            Fragment fragment = getFragmentManager().findFragmentByTag("listFragment");
            if(fragment == null || !fragment.isVisible()){
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.listDetailsFragmentHolder, listFragment, "listFragment");
                transaction.addToBackStack("listFragment");
                transaction.commit();
            }

        }else{
            System.out.println("Error FragmentManager not found");
        }

        listFragment.getAdapter().setListener(this);
    }

    @Override
    public void onItemClick(Ride ride) {
        if(getFragmentManager() != null){
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.listDetailsFragmentHolder, detailsFragment);
            transaction.addToBackStack(null);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Ride", ride);
            detailsFragment.setArguments(bundle);
            transaction.commit();
        }
    }

    public Fragment getDetailsFragment() {
        return detailsFragment;
    }

    public ListFragment getListFragment() {
        return listFragment;
    }
}
