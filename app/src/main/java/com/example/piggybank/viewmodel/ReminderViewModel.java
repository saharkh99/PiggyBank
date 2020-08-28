package com.example.piggybank.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.piggybank.Network.BaseApiService;
import com.example.piggybank.Network.UtilsApi;
import com.example.piggybank.model.Repository;
import com.example.piggybank.model.Task;

import java.util.List;

public class ReminderViewModel extends ViewModel {
    private Repository repository;
    private BaseApiService mApiService;

    public ReminderViewModel(@NonNull Application application) {
        super();
        repository = new Repository(application);
        mApiService = UtilsApi.getAPIService();
    }

    public MutableLiveData<Boolean> saveTask(double amount, String title, String dates) {

        return repository.saveItems(mApiService.saveTask(amount, title, dates));

    }

    public MutableLiveData<List<Task>> getTask() {
        return repository.getTask(mApiService.getTask(5), "task");
    }

}
