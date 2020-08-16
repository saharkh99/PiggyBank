package com.example.piggybank.Network;

import android.content.Context;
import android.util.Log;

import com.example.piggybank.dao.AppDataBase;
import com.example.piggybank.dao.LastItemsDAO;
import com.example.piggybank.model.MonthlyReport;
import com.example.piggybank.model.Transaction;
import com.example.piggybank.model.Types;
import com.example.piggybank.ui.Progress;
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


public class LoadReport {
    private static boolean bool = false;
    private static Disposable disposable;
    private LoadReport.onSaveItem mlistener;

    public interface onSaveItem {
        void onItemClick(boolean result, String month, String balance, String expense, String income);

    }
    public interface getTrans {

        void getReport(List<Transaction> transaction);
    }

    public void saveOnItemListener(LoadReport.onSaveItem listener) {
        mlistener = listener;
    }

    public static void getReport(String month, Context context, final onSaveItem listener
    ) {
        BaseApiService mApiService;
        mApiService = UtilsApi.getAPIService();
        mApiService.getMonthlyReport(month)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(JsonObject jsonObject) {
                        MonthlyReport monthlyReport = new MonthlyReport();
                        double totalIncome = 0, totalExpense = 0;
                        try {
                            Log.d("omad", "omad");
                            JSONObject jsObject = new JSONObject(jsonObject.toString());
                            String res = jsObject.getString("sumCost");
                            String res2 = jsObject.getString("sumIncome");
                            JSONArray jsonArray = new JSONArray(res);
                            JSONArray jsonArray2 = new JSONArray(res2);
                            if (jsonArray != null && jsonArray2 != null) {
                                AppDataBase db;
                                db = AppDataBase.getDatabase(context);
                                LastItemsDAO itemsDAO = db.getItemDAO();
                                itemsDAO.nukeMTableReport();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject js = jsonArray.getJSONObject(i);
                                    totalExpense = js.getDouble("SUM(amount)");
                                    monthlyReport.setTotalExpense(totalExpense);
                                    Log.d("omad", monthlyReport.getTotalExpense() + "");
                                }
                                for (int i = 0; i < jsonArray2.length(); i++) {
                                    JSONObject js = jsonArray2.getJSONObject(i);
                                    totalIncome = js.getDouble("SUM(amount)");
                                    monthlyReport.setTotalIncome(totalIncome);
                                    Log.d("omad", monthlyReport.getTotalExpense() + "");

                                }
                                monthlyReport.setBalance(totalIncome - totalExpense);
                                monthlyReport.setIdAccount("u1");
                                monthlyReport.setMonth(Types.getDate(false));

                                itemsDAO.insertReport(monthlyReport);
                                bool = true;
                                listener.onItemClick(bool, month, String.valueOf(totalIncome - totalExpense), String.valueOf(totalIncome)
                                        , String.valueOf(totalExpense));
                            }
                        } catch (Exception e) {
                            bool = false;

                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void getMonthlyReport(boolean IsCost, String month, Context context, final getTrans listener) {
        if (IsCost) {
            BaseApiService mApiService;
            mApiService = UtilsApi.getAPIService();
            mApiService.getCostsMonthly(month)
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
                                String res = js.getString("cost");
                                List<Transaction> transactions = new ArrayList<>();
                                JSONArray jsonArray = new JSONArray(res);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject js1 = jsonArray.getJSONObject(i);
                                    Transaction transaction = new Transaction();
                                    transaction.setAmount(js1.getDouble("amount"));
                                    transaction.setId(js1.getInt("id"));
                                    transaction.setColor(js1.getInt("color"));
                                    transaction.setIdAccount(js1.getString("idAccount"));
                                    transaction.setType(js1.getString("type"));
                                    transaction.setItemType("هزینه ");
                                    transaction.setDates("dates");
                                    transactions.add(transaction);
                                }
                                listener.getReport(transactions);
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
            BaseApiService mApiService;
            mApiService = UtilsApi.getAPIService();
            mApiService.getCostsMonthly(month)
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
                                String res = js.getString("cost");
                                List<Transaction> transactions = new ArrayList<>();
                                JSONArray jsonArray = new JSONArray(res);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject js1 = jsonArray.getJSONObject(i);
                                    Transaction transaction = new Transaction();
                                    transaction.setAmount(js1.getDouble("amount"));
                                    transaction.setId(js1.getInt("id"));
                                    transaction.setColor(js1.getInt("color"));
                                    transaction.setIdAccount(js1.getString("idAccount"));
                                    transaction.setType(js1.getString("type"));
                                    transaction.setItemType("درامد ");
                                    transaction.setDates("dates");
                                    transactions.add(transaction);
                                }
                                listener.getReport(transactions);
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


}
