package com.example.piggybank.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.piggybank.Network.BaseApiService;
import com.example.piggybank.Network.UtilsApi;
import com.example.piggybank.model.MonthlyReport;
import com.example.piggybank.model.Repository;
import com.example.piggybank.model.Transaction;

import java.util.List;


public class HomeFragmentViewModel extends ViewModel {
    private Repository repository;
    private BaseApiService mApiService;

    public HomeFragmentViewModel(@NonNull Application application) {
        super();
        repository = new Repository(application);
        mApiService = UtilsApi.getAPIService();
    }


    public MutableLiveData<MonthlyReport> getReportMonthly(String month) {
        return repository.getReportMonthly(month);
    }

    public MutableLiveData<List<Transaction>> getLastCost() {
        return repository.getLastCosts(mApiService.getLastCosts(5), "cost");
    }

    public MutableLiveData<List<Transaction>> getLastIncome() {
        return repository.getLastIncome(mApiService.getLastIncomes(5), "income");
    }

}
