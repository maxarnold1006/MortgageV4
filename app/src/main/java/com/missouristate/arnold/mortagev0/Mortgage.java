package com.missouristate.arnold.mortagev0; //Package

import java.text.DecimalFormat; //Import

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Mortgage {
    //Declare CONSTANTS
    public final DecimalFormat MONEY = new DecimalFormat("$#,##0.##");
    private static final String PREFERENCE_AMOUNT = "amount";
    private static final String PREFERENCE_YEARS = "years";
    private static final String PREFERENCE_RATE = "rate";

    //Declare variables
    private float amount;
    private int years;
    private float rate;

    //Instantiate Mortgage from preferences
        public Mortgage(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        setAmount(pref.getFloat(PREFERENCE_AMOUNT, 1000000.0f));
        setYears(pref.getInt(PREFERENCE_YEARS, 30));
        setRate(pref.getFloat(PREFERENCE_RATE, 0.035f));
    }

    //Write mortgage data to preferences
    public void setPreferences(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat(PREFERENCE_AMOUNT, amount);
        editor.putInt(PREFERENCE_YEARS, years);
        editor.putFloat(PREFERENCE_RATE, rate);
        editor.commit();
    }


    //Mutator
    public void setAmount(float newAmount) {
        if(newAmount >= 0)
            amount = newAmount;
    }

    //Mutator
    public void setYears(int newYears) {
        if(newYears >= 0)
            years = newYears;
    }

    //Mutator
    public void setRate(float newRate) {
        if(newRate >= 0)
            rate = newRate;
    }

    //Accessor
    public float getAmount() {
        return amount;
    }

    //Converts value into string
    public String getFormattedAmount() {
        return MONEY.format(amount);
    }

    //Accessor
    public int getYears(){
        return years;
    }


    //Accessor
    public float getRate() {
        return rate;
    }


    public float monthlyPayment(){
        float mRate = rate / 12;
        double temp = Math.pow(1/(1 + mRate), years * 12);
        return amount * mRate / (float) (1 - temp);
    }

    //Converts value into string
    public String formattedMonthlyPayment(){
        return MONEY.format(monthlyPayment());
    }


    //Finds total payment (monthly payment * the amount of years * 12 months for yearly)
    public float totalPayment(){
        return monthlyPayment() * years * 12;
    }

    //Converts value into string
    public String formattedTotalPayment(){
        return MONEY.format(totalPayment());
    }


}


