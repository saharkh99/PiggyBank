package com.example.piggybank.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FragmentFactory extends ViewModelProvider.NewInstanceFactory {


        @NonNull
        private final Application application;

        public FragmentFactory(@NonNull Application application) {
            this.application = application;

        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass == HomeFragmentViewModel.class) {
                return (T) new HomeFragmentViewModel(application);
            }
           else if (modelClass == NewItemFragmentViewModel.class) {
                return (T) new NewItemFragmentViewModel(application);
            }
            else if (modelClass == ReportFragmentViewModel.class) {
                return (T) new ReportFragmentViewModel(application);
            }
            else if (modelClass == ListItemViewModel.class) {
                return (T) new ListItemViewModel(application);
            }
            else if (modelClass == ReminderViewModel.class) {
                return (T) new ReminderViewModel(application);
            }
            return null;
        }
    }

