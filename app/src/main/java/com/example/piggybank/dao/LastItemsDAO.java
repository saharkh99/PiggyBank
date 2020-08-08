package com.example.piggybank.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.piggybank.model.Transaction;

@Dao
public interface LastItemsDAO {
    @Insert
    public void insert(Transaction... items);
    @Update
    public void update(Transaction... items);
    @Delete
    public void delete(Transaction item);

    @Query("DELETE FROM items")
    public void nukeTable();

    @Query("SELECT * FROM items WHERE id = :id")
    public Transaction getItemById(int id);
}
