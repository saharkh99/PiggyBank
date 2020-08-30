package com.example.piggybank.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.piggybank.Network.BaseApiService;
import com.example.piggybank.Network.UtilsApi;
import com.example.piggybank.model.Repository;
import com.example.piggybank.model.Transaction;

import java.util.List;

public class ListItemViewModel extends ViewModel {
    private Repository repository;

    private BaseApiService mApiService;

    public ListItemViewModel(@NonNull Application application) {
        super();
        repository = new Repository(application);
        mApiService = UtilsApi.getAPIService();
    }

    public MutableLiveData<List<Transaction>> getItemsMonthly(String month) {
        return repository.getItemsMonthly(mApiService.getItemsMonthly(month));
    }

    public MutableLiveData<Boolean> deleteCost(int id) {
        return repository.deleteItem(mApiService.deleteItem("cost", id));
    }

    public MutableLiveData<Boolean> deleteIncome(int id) {
        return repository.deleteItem(mApiService.deleteItem("income", id));
    }

    public MutableLiveData<Boolean> deleteTask(int id) {
        return repository.deleteItem(mApiService.deleteItem("task", id));
    }


}
