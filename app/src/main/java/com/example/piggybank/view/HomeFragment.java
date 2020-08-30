package com.example.piggybank.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piggybank.R;
import com.example.piggybank.adapter.ItemAdapter;
import com.example.piggybank.databinding.HomeFragmentBinding;
import com.example.piggybank.Util.Utilities;
import com.example.piggybank.viewmodel.FragmentFactory;
import com.example.piggybank.viewmodel.HomeFragmentViewModel;

public class HomeFragment extends Fragment {
    private static HomeFragmentViewModel viewModel;
    private HomeFragmentBinding binding;
    static TextView balance, income, expense, month;
    private RecyclerView eRecyclerView, iRecyclerView;
    static ItemAdapter itemAdapter, itemAdapter1;
    private CardView reminder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater
                , R.layout.home_fragment, container, false);
        viewModel = ViewModelProviders.of(this,
                new FragmentFactory(getActivity().getApplication()))
                .get(HomeFragmentViewModel.class);
        month = binding.month;
        expense = binding.expense;
        income = binding.income;
        balance = binding.balance;
        eRecyclerView = binding.lastExpenses;
        iRecyclerView = binding.lastIncomes;
        reminder = binding.addReminder;
        binding.setClickHandler(new AddAndEditTaskClickHandlers(this.getView()));

        initReportHome(Utilities.getDate(false));
        setRecyclerViewCostsList();
        setRecyclerViewIncomesList();
        return binding.getRoot();
    }


    private void setRecyclerViewCostsList() {

        if (viewModel.getLastCost() != null) {
            viewModel.getLastCost().observe(getActivity(), transactions -> {
                itemAdapter = new ItemAdapter(getActivity(), transactions, false);
                eRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                eRecyclerView.setAdapter(itemAdapter);
            });
        } else {
            TextView textView = new TextView(getContext());
            textView.setText("هزینه ای وجود ندارد");
        }
    }

    private void setRecyclerViewIncomesList() {

        if (viewModel.getLastIncome() != null) {
            viewModel.getLastIncome().observe(getActivity(), transactions -> {
                itemAdapter1 = new ItemAdapter(getActivity(), transactions, false);
                iRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                iRecyclerView.setAdapter(itemAdapter1);
            });
        } else {
            TextView textView = new TextView(getContext());
            textView.setText("درامدی ای وجود ندارد");
        }
    }

    private void initReportHome(String months) {
        if (viewModel.getReportMonthly(months) != null) {
            viewModel.getReportMonthly(months).observe(getActivity(), monthlyReport -> {
                month.setText("ماه :" + months);
                int balanceInt = (int) monthlyReport.getBalance();
                if (balanceInt < 0) {
                    balance.setText("مانده :" + "-" + Math.abs(balanceInt));
                } else
                    balance.setText("مانده :" + Math.abs(balanceInt));

                expense.setText("هزینه کل :" + (int) monthlyReport.getTotalExpense());
                income.setText("درامد :" + (int) monthlyReport.getTotalIncome());
            });
        } else {
            month.setText("ماه :" + months);
            balance.setText("مانده :" + "0 تومان");
            expense.setText("هزینه کل :" + "0 تومان");
            income.setText("درامد :" + "0تومان");
        }
    }

    public class AddAndEditTaskClickHandlers {
        private View context;

        public AddAndEditTaskClickHandlers(View context) {
            this.context = context;
            binding.setClickHandler(this);
        }


        public void onAddButtonClicked(View view) {
            ReminderFragment reminderFragment = new ReminderFragment();
            reminderFragment.show(getFragmentManager(), "tasks");

        }
    }

}
