package com.example.piggybank.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.piggybank.R;
import com.example.piggybank.databinding.ReportFragmentBinding;
import com.example.piggybank.Util.Types;
import com.example.piggybank.viewmodel.FragmentFactory;
import com.example.piggybank.viewmodel.ReportFragmentViewModel;
import com.github.mikephil.charting.charts.PieChart;

public class ReportFragment extends Fragment {
    private static ReportFragmentViewModel viewModel;
    private ReportFragmentBinding binding;
    private PieChart chartView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater
                , R.layout.report_fragment, container, false);
        viewModel = ViewModelProviders.of(this,
                new FragmentFactory(getActivity().getApplication()))
                .get(ReportFragmentViewModel.class);
        setChart();
        setMonthCart(Types.getDate(false));
        setDayChart(Types.getDate(true));
        binding.setClickHandler(new AddAndEditReportClickHandlers(this.getView()));

        return binding.getRoot();
    }

    private void setDayChart(String date) {
        TextView day,expense,income;
        day=binding.reportDate;
        expense=binding.reportMostCost;
        income=binding.reportDailyIncome;

        if (viewModel.getItemsDaily(date) != null) {
           viewModel.getItemsDaily(date).observe(getActivity(), transactions -> {
               int max=0;
               if(transactions.size()!=0)max=(int) transactions.get(0).getAmount();
               for(int i=0;i<transactions.size();i++){
                   if(transactions.get(i).getAmount()>max){
                       max= (int) transactions.get(i).getAmount();
                   }
               }
               day.setText(date);
               expense.setText("بیشترین هزینه :"+max);
               income.setText("");
           });
        }
    }

    private void setMonthCart(String months) {
        TextView month,expense,income;
        month=binding.reportMonth;
        expense=binding.reportCost;
        income=binding.reportIncome;

        if (viewModel.getReportMonthly(months) != null) {
            viewModel.getReportMonthly(months).observe(getActivity(), monthlyReport -> {
                month.setText("ماه :" + months);
                expense.setText("هزینه کل :" + (int)monthlyReport.getTotalExpense());
                income.setText("درامد :" + (int)monthlyReport.getTotalIncome());
            });
        }
    }

    private void setChart() {
        chartView = binding.monthlyPieChart;
        viewModel.getItemsMonthly(Types.getDate(false)).observe(getActivity(), transactions -> viewModel.getPieChart(chartView, transactions));

    }
    public class AddAndEditReportClickHandlers {
        View context;

        public AddAndEditReportClickHandlers(View context) {
            this.context = context;
            binding.setClickHandler(this);
        }


        public void onButtonClicked(View view) {
            ListItems listItems=new ListItems();
            listItems.setTargetFragment(ReportFragment.this, 1);
            listItems.show(getFragmentManager(), "list_show");
        }
    }
}
