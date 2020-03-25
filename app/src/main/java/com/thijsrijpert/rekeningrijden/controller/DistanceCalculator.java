package com.thijsrijpert.rekeningrijden.controller;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thijsrijpert.rekeningrijden.controller.viewdata.RideViewData;
import com.thijsrijpert.rekeningrijden.model.Ride;

public class DistanceCalculator {

    private final RequestQueue queue;
    private final JsonParser jsonParser;
    private final Activity activity;

    public DistanceCalculator(Activity activity){
        queue = Volley.newRequestQueue(activity);
        jsonParser = new JsonParser();
        this.activity = activity;
    }

    public void calculate(Ride ride, RideViewData viewData)  {

        try {
            if(ride.getStoplocation() == null){
                throw new NullPointerException();
            }
            double[] startRides = locationToDouble(ride.getStartlocation());
            double[] stopRides = locationToDouble(ride.getStoplocation());
            String request = "https://api.openrouteservice.org/v2/directions/driving-car?" +
                    "api_key=5b3ce3597851110001cf6248f4d3481584954bbea3872b0cd0f4afb8" +
                    "&start=" + startRides[1] + "," + startRides[0] +
                    "&end=" + stopRides[1] + "," + stopRides[0];

            StringRequest stringRequest = new StringRequest(Request.Method.GET, request, response -> {
                try {
                    System.out.println(response);
                    JsonObject json = jsonParser.parse(response).getAsJsonObject();
                    JsonObject features = json.getAsJsonArray("features").get(0).getAsJsonObject();
                    JsonObject properties = features.getAsJsonObject("properties");
                    JsonObject segments = properties.getAsJsonArray("segments").get(0).getAsJsonObject();
                    viewData.setDistance(segments.get("distance").getAsInt());
                    viewData.loadDistance(ride, activity);
                }catch(NullPointerException e){
                    viewData.setDistance(-1);
                    viewData.loadDistance(ride, activity);
                    e.printStackTrace();
                }
            }, error -> {
                viewData.setDistance(-1);
                viewData.loadDistance(ride, activity);
                System.out.println(error.toString());
            });
            queue.add(stringRequest);
        }catch(NullPointerException e){
            viewData.setDistance(-1);
            viewData.loadDistance(ride, activity);
            e.printStackTrace();
        }

    }

    public double[] locationToDouble(String location){
        String[] cCoordinates = location.split(";",2);
        return new double[]{
                Double.parseDouble(cCoordinates[0]),
                Double.parseDouble(cCoordinates[1])
        };
    }
}
