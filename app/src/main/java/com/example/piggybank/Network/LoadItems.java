package com.example.piggybank.Network;

import android.content.Context;
import android.util.Log;
import com.example.piggybank.dao.AppDataBase;
import com.example.piggybank.dao.LastItemsDAO;
import com.example.piggybank.model.Transaction;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoadItems {
    private LoadItems.onSaveItem mlistener;
    static List<Transaction>transactions=new ArrayList<>() ;

    public interface onSaveItem{
        void onItemClick(boolean result, List<Transaction>transactions);
    }
    public  void saveOnItemListener(LoadItems.onSaveItem listener){
        mlistener=listener;
    }

    public static void getItem(Context context, boolean IsCost,onSaveItem mlistener) {
        BaseApiService mApiService;
        mApiService = UtilsApi.getAPIService();
        if (IsCost) {
            mApiService.getLastCosts()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<JsonObject>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(JsonObject jsonObject) {
                            try {
                                JSONObject js = new JSONObject(jsonObject.toString());
                                String res = js.getString("costs");
                                parseJson(context, res, "cost",IsCost,mlistener);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }
                    });


        } else {
            mApiService.getLastIncomes()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<JsonObject>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(JsonObject jsonObject) {
                            try {
                                JSONObject js = new JSONObject(jsonObject.toString());
                                String res = js.getString("income");
                                parseJson(context, res, "income",IsCost,mlistener);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    private static void parseJson(Context context, String res, String type,boolean IsCost,final onSaveItem listener) throws JSONException {
        JSONArray jsonArray = new JSONArray(res);
        AppDataBase db;
        db = AppDataBase.getDatabase(context);
        LastItemsDAO itemDAO = db.getItemDAO();
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
            transaction.setItemType(String.valueOf(IsCost));
            transaction.setDates("dates");
            itemDAO.insert(transaction);
            transactions.add(transaction);

        }
        listener.onItemClick(true,transactions);

    }
}
