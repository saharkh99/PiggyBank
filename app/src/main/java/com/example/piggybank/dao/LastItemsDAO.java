package com.example.piggybank.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.piggybank.model.MonthlyReport;
import com.example.piggybank.model.Transaction;

@Dao
public interface LastItemsDAO {
    @Insert
    public void insert(Transaction... items);
    @Insert
    public void insert(MonthlyReport... items);
    @Update
    public void update(Transaction... items);
    @Delete
    public void delete(Transaction items);

    @Query("DELETE FROM items")
    public void nukeTable();

    @Query("DELETE FROM monthlyreport")
    public void nukeMTable();

    @Query("SELECT * FROM items WHERE id = :id")
    public Transaction getItemById(int id);

    @Query("SELECT * FROM MonthlyReport ")
    public MonthlyReport getItemById();

    @Query("SELECT * FROM MonthlyReport")
    public MonthlyReport getReport();
}
