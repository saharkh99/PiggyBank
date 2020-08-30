package com.example.piggybank.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 *  type of transaction with id , name , icon
 */
@Entity(tableName = "types")
public class Type extends BaseObservable {
    @PrimaryKey
    private int idType;
    private int iconType;
    private String nameType;

    @Bindable
    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
        notifyPropertyChanged(BR.idType);
    }

    @Bindable
    public int getIconType() {
        return iconType;
    }


    public void setIconType(int iconType) {
        this.iconType = iconType;
        notifyPropertyChanged(BR.iconType);

    }

    @Bindable
    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
        notifyPropertyChanged(BR.nameType);

    }
}
