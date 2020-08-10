package com.example.piggybank;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.piggybank.Network.SaveReport;
import com.example.piggybank.dao.AppDataBase;
import com.example.piggybank.dao.LastItemsDAO;
import com.example.piggybank.model.MonthlyReport;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment {
    View view;
    TextView balance, income, expense, month;
    RecyclerView lastExpenseRecycle, lastIncomeRecycle, lastReminderRecycle;
    Disposable  disposable1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        findView();
        initReportHome();
        return view;
    }

    private void initReportHome() {

        SaveReport.getReport("esf", getActivity(), (result, months, balances, expenses, incomes) -> {
            Log.d("omadam", "omadam");
            if(result){
                Log.d("omadam", "omadam");
                month.setText(months+"ماه :");
                balance.setText(balances+"مانده :");
                expense.setText(expenses+"هزینه کل :");
                income.setText(incomes+"درامد :");
            }
        });
    }

    private void findView() {
        lastExpenseRecycle = view.findViewById(R.id.last_expenses);
        lastIncomeRecycle = view.findViewById(R.id.last_incomes);
        lastReminderRecycle = view.findViewById(R.id.last_reminders);
        balance = view.findViewById(R.id.balance);
        income = view.findViewById(R.id.income);
        expense = view.findViewById(R.id.expense);
        month = view.findViewById(R.id.month);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable1.dispose();
    }
}
