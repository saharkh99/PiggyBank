package com.example.piggybank.model;

import android.graphics.drawable.Drawable;

public class Transaction {

   private int id;
   private double amount;
   private Drawable type;

   public Transaction(double amount,Drawable type){
       this.type=type;
       this.amount=amount;
   }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Drawable getType() {
        return type;
    }

    public void setType(Drawable type) {
        this.type = type;
    }
}
