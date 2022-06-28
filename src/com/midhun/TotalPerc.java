package com.midhun;

public class TotalPerc {

    static float total(float phy,float che,float math){
        float tt = phy+che+math;
        return tt;
    }
    static float perc(float tot){
        float percent = tot/3;
        return percent;
    }
}
