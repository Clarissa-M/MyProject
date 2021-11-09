package com.example.projectvers2;
import java.util.*;

public class DataHandler {
    private String timeFrame; //day, week, or month (d, w, m)
    private int limitAmount; // the amount the user has set for the limit
    private int spentAmount; // the amount the user has spent within the timeframe
    private Date curDate; // the current date
    private Date limSetDate; //the date the user set the limit

    public DataHandler(Date lsD, int limAm, int spentAmount, String timeFrame){
        this.timeFrame = timeFrame;
        this.limitAmount = limAm;
        this.spentAmount = spentAmount;
        this.limSetDate = lsD;
        this.curDate = new Date();

    }

    public DataHandler(){
        this.curDate = new Date();
    }

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

    public void setSpentAmount(int amount){
        this.spentAmount = amount;
    }
    public int getSpentAmount(){
        return this.spentAmount;
    }

    public void setLimSetDate(Date d){
        this.limSetDate = d;
    }

    public Date getLimSetDate(){
        return this.limSetDate;
    }





}

