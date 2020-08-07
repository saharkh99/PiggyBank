package com.example.piggybank.Network;
import android.util.Log;
import android.view.View;
import com.example.piggybank.ui.Progress;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class SaveItems {
    static boolean result=false;
    public static boolean saveCost(double amount, int color, String type, String idAccount, final Progress progress){
    BaseApiService mApiService;
    progress.setVisibility(View.VISIBLE);
    mApiService = UtilsApi.getAPIService();
    mApiService.saveCost(amount,color,type, idAccount).enqueue(new Callback<JsonObject>() {
        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            progress.setVisibility(View.GONE);
            if (response.isSuccessful()) {
                Log.d("suceess", "success");
                try {
                    if (response.code() == 400) {
                        Log.d("Error code 400", response.errorBody().string());
                    }
                    JSONObject object = new JSONObject(response.body().toString());
                    String res = object.getString("add");
                    if(res.equals("true")){
                        result=true;
                    }
                }
                catch (Exception e){
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
   return result;
}
    public static boolean saveIncome(double amount, int color, String type, String idAccount, final Progress progress){
        BaseApiService mApiService;
        progress.setVisibility(View.VISIBLE);
        mApiService = UtilsApi.getAPIService();
        mApiService.saveIncome(amount,color,type, idAccount).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progress.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    Log.d("suceess", "success");
                    try {
                        if (response.code() == 400) {
                            Log.d("Error code 400", response.errorBody().string());
                        }
                        JSONObject object = new JSONObject(response.body().toString());
                        String res = object.getString("add");
                        if(res.equals("true")){
                            result=true;
                        }
                    }
                    catch (Exception e){
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
        return result;
    }
}
