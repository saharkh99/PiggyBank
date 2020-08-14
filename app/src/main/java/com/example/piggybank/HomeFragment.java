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
    RecyclerView lastReminderRecycle;
    Disposable disposable1;
    RecyclerView eRecyclerView, iRecyclerView;
    static ItemAdapter itemAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        findView();
        initReportHome();
        setRecyclerView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setRecyclerView();
    }


    private  void setRecyclerView() {
        LoadItems.getCosts(getActivity(),  (result, transactions) -> {
            List<Transaction> transaction = transactions;
            if(result) {
                itemAdapter = new ItemAdapter(getActivity(), transaction);
                eRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                eRecyclerView.setAdapter(itemAdapter);
            }
        });
        LoadItems.getIncomes(getActivity(),  (result, transactions) -> {
            List<Transaction> transaction = transactions;
            if(result) {
                itemAdapter = new ItemAdapter(getActivity(), transaction);
                iRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                iRecyclerView.setAdapter(itemAdapter);

            }
        });
    }

    private void initReportHome() {

        LoadReport.getReport("esf", getActivity(), (result, months, balances, incomes, expenses) -> {
            if (result) {
                month.setText( "ماه :"+months );
                balance.setText( "مانده :"+balances );
                expense.setText( "هزینه کل :"+expenses );
                income.setText("درامد :"+incomes);
            }
        });
    }

    private void findView() {
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
