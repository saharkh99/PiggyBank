package com.example.piggybank.model;

import android.graphics.drawable.Drawable;

import com.example.piggybank.R;

import java.util.Arrays;
import java.util.List;

public class Types {
   private static List<String>types=Arrays.asList(new String[]{"hospital", "beauty", "bill", "exchange", "check", "clothes", "foods", "gas", "present", "income", "transport"});
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
}
