package com.example.piggybank.model;

public class Transaction {

   private int id;
   private double amount;
   private String type;

   public Transaction(double amount,String type){
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
