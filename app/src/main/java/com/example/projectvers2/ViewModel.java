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
    private Boolean hasData = false;




    public ViewModel (){
        Log.i("ViewModel", "ViewModel is created");

    }

    public ViewModel(int limitAmount, int SpentAmount){
        this.limitAmount = limitAmount;
        this.spentAmount = SpentAmount;
    }


    public boolean hasReachedLimit(){
        if (limitAmount == spentAmount) return true;
        else return false;

    }

    public boolean hasOverspent(){
        if (spentAmount > limitAmount) return  true;
        else return false;
    }

    public double calcOverspend(){
        double over = spentAmount-limitAmount;
        return over;

    }

    public boolean getHasData(){
        return hasData;
    }

    public void setHasDataTrue(){
        this.hasData = true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setEndDate() {


        Log.i("ViewModel", "set date is" + setDate);

        if (timeFrame.equals("day")) {
            endDate = setDate.plus(1, ChronoUnit.DAYS);
            System.out.println("Day after one day: " + endDate);
        }
        if (timeFrame.equals("week")) {
            //Adding one week to the current date
            endDate = setDate.plus(1, ChronoUnit.WEEKS);
            System.out.println("Day after one week: " + endDate);
        }
        if (timeFrame.equals("month")) {
            endDate = setDate.plus(1, ChronoUnit.MONTHS);
            System.out.println("Day after one month: " + endDate);
        }

    }

    public LocalDate getEndDate(){ return endDate;}
    public void endDateSetter(LocalDate date){endDate = date;}

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

    public void setSpentAmount() {
        if (timeFrame.equals("day")) {
            spentAmount = 14;
        }
        if (timeFrame.equals("week")) {
            spentAmount = 38;
        }
        if (timeFrame.equals("month")) {
            spentAmount = 59;
        }


        //this.spentAmount = spentAmount;
        }
    public double getSpentAmount() {
        if (timeFrame.equals("day")) {
            return 14;
        }
        if (timeFrame.equals("week")) {
            return 38;
        }
        if (timeFrame.equals("month")) {
            return 59;
        }
        else return 0;
    }

    public void setSetDate(LocalDate setDate) { this.setDate = setDate; }
    public LocalDate getSetDate() { return setDate; }


    //override the on destroy method to save data before it is destroyed
}
