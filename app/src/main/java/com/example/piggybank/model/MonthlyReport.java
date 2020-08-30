package com.example.piggybank.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;

import static androidx.room.ForeignKey.CASCADE;

//changed
@Entity(tableName = "MonthlyReport", foreignKeys = @ForeignKey(entity = Account.class,
        parentColumns = "id", childColumns = "idAccount", onDelete = CASCADE))
public class MonthlyReport extends BaseObservable implements Parcelable {

    @NonNull
    @PrimaryKey
    private String month;
    private double balance;
    private double totalExpense;
    private double totalIncome;
    private String idAccount;

    public MonthlyReport() {
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(@NonNull String month) {
        this.month = month;

    }

    @Bindable
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
        notifyPropertyChanged(BR.balance);
    }

    @Bindable
    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
        notifyPropertyChanged(BR.totalExpense);

    }

    @Bindable
    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
        notifyPropertyChanged(BR.totalIncome);

    }

    @Bindable
    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
        notifyPropertyChanged(BR.idAccount);

    }

    @Override
    public int hashCode() {
        return Objects.hash(getBalance(), getMonth(), getTotalExpense(), getTotalIncome());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(balance);
        parcel.writeValue(totalExpense);
        parcel.writeValue(totalIncome);
        parcel.writeValue(month);

    }

    public final static Parcelable.Creator<MonthlyReport> CREATOR = new Creator<MonthlyReport>() {

        @SuppressWarnings({
                "unchecked"
        })
        public MonthlyReport createFromParcel(Parcel in) {
            return new MonthlyReport(in);
        }

        public MonthlyReport[] newArray(int size) {
            return (new MonthlyReport[size]);
        }

    };

    protected MonthlyReport(Parcel in) {
        this.balance = ((Double) in.readValue((Double.class.getClassLoader())));
        this.month = ((String) in.readValue((String.class.getClassLoader())));
        this.totalExpense = ((Double) in.readValue((Double.class.getClassLoader())));
        this.totalIncome = ((Double) in.readValue((Double.class.getClassLoader())));

    }


}