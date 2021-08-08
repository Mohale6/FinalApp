package com.example.finalassignment.model;

public class MoneyTransfer {

    private double balance;
    private double wdValue;

    //set current balance
    public void setBalance(double b) {

        balance = b;

    }//end setBalance


    public double getBalance() {

        return balance - wdValue;


    }


    public void getNewBalance() {}

}


