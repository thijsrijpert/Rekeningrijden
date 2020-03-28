package com.thijsrijpert.rekeningrijden.model;

import java.util.ArrayList;

public class Colors {

    private ArrayList<String> colors;

    public Colors(){
        colors = new ArrayList<>();
        colors.add("Grijs");
        colors.add("Zwart");
        colors.add("Blauw");
        colors.add("Wit");
        colors.add("Rood");
        colors.add("Groen");
        colors.add("Bruin");
        colors.add("Beige");
        colors.add("Geel");
        colors.add("Oranje");
        colors.add("Paars");
        colors.add("Creme");
        colors.add("Rose");
    }

    public ArrayList<String> getColors() {
        return colors;
    }
}
