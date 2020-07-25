package com.example.piggybank;

public class Expense extends Transaction {

    public Expense(double amount, String type) {
        super(amount, type);
    }

    public void subMoney(double total, double amount){
       total-=amount;
    }
}
