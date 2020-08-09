package com.example.piggybank.Network;

import android.content.Context;
import android.util.Log;
import androidx.room.Room;
import com.example.piggybank.dao.AppDataBase;
import com.example.piggybank.dao.LastItemsDAO;
import com.example.piggybank.model.MonthlyReport;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveReport {
    private static boolean bool=false;
    public static boolean getReport(String month, Context context){
        BaseApiService mApiService;
        mApiService = UtilsApi.getAPIService();
        mApiService.getMonthlyReport(month).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    MonthlyReport monthlyReport=new MonthlyReport();
                    double totalIncome=0,totalExpense=0;
                  try {
                      JSONObject jsonObject = new JSONObject(response.body().toString());
                      String res = jsonObject.getString("sumCost");
                      String res2 = jsonObject.getString("sumIncome");
                      JSONArray jsonArray = new JSONArray(res);
                      JSONArray jsonArray2 = new JSONArray(res2);
                      AppDataBase database = Room.databaseBuilder(context, AppDataBase.class, "mydb")
                              .allowMainThreadQueries()
                              .build();
                      LastItemsDAO itemDAO = database.getItemDAO();
                      itemDAO.nukeMTable();
                      for (int i = 0; i < jsonArray.length(); i++) {
                          JSONObject js = jsonArray.getJSONObject(i);
                          totalExpense=js.getDouble("SUM(amount)");
                          monthlyReport.setTotalExpense(totalExpense);
                      }
                      for (int i = 0; i < jsonArray2.length(); i++) {
                          JSONObject js = jsonArray2.getJSONObject(i);
                          totalIncome=js.getDouble("SUM(amount)");
                          monthlyReport.setTotalIncome(totalIncome);
                      }
                      monthlyReport.setBalance(totalIncome-totalExpense);
                      monthlyReport.setIdAccount("u1");
                      monthlyReport.setMonth("esf");
                      itemDAO.insert(monthlyReport);
                      Log.d("dao", itemDAO.getItemById().getBalance()+"");
                      bool=true;
                  }catch (Exception e){
                      bool=false;
                      e.getStackTrace();
                  }

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                t.printStackTrace();
            }
        });
        return bool;
    }
}
