package com.example.piggybank;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.piggybank.Network.SaveReport;
import com.example.piggybank.dao.AppDataBase;
import com.example.piggybank.dao.LastItemsDAO;
import com.example.piggybank.model.MonthlyReport;

public class HomeFragment extends Fragment {
    View view;
    TextView balance,income,expense,month;
    RecyclerView  lastExpenseRecycle,lastIncomeRecycle,lastReminderRecycle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        findView();
        initReportHome();
        return view;
    }

    private void initReportHome() {

        boolean bool=SaveReport.getReport("esf", getActivity());
        if(bool) {
            AppDataBase database = Room.databaseBuilder(getActivity(), AppDataBase.class, "mydb1")
                    .allowMainThreadQueries()
                    .build();
            LastItemsDAO itemDAO = database.getItemDAO();
            MonthlyReport monthlyReport = itemDAO.getReport();
            month.setText(monthlyReport.getMonth());
            balance.setText(String.valueOf(monthlyReport.getBalance()));
            income.setText(String.valueOf(monthlyReport.getTotalIncome()));
            expense.setText(String.valueOf(monthlyReport.getTotalExpense()));
        }
    }

    private void findView() {
        lastExpenseRecycle=view.findViewById(R.id.last_expenses);
        lastIncomeRecycle=view.findViewById(R.id.last_incomes);
        lastReminderRecycle=view.findViewById(R.id.last_reminders);
        balance=view.findViewById(R.id.balance);
        income=view.findViewById(R.id.income);
        expense=view.findViewById(R.id.expense);
        month=view.findViewById(R.id.month);
    }
}
