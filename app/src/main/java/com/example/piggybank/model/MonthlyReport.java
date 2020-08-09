package com.example.piggybank.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MonthlyReport")
public class MonthlyReport {
    @NonNull
    @PrimaryKey
    private String month;
    private double balance;
    private double TotalExpense;
    private double TotalIncome;
    private String idAccount;

    @NonNull
    public String getMonth() {
        return month;
    }

    public void setMonth(@NonNull String month) {
        this.month = month;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTotalExpense() {
        return TotalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        TotalExpense = totalExpense;
    }

    public double getTotalIncome() {
        return TotalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        TotalIncome = totalIncome;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }
}