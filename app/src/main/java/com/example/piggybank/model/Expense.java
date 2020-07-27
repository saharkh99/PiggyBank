package com.example.piggybank.model;

import com.example.piggybank.model.Transaction;

public class Expense extends Transaction {

    public Expense(double amount, String type) {
        super(amount, type);
    }

    public void subMoney(double total, double amount){
       total-=amount;
    }
}
