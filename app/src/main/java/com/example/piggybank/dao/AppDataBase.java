package com.example.piggybank.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.piggybank.model.MonthlyReport;
import com.example.piggybank.model.Transaction;

@Database(entities = {Transaction.class,MonthlyReport.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {
    public abstract LastItemsDAO getItemDAO();
    private static AppDataBase INSTANCE;
    public static AppDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    AppDataBase.class,
                                    "mydb").allowMainThreadQueries().fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}