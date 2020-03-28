package com.thijsrijpert.rekeningrijden.model;

public class LocationCharges extends Charges<LocationCharge> {

    public Integer hasPrimary(LocationCharge c){
        int left = 0;
        int middle = -1;
        int right = charges.size();
        double[] cCoordinates = locationToDouble(c.getLocation());
        while(middle != left && middle != right && left < right){
            middle = left + (right - left)/2;
            LocationCharge t = charges.get(middle);
            if(t.getLocation().equals(c.getLocation())){
                return middle;
            }else{
                double[] tCoordinates = locationToDouble(t.getLocation());
                if(tCoordinates[0] == cCoordinates[0]){
                    if(tCoordinates[1] > cCoordinates[1]) {
                        right--;
                    }else {
                        left++;
                    }
                }else if(tCoordinates[0] > cCoordinates[0]) {
                    left = middle;
                }else{
                    right = middle;
                }
            }
        }
        return null;
    }

    public Integer getBinaryIndex(LocationCharge c, int goal){
        Integer randomIndex = hasPrimary(c);
        if(randomIndex != null){
            Integer index = randomIndex;
            LocationCharge t = charges.get(index);

            if(goal == 1 && t.equals(c)){
                return index;
            }

            while(t.getLocation().equals(c.getLocation())) {
                index++;
                t = charges.get(index);
                if(goal == 1 && t.equals(c)) {
                    return index;
                }
            }

            if(goal == 2){
                return index - 1;
            }

            index = randomIndex;

            while(t.getLocation().equals(c.getLocation())) {
                index--;
                t = charges.get(index);
                if(goal == 1 && t.equals(c)) {
                    return index;
                }
            }

            return index + 1;
        }

        return null;
    }

    public double[] locationToDouble(String location){
        String[] cCoordinates = location.split(";",2);
         return new double[]{
                Double.parseDouble(cCoordinates[0]),
                Double.parseDouble(cCoordinates[1])
        };
    }

}
