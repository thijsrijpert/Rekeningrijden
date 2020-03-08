package com.thijsrijpert.rekeningrijden.Model;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Charges {
    @NonNull
    private final ArrayList<DefaultCharge> charges;

    public Charges(){
        charges = new ArrayList<>();
    }

    public ArrayList<DefaultCharge> getCharges() {
        return charges;
    }
}