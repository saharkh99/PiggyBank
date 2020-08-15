package com.example.piggybank.model;

import android.graphics.drawable.Drawable;

import com.example.piggybank.R;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Types {
   private static List<String>types=Arrays.asList(new String[]{"hospital", "beauty", "bill", "exchange", "check", "clothes", "foods", "gas", "present", "income", "internet","transport"});
   private static List<String>months=Arrays.asList(new String[]{"فروردین","اردیبهشت","خرداد","تیر","مرداد","شهریور","مهر","ابان","آذر","دی","بهمن","اسفند"});
    private static int[] icons={R.drawable.ambulance,R.drawable.barbershop,R.drawable.bill,R.drawable.cardexchange,R.drawable.check,R.drawable.clothes
            , R.drawable.food,R.drawable.gasstation,R.drawable.gift,R.drawable.income,R.drawable.internet,R.drawable.transport};
    public static int getRes(String icon){
        for(int i=0;i<types.size();i++){
            if(types.get(i).equals(icon)){
                return icons[i];
            }
        }
        return 0;
    }
    public static String getMonth(int nOfMonth){
        return months.get(nOfMonth);
    }
    public static String getDate(boolean IsDate) {
        java.util.Calendar calendar= java.util.Calendar.getInstance(TimeZone.getTimeZone("Asia/Iran"),new Locale("fa") );
        int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        int month = calendar.get(java.util.Calendar.MONTH) + 1;
        int year = calendar.get(java.util.Calendar.YEAR);
        int Day_Of_Year = calendar.get(java.util.Calendar.DAY_OF_YEAR);
        if (Day_Of_Year <= 80)
            year -= 622;
        else
            year -= 621;

        switch (month) {
            case 1:
                if (day < 21)
                {
                    month = 10;
                    day += 10;
                }
                else
                {
                    month = 11;
                    day -= 20;
                }
                break;

            case 2:
                if (day < 20)
                {
                    month = 11;
                    day += 11;
                }
                else
                {
                    month = 12;
                    day -= 19;
                }
                break;

            case 3:
                if (day < 21)
                {
                    month = 12;
                    day += 9;
                }
                else
                {
                    month = 1;
                    day -= 20;
                }
                break;

            case 4:
                if (day < 21)
                {
                    month = 1;
                    day += 11;
                }
                else
                {
                    month = 2;
                    day -= 20;
                }
                break;

            case 5:
            case 6:
                if (day < 22)
                {
                    month -= 3;
                    day += 10;
                }
                else
                {
                    month -= 2;
                    day -= 21;
                }
                break;

            case 7:
            case 8:
            case 9:
                if (day < 23)
                {
                    month -= 3;
                    day += 9;
                }
                else
                {
                    month -= 2;
                    day -= 22;
                }
                break;

            case 10:
                if (day < 23)
                {
                    month = 7;
                    day += 8;
                }
                else
                {
                    month = 8;
                    day -= 22;
                }
                break;

            case 11:
            case 12:
                if (day < 22)
                {
                    month -= 3;
                    day += 9;
                }
                else
                {
                    month -= 2;
                    day -= 21;
                }
                break;
        }

        if(IsDate)
        return  " "+String.valueOf(day) + " " + getMonth(month-1) + " " + String.valueOf(year)+" ";
        else
        return getMonth(month-1);
    }

}
