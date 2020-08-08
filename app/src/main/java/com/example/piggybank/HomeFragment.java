package com.example.piggybank;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piggybank.Network.InternetConnection;
import com.example.piggybank.Network.LoadItems;

public class HomeFragment extends Fragment {
    View view;
    Button expense,income;
    RecyclerView  lastExpenseRecycle,lastIncomeRecycle,lastReminderRecycle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        findView();
        return view;
    }

    private void findView() {
        lastExpenseRecycle=view.findViewById(R.id.last_expenses);
        lastIncomeRecycle=view.findViewById(R.id.last_incomes);
        lastReminderRecycle=view.findViewById(R.id.last_reminders);

       // expense=view.findViewById(R.id.new_expense);
      //  income=view.findViewById(R.id.new_income);
    }
}
