package com.example.piggybank.model;

import com.example.piggybank.Util.Types;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;


public class Chart {
    private PieData pieData;
    private PieDataSet pieDataSet;
    private int hospital, beauty, bill, exchange, check, clothes, foods, gas, present, income, internet, transport;

    public void pieChartMonthly(PieChart pieChart, List<Transaction> transactionsList) {

        pieDataSet = new PieDataSet(setData(transactionsList, pieChart), "");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setLabel("گزارش ماهیانه");
        pieDataSet.setValueTextColor(Color.WHITE);
        pieData.setDrawValues(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.getDescription().setText("گزارش ماه ");
        pieDataSet.setValueTextSize(2f);

    }

    private List<PieEntry> setData(List<Transaction> transactionsList, PieChart pieChart) {
        List<PieEntry> data = new ArrayList<>();
        for (int i = 0; i < transactionsList.size(); i++) {
            String type = Types.getRes(transactionsList.get(i).getType());
            switch (type) {
                case "hospital":
                    hospital++;
                case "beauty":
                    beauty++;
                case "bill":
                    bill++;
                case "exchange":
                    exchange++;
                case "check":
                    check++;
                case "clothes":
                    clothes++;
                case "foods":
                    foods++;
                case "gas":
                    gas++;
                case "present":
                    present++;
                case "income":
                    income++;
                case "internet":
                    internet++;
                case "transport":
                    transport++;
            }
        }
        data.add(new PieEntry(hospital, "پزشکلی"));
        data.add(new PieEntry(beauty, "زیبایی"));
        data.add(new PieEntry(bill, "قبض"));
        data.add(new PieEntry(exchange, "کارت به کارت"));
        data.add(new PieEntry(check, "خرید اینترنتی"));
        data.add(new PieEntry(clothes, "لباس"));
        data.add(new PieEntry(foods, "غذا"));
        data.add(new PieEntry(gas, "بنزین"));
        data.add(new PieEntry(present, "هدیه"));
        data.add(new PieEntry(internet, "اینرنت"));
        data.add(new PieEntry(transport, "حمل و نقل"));
        return data;
    }

}
