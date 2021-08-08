package com.example.finalassignment.model;

public class UserDetails {
    String First_Name, Last_Name, Amount,Pin;
    int Phone;


    public UserDetails()
    {

    }

    public String getFirst_Name() { return First_Name; }
    public void setLast_Name(String last_Name) { Last_Name = last_Name; }



    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }


    public int getDate() {
        return Phone;
    }

    public void setDate(int phone) {
        Phone = phone;
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String pin) {
        Pin = pin;
    }
}

