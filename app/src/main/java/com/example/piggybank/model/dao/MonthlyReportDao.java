package com.example.piggybank.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.piggybank.model.MonthlyReport;
import java.util.List;
//changed
@Dao
public interface MonthlyReportDao {

    @Insert
    public void insertReport(MonthlyReport... monthlyReports);

    @Query("DELETE FROM MonthlyReport")
    public void nukeMTableReport();

    @Query("SELECT * FROM MonthlyReport")
    public LiveData<List<MonthlyReport>> getReport();
}
