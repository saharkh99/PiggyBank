package com.example.piggybank;

public class Income extends Transaction{
    public Income(double amount, String type) {
        super(amount, type);
    }

    public void addMoney(double total, double amount){
        total+=amount;
    }
}
