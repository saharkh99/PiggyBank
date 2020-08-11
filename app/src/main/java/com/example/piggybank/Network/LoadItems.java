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
    static BaseApiService mApiService = UtilsApi.getAPIService();

    public interface onSaveItem {
        void onItemClick(boolean result, List<Transaction> transactions);
    }

    public void saveOnItemListener(LoadItems.onSaveItem listener) {
        mlistener = listener;
    }

    public static void getCosts(Context context, onSaveItem mlistener) {
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
                            parseJson(context, res, true, mlistener);
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

    public static void getIncomes(Context context, onSaveItem mlistener) {
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
                            parseJson(context, res, false, mlistener);
                            Log.d("fuck", res);
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

    private static void parseJson(Context context, String res, boolean IsCost, final onSaveItem listener) throws JSONException {
        List<Transaction> transactions = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(res);
        AppDataBase db;
        db = AppDataBase.getDatabase(context);
        LastItemsDAO itemDAO = db.getItemDAO();
        itemDAO.nukeTable();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject js = jsonArray.getJSONObject(i);
            Transaction transaction = new Transaction();
            transaction.setAmount(js.getDouble("amount"));
            transaction.setId(js.getInt("id"));
            transaction.setColor(js.getInt("color"));
            transaction.setIdAccount(js.getString("idAccount"));
            transaction.setType(js.getString("type"));
            transaction.setItemType(String.valueOf(IsCost));
            transaction.setDates("dates");
            itemDAO.insert(transaction);
            transactions.add(transaction);

        }
        listener.onItemClick(true, transactions);

    }
}
