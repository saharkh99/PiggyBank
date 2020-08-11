package com.example.piggybank.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.piggybank.model.MonthlyReport;
import com.example.piggybank.model.Transaction;

import io.reactivex.Single;

@Dao
public interface LastItemsDAO {
    @Insert
    public void insert(Transaction... items);
    @Insert
    public void insertReport(MonthlyReport... monthlyReports);
    @Update
    public void update(Transaction... items);
    @Delete
    public void delete(Transaction items);

    @Query("DELETE FROM items")
    public void nukeTable();

    @Query("DELETE FROM MonthlyReport")
    public void nukeMTableReport();

    @Query("SELECT * FROM items WHERE id = :id")
    public Transaction getItemById(int id);

 //   @Query("SELECT * FROM items ")
   // public MonthlyReport getItemById();

    @Query("SELECT * FROM MonthlyReport")
    public Single<MonthlyReport> getReport();
}
