package com.example.piggybank.model;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.PrimaryKey;
//changed

public class Task extends BaseObservable {
    @PrimaryKey
    @NonNull
    private int idTask;
    private double amountTask;
    private String titleTask;
    private String datesTask;

    @Bindable
    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
        notifyPropertyChanged(BR.idTask);
    }

    @Bindable
    public double getAmountTask() {
        return amountTask;
    }

    public void setAmountTask(double amountTask) {
        this.amountTask = amountTask;
        notifyPropertyChanged(BR.amountTask);

    }

    @Bindable
    public String getTitleTask() {
        return titleTask;
    }

    public void setTitleTask(String titleTask) {
        this.titleTask = titleTask;
        notifyPropertyChanged(BR.titleTask);

    }

    @Bindable
    public String getDatesTask() {
        return datesTask;
    }

    public void setDatesTask(String datesTask) {
        this.datesTask = datesTask;
        notifyPropertyChanged(BR.datesTask);

    }
}
