package com.example.piggybank.model;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Objects;
//changed
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "items",foreignKeys ={@ForeignKey(entity = Type.class,
        parentColumns = "idType",childColumns = "type",onDelete = CASCADE),@ForeignKey(entity = Account.class,
        parentColumns = "id",childColumns = "idAccount",onDelete = CASCADE)}
        )
public class Transaction extends BaseObservable {
    @PrimaryKey
    @NonNull
    private int id;
    private double amount;
    private int type;
    private int color;
    private String idAccount;
    private String itemType;
    private String dates;

    @Bindable
    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
        notifyPropertyChanged(BR.dates);
    }

    @Bindable
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
        notifyPropertyChanged(BR.itemType);

    }

    @Bindable
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        notifyPropertyChanged(BR.color);

    }

    @Bindable
    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
        notifyPropertyChanged(BR.idAccount);

    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);

    }

    @Bindable
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
        notifyPropertyChanged(BR.amount);

    }

    @Bindable
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        notifyPropertyChanged(BR.type);

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction transaction = (Transaction) o;
        return getId() == transaction.getId() &&
                getIdAccount() == transaction.getIdAccount() &&
                getAmount()==transaction.getAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdAccount(), getId(), getItemType(), getAmount(),getDates(),getType());
    }
}