package com.example.piggybank;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piggybank.Network.LoadItems;
import com.example.piggybank.Network.LoadReport;
import com.example.piggybank.adapter.ItemAdapter;
import com.example.piggybank.model.Transaction;
import java.util.List;
import io.reactivex.disposables.Disposable;

public class HomeFragment extends Fragment {
    View view;
    TextView balance, income, expense, month;
    RecyclerView lastExpenseRecycle, lastIncomeRecycle, lastReminderRecycle;
    Disposable disposable1;
    RecyclerView eRecyclerView, iRecyclerView;
    ItemAdapter itemAdapter;
    List<Transaction> transaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        findView();
        initReportHome();
        setRecyclerView();
        return view;
    }

    private void setRecyclerView() {
        LoadItems.getItem(getActivity(), true, (result, transactions) -> {
            transaction = transactions;
            if(result) {
                itemAdapter = new ItemAdapter(getActivity(), transaction);
                eRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                eRecyclerView.setAdapter(itemAdapter);
            }
        });
        LoadItems.getItem(getActivity(), false, (result, transactions) -> {
            transaction = transactions;
            if(result) {
                itemAdapter = new ItemAdapter(getActivity(), transaction);
                iRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                iRecyclerView.setAdapter(itemAdapter);
            }
        });
    }

    private void initReportHome() {

        LoadReport.getReport("esf", getActivity(), (result, months, balances, expenses, incomes) -> {
            if (result) {
                month.setText(months + "ماه :");
                balance.setText(balances + "مانده :");
                expense.setText(expenses + "هزینه کل :");
                income.setText(incomes + "درامد :");
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
        eRecyclerView = view.findViewById(R.id.last_expenses);
        iRecyclerView = view.findViewById(R.id.last_incomes);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
      //  disposable1.dispose();
    }


}
