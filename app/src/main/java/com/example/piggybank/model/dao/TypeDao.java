package com.example.piggybank.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import com.example.piggybank.model.Type;
//changed
@Dao
public interface TypeDao {
    @Insert
    public void insert(Type... types);
    @Update
    public void update(Type... types);
    @Delete
    public void delete(Type types);
}
