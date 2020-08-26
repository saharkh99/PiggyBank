package com.example.piggybank.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.piggybank.model.MonthlyReport;
import com.example.piggybank.model.Transaction;

import java.util.List;

import io.reactivex.Single;
//changed
@Dao
public interface TransactionDao {
    @Insert
    public void insert(Transaction... items);
    @Update
    public void update(Transaction... items);
    @Delete
    public void delete(Transaction items);

    @Query("DELETE FROM items")
    public void nukeTable();


    @Query("SELECT * FROM items WHERE id = :id")
    public Transaction getItemById(int id);

    @Query("SELECT * FROM items ")
    public LiveData<List<Transaction>> getItems();
}
