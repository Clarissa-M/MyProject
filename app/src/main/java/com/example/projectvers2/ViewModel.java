package com.example.projectvers2;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.Date;

public class ViewModel extends androidx.lifecycle.ViewModel {

    private String timeFrame; //day, week, or month (d, w, m)
    private int limitAmount; // the amount the user has set for the limit
    private double spentAmount; // the amount the user has spent in the given timeframe
    private LocalDate setDate;
    private LocalDate endDate;


    public ViewModel (){
        Log.i("ViewModel", "ViewModel is created");

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setEndDate() {


        Log.i("ViewModel", "set date is" + setDate);

        if (timeFrame.equals("day")) {
            endDate = setDate.plus(1, ChronoUnit.DAYS);
            System.out.println("Day after one week: " + endDate);
        }
        if (timeFrame.equals("week")) {
            //Adding one week to the current date
            endDate = setDate.plus(1, ChronoUnit.WEEKS);
            System.out.println("Day after one week: " + endDate);
        }
        if (timeFrame.equals("month")) {
            endDate = setDate.plus(1, ChronoUnit.MONTHS);
            System.out.println("Day after one week: " + endDate);
        }

    }

    public LocalDate getEndDate(){ return endDate;}

    //getters and setters
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

    public void setSpentAmount(double spentAmount) { this.spentAmount = spentAmount; }
    public double getSpentAmount() { return spentAmount; }

    public void setSetDate(LocalDate setDate) { this.setDate = setDate; }
    public LocalDate getSetDate() { return setDate; }


    //override the on destroy method to save data before it is destroyed
}
