package com.example.piggybank.model;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;


public class Chart {
    int hospital, beauty, bill, exchange, check, clothes, foods, gas, present, income, internet,transport;
    public void pieChart(Resources resource, AnyChartView anyChartView,List<Transaction>transactionsList){
        List<DataEntry> data = new ArrayList<>();
        for(int i=0;i<transactionsList.size();i++){
            String type=transactionsList.get(i).getType();
            switch (type){
                case "hospital":hospital++;
                case "beauty":beauty++;
                case "bill":bill++;
                case "exchange":exchange++;
                case "check":check++;
                case "clothes":clothes++;
                case "foods":foods++;
                case "gas":gas++;
                case "present":present++;
                case "income":income++;
                case "internet":internet++;
                case "transport":transport++;
            }
        }
        data.add(new ValueDataEntry("پزشکلی", hospital));
        data.add(new ValueDataEntry("زیبایی", beauty));
        data.add(new ValueDataEntry("قبض", bill));
        data.add(new ValueDataEntry("کارت به کارت", exchange));
        data.add(new ValueDataEntry("خرید اینترنتی", check));
        data.add(new ValueDataEntry("لباس", clothes));
        data.add(new ValueDataEntry("غذا", foods));
        data.add(new ValueDataEntry("بنزین", gas));
        data.add(new ValueDataEntry("هدیه", present));
        data.add(new ValueDataEntry("اینرنت", internet));
        data.add(new ValueDataEntry("حمل و نقل", transport));

        Pie pie = AnyChart.pie();
        pie.data(data);

        pie.title("گزارش ماهیانه");
        anyChartView.setChart(pie);

    }

    public void barChart(Resources resource, AnyChartView anyChartView,List<Transaction>transactionsList){
        List<DataEntry> data = new ArrayList<>();
        for(int i=0;i<transactionsList.size();i++){
            String type=transactionsList.get(i).getType();
            switch (type){
                case "hospital":hospital++;
                case "beauty":beauty++;
                case "bill":bill++;
                case "exchange":exchange++;
                case "check":check++;
                case "clothes":clothes++;
                case "foods":foods++;
                case "gas":gas++;
                case "present":present++;
                case "income":income++;
                case "internet":internet++;
                case "transport":transport++;
            }
        }
        data.add(new ValueDataEntry("پزشکلی", hospital));
        data.add(new ValueDataEntry("زیبایی", beauty));
        data.add(new ValueDataEntry("قبض", bill));
        data.add(new ValueDataEntry("کارت به کارت", exchange));
        data.add(new ValueDataEntry("خرید اینترنتی", check));
        data.add(new ValueDataEntry("لباس", clothes));
        data.add(new ValueDataEntry("غذا", foods));
        data.add(new ValueDataEntry("بنزین", gas));
        data.add(new ValueDataEntry("هدیه", present));
        data.add(new ValueDataEntry("اینرنت", internet));
        data.add(new ValueDataEntry("حمل و نقل", transport));
        Cartesian barChart=AnyChart.bar();
        barChart.animation(true);
        barChart.data(data);
        barChart.title("گزارش ماهیانه");
        anyChartView.setChart(barChart);

    }







}
