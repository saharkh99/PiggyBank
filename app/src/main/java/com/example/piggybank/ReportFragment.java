package com.example.piggybank;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anychart.AnyChartView;
import com.example.piggybank.Network.LoadReport;
import com.example.piggybank.model.Chart;
import com.example.piggybank.model.Transaction;

import java.util.List;

public class ReportFragment extends Fragment {
    View view;
    AnyChartView chartView,chartView1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.report_fragment, container, false);
        chartView=view.findViewById(R.id.monthly_pie_Chart);
        chartView1=view.findViewById(R.id.monthly_bar_Chart);
        Chart chart=new Chart();
        Chart chart1=new Chart();

        LoadReport.getMonthlyReport(true, "مرداد", getContext(), new LoadReport.getTrans() {
            List<Transaction>transactions;
            @Override
            public void getReport(List<Transaction> transaction) {
               transactions=transaction;
                Log.d("resid", "resid");
                chart.pieChart(getResources(), chartView,transactions);
                chart1.barChart(getResources(), chartView1, transactions);
            }

        });


        return view;
    }
}
