package com.example.piggybank.Network;
import android.content.Context;
import android.util.Log;
import androidx.room.Room;
import com.example.piggybank.dao.AppDataBase;
import com.example.piggybank.dao.LastItemsDAO;
import com.example.piggybank.model.Transaction;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoadItems {
    public static void getItem(Context context,boolean IsCost){
        BaseApiService mApiService;
       // progress.setVisibility(View.VISIBLE);
        mApiService = UtilsApi.getAPIService();
        if (IsCost) {
            mApiService.getLastCosts().enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    //  progress.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        try {
                            if (response.code() == 400) {
                                Log.d("Error code 400", response.errorBody().string());
                            }
                            JSONObject jsonObject = new JSONObject(response.body().toString());
                            String res = jsonObject.getString("costs");
                            parseJson(context, res, "cost");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.e("debug", "onFailure: ERROR > " + t.toString());
                    t.printStackTrace();
                }
            });
        }
        else{
            mApiService.getLastIncomes().enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    //  progress.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        try {
                            if (response.code() == 400) {
                                Log.d("Error code 400", response.errorBody().string());
                            }
                            JSONObject jsonObject = new JSONObject(response.body().toString());
                            String res = jsonObject.getString("costs");
                            parseJson(context, res, "cost");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.e("debug", "onFailure: ERROR > " + t.toString());
                    t.printStackTrace();
                }
            });
        }
    }

    private static void parseJson(Context context, String res, String type) throws JSONException {
        JSONArray jsonArray = new JSONArray(res);
        AppDataBase database = Room.databaseBuilder(context, AppDataBase.class, "mydb")
                .allowMainThreadQueries()
                .build();
        LastItemsDAO itemDAO = database.getItemDAO();
        itemDAO.nukeTable();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject js = jsonArray.getJSONObject(i);
            Log.d("js", js.getString("id"));
            Transaction transaction = new Transaction();
            transaction.setAmount(js.getDouble("amount"));
            transaction.setId(js.getInt("id"));
            transaction.setColor(js.getInt("color"));
            transaction.setIdAccount(js.getString("idAccount"));
            transaction.setType(js.getString("type"));
            transaction.setItemType("cost");
            transaction.setDates("dates");
            itemDAO.insert(transaction);
        }
    }
}
