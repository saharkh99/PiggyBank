package com.example.piggybank.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.piggybank.model.MonthlyReport;
import com.example.piggybank.model.Transaction;

@Database(entities = {Transaction.class,MonthlyReport.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {
    public abstract LastItemsDAO getItemDAO();
}