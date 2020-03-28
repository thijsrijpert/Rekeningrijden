package com.thijsrijpert.rekeningrijden.model;

import java.util.ArrayList;

public class Types {
    private final ArrayList<String> types;

    public Types(){
        types = new ArrayList<>();
        types.add("Personenauto");
        types.add("Bedrijfsauto");
        types.add("Bromfiets");
        types.add("Motorfiets");
        types.add("Driewieligmoterrijtuig");
        types.add("Bus");
        types.add("Motorfiets met zijspan");
    }

    public ArrayList<String> getTypes() {
        return types;
    }
}
