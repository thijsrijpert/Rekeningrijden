package com.thijsrijpert.rekeningrijden.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public abstract class Charges<T extends DefaultCharge> {
    @NonNull
    protected final ArrayList<T> charges;

    public Charges(){
        charges = new ArrayList<>();
    }

    @NonNull
    public ArrayList<T> getCharges() {
        return charges;
    }

    public void update(T c) throws NullPointerException{
        int index = getBinaryIndex(c, 1);
        charges.set(index, c);
    }

    public void create(T c){
        Integer index = getBinaryIndex(c, 0);
        if(index != null){
            charges.add(index, c);
        }else{
            charges.add(c);
        }


    }

    abstract Integer getBinaryIndex(T c, int goal);

    abstract Integer hasPrimary(T c);

}