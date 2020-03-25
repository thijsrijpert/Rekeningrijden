package com.thijsrijpert.rekeningrijden.model;

public class TimeCharges extends Charges<TimeCharge> {

    public Integer hasPrimary(TimeCharge c){
        int left = 0;
        int middle = -1;
        int right = charges.size();
        while(middle != left && middle != right){
            middle = left + (right - left)/2;
            TimeCharge t = charges.get(middle);
            if(t.getTime().equals(c.getTime())){
                return middle;
            }else{
                if(t.getTime().toSecondOfDay() > t.getTime().toSecondOfDay()) {
                    left = middle;
                }else{
                    right = middle;
                }
            }
        }
        return null;
    }

    public Integer getBinaryIndex(TimeCharge c, int goal){
        Integer randomIndex = hasPrimary(c);
        if(randomIndex != null){
            Integer index = randomIndex;
            TimeCharge t = charges.get(index);

            if(goal == 1 && t.equals(c)){
                return index;
            }

            while(t.getTime().equals(c.getTime())) {
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

            while(t.getTime().equals(c.getTime())) {
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
}
