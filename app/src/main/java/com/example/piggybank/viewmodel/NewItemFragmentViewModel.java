package com.example.piggybank.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.piggybank.Network.BaseApiService;
import com.example.piggybank.Network.UtilsApi;
import com.example.piggybank.model.MonthlyReport;
import com.example.piggybank.model.Repository;

public class NewItemFragmentViewModel extends ViewModel {
    private Repository repository;
    private BaseApiService mApiService;


    public NewItemFragmentViewModel(@NonNull Application application) {
        super();
        repository = new Repository(application);
        mApiService = UtilsApi.getAPIService();
    }

    public MutableLiveData<Boolean> saveCost(double amount, int color, int type, String idAccount, String dates) {

        return repository.saveItems(mApiService.saveCost(amount, color, type, idAccount, dates));

    }
    public MutableLiveData<MonthlyReport> getReportMonthly(String month) {
        return repository.getReportMonthly(month);
    }

    public MutableLiveData<Boolean> saveIncome(double amount, int color, int type, String idAccount, String dates) {

        return repository.saveItems(mApiService.saveIncome(amount, color, type, idAccount, dates));

    }

}
