package com.example.piggybank.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.piggybank.Network.BaseApiService;
import com.example.piggybank.Network.UtilsApi;
import com.example.piggybank.model.Chart;
import com.example.piggybank.model.MonthlyReport;
import com.example.piggybank.model.Repository;
import com.example.piggybank.model.Transaction;
import com.github.mikephil.charting.charts.PieChart;

import java.util.List;

public class ReportFragmentViewModel extends ViewModel {
    private Chart chart1, chart2;
    private Repository repository;

    private BaseApiService mApiService;

    public ReportFragmentViewModel(@NonNull Application application) {
        super();
        chart1 = new Chart();
        chart2 = new Chart();
        repository = new Repository(application);
        mApiService = UtilsApi.getAPIService();
    }

    public MutableLiveData<List<Transaction>> getItemsMonthly(String month) {
        return repository.getItemsMonthly(mApiService.getItemsMonthly(month));
    }
    public MutableLiveData<List<Transaction>> getItemsDaily(String date) {
        return repository.getItemsMonthly(mApiService.getItemsDaily(date));
    }
    public MutableLiveData<MonthlyReport> getReportMonthly(String month) {
        return repository.getReportMonthly(month);
    }


    public void getPieChart(PieChart anyChartView, List<Transaction> transactions) {

        chart1.pieChartMonthly(anyChartView, transactions);
    }


}
