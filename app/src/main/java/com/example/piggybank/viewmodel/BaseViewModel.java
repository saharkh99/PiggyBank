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

public class BaseViewModel extends ViewModel {
    private Repository repository;
    private BaseApiService mApiService;

    public BaseViewModel(@NonNull Application application) {
        super();
        repository = new Repository(application);
        mApiService = UtilsApi.getAPIService();
    }

    public MutableLiveData<List<Task>> getTask() {
        return repository.getTask(mApiService.getTask(50), "task");
    }

}
