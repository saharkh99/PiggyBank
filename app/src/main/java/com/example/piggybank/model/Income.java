package com.example.piggybank.model;

import android.graphics.drawable.Drawable;

public class Income extends Transaction {
    public Income(double amount, Drawable type) {
        super(amount, type);
    }

    public void addMoney(double total, double amount){
        total+=amount;
    }
}
