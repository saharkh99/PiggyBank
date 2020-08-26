package com.example.piggybank.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.piggybank.Network.BaseApiService;
import com.example.piggybank.Network.UtilsApi;
import com.example.piggybank.model.Repository;

public class ReminderViewModel extends ViewModel {
    private Repository repository;
    private BaseApiService mApiService;

    public ReminderViewModel(@NonNull Application application) {
        super();
        repository = new Repository(application);
        mApiService = UtilsApi.getAPIService();
    }
}
