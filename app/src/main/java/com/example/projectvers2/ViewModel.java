package com.example.projectvers2;

import android.util.Log;

public class ViewModel extends androidx.lifecycle.ViewModel {

    private String timeFrame; //day, week, or month (d, w, m)
    private int limitAmount; // the amount the user has set for the limit
    private double spentAmount; // the amount the user has spent in the given timeframe


    public ViewModel (){
        Log.i("ViewModel", "ViewModel is created");

    }

    public void setTimeFrame(String tf){
        this.timeFrame = tf;
    }
    public String getTimeFrame(){
        return this.timeFrame;
    }

    public void setLimitAmount(int amount){
        this.limitAmount = amount;
    }
    public int getLimitAmount(){
        return this.limitAmount;
    }

    public void setSpentAmount(double spentAmount) {
        this.spentAmount = spentAmount;
    }

    public double getSpentAmount() {
        return spentAmount;
    }
    //override the on destroy method to save data before it is destroyed
}
